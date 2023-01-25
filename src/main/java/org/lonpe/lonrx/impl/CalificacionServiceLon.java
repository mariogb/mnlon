
        
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
public class CalificacionServiceLon extends AbstractLon<Calificacion> implements IServiceLon<Calificacion>{  
        
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
        
    
        DCModel dcm = new DCModel("calificacion");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property calificacion   
                
        final SProperty calificacion = new SProperty("calificacion", BIGDECIMAL,true,false);   
            
                            
        calificacion.setMin(0); 
        calificacion.setMax(10);  
    dcm.addSProperty(calificacion);             //Create property fecha   
                
        final SProperty fecha = new SProperty("fecha", LOCALDATE,true,false);   
            
             
    dcm.addSProperty(fecha);             
    

    
//1
    final SMto alumno = new SMto("alumno","alumno"); 
    alumno.setPc("pname"); 
    dcm.addSMto(alumno); 

//1
    final SMto materia = new SMto("materia","materia"); 
    materia.setPc("pname"); 
    dcm.addSMto(materia); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","calificacion_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("calificacion.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("calificacion.calificacion_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "calificacion_pkey");
                    
//calificacion -------------------------------------------
    names.add("calificacion");
    insertMapFields.put("calificacion.calificacion","calificacion");  
    sortMapFields.put("calificacion", "calificacion_calificacion");
                
//fecha -------------------------------------------
    names.add("fecha");
    insertMapFields.put("calificacion.fecha","fecha");  
    sortMapFields.put("fecha", "calificacion_fecha");
               // alumno --------------------
        names.add("alumno_id");
        
        insertMapFields.put("calificacion.alumno_id","alumno_id");
        
        
        names.add("alumno_pkey");        
            sortMapFields.put( "alumno_pkey", "alumno_pkey");

        
   names.add("alumno_pname");
            sortMapFields.put( "alumno_pname", "alumno_pname");
            // materia --------------------
        names.add("materia_id");
        
        insertMapFields.put("calificacion.materia_id","materia_id");
        
        
        names.add("materia_pkey");        
            sortMapFields.put( "materia_pkey", "materia_pkey");

        
   names.add("materia_pname");
            sortMapFields.put( "materia_pname", "materia_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Calificacion dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBigDecimal(dc0.getCalificacion());
        t.addLocalDate( dc0.getFecha()  );
   
                if(dc0.getAlumno()!=null){
            t.addLong(dc0.getAlumno().getId());
                }
   
                if(dc0.getMateria()!=null){
            t.addLong(dc0.getMateria().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO calificacion(pkey,calificacion,fecha,alumno_id,materia_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_calificacion'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Calificacion calificacion) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(calificacion, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("calificacion", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "calificacion_pkey");
    slcb.doEqInPSimple( "pkey", "calificacion_pkey");   
    slcb.doGEPSimpleLocalDate( "fecha", "calificacion_fecha");
    slcb.doLPSimpleLocalDate( "fecha", "calificacion_fecha");                
        
        slcb.doIlPSimple2( "alumno_pkey", "alumno_pkey");
        slcb.doEQPSimple2( "alumno_pkey", "alumno_pkey");
        slcb.doInLongCondition("alumno_id", "alumno_id");        
    
// 1 Alumno pname
        slcb.doIlPSimple2( "alumno_pname", "alumno_pname");
    

        slcb.doIlPSimple2( "materia_pkey", "materia_pkey");
        slcb.doEQPSimple2( "materia_pkey", "materia_pkey");
        slcb.doInLongCondition("materia_id", "materia_id");        
    
// 1 Materia pname
        slcb.doIlPSimple2( "materia_pname", "materia_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  calificacion.id as calificacion_id,calificacion.pkey as calificacion_pkey,calificacion.calificacion as calificacion_calificacion,calificacion.fecha as calificacion_fecha,alumno.id as alumno_id,alumno.pkey as alumno_pkey,alumno.pname as alumno_pname,materia.id as materia_id,materia.pkey as materia_pkey,materia.pname as materia_pname   FROM   calificacion,  alumno as alumno,  materia as materia   WHERE  calificacion.alumno_id = alumno.id AND calificacion.materia_id = materia.id";
    private static String sqlCount = "SELECT  count(calificacion.id)   FROM   calificacion,  alumno as alumno,  materia as materia   WHERE  calificacion.alumno_id = alumno.id AND calificacion.materia_id = materia.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        