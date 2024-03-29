
        
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
public class DepartamentBaseTimePeriodServiceLon extends AbstractLon<DepartamentBaseTimePeriod> implements IServiceLon<DepartamentBaseTimePeriod>{  
        
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
        
    
        DCModel dcm = new DCModel("departamentBaseTimePeriod");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    
//1
    final SMto baseTimePeriod = new SMto("baseTimePeriod","baseTimePeriod"); 
    dcm.addSMto(baseTimePeriod); 

//1
    final SMto departament = new SMto("departament","departament"); 
    departament.setPc("pname"); 
    dcm.addSMto(departament); 
    

    
//ON RELATION departamentBaseTimePeriod    
    final SOtm purchaseContracts = new SOtm("purchaseContracts","purchaseContract","departamentBaseTimePeriod");                    
    dcm.addSOtm(purchaseContracts); 
//ON RELATION departamentBaseTimePeriod    
    final SOtm departamentJobInstances = new SOtm("departamentJobInstances","departamentJobInstance","departamentBaseTimePeriod");                    
    dcm.addSOtm(departamentJobInstances); 
        //
            final SOtm appointments = new SOtm("appointments","appointment","purchaseContract"); 
            dcm.addSOtm2(appointments);
            

            final SOtm purchases = new SOtm("purchases","purchase","purchaseContract"); 
            dcm.addSOtm2(purchases);
            

            final SOtm appointmens = new SOtm("appointmens","appointment","departamentJobInstance"); 
            dcm.addSOtm2(appointmens);
            
        //;
        
        //
            final SOtm invoiceLines = new SOtm("invoiceLines","invoiceLineIn","purchase"); 
            dcm.addSOtm3(invoiceLines);            

            final SOtm payments = new SOtm("payments","paymentOut","purchase"); 
            dcm.addSOtm3(payments);            
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
    sortMapFields.put("id","departament_base_time_period_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("departament_base_time_period.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("departament_base_time_period.departament_base_time_period_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "departament_base_time_period_pkey");
                   // baseTimePeriod --------------------
        names.add("baseTimePeriod_id");
        
        insertMapFields.put("departament_base_time_period.base_time_period_id","baseTimePeriod_id");
        
        
        names.add("baseTimePeriod_pkey");        
            sortMapFields.put( "baseTimePeriod_pkey", "base_time_period_pkey");

        //   base 
        names.add("base_id");
            
        names.add("base_pkey");
   names.add("base_pname");//   timePeriod 
        names.add("timePeriod_id");
            
        names.add("timePeriod_pkey");
   names.add("timePeriod_pname");// departament --------------------
        names.add("departament_id");
        
        insertMapFields.put("departament_base_time_period.departament_id","departament_id");
        
        
        names.add("departament_pkey");        
            sortMapFields.put( "departament_pkey", "departament_pkey");

        
   names.add("departament_pname");
            sortMapFields.put( "departament_pname", "departament_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final DepartamentBaseTimePeriod dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getBaseTimePeriod()!=null){
            t.addLong(dc0.getBaseTimePeriod().getId());
                }
   
                if(dc0.getDepartament()!=null){
            t.addLong(dc0.getDepartament().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO departament_base_time_period(pkey,base_time_period_id,departament_id,id) VALUES ($1,$2,$3,(select nextval('seq_departament_base_time_period'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(DepartamentBaseTimePeriod departamentBaseTimePeriod) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(departamentBaseTimePeriod, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("departament_base_time_period", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "departament_base_time_period_pkey");
    slcb.doEqInPSimple( "pkey", "departament_base_time_period_pkey");
        
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");        
    
// 1 BaseTimePeriod undefined

        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");        
    
// 1 Departament pname
        slcb.doIlPSimple2( "departament_pname", "departament_pname");
    

        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");                  

//2  Base pname

        slcb.doIlPSimple2( "base_pname", "base_pname");                    

        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");                  

//2  TimePeriod pname

        slcb.doIlPSimple2( "timePeriod_pname", "time_period_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname   FROM   departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  departament as departament   WHERE  departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND departament_base_time_period.departament_id = departament.id";
    private static String sqlCount = "SELECT  count(departament_base_time_period.id)   FROM   departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  departament as departament   WHERE  departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND departament_base_time_period.departament_id = departament.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        