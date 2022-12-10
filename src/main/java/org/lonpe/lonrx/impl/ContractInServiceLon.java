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
public class ContractInServiceLon extends AbstractLon<ContractIn> implements IServiceLon<ContractIn> {

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

        DCModel dcm = new DCModel("contractIn", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto programBaseTimePeriod = new SMto("programBaseTimePeriod", "programBaseTimePeriod");
        dcm.addSMto(programBaseTimePeriod);
//1
        final SMto thirdPerson = new SMto("thirdPerson", "thirdPerson");
        thirdPerson.setPc("pname");
        dcm.addSMto(thirdPerson);

//ON RELATION contract    
        final SOtm comercialDocuments = new SOtm("comercialDocuments", "comercialDocumentIn", "contract");
        dcm.addSOtm(comercialDocuments);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "contract_in_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("contract_in.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("contract_in.contract_in_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "contract_in_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("contract_in.pname", "pname");
        sortMapFields.put("pname", "contract_in_pname");
        // programBaseTimePeriod --------------------
        names.add("programBaseTimePeriod_id");

        insertMapFields.put("contract_in.program_base_time_period_id", "programBaseTimePeriod_id");

        names.add("programBaseTimePeriod_pkey");
        sortMapFields.put("programBaseTimePeriod_pkey", "program_base_time_period_pkey");

        //   baseTimePeriod 
        names.add("baseTimePeriod_id");

        names.add("baseTimePeriod_pkey");//   base 
        names.add("base_id");

        names.add("base_pkey");
        names.add("base_pname");//   timePeriod 
        names.add("timePeriod_id");

        names.add("timePeriod_pkey");
        names.add("timePeriod_pname");//   program 
        names.add("program_id");

        names.add("program_pkey");
        names.add("program_pname");// thirdPerson --------------------
        names.add("thirdPerson_id");

        insertMapFields.put("contract_in.third_person_id", "thirdPerson_id");

        names.add("thirdPerson_pkey");
        sortMapFields.put("thirdPerson_pkey", "third_person_pkey");

        names.add("thirdPerson_pname");
        sortMapFields.put("thirdPerson_pname", "third_person_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ContractIn dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());

        if (dc0.getProgramBaseTimePeriod() != null) {
            t.addLong(dc0.getProgramBaseTimePeriod().getId());
        }

        if (dc0.getThirdPerson() != null) {
            t.addLong(dc0.getThirdPerson().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO contract_in(pkey,pname,program_base_time_period_id,third_person_id,id) VALUES ($1,$2,$3,$4,(select nextval('seq_contract_in'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(ContractIn contractIn) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(contractIn, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "contract_in", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "contract_in_pkey");
        slcb.doEqInPSimple("pkey", "contract_in_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "contract_in_pname");
        slcb.doEqInPSimple("pname", "contract_in_pname");

        slcb.doIlPSimple2("programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2("programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");
        // //ProgramBaseTimePeriod undefined

        slcb.doIlPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");
        // //ThirdPerson 1
        slcb.doIlPSimple2("thirdPerson_pname", "third_person_pname");

        slcb.doIlPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");
//BaseTimePeriod undefined

        slcb.doIlPSimple2("base_pkey", "base_pkey");
        slcb.doEQPSimple2("base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");

        slcb.doIlPSimple2("timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2("timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");

        slcb.doIlPSimple2("program_pkey", "program_pkey");
        slcb.doEQPSimple2("program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");
//Program 3

        slcb.doIlPSimple2("program_pname", "program_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  contract_in.id as contract_in_id,contract_in.pkey as contract_in_pkey,contract_in.pname as contract_in_pname,program_base_time_period.id as program_base_time_period_id,program_base_time_period.pkey as program_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,program.id as program_id, program.pkey as program_pkey,program.pname as program_pname,third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname   FROM   contract_in,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program,  third_person as third_person   WHERE  contract_in.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id AND contract_in.third_person_id = third_person.id";
    private static String sqlCount = "SELECT  count(contract_in.id)   FROM   contract_in,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program,  third_person as third_person   WHERE  contract_in.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id AND contract_in.third_person_id = third_person.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
