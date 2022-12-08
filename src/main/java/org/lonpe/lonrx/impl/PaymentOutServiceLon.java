
        
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
            
             
    dcm.addSProperty(pkey);             
    

    //1
    final SMto paymentOutForm = new SMto("paymentOutForm","paymentOutForm"); 
    dcm.addSMto(paymentOutForm); 
//1
    final SMto comercialDocumentOut = new SMto("comercialDocumentOut","comercialDocumentOut"); 
    comercialDocumentOut.setPc("pname"); 
    dcm.addSMto(comercialDocumentOut); 
    

    
    
        
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
   names.add("paymentOutType_pname");// comercialDocumentOut --------------------
        names.add("comercialDocumentOut_id");
        
        insertMapFields.put("payment_out.comercial_document_out_id","comercialDocumentOut_id");
        
        
        names.add("comercialDocumentOut_pkey");        
            sortMapFields.put( "comercialDocumentOut_pkey", "comercial_document_out_pkey");

        
   names.add("comercialDocumentOut_pname");
            sortMapFields.put( "comercialDocumentOut_pname", "comercial_document_out_pname");
            //   contract 
        names.add("contract_id");
            
        names.add("contract_pkey");
   names.add("contract_pname");//   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");
            
        names.add("departamentBaseTimePeriod_pkey");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");//   userAutor 
        names.add("userAutor_id");
            
        names.add("userAutor_pkey");
   names.add("userAutor_pname");//   comercialDocumentType 
        names.add("comercialDocumentType_id");
            
        names.add("comercialDocumentType_pkey");
   names.add("comercialDocumentType_pname");
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PaymentOut dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getPaymentOutForm()!=null){
            t.addLong(dc0.getPaymentOutForm().getId());
                }
   
                if(dc0.getComercialDocumentOut()!=null){
            t.addLong(dc0.getComercialDocumentOut().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO payment_out(pkey,payment_out_form_id,comercial_document_out_id,id) VALUES ($1,$2,$3,(select nextval('seq_payment_out'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(PaymentOut paymentOut) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(paymentOut, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "payment_out", objForQuery);
        
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
    // //PaymentOutForm undefined

        slcb.doIlPSimple2( "comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doEQPSimple2( "comercialDocumentOut_pkey", "comercial_document_out_pkey");
        slcb.doInLongCondition("comercialDocumentOut_id", "comercial_document_out_id");        
    // //ComercialDocumentOut 4
        slcb.doIlPSimple2( "comercialDocumentOut_pname", "comercial_document_out_pname");
    

        slcb.doIlPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2( "monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");                  
//MonetaryAccount 1

        slcb.doIlPSimple2( "monetaryAccount_pname", "monetary_account_pname");                    

        slcb.doIlPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doEQPSimple2( "paymentOutType_pkey", "payment_out_type_pkey");
        slcb.doInLongCondition("paymentOutType_id", "payment_out_type_id");                  
//PaymentOutType 1

        slcb.doIlPSimple2( "paymentOutType_pname", "payment_out_type_pname");                    

        slcb.doIlPSimple2( "contract_pkey", "contract_out_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_out_pkey");
        slcb.doInLongCondition("contract_id", "contract_out_id");                  
//ContractOut 1

        slcb.doIlPSimple2( "contract_pname", "contract_out_pname");                    

        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");                            

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                            

        slcb.doIlPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");                  
//UserLon 4

        slcb.doIlPSimple2( "userAutor_pname", "user_lon_pname");                    

        slcb.doIlPSimple2( "comercialDocumentType_pkey", "comercial_document_type_out_pkey");
        slcb.doEQPSimple2( "comercialDocumentType_pkey", "comercial_document_type_out_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_out_id");                  
//ComercialDocumentTypeOut 2

        slcb.doIlPSimple2( "comercialDocumentType_pname", "comercial_document_type_out_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  payment_out.id as payment_out_id,payment_out.pkey as payment_out_pkey,payment_out_form.id as payment_out_form_id,payment_out_form.pkey as payment_out_form_pkey,monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,payment_out_type.id as payment_out_type_id, payment_out_type.pkey as payment_out_type_pkey,payment_out_type.pname as payment_out_type_pname,comercial_document_out.id as comercial_document_out_id,comercial_document_out.pkey as comercial_document_out_pkey,comercial_document_out.pname as comercial_document_out_pname,contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname   FROM   payment_out,  payment_out_form as payment_out_form,  monetary_account as monetary_account,  payment_out_type as payment_out_type,  comercial_document_out as comercial_document_out,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type   WHERE  payment_out.payment_out_form_id = payment_out_form.id AND payment_out_form.monetary_account_id = monetary_account.id AND payment_out_form.payment_out_type_id = payment_out_type.id AND payment_out.comercial_document_out_id = comercial_document_out.id AND comercial_document_out.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document_out.user_autor_id = user_autor.id AND comercial_document_out.comercial_document_type_id = comercial_document_type.id";
    private static String sqlCount = "SELECT  count(payment_out.id)   FROM   payment_out,  payment_out_form as payment_out_form,  monetary_account as monetary_account,  payment_out_type as payment_out_type,  comercial_document_out as comercial_document_out,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type   WHERE  payment_out.payment_out_form_id = payment_out_form.id AND payment_out_form.monetary_account_id = monetary_account.id AND payment_out_form.payment_out_type_id = payment_out_type.id AND payment_out.comercial_document_out_id = comercial_document_out.id AND comercial_document_out.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document_out.user_autor_id = user_autor.id AND comercial_document_out.comercial_document_type_id = comercial_document_type.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        