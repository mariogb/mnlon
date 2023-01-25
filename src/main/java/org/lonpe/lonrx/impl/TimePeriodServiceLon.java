
        
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
public class TimePeriodServiceLon extends AbstractLon<TimePeriod> implements IServiceLon<TimePeriod>{  
        
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
        
    
        DCModel dcm = new DCModel("timePeriod", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property beginDate   
                
        final SProperty beginDate = new SProperty("beginDate", LOCALDATE,true,false);   
            
             
    dcm.addSProperty(beginDate);             //Create property endDate   
                
        final SProperty endDate = new SProperty("endDate", LOCALDATE,true,false);   
            
             
    dcm.addSProperty(endDate);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,true);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property typeLon   
                
        final SProperty typeLon = new SProperty("typeLon", STRING,true,false);   
            
            
        final List<String> typeLonInList = new ArrayList<>();
                
        typeLonInList.add("CONFIG"); 
        typeLonInList.add("OPERATION"); 
        typeLonInList.add("FINISH"); 
        typeLon.setInList(typeLonInList);                 
    dcm.addSProperty(typeLon);             
    

    
    

    
//ON RELATION timePeriod    
    final SOtm baseTimePeriods = new SOtm("baseTimePeriods","baseTimePeriod","timePeriod");                    
    dcm.addSOtm(baseTimePeriods); 
        //
            final SOtm departamentBaseTimePeriods = new SOtm("departamentBaseTimePeriods","departamentBaseTimePeriod","baseTimePeriod"); 
            dcm.addSOtm2(departamentBaseTimePeriods);
            

            final SOtm programBaseTimePeriods = new SOtm("programBaseTimePeriods","programBaseTimePeriod","baseTimePeriod"); 
            dcm.addSOtm2(programBaseTimePeriods);
            
        //;
        
        //
            final SOtm purchaseContracts = new SOtm("purchaseContracts","purchaseContract","departamentBaseTimePeriod"); 
            dcm.addSOtm3(purchaseContracts);            

            final SOtm departamentJobInstances = new SOtm("departamentJobInstances","departamentJobInstance","departamentBaseTimePeriod"); 
            dcm.addSOtm3(departamentJobInstances);            

            final SOtm saleContracts = new SOtm("saleContracts","saleContract","programBaseTimePeriod"); 
            dcm.addSOtm3(saleContracts);            
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
    sortMapFields.put("id","time_period_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("time_period.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("time_period.time_period_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "time_period_pkey");
                    
//beginDate -------------------------------------------
    names.add("beginDate");
    insertMapFields.put("time_period.begin_date","beginDate");  
    sortMapFields.put("beginDate", "time_period_begin_date");
                
//endDate -------------------------------------------
    names.add("endDate");
    insertMapFields.put("time_period.end_date","endDate");  
    sortMapFields.put("endDate", "time_period_end_date");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("time_period.pname","pname");  
//Used to map error on index to source property
    insertMapFields.put("time_period.time_period_uidx_pname","pname");  
    sortMapFields.put("pname", "time_period_pname");
                    
//typeLon -------------------------------------------
    names.add("typeLon");
    insertMapFields.put("time_period.type_lon","typeLon");  
    sortMapFields.put("typeLon", "time_period_type_lon");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final TimePeriod dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDate( dc0.getBeginDate()  );
        t.addLocalDate( dc0.getEndDate()  );
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
    }

    

private static final String SQLINSERT = "INSERT INTO time_period(pkey,begin_date,end_date,pname,type_lon,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_time_period'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(TimePeriod timePeriod) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(timePeriod, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("time_period", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "time_period_pkey");
    slcb.doEqInPSimple( "pkey", "time_period_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "time_period_pname");
     slcb.doEqInPSimple( "pname", "time_period_pname");               
    slcb.doGEPSimpleLocalDate( "beginDate", "time_period_begin_date");
    slcb.doLPSimpleLocalDate( "beginDate", "time_period_begin_date");                   
    slcb.doGEPSimpleLocalDate( "endDate", "time_period_end_date");
    slcb.doLPSimpleLocalDate( "endDate", "time_period_end_date");                
    slcb.doEqInPSimple( "typeLon", "time_period_type_lon");                     
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  time_period.id as time_period_id,time_period.pkey as time_period_pkey,time_period.begin_date as time_period_begin_date,time_period.end_date as time_period_end_date,time_period.pname as time_period_pname,time_period.type_lon as time_period_type_lon   FROM   time_period ";
    private static String sqlCount = "SELECT  count(time_period.id)   FROM   time_period ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        