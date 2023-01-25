
        
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
public class BaseServiceLon extends AbstractLon<Base> implements IServiceLon<Base>{  
        
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
        
    
        DCModel dcm = new DCModel("base", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,true);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
    

    
//ON RELATION base    
    final SOtm baseTimePeriods = new SOtm("baseTimePeriods","baseTimePeriod","base");                    
    dcm.addSOtm(baseTimePeriods); 
//ON RELATION base    
    final SOtm workSpaceGroups = new SOtm("workSpaceGroups","workSpaceGroup","base");                    
    dcm.addSOtm(workSpaceGroups); 
//ON RELATION base    
    final SOtm baseUserLons = new SOtm("baseUserLons","baseUserLon","base");                    
    dcm.addSOtm(baseUserLons); 
        //
            final SOtm departamentBaseTimePeriods = new SOtm("departamentBaseTimePeriods","departamentBaseTimePeriod","baseTimePeriod"); 
            dcm.addSOtm2(departamentBaseTimePeriods);
            

            final SOtm programBaseTimePeriods = new SOtm("programBaseTimePeriods","programBaseTimePeriod","baseTimePeriod"); 
            dcm.addSOtm2(programBaseTimePeriods);
            

            final SOtm workSpaces = new SOtm("workSpaces","workSpace","workSpaceGroup"); 
            dcm.addSOtm2(workSpaces);
            
        //;
        
        //
            final SOtm purchaseContracts = new SOtm("purchaseContracts","purchaseContract","departamentBaseTimePeriod"); 
            dcm.addSOtm3(purchaseContracts);            

            final SOtm departamentJobInstances = new SOtm("departamentJobInstances","departamentJobInstance","departamentBaseTimePeriod"); 
            dcm.addSOtm3(departamentJobInstances);            

            final SOtm saleContracts = new SOtm("saleContracts","saleContract","programBaseTimePeriod"); 
            dcm.addSOtm3(saleContracts);            

            final SOtm appointments = new SOtm("appointments","appointment","workSpace"); 
            dcm.addSOtm3(appointments);            

            final SOtm stockRack = new SOtm("stockRack","stockRack","workSpace"); 
            dcm.addSOtm3(stockRack);            
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
    sortMapFields.put("id","base_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("base.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("base.base_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "base_pkey");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("base.pname","pname");  
//Used to map error on index to source property
    insertMapFields.put("base.base_uidx_pname","pname");  
    sortMapFields.put("pname", "base_pname");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Base dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    

private static final String SQLINSERT = "INSERT INTO base(pkey,pname,id) VALUES ($1,$2,(select nextval('seq_base'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Base base) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(base, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("base", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "base_pkey");
    slcb.doEqInPSimple( "pkey", "base_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "base_pname");
     slcb.doEqInPSimple( "pname", "base_pname");            
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  base.id as base_id,base.pkey as base_pkey,base.pname as base_pname   FROM   base ";
    private static String sqlCount = "SELECT  count(base.id)   FROM   base ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        