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
public class PaymentInServiceLon extends AbstractLon<PaymentIn> implements IServiceLon<PaymentIn> {

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

    private DCModel elModelo() {

        DCModel dcm = new DCModel("paymentIn");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);

        //1
        final SMto paymentInForm = new SMto("paymentInForm", "paymentInForm");
        dcm.addSMto(paymentInForm);
//1
        final SMto comercialDocumentIn = new SMto("comercialDocumentIn", "comercialDocumentIn");
        comercialDocumentIn.setPc("pname");
        dcm.addSMto(comercialDocumentIn);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "payment_in_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("payment_in.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("payment_in.payment_in_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "payment_in_pkey");
        // paymentInForm --------------------
        names.add("paymentInForm_id");

        insertMapFields.put("payment_in.payment_in_form_id", "paymentInForm_id");

        names.add("paymentInForm_pkey");
        sortMapFields.put("paymentInForm_pkey", "payment_in_form_pkey");

        //   monetaryAccount 
        names.add("monetaryAccount_id");

        names.add("monetaryAccount_pkey");
        names.add("monetaryAccount_pname");//   paymentInType 
        names.add("paymentInType_id");

        names.add("paymentInType_pkey");
        names.add("paymentInType_pname");// comercialDocumentIn --------------------
        names.add("comercialDocumentIn_id");

        insertMapFields.put("payment_in.comercial_document_in_id", "comercialDocumentIn_id");

        names.add("comercialDocumentIn_pkey");
        sortMapFields.put("comercialDocumentIn_pkey", "comercial_document_in_pkey");

        names.add("comercialDocumentIn_pname");
        sortMapFields.put("comercialDocumentIn_pname", "comercial_document_in_pname");
        //   contract 
        names.add("contract_id");

        names.add("contract_pkey");
        names.add("contract_pname");//   programBaseTimePeriod 
        names.add("programBaseTimePeriod_id");

        names.add("programBaseTimePeriod_pkey");//   thirdPerson 
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
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final PaymentIn dc0, final Tuple t) {
        t.addString(dc0.getPkey());

        if (dc0.getPaymentInForm() != null) {
            t.addLong(dc0.getPaymentInForm().getId());
        }

        if (dc0.getComercialDocumentIn() != null) {
            t.addLong(dc0.getComercialDocumentIn().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO payment_in(pkey,payment_in_form_id,comercial_document_in_id,id) VALUES ($1,$2,$3,(select nextval('seq_payment_in'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(PaymentIn paymentIn) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(paymentIn, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "payment_in", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "payment_in_pkey");
        slcb.doEqInPSimple("pkey", "payment_in_pkey");

        slcb.doIlPSimple2("paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doEQPSimple2("paymentInForm_pkey", "payment_in_form_pkey");
        slcb.doInLongCondition("paymentInForm_id", "payment_in_form_id");
        // //PaymentInForm undefined

        slcb.doIlPSimple2("comercialDocumentIn_pkey", "comercial_document_in_pkey");
        slcb.doEQPSimple2("comercialDocumentIn_pkey", "comercial_document_in_pkey");
        slcb.doInLongCondition("comercialDocumentIn_id", "comercial_document_in_id");
        // //ComercialDocumentIn 4
        slcb.doIlPSimple2("comercialDocumentIn_pname", "comercial_document_in_pname");

        slcb.doIlPSimple2("monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doEQPSimple2("monetaryAccount_pkey", "monetary_account_pkey");
        slcb.doInLongCondition("monetaryAccount_id", "monetary_account_id");
//MonetaryAccount 1

        slcb.doIlPSimple2("monetaryAccount_pname", "monetary_account_pname");

        slcb.doIlPSimple2("paymentInType_pkey", "payment_in_type_pkey");
        slcb.doEQPSimple2("paymentInType_pkey", "payment_in_type_pkey");
        slcb.doInLongCondition("paymentInType_id", "payment_in_type_id");
//PaymentInType 1

        slcb.doIlPSimple2("paymentInType_pname", "payment_in_type_pname");

        slcb.doIlPSimple2("contract_pkey", "contract_in_pkey");
        slcb.doEQPSimple2("contract_pkey", "contract_in_pkey");
        slcb.doInLongCondition("contract_id", "contract_in_id");
//ContractIn 1

        slcb.doIlPSimple2("contract_pname", "contract_in_pname");

        slcb.doIlPSimple2("programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2("programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");

        slcb.doIlPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");

        slcb.doIlPSimple2("userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2("userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");
//UserLon 4

        slcb.doIlPSimple2("userAutor_pname", "user_lon_pname");

        slcb.doIlPSimple2("comercialDocumentType_pkey", "comercial_document_type_in_pkey");
        slcb.doEQPSimple2("comercialDocumentType_pkey", "comercial_document_type_in_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_in_id");
//ComercialDocumentTypeIn 2

        slcb.doIlPSimple2("comercialDocumentType_pname", "comercial_document_type_in_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  payment_in.id as payment_in_id,payment_in.pkey as payment_in_pkey,payment_in_form.id as payment_in_form_id,payment_in_form.pkey as payment_in_form_pkey,monetary_account.id as monetary_account_id, monetary_account.pkey as monetary_account_pkey,monetary_account.pname as monetary_account_pname,payment_in_type.id as payment_in_type_id, payment_in_type.pkey as payment_in_type_pkey,payment_in_type.pname as payment_in_type_pname,comercial_document_in.id as comercial_document_in_id,comercial_document_in.pkey as comercial_document_in_pkey,comercial_document_in.pname as comercial_document_in_pname,contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname   FROM   payment_in,  payment_in_form as payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type,  comercial_document_in as comercial_document_in,  contract_in as contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_in as comercial_document_type   WHERE  payment_in.payment_in_form_id = payment_in_form.id AND payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id AND payment_in.comercial_document_in_id = comercial_document_in.id AND comercial_document_in.contract_id = contract.id AND contract.program_base_time_period_id = program_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document_in.user_autor_id = user_autor.id AND comercial_document_in.comercial_document_type_id = comercial_document_type.id";
    private static String sqlCount = "SELECT  count(payment_in.id)   FROM   payment_in,  payment_in_form as payment_in_form,  monetary_account as monetary_account,  payment_in_type as payment_in_type,  comercial_document_in as comercial_document_in,  contract_in as contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_in as comercial_document_type   WHERE  payment_in.payment_in_form_id = payment_in_form.id AND payment_in_form.monetary_account_id = monetary_account.id AND payment_in_form.payment_in_type_id = payment_in_type.id AND payment_in.comercial_document_in_id = comercial_document_in.id AND comercial_document_in.contract_id = contract.id AND contract.program_base_time_period_id = program_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document_in.user_autor_id = user_autor.id AND comercial_document_in.comercial_document_type_id = comercial_document_type.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
