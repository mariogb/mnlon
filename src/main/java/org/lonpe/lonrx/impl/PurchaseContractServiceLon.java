
        
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
public class PurchaseContractServiceLon extends AbstractLon<PurchaseContract> implements IServiceLon<PurchaseContract>{  
        
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
        
    
        DCModel dcm = new DCModel("purchaseContract", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
//1
    final SMto departamentBaseTimePeriod = new SMto("departamentBaseTimePeriod","departamentBaseTimePeriod"); 
    dcm.addSMto(departamentBaseTimePeriod); 

//1
    final SMto thirdPerson = new SMto("thirdPerson","thirdPerson"); 
    thirdPerson.setPc("pname"); 
    dcm.addSMto(thirdPerson); 
    

    
//ON RELATION purchaseContract    
    final SOtm appointments = new SOtm("appointments","appointment","purchaseContract");                    
    dcm.addSOtm(appointments); 
//ON RELATION purchaseContract    
    final SOtm purchases = new SOtm("purchases","purchase","purchaseContract");                    
    dcm.addSOtm(purchases); 
        //
            final SOtm invoiceLines = new SOtm("invoiceLines","invoiceLineIn","purchase"); 
            dcm.addSOtm2(invoiceLines);
            

            final SOtm payments = new SOtm("payments","paymentOut","purchase"); 
            dcm.addSOtm2(payments);
            
        //;
        
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","purchase_contract_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("purchase_contract.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("purchase_contract.purchase_contract_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "purchase_contract_pkey");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("purchase_contract.pname","pname");  
    sortMapFields.put("pname", "purchase_contract_pname");
                   // departamentBaseTimePeriod --------------------
        names.add("departamentBaseTimePeriod_id");
        
        insertMapFields.put("purchase_contract.departament_base_time_period_id","departamentBaseTimePeriod_id");
        
        
        names.add("departamentBaseTimePeriod_pkey");        
            sortMapFields.put( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");

        //   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   base 
        names.add("base_id");
            
        names.add("base_pkey");
   names.add("base_pname");//   timePeriod 
        names.add("timePeriod_id");
            
        names.add("timePeriod_pkey");
   names.add("timePeriod_pname");//   departament 
        names.add("departament_id");
            
        names.add("departament_pkey");
   names.add("departament_pname");// thirdPerson --------------------
        names.add("thirdPerson_id");
        
        insertMapFields.put("purchase_contract.third_person_id","thirdPerson_id");
        
        
        names.add("thirdPerson_pkey");        
            sortMapFields.put( "thirdPerson_pkey", "third_person_pkey");

        
   names.add("thirdPerson_pname");
            sortMapFields.put( "thirdPerson_pname", "third_person_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PurchaseContract dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
   
                if(dc0.getDepartamentBaseTimePeriod()!=null){
            t.addLong(dc0.getDepartamentBaseTimePeriod().getId());
                }
   
                if(dc0.getThirdPerson()!=null){
            t.addLong(dc0.getThirdPerson().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO purchase_contract(pkey,pname,departament_base_time_period_id,third_person_id,id) VALUES ($1,$2,$3,$4,(select nextval('seq_purchase_contract'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(PurchaseContract purchaseContract) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(purchaseContract, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("purchase_contract", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "purchase_contract_pkey");
    slcb.doEqInPSimple( "pkey", "purchase_contract_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "purchase_contract_pname");
     slcb.doEqInPSimple( "pname", "purchase_contract_pname");            
        
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");        
    
// 1 DepartamentBaseTimePeriod undefined

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");        
    
// 1 ThirdPerson pname
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");
    

        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");                  

//2  BaseTimePeriod undefined

//3  Base pname                                
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");                            

//3  TimePeriod pname                                
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");                            

        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");                  

//2  Departament pname

        slcb.doIlPSimple2( "departament_pname", "departament_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  purchase_contract.id as purchase_contract_id,purchase_contract.pkey as purchase_contract_pkey,purchase_contract.pname as purchase_contract_pname,departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname   FROM   purchase_contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  departament as departament,  third_person as third_person   WHERE  purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND departament_base_time_period.departament_id = departament.id AND purchase_contract.third_person_id = third_person.id";
    private static String sqlCount = "SELECT  count(purchase_contract.id)   FROM   purchase_contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  departament as departament,  third_person as third_person   WHERE  purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND departament_base_time_period.departament_id = departament.id AND purchase_contract.third_person_id = third_person.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        