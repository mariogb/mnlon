
        
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
public class BaseTimePeriodServiceLon extends AbstractLon<BaseTimePeriod> implements IServiceLon<BaseTimePeriod>{  
        
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
        
    
        DCModel dcm = new DCModel("baseTimePeriod");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    //1
    final SMto base = new SMto("base","base"); 
    base.setPc("pname"); 
    dcm.addSMto(base); 
//1
    final SMto timePeriod = new SMto("timePeriod","timePeriod"); 
    timePeriod.setPc("pname"); 
    dcm.addSMto(timePeriod); 
    

    
    final SOtm departamentBaseTimePeriods = new SOtm("departamentBaseTimePeriods","departamentBaseTimePeriod","undefined");                     
    dcm.addSOtm(departamentBaseTimePeriods); 
    final SOtm programBaseTimePeriods = new SOtm("programBaseTimePeriods","programBaseTimePeriod","undefined");                     
    dcm.addSOtm(programBaseTimePeriods); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","base_time_period_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("base_time_period.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("base_time_period.base_time_period_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "base_time_period_pkey");
                   // base --------------------
        names.add("base_id");
        
        insertMapFields.put("base_time_period.base_id","base_id");
        
        
        names.add("base_pkey");        
            sortMapFields.put( "base_pkey", "base_pkey");

        
   names.add("base_pname");
            sortMapFields.put( "base_pname", "base_pname");
            // timePeriod --------------------
        names.add("timePeriod_id");
        
        insertMapFields.put("base_time_period.time_period_id","timePeriod_id");
        
        
        names.add("timePeriod_pkey");        
            sortMapFields.put( "timePeriod_pkey", "time_period_pkey");

        
   names.add("timePeriod_pname");
            sortMapFields.put( "timePeriod_pname", "time_period_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final BaseTimePeriod dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getBase()!=null){
            t.addLong(dc0.getBase().getId());
                }
   
                if(dc0.getTimePeriod()!=null){
            t.addLong(dc0.getTimePeriod().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO base_time_period(pkey,base_id,time_period_id,id) VALUES ($1,$2,$3,(select nextval('seq_base_time_period'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(BaseTimePeriod baseTimePeriod) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(baseTimePeriod, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "base_time_period", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "base_time_period_pkey");
    slcb.doEqInPSimple( "pkey", "base_time_period_pkey");
        
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");        
    // //Base 1
        slcb.doIlPSimple2( "base_pname", "base_pname");
    

        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");        
    // //TimePeriod 3
        slcb.doIlPSimple2( "timePeriod_pname", "time_period_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id,time_period.pkey as time_period_pkey,time_period.pname as time_period_pname   FROM   base_time_period,  base as base,  time_period as time_period   WHERE  base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id";
    private static String sqlCount = "SELECT  count(base_time_period.id)   FROM   base_time_period,  base as base,  time_period as time_period   WHERE  base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        