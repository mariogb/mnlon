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
public class ComercialDocumentOutServiceLon extends AbstractLon<ComercialDocumentOut> implements IServiceLon<ComercialDocumentOut> {

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

        DCModel dcm = new DCModel("comercialDocumentOut", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property createdDate   

        final SProperty createdDate = new SProperty("createdDate", LOCALDATETIME, true, false);

//Set by system
        createdDate.setSetBySys("now");
        dcm.addSProperty(createdDate);             //Create property documentDate   

        final SProperty documentDate = new SProperty("documentDate", LOCALDATETIME, false, false);

        dcm.addSProperty(documentDate);             //Create property folio   

        final SProperty folio = new SProperty("folio", STRING, true, false);

        dcm.addSProperty(folio);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);             //Create property status   

        final SProperty status = new SProperty("status", STRING, true, false);

        final List<String> statusInList = new ArrayList<>();

        statusInList.add("PENDENT");
        statusInList.add("SUPPLIED");
        statusInList.add("CANCEL");
        status.setInList(statusInList);
        dcm.addSProperty(status);             //Create property supplyDate   

        final SProperty supplyDate = new SProperty("supplyDate", LOCALDATETIME, false, false);

        dcm.addSProperty(supplyDate);

        //1
        final SMto contract = new SMto("contract", "contractOut");
        contract.setPc("pname");
        dcm.addSMto(contract);
//1
        final SMto userAutor = new SMto("userAutor", "userLon");
        userAutor.setPc("pname");
        userAutor.setSetBySys("putCurrentUser");
        dcm.addSMto(userAutor);
//1
        final SMto comercialDocumentType = new SMto("comercialDocumentType", "comercialDocumentTypeOut");
        comercialDocumentType.setPc("pname");
        dcm.addSMto(comercialDocumentType);

//ON RELATION comercialDocument    
        final SOtm invoiceLines = new SOtm("invoiceLines", "invoiceLineIn", "comercialDocument");
        dcm.addSOtm(invoiceLines);
        final SOtm payments = new SOtm("payments", "paymentOut", "undefined");
        dcm.addSOtm(payments);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "comercial_document_out_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("comercial_document_out.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("comercial_document_out.comercial_document_out_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "comercial_document_out_pkey");

//createdDate -------------------------------------------
        names.add("createdDate");
        insertMapFields.put("comercial_document_out.created_date", "createdDate");
        sortMapFields.put("createdDate", "comercial_document_out_created_date");

//documentDate -------------------------------------------
        names.add("documentDate");
        insertMapFields.put("comercial_document_out.document_date", "documentDate");
        sortMapFields.put("documentDate", "comercial_document_out_document_date");

//folio -------------------------------------------
        names.add("folio");
        insertMapFields.put("comercial_document_out.folio", "folio");
//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("comercial_document_out.pname", "pname");
        sortMapFields.put("pname", "comercial_document_out_pname");

//status -------------------------------------------
        names.add("status");
        insertMapFields.put("comercial_document_out.status", "status");
//supplyDate -------------------------------------------
        names.add("supplyDate");
        insertMapFields.put("comercial_document_out.supply_date", "supplyDate");
        sortMapFields.put("supplyDate", "comercial_document_out_supply_date");
        // contract --------------------
        names.add("contract_id");

        insertMapFields.put("comercial_document_out.contract_id", "contract_id");

        names.add("contract_pkey");
        sortMapFields.put("contract_pkey", "contract_pkey");

        names.add("contract_pname");
        sortMapFields.put("contract_pname", "contract_pname");
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

        insertMapFields.put("comercial_document_out.user_autor_id", "userAutor_id");

        names.add("userAutor_pkey");
        sortMapFields.put("userAutor_pkey", "user_autor_pkey");

        names.add("userAutor_pname");
        sortMapFields.put("userAutor_pname", "user_autor_pname");
        // comercialDocumentType --------------------
        names.add("comercialDocumentType_id");

        insertMapFields.put("comercial_document_out.comercial_document_type_id", "comercialDocumentType_id");

        names.add("comercialDocumentType_pkey");
        sortMapFields.put("comercialDocumentType_pkey", "comercial_document_type_pkey");

        names.add("comercialDocumentType_pname");
        sortMapFields.put("comercialDocumentType_pname", "comercial_document_type_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ComercialDocumentOut dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addLocalDateTime(dc0.getDocumentDate());
        t.addString(dc0.getFolio());
        t.addString(dc0.getPname());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());

        if (dc0.getContract() != null) {
            t.addLong(dc0.getContract().getId());
        }

        if (dc0.getUserAutor() != null) {
            t.addLong(dc0.getUserAutor().getId());
        }

        if (dc0.getComercialDocumentType() != null) {
            t.addLong(dc0.getComercialDocumentType().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO comercial_document_out(pkey,created_date,document_date,folio,pname,status,supply_date,contract_id,user_autor_id,comercial_document_type_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,(select nextval('seq_comercial_document_out'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(ComercialDocumentOut comercialDocumentOut) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(comercialDocumentOut, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "comercial_document_out", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "comercial_document_out_pkey");
        slcb.doEqInPSimple("pkey", "comercial_document_out_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "comercial_document_out_pname");
        slcb.doEqInPSimple("pname", "comercial_document_out_pname");
        slcb.doEqInPSimple("status", "comercial_document_out_status");

        slcb.doIlPSimple2("contract_pkey", "contract_pkey");
        slcb.doEQPSimple2("contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");
        // //ContractOut 1
        slcb.doIlPSimple2("contract_pname", "contract_pname");

        slcb.doIlPSimple2("userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2("userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");
        // //UserLon 4
        slcb.doIlPSimple2("userAutor_pname", "user_autor_pname");

        slcb.doIlPSimple2("comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doEQPSimple2("comercialDocumentType_pkey", "comercial_document_type_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_id");
        // //ComercialDocumentTypeOut 2
        slcb.doIlPSimple2("comercialDocumentType_pname", "comercial_document_type_pname");

        slcb.doIlPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");
//DepartamentBaseTimePeriod undefined

        slcb.doIlPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");

        slcb.doIlPSimple2("departament_pkey", "departament_pkey");
        slcb.doEQPSimple2("departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");

        slcb.doIlPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");
//ThirdPerson 1

        slcb.doIlPSimple2("thirdPerson_pname", "third_person_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  comercial_document_out.id as comercial_document_out_id,comercial_document_out.pkey as comercial_document_out_pkey,comercial_document_out.created_date as comercial_document_out_created_date,comercial_document_out.document_date as comercial_document_out_document_date,comercial_document_out.folio as comercial_document_out_folio,comercial_document_out.pname as comercial_document_out_pname,comercial_document_out.status as comercial_document_out_status,comercial_document_out.supply_date as comercial_document_out_supply_date,contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,comercial_document_type.id as comercial_document_type_id,comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname   FROM   comercial_document_out,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type   WHERE  comercial_document_out.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND contract.third_person_id = third_person.id AND comercial_document_out.user_autor_id = user_autor.id AND comercial_document_out.comercial_document_type_id = comercial_document_type.id";
    private static String sqlCount = "SELECT  count(comercial_document_out.id)   FROM   comercial_document_out,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type   WHERE  comercial_document_out.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND contract.third_person_id = third_person.id AND comercial_document_out.user_autor_id = user_autor.id AND comercial_document_out.comercial_document_type_id = comercial_document_type.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
