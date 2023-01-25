
        
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
public class PaymentInFormServiceLon extends AbstractLon<PaymentInForm> implements IServiceLon<PaymentInForm>{  
        
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
        
    
        DCModel dcm = new DCModel("paymentInForm");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    
//1
    final SMto monetaryAccount = new SMto("monetaryAccount","monetaryAccount"); 
    monetaryAccount.setPc("pname"); 
    dcm.addSMto(monetaryAccount); 

//1
    final SMto paymentInType = new SMto("paymentInType","paymentInType"); 
    paymentInType.setPc("pname"); 
    dcm.addSMto(paymentInType); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","payment_in_form_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("payment_in_form.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("payment_in_form.payment_in_form_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "payment_in_form_pkey");
                   // monetaryAccount --------------------
        names.add("monetaryAccount_id");
        
        insertMapFields.put("payment_in_form.monetary_account_id","monetaryAccount_id");
        
        
        names.add("monetaryAccount_pkey");        
            sortMapFields.put( "monetaryAccount_pkey", "monetary_account_pkey");

        
   names.add("monetaryAccount_pname");
            sortMapFields.put( "monetaryAccount_pname", "monetary_account_pname");
            // paymentInType --------------------
        names.add("paymentInType_id");
        
        insertMapFields.put("payment_in_form.payment_in_type_id","paymentInType_id");
        
        
        names.add("paymentInType_pkey");        
            sortMapFields.put( "paymentInType_pkey", "payment_in_type_pkey");

        
   names.add("paymentInType_pname");
            sortMapFields.put( "paymentInType_pname", "payment_in_type_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PaymentInForm dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getMonetaryAccount()!=null){
            t.addLong(dc0.getMonetaryAccount().getId());
                }
   
                if(dc0.getPaymentInType()!=null){
            t.addLong(dc0.getPaymentInType().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO payment_in_form(pkey,monetary_account_id,payment_in_type_id,id) VALUES ($1,$2,$3,(select nextval('seq_payment_in_form'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(PaymentInForm paymentInForm) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(paymentInForm, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("payment_in_form", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "payment_in_form_pkey");
    slcb.doEqInPSimple( "pkey", "payment_in_form_pkey");
        
        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");        
    
// 1 MonetaryAccount pname
        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname");
    

        slcb.doIlPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doEQPSimple2( "paymentInType_pkey", "payment_in_type_pkey");
        slcb.doInLongCondition("paymentInType_id", "payment_in_type_id");        
    
// 1 PaymentInType pname
        slcb.doIlPSimple2( "paymentInType_pname", "payment_in_type_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  payment_in_form.id as payment_in_form_id,payment_in_form.pkey as payment_in_form_pkey,monetary_account.id as monetary_account_id,monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,payment_in_type.id as payment_in_type_id,payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname   FROM   payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type   WHERE  payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id";
    private static String sqlCount = "SELECT  count(payment_in_form.id)   FROM   payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type   WHERE  payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        