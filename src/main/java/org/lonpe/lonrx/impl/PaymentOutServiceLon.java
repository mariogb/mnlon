
        
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
public class PaymentOutServiceLon extends AbstractLon<PaymentOut> implements IServiceLon<PaymentOut>{  
        
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
        
    
        DCModel dcm = new DCModel("paymentOut");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property quantity   
                
        final SProperty quantity = new SProperty("quantity", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(quantity);             
    

    
//1
    final SMto paymentOutForm = new SMto("paymentOutForm","paymentOutForm"); 
    dcm.addSMto(paymentOutForm); 

//1
    final SMto purchase = new SMto("purchase","purchase"); 
    purchase.setPc("pname"); 
    dcm.addSMto(purchase); 

//1
    final SMto outAccount = new SMto("outAccount","monetaryAccount"); 
    outAccount.setPc("pname"); 
    dcm.addSMto(outAccount); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","payment_out_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("payment_out.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("payment_out.payment_out_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "payment_out_pkey");
                    
//quantity -------------------------------------------
    names.add("quantity");
    insertMapFields.put("payment_out.quantity","quantity");  
    sortMapFields.put("quantity", "payment_out_quantity");
               // paymentOutForm --------------------
        names.add("paymentOutForm_id");
        
        insertMapFields.put("payment_out.payment_out_form_id","paymentOutForm_id");
        
        
        names.add("paymentOutForm_pkey");        
            sortMapFields.put( "paymentOutForm_pkey", "payment_out_form_pkey");

        //   monetaryAccount 
        names.add("monetaryAccount_id");
            
        names.add("monetaryAccount_pkey");
   names.add("monetaryAccount_pname");//   paymentOutType 
        names.add("paymentOutType_id");
            
        names.add("paymentOutType_pkey");
   names.add("paymentOutType_pname");// purchase --------------------
        names.add("purchase_id");
        
        insertMapFields.put("payment_out.purchase_id","purchase_id");
        
        
        names.add("purchase_pkey");        
            sortMapFields.put( "purchase_pkey", "purchase_pkey");

        
   names.add("purchase_pname");
            sortMapFields.put( "purchase_pname", "purchase_pname");
            //   purchaseContract 
        names.add("purchaseContract_id");
            
        names.add("purchaseContract_pkey");
   names.add("purchaseContract_pname");//   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");
            
        names.add("departamentBaseTimePeriod_pkey");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");//   userAutor 
        names.add("userAutor_id");
            
        names.add("userAutor_pkey");
   names.add("userAutor_pname");//   purchaseType 
        names.add("purchaseType_id");
            
        names.add("purchaseType_pkey");
   names.add("purchaseType_pname");// outAccount --------------------
        names.add("outAccount_id");
        
        insertMapFields.put("payment_out.out_account_id","outAccount_id");
        
        
        names.add("outAccount_pkey");        
            sortMapFields.put( "outAccount_pkey", "out_account_pkey");

        
   names.add("outAccount_pname");
            sortMapFields.put( "outAccount_pname", "out_account_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PaymentOut dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBigDecimal(dc0.getQuantity());
   
                if(dc0.getPaymentOutForm()!=null){
            t.addLong(dc0.getPaymentOutForm().getId());
                }
   
                if(dc0.getPurchase()!=null){
            t.addLong(dc0.getPurchase().getId());
                }
   
                if(dc0.getOutAccount()!=null){
            t.addLong(dc0.getOutAccount().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO payment_out(pkey,quantity,payment_out_form_id,purchase_id,out_account_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_payment_out'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(PaymentOut paymentOut) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(paymentOut, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("payment_out", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "payment_out_pkey");
    slcb.doEqInPSimple( "pkey", "payment_out_pkey");
        
        slcb.doIlPSimple2( "paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doEQPSimple2( "paymentOutForm_pkey", "payment_out_form_pkey");
        slcb.doInLongCondition("paymentOutForm_id", "payment_out_form_id");        
    
// 1 PaymentOutForm undefined

        slcb.doIlPSimple2( "purchase_pkey", "purchase_pkey");
        slcb.doEQPSimple2( "purchase_pkey", "purchase_pkey");
        slcb.doInLongCondition("purchase_id", "purchase_id");        
    
// 1 Purchase pname
        slcb.doIlPSimple2( "purchase_pname", "purchase_pname");
    

        slcb.doIlPSimple2( "outAccount_pkey", "out_account_pkey");
        slcb.doEQPSimple2( "outAccount_pkey", "out_account_pkey");
        slcb.doInLongCondition("outAccount_id", "out_account_id");        
    
// 1 MonetaryAccount pname
        slcb.doIlPSimple2( "outAccount_pname", "out_account_pname");
    

        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");                  

//2  MonetaryAccount pname

        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname");                    

        slcb.doIlPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doEQPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doInLongCondition("paymentOutType_id", "payment_out_type_id");                  

//2  PaymentOutType pname

        slcb.doIlPSimple2( "paymentOutType_pname", "payment_out_type_pname");                    

        slcb.doIlPSimple2( "purchaseContract_pkey", "purchase_contract_pkey");
        slcb.doEQPSimple2( "purchaseContract_pkey", "purchase_contract_pkey");
        slcb.doInLongCondition("purchaseContract_id", "purchase_contract_id");                  

//2  PurchaseContract pname

        slcb.doIlPSimple2( "purchaseContract_pname", "purchase_contract_pname");                    

//3  DepartamentBaseTimePeriod undefined                                
        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");                            

//3  ThirdPerson pname                                
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                            

        slcb.doIlPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");                  

//2  UserLon pname

        slcb.doIlPSimple2( "userAutor_pname", "user_lon_pname");                    

        slcb.doIlPSimple2( "purchaseType_pkey", "purchase_type_pkey");
        slcb.doEQPSimple2( "purchaseType_pkey", "purchase_type_pkey");
        slcb.doInLongCondition("purchaseType_id", "purchase_type_id");                  

//2  PurchaseType pname

        slcb.doIlPSimple2( "purchaseType_pname", "purchase_type_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  payment_out.id as payment_out_id,payment_out.pkey as payment_out_pkey,payment_out.quantity as payment_out_quantity,payment_out_form.id as payment_out_form_id,payment_out_form.pkey as payment_out_form_pkey,monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,payment_out_type.id as payment_out_type_id, payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname,purchase.id as purchase_id,purchase.pkey as purchase_pkey,purchase.pname as purchase_pname,purchase_contract.id as purchase_contract_id, purchase_contract.pkey as purchase_contract_pkey,purchase_contract.pname as purchase_contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,purchase_type.id as purchase_type_id, purchase_type.pkey as purchase_type_pkey,purchase_type.pname as purchase_type_pname,out_account.id as out_account_id,out_account.pkey as out_account_pkey,out_account.pname as out_account_pname   FROM   payment_out,  payment_out_form as payment_out_form,  monetary_account as monetary_account,  payment_out_type as payment_out_type,  purchase as purchase,  purchase_contract as purchase_contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  purchase_type as purchase_type,  monetary_account as out_account   WHERE  payment_out.payment_out_form_id = payment_out_form.id AND payment_out_form.monetary_account_id = monetary_account.id AND payment_out_form.payment_out_type_id = payment_out_type.id AND payment_out.purchase_id = purchase.id AND purchase.purchase_contract_id = purchase_contract.id AND purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND purchase_contract.third_person_id = third_person.id AND purchase.user_autor_id = user_autor.id AND purchase.purchase_type_id = purchase_type.id AND payment_out.out_account_id = out_account.id";
    private static String sqlCount = "SELECT  count(payment_out.id)   FROM   payment_out,  payment_out_form as payment_out_form,  monetary_account as monetary_account,  payment_out_type as payment_out_type,  purchase as purchase,  purchase_contract as purchase_contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  purchase_type as purchase_type,  monetary_account as out_account   WHERE  payment_out.payment_out_form_id = payment_out_form.id AND payment_out_form.monetary_account_id = monetary_account.id AND payment_out_form.payment_out_type_id = payment_out_type.id AND payment_out.purchase_id = purchase.id AND purchase.purchase_contract_id = purchase_contract.id AND purchase_contract.departament_base_time_period_id = departament_base_time_period.id AND purchase_contract.third_person_id = third_person.id AND purchase.user_autor_id = user_autor.id AND purchase.purchase_type_id = purchase_type.id AND payment_out.out_account_id = out_account.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        