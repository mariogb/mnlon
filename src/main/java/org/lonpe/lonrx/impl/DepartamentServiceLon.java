
        
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
public class DepartamentServiceLon extends AbstractLon<Departament> implements IServiceLon<Departament>{  
        
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
        
    
        DCModel dcm = new DCModel("departament", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property description   
                
        final SProperty description = new SProperty("description", STRING,false,false);   
            
             
    dcm.addSProperty(description);             //Create property fastKey   
                
        final SProperty fastKey = new SProperty("fastKey", STRING,false,false);   
            
            
// hasIndex 
    fastKey.setWithIndex(true);   
    dcm.addSProperty(fastKey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,true);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
    

    
//ON RELATION departament    
    final SOtm departamentBaseTimePeriods = new SOtm("departamentBaseTimePeriods","departamentBaseTimePeriod","departament");                    
    dcm.addSOtm(departamentBaseTimePeriods); 
//ON RELATION departament    
    final SOtm departamentUserLons = new SOtm("departamentUserLons","departamentUserLon","departament");                    
    dcm.addSOtm(departamentUserLons); 
//ON RELATION departament    
    final SOtm departamenJobs = new SOtm("departamenJobs","departamentJob","departament");                    
    dcm.addSOtm(departamenJobs); 
        //
            final SOtm purchaseContracts = new SOtm("purchaseContracts","purchaseContract","departamentBaseTimePeriod"); 
            dcm.addSOtm2(purchaseContracts);
            

            final SOtm departamentJobInstances = new SOtm("departamentJobInstances","departamentJobInstance","departamentBaseTimePeriod"); 
            dcm.addSOtm2(departamentJobInstances);
            

            final SOtm programJobs = new SOtm("programJobs","programJob","departamentJob"); 
            dcm.addSOtm2(programJobs);
            
        //;
        
        //
            final SOtm appointments = new SOtm("appointments","appointment","purchaseContract"); 
            dcm.addSOtm3(appointments);            

            final SOtm purchases = new SOtm("purchases","purchase","purchaseContract"); 
            dcm.addSOtm3(purchases);            

            final SOtm appointmens = new SOtm("appointmens","appointment","departamentJobInstance"); 
            dcm.addSOtm3(appointmens);            
        //
        
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","departament_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("departament.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("departament.departament_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "departament_pkey");
                    
//description -------------------------------------------
    names.add("description");
    insertMapFields.put("departament.description","description");   
//fastKey -------------------------------------------
    names.add("fastKey");
    insertMapFields.put("departament.fast_key","fastKey");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("departament.pname","pname");  
//Used to map error on index to source property
    insertMapFields.put("departament.departament_uidx_pname","pname");  
    sortMapFields.put("pname", "departament_pname");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Departament dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
    }

    

private static final String SQLINSERT = "INSERT INTO departament(pkey,description,fast_key,pname,id) VALUES ($1,$2,$3,$4,(select nextval('seq_departament'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Departament departament) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(departament, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("departament", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "departament_pkey");
    slcb.doEqInPSimple( "pkey", "departament_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "departament_pname");
     slcb.doEqInPSimple( "pname", "departament_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "departament_fast_key");
    slcb.doEqInPSimple( "fastKey", "departament_fast_key");                    
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  departament.id as departament_id,departament.pkey as departament_pkey,departament.description as departament_description,departament.fast_key as departament_fast_key,departament.pname as departament_pname   FROM   departament ";
    private static String sqlCount = "SELECT  count(departament.id)   FROM   departament ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        