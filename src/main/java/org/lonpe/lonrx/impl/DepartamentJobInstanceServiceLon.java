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
public class DepartamentJobInstanceServiceLon extends AbstractLon<DepartamentJobInstance> implements IServiceLon<DepartamentJobInstance> {

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

        DCModel dcm = new DCModel("departamentJobInstance", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property description   

        final SProperty description = new SProperty("description", STRING, false, false);

        dcm.addSProperty(description);             //Create property nhoras   

        final SProperty nhoras = new SProperty("nhoras", INTEGER, true, false);

        dcm.addSProperty(nhoras);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, true);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto departamentJob = new SMto("departamentJob", "departamentJob");
        departamentJob.setPc("pname");
        dcm.addSMto(departamentJob);
//1
        final SMto departamentBaseTimePeriod = new SMto("departamentBaseTimePeriod", "departamentBaseTimePeriod");
        dcm.addSMto(departamentBaseTimePeriod);

        final SOtm appointmens = new SOtm("appointmens", "appointment", "undefined");
        dcm.addSOtm(appointmens);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "departament_job_instance_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("departament_job_instance.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "departament_job_instance_pkey");

//description -------------------------------------------
        names.add("description");
        insertMapFields.put("departament_job_instance.description", "description");
//nhoras -------------------------------------------
        names.add("nhoras");
        insertMapFields.put("departament_job_instance.nhoras", "nhoras");
        sortMapFields.put("nhoras", "departament_job_instance_nhoras");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("departament_job_instance.pname", "pname");
//Used to map error on index to source property
        insertMapFields.put("departament_job_instance.departament_job_instance_uidx_pname", "pname");
        sortMapFields.put("pname", "departament_job_instance_pname");
        // departamentJob --------------------
        names.add("departamentJob_id");

        insertMapFields.put("departament_job_instance.departament_job_id", "departamentJob_id");

        names.add("departamentJob_pkey");
        sortMapFields.put("departamentJob_pkey", "departament_job_pkey");

        names.add("departamentJob_pname");
        sortMapFields.put("departamentJob_pname", "departament_job_pname");
        //   departament 
        names.add("departament_id");

        names.add("departament_pkey");
        names.add("departament_pname");// departamentBaseTimePeriod --------------------
        names.add("departamentBaseTimePeriod_id");

        insertMapFields.put("departament_job_instance.departament_base_time_period_id", "departamentBaseTimePeriod_id");

        names.add("departamentBaseTimePeriod_pkey");
        sortMapFields.put("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");

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
        names.add("departament_pname");
    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final DepartamentJobInstance dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addInteger(dc0.getNhoras());
        t.addString(dc0.getPname());

        if (dc0.getDepartamentJob() != null) {
            t.addLong(dc0.getDepartamentJob().getId());
        }

        if (dc0.getDepartamentBaseTimePeriod() != null) {
            t.addLong(dc0.getDepartamentBaseTimePeriod().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO departament_job_instance(pkey,description,nhoras,pname,departament_job_id,departament_base_time_period_id,id) VALUES ($1,$2,$3,$4,$5,$6,(select nextval('seq_departament_job_instance'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(DepartamentJobInstance departamentJobInstance) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(departamentJobInstance, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "departament_job_instance", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "departament_job_instance_pkey");
        slcb.doEqInPSimple("pkey", "departament_job_instance_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "departament_job_instance_pname");
        slcb.doEqInPSimple("pname", "departament_job_instance_pname");
        slcb.doGEPSimpleInt("nhoras", "departament_job_instance_nhoras");
        slcb.doLTPSimpleInt("nhoras", "departament_job_instance_nhoras");

        slcb.doIlPSimple2("departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2("departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");
        // //DepartamentJob 4
        slcb.doIlPSimple2("departamentJob_pname", "departament_job_pname");

        slcb.doIlPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");
        // //DepartamentBaseTimePeriod undefined

        slcb.doIlPSimple2("departament_pkey", "departament_pkey");
        slcb.doEQPSimple2("departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");
//Departament 3

        slcb.doIlPSimple2("departament_pname", "departament_pname");

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
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  departament_job_instance.id as departament_job_instance_id,departament_job_instance.pkey as departament_job_instance_pkey,departament_job_instance.description as departament_job_instance_description,departament_job_instance.nhoras as departament_job_instance_nhoras,departament_job_instance.pname as departament_job_instance_pname,departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,departament_base_time_period.id as departament_base_time_period_id,departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname   FROM   departament_job_instance,  departament_job as departament_job,  departament as departament,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period   WHERE  departament_job_instance.departament_job_id = departament_job.id AND departament_job.departament_id = departament.id AND departament_job_instance.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id";
    private static String sqlCount = "SELECT  count(departament_job_instance.id)   FROM   departament_job_instance,  departament_job as departament_job,  departament as departament,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period   WHERE  departament_job_instance.departament_job_id = departament_job.id AND departament_job.departament_id = departament.id AND departament_job_instance.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
