
        
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
public class PaymentInServiceLon extends AbstractLon<PaymentIn> implements IServiceLon<PaymentIn>{  
        
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
        
    
        DCModel dcm = new DCModel("paymentIn");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property quantity   
                
        final SProperty quantity = new SProperty("quantity", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(quantity);             
    

    
//1
    final SMto paymentInForm = new SMto("paymentInForm","paymentInForm"); 
    dcm.addSMto(paymentInForm); 

//1
    final SMto sale = new SMto("sale","sale"); 
    sale.setPc("pname"); 
    dcm.addSMto(sale); 

//1
    final SMto inAccount = new SMto("inAccount","monetaryAccount"); 
    inAccount.setPc("pname"); 
    dcm.addSMto(inAccount); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","payment_in_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("payment_in.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("payment_in.payment_in_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "payment_in_pkey");
                    
//quantity -------------------------------------------
    names.add("quantity");
    insertMapFields.put("payment_in.quantity","quantity");  
    sortMapFields.put("quantity", "payment_in_quantity");
               // paymentInForm --------------------
        names.add("paymentInForm_id");
        
        insertMapFields.put("payment_in.payment_in_form_id","paymentInForm_id");
        
        
        names.add("paymentInForm_pkey");        
            sortMapFields.put( "paymentInForm_pkey", "payment_in_form_pkey");

        //   monetaryAccount 
        names.add("monetaryAccount_id");
            
        names.add("monetaryAccount_pkey");
   names.add("monetaryAccount_pname");//   paymentInType 
        names.add("paymentInType_id");
            
        names.add("paymentInType_pkey");
   names.add("paymentInType_pname");// sale --------------------
        names.add("sale_id");
        
        insertMapFields.put("payment_in.sale_id","sale_id");
        
        
        names.add("sale_pkey");        
            sortMapFields.put( "sale_pkey", "sale_pkey");

        
   names.add("sale_pname");
            sortMapFields.put( "sale_pname", "sale_pname");
            //   saleContract 
        names.add("saleContract_id");
            
        names.add("saleContract_pkey");
   names.add("saleContract_pname");//   programBaseTimePeriod 
        names.add("programBaseTimePeriod_id");
            
        names.add("programBaseTimePeriod_pkey");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");//   userAutor 
        names.add("userAutor_id");
            
        names.add("userAutor_pkey");
   names.add("userAutor_pname");//   saleType 
        names.add("saleType_id");
            
        names.add("saleType_pkey");
   names.add("saleType_pname");// inAccount --------------------
        names.add("inAccount_id");
        
        insertMapFields.put("payment_in.in_account_id","inAccount_id");
        
        
        names.add("inAccount_pkey");        
            sortMapFields.put( "inAccount_pkey", "in_account_pkey");

        
   names.add("inAccount_pname");
            sortMapFields.put( "inAccount_pname", "in_account_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PaymentIn dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addBigDecimal(dc0.getQuantity());
   
                if(dc0.getPaymentInForm()!=null){
            t.addLong(dc0.getPaymentInForm().getId());
                }
   
                if(dc0.getSale()!=null){
            t.addLong(dc0.getSale().getId());
                }
   
                if(dc0.getInAccount()!=null){
            t.addLong(dc0.getInAccount().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO payment_in(pkey,quantity,payment_in_form_id,sale_id,in_account_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_payment_in'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(PaymentIn paymentIn) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(paymentIn, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("payment_in", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "payment_in_pkey");
    slcb.doEqInPSimple( "pkey", "payment_in_pkey");
        
        slcb.doIlPSimple2( "paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doEQPSimple2( "paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doInLongCondition("paymentInForm_id", "payment_in_form_id");        
    
// 1 PaymentInForm undefined

        slcb.doIlPSimple2( "sale_pkey", "sale_pkey");
        slcb.doEQPSimple2( "sale_pkey", "sale_pkey");
        slcb.doInLongCondition("sale_id", "sale_id");        
    
// 1 Sale pname
        slcb.doIlPSimple2( "sale_pname", "sale_pname");
    

        slcb.doIlPSimple2( "inAccount_pkey", "in_account_pkey");
        slcb.doEQPSimple2( "inAccount_pkey", "in_account_pkey");
        slcb.doInLongCondition("inAccount_id", "in_account_id");        
    
// 1 MonetaryAccount pname
        slcb.doIlPSimple2( "inAccount_pname", "in_account_pname");
    

        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");                  

//2  MonetaryAccount pname

        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname");                    

        slcb.doIlPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doEQPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doInLongCondition("paymentInType_id", "payment_in_type_id");                  

//2  PaymentInType pname

        slcb.doIlPSimple2( "paymentInType_pname", "payment_in_type_pname");                    

        slcb.doIlPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doEQPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doInLongCondition("saleContract_id", "sale_contract_id");                  

//2  SaleContract pname

        slcb.doIlPSimple2( "saleContract_pname", "sale_contract_pname");                    

//3  ProgramBaseTimePeriod undefined                                
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");                            

//3  ThirdPerson pname                                
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                            

        slcb.doIlPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");                  

//2  UserLon pname

        slcb.doIlPSimple2( "userAutor_pname", "user_lon_pname");                    

        slcb.doIlPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doEQPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doInLongCondition("saleType_id", "sale_type_id");                  

//2  SaleType pname

        slcb.doIlPSimple2( "saleType_pname", "sale_type_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  payment_in.id as payment_in_id,payment_in.pkey as payment_in_pkey,payment_in.quantity as payment_in_quantity,payment_in_form.id as payment_in_form_id,payment_in_form.pkey as payment_in_form_pkey,monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,payment_in_type.id as payment_in_type_id, payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname,sale.id as sale_id,sale.pkey as sale_pkey,sale.pname as sale_pname,sale_contract.id as sale_contract_id, sale_contract.pkey as sale_contract_pkey,sale_contract.pname as sale_contract_pname,program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,sale_type.id as sale_type_id, sale_type.pkey as sale_type_pkey,sale_type.pname as sale_type_pname,in_account.id as in_account_id,in_account.pkey as in_account_pkey,in_account.pname as in_account_pname   FROM   payment_in,  payment_in_form as payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type,  sale as sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type,  monetary_account as in_account   WHERE  payment_in.payment_in_form_id = payment_in_form.id AND payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id AND payment_in.sale_id = sale.id AND sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id AND payment_in.in_account_id = in_account.id";
    private static String sqlCount = "SELECT  count(payment_in.id)   FROM   payment_in,  payment_in_form as payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type,  sale as sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type,  monetary_account as in_account   WHERE  payment_in.payment_in_form_id = payment_in_form.id AND payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id AND payment_in.sale_id = sale.id AND sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id AND payment_in.in_account_id = in_account.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        