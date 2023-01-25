
        
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
public class PurchaseServiceLon extends AbstractLon<Purchase> implements IServiceLon<Purchase>{  
        
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
        
    
        DCModel dcm = new DCModel("purchase", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property createdDate   
                
        final SProperty createdDate = new SProperty("createdDate", LOCALDATETIME,true,false);   
            
             
//Set by system
   createdDate.setSetBySys("now");   
    dcm.addSProperty(createdDate);             //Create property documentDate   
                
        final SProperty documentDate = new SProperty("documentDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(documentDate);             //Create property folio   
                
        final SProperty folio = new SProperty("folio", STRING,true,false);   
            
             
    dcm.addSProperty(folio);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property status   
                
        final SProperty status = new SProperty("status", STRING,true,false);   
            
            
        final List<String> statusInList = new ArrayList<>();
                
        statusInList.add("PENDENT"); 
        statusInList.add("SUPPLIED"); 
        statusInList.add("CANCEL"); 
        status.setInList(statusInList);                 
    dcm.addSProperty(status);             //Create property supplyDate   
                
        final SProperty supplyDate = new SProperty("supplyDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(supplyDate);             
    

    
//1
    final SMto purchaseContract = new SMto("purchaseContract","purchaseContract"); 
    purchaseContract.setPc("pname"); 
    dcm.addSMto(purchaseContract); 

//1
    final SMto userAutor = new SMto("userAutor","userLon"); 
    userAutor.setPc("pname"); 
    userAutor.setSetBySys("putCurrentUser");
    dcm.addSMto(userAutor); 

//1
    final SMto purchaseType = new SMto("purchaseType","purchaseType"); 
    purchaseType.setPc("pname"); 
    dcm.addSMto(purchaseType); 
    

    
//ON RELATION purchase    
    final SOtm invoiceLines = new SOtm("invoiceLines","invoiceLineIn","purchase");                    
    dcm.addSOtm(invoiceLines); 
//ON RELATION purchase    
    final SOtm payments = new SOtm("payments","paymentOut","purchase");                    
    dcm.addSOtm(payments); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","purchase_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("purchase.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("purchase.purchase_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "purchase_pkey");
                    
//createdDate -------------------------------------------
    names.add("createdDate");
    insertMapFields.put("purchase.created_date","createdDate");  
    sortMapFields.put("createdDate", "purchase_created_date");
                
//documentDate -------------------------------------------
    names.add("documentDate");
    insertMapFields.put("purchase.document_date","documentDate");  
    sortMapFields.put("documentDate", "purchase_document_date");
                
//folio -------------------------------------------
    names.add("folio");
    insertMapFields.put("purchase.folio","folio");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("purchase.pname","pname");  
    sortMapFields.put("pname", "purchase_pname");
                    
//status -------------------------------------------
    names.add("status");
    insertMapFields.put("purchase.status","status");   
//supplyDate -------------------------------------------
    names.add("supplyDate");
    insertMapFields.put("purchase.supply_date","supplyDate");  
    sortMapFields.put("supplyDate", "purchase_supply_date");
               // purchaseContract --------------------
        names.add("purchaseContract_id");
        
        insertMapFields.put("purchase.purchase_contract_id","purchaseContract_id");
        
        
        names.add("purchaseContract_pkey");        
            sortMapFields.put( "purchaseContract_pkey", "purchase_contract_pkey");

        
   names.add("purchaseContract_pname");
            sortMapFields.put( "purchaseContract_pname", "purchase_contract_pname");
            //   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");
            
        names.add("departamentBaseTimePeriod_pkey");//   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   departament 
        names.add("departament_id");
            
        names.add("departament_pkey");
   names.add("departament_pname");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");// userAutor --------------------
        names.add("userAutor_id");
        
        insertMapFields.put("purchase.user_autor_id","userAutor_id");
        
        
        names.add("userAutor_pkey");        
            sortMapFields.put( "userAutor_pkey", "user_autor_pkey");

        
   names.add("userAutor_pname");
            sortMapFields.put( "userAutor_pname", "user_autor_pname");
            // purchaseType --------------------
        names.add("purchaseType_id");
        
        insertMapFields.put("purchase.purchase_type_id","purchaseType_id");
        
        
        names.add("purchaseType_pkey");        
            sortMapFields.put( "purchaseType_pkey", "purchase_type_pkey");

        
   names.add("purchaseType_pname");
            sortMapFields.put( "purchaseType_pname", "purchase_type_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Purchase dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addLocalDateTime(dc0.getDocumentDate());
        t.addString(dc0.getFolio());
        t.addString(dc0.getPname());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
   
                if(dc0.getPurchaseContract()!=null){
            t.addLong(dc0.getPurchaseContract().getId());
                }
   
                if(dc0.getUserAutor()!=null){
            t.addLong(dc0.getUserAutor().getId());
                }
   
                if(dc0.getPurchaseType()!=null){
            t.addLong(dc0.getPurchaseType().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO purchase(pkey,created_date,document_date,folio,pname,status,supply_date,purchase_contract_id,user_autor_id,purchase_type_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,(select nextval('seq_purchase'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Purchase purchase) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(purchase, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("purchase", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "purchase_pkey");
    slcb.doEqInPSimple( "pkey", "purchase_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "purchase_pname");
     slcb.doEqInPSimple( "pname", "purchase_pname");            
    slcb.doEqInPSimple( "status", "purchase_status");                     
        
        slcb.doIlPSimple2( "purchaseContract_pkey", "purchase_contract_pkey");
        slcb.doEQPSimple2( "purchaseContract_pkey", "purchase_contract_pkey");
        slcb.doInLongCondition("purchaseContract_id", "purchase_contract_id");        
    
// 1 PurchaseContract pname
        slcb.doIlPSimple2( "purchaseContract_pname", "purchase_contract_pname");
    

        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");        
    
// 1 UserLon pname
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname");
    

        slcb.doIlPSimple2( "purchaseType_pkey", "purchase_type_pkey");
        slcb.doEQPSimple2( "purchaseType_pkey", "purchase_type_pkey");
        slcb.doInLongCondition("purchaseType_id", "purchase_type_id");        
    
// 1 PurchaseType pname
        slcb.doIlPSimple2( "purchaseType_pname", "purchase_type_pname");
    

        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");                  

//2  DepartamentBaseTimePeriod undefined

//3  BaseTimePeriod undefined                                
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");                            

//3  Departament pname                                
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");                            

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                  

//2  ThirdPerson pname

        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  purchase.id as purchase_id,purchase.pkey as purchase_pkey,purchase.created_date as purchase_created_date,purchase.document_date as purchase_document_date,purchase.folio as purchase_folio,purchase.pname as purchase_pname,purchase.status as purchase_status,purchase.supply_date as purchase_supply_date,purchase_contract.id as purchase_contract_id,purchase_contract.pkey as purchase_contract_pkey,purchase_contract.pname as purchase_contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,purchase_type.id as purchase_type_id,purchase_type.pkey as purchase_type_pkey,purchase_type.pname as purchase_type_pname   FROM   purchase,  purchase_contract as purchase_contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  user_lon as user_autor,  purchase_type as purchase_type   WHERE  purchase.purchase_contract_id = purchase_contract.id AND purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND purchase_contract.third_person_id = third_person.id AND purchase.user_autor_id = user_autor.id AND purchase.purchase_type_id = purchase_type.id";
    private static String sqlCount = "SELECT  count(purchase.id)   FROM   purchase,  purchase_contract as purchase_contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  user_lon as user_autor,  purchase_type as purchase_type   WHERE  purchase.purchase_contract_id = purchase_contract.id AND purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND purchase_contract.third_person_id = third_person.id AND purchase.user_autor_id = user_autor.id AND purchase.purchase_type_id = purchase_type.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        