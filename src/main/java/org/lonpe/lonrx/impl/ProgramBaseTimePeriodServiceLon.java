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
public class ProgramBaseTimePeriodServiceLon extends AbstractLon<ProgramBaseTimePeriod> implements IServiceLon<ProgramBaseTimePeriod> {

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

        DCModel dcm = new DCModel("programBaseTimePeriod");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);

        //1
        final SMto baseTimePeriod = new SMto("baseTimePeriod", "baseTimePeriod");
        dcm.addSMto(baseTimePeriod);
//1
        final SMto program = new SMto("program", "program");
        program.setPc("pname");
        dcm.addSMto(program);

        final SOtm contracts = new SOtm("contracts", "contractIn", "undefined");
        dcm.addSOtm(contracts);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "program_base_time_period_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("program_base_time_period.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("program_base_time_period.program_base_time_period_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "program_base_time_period_pkey");
        // baseTimePeriod --------------------
        names.add("baseTimePeriod_id");

        insertMapFields.put("program_base_time_period.base_time_period_id", "baseTimePeriod_id");

        names.add("baseTimePeriod_pkey");
        sortMapFields.put("baseTimePeriod_pkey", "base_time_period_pkey");

        //   base 
        names.add("base_id");

        names.add("base_pkey");
        names.add("base_pname");//   timePeriod 
        names.add("timePeriod_id");

        names.add("timePeriod_pkey");
        names.add("timePeriod_pname");// program --------------------
        names.add("program_id");

        insertMapFields.put("program_base_time_period.program_id", "program_id");

        names.add("program_pkey");
        sortMapFields.put("program_pkey", "program_pkey");

        names.add("program_pname");
        sortMapFields.put("program_pname", "program_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ProgramBaseTimePeriod dc0, final Tuple t) {
        t.addString(dc0.getPkey());

        if (dc0.getBaseTimePeriod() != null) {
            t.addLong(dc0.getBaseTimePeriod().getId());
        }

        if (dc0.getProgram() != null) {
            t.addLong(dc0.getProgram().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO program_base_time_period(pkey,base_time_period_id,program_id,id) VALUES ($1,$2,$3,(select nextval('seq_program_base_time_period'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(ProgramBaseTimePeriod programBaseTimePeriod) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(programBaseTimePeriod, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "program_base_time_period", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "program_base_time_period_pkey");
        slcb.doEqInPSimple("pkey", "program_base_time_period_pkey");

        slcb.doIlPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2("baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");
        // //BaseTimePeriod undefined

        slcb.doIlPSimple2("program_pkey", "program_pkey");
        slcb.doEQPSimple2("program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");
        // //Program 3
        slcb.doIlPSimple2("program_pname", "program_pname");

        slcb.doIlPSimple2("base_pkey", "base_pkey");
        slcb.doEQPSimple2("base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");
//Base 1

        slcb.doIlPSimple2("base_pname", "base_pname");

        slcb.doIlPSimple2("timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2("timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");
//TimePeriod 3

        slcb.doIlPSimple2("timePeriod_pname", "time_period_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  program_base_time_period.id as program_base_time_period_id,program_base_time_period.pkey as program_base_time_period_pkey,base_time_period.id as base_time_period_id,base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,program.id as program_id,program.pkey as program_pkey,program.pname as program_pname   FROM   program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program   WHERE  program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id";
    private static String sqlCount = "SELECT  count(program_base_time_period.id)   FROM   program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program   WHERE  program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
