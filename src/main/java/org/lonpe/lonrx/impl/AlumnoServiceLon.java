
        
package org.lonpe.lonrx.impl;            

            
import io.reactivex.Single;
import io.vertx.reactivex.sqlclient.Tuple;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Map;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashSet;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import io.reactivex.Observable;
import io.micronaut.http.HttpParameters;

import org.lonpe.model.*;
import org.lonpe.model.*;
import org.lonpe.sql.CrudLon;
import org.lonpe.dcmodel.DCModel;
import org.lonpe.dcmodel.SMto;
import org.lonpe.dcmodel.SProperty;
import org.lonpe.dcmodel.SOtm;
import org.lonpe.sql.ConditionInfo;
import org.lonpe.sql.SqlLonConditionsBuilder;
import org.lonpe.forquery.ObjForQuery;
import org.lonpe.sql.SqlStructureBuilder;
import org.lonpe.lonrx.IServiceLon;
import org.lonpe.lonrx.AbstractLon;


@Singleton
public class AlumnoServiceLon extends AbstractLon<Alumno> implements IServiceLon<Alumno>{  
        
    @Inject 
    CrudLon crudLon;

    private DCModel dCModel;
    /**
     * @return the dCModel
     */
     @Override
    public DCModel getdCModel() {        
        return dCModel;
    }
    
    private DCModel elModelo(){
        
    
        DCModel dcm = new DCModel("alumno", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property activo   
                
        final SProperty activo = new SProperty("activo", BOOLEAN,true,false);   
            
             
    dcm.addSProperty(activo);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property primer_apellido   
                
        final SProperty primer_apellido = new SProperty("primer_apellido", STRING,true,false);   
            
             
    dcm.addSProperty(primer_apellido);             //Create property segundo_apellido   
                
        final SProperty segundo_apellido = new SProperty("segundo_apellido", STRING,true,false);   
            
             
    dcm.addSProperty(segundo_apellido);             
    

    
    

    
//ON RELATION alumno    
    final SOtm calificaciones = new SOtm("calificaciones","calificacion","alumno");                    
    dcm.addSOtm(calificaciones); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","alumno_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("alumno.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("alumno.alumno_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "alumno_pkey");
                    
//activo -------------------------------------------
    names.add("activo");
    insertMapFields.put("alumno.activo","activo");  
    sortMapFields.put("activo", "alumno_activo");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("alumno.pname","pname");  
    sortMapFields.put("pname", "alumno_pname");
                    
//primer_apellido -------------------------------------------
    names.add("primer_apellido");
    insertMapFields.put("alumno.primer_apellido","primer_apellido");  
    sortMapFields.put("primer_apellido", "alumno_primer_apellido");
                    
//segundo_apellido -------------------------------------------
    names.add("segundo_apellido");
    insertMapFields.put("alumno.segundo_apellido","segundo_apellido");  
    sortMapFields.put("segundo_apellido", "alumno_segundo_apellido");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Alumno dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBoolean(dc0.getActivo());
        t.addString(dc0.getPname());
        t.addString(dc0.getPrimer_apellido());
        t.addString(dc0.getSegundo_apellido());
    }

    

private static final String SQLINSERT = "INSERT INTO alumno(pkey,activo,pname,primer_apellido,segundo_apellido,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_alumno'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Alumno alumno) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(alumno, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("alumno", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "alumno_pkey");
    slcb.doEqInPSimple( "pkey", "alumno_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "alumno_pname");
     slcb.doEqInPSimple( "pname", "alumno_pname");            
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  alumno.id as alumno_id,alumno.pkey as alumno_pkey,alumno.activo as alumno_activo,alumno.pname as alumno_pname,alumno.primer_apellido as alumno_primer_apellido,alumno.segundo_apellido as alumno_segundo_apellido   FROM   alumno ";
    private static String sqlCount = "SELECT  count(alumno.id)   FROM   alumno ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        