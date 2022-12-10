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
public class DepartamentJobServiceLon extends AbstractLon<DepartamentJob> implements IServiceLon<DepartamentJob> {

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

        DCModel dcm = new DCModel("departamentJob", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property description   

        final SProperty description = new SProperty("description", STRING, false, false);

        dcm.addSProperty(description);             //Create property fastKey   

        final SProperty fastKey = new SProperty("fastKey", STRING, false, false);

// hasIndex 
        fastKey.setWithIndex(true);
        dcm.addSProperty(fastKey);             //Create property nhoras   

        final SProperty nhoras = new SProperty("nhoras", INTEGER, true, false);

        dcm.addSProperty(nhoras);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, true);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto departament = new SMto("departament", "departament");
        departament.setPc("pname");
        dcm.addSMto(departament);

        final SOtm departamentJobInstances = new SOtm("departamentJobInstances", "departamentJobInstance", "undefined");
        dcm.addSOtm(departamentJobInstances);
        final SOtm programJobs = new SOtm("programJobs", "programJob", "undefined");
        dcm.addSOtm(programJobs);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "departament_job_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("departament_job.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("departament_job.departament_job_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "departament_job_pkey");

//description -------------------------------------------
        names.add("description");
        insertMapFields.put("departament_job.description", "description");
//fastKey -------------------------------------------
        names.add("fastKey");
        insertMapFields.put("departament_job.fast_key", "fastKey");
//nhoras -------------------------------------------
        names.add("nhoras");
        insertMapFields.put("departament_job.nhoras", "nhoras");
        sortMapFields.put("nhoras", "departament_job_nhoras");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("departament_job.pname", "pname");
//Used to map error on index to source property
        insertMapFields.put("departament_job.departament_job_uidx_pname", "pname");
        sortMapFields.put("pname", "departament_job_pname");
        // departament --------------------
        names.add("departament_id");

        insertMapFields.put("departament_job.departament_id", "departament_id");

        names.add("departament_pkey");
        sortMapFields.put("departament_pkey", "departament_pkey");

        names.add("departament_pname");
        sortMapFields.put("departament_pname", "departament_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final DepartamentJob dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addInteger(dc0.getNhoras());
        t.addString(dc0.getPname());

        if (dc0.getDepartament() != null) {
            t.addLong(dc0.getDepartament().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO departament_job(pkey,description,fast_key,nhoras,pname,departament_id,id) VALUES ($1,$2,$3,$4,$5,$6,(select nextval('seq_departament_job'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(DepartamentJob departamentJob) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(departamentJob, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "departament_job", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "departament_job_pkey");
        slcb.doEqInPSimple("pkey", "departament_job_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "departament_job_pname");
        slcb.doEqInPSimple("pname", "departament_job_pname");
// withIndex true
        slcb.doIlPSimple2("fastKey", "departament_job_fast_key");
        slcb.doEqInPSimple("fastKey", "departament_job_fast_key");
        slcb.doGEPSimpleInt("nhoras", "departament_job_nhoras");
        slcb.doLTPSimpleInt("nhoras", "departament_job_nhoras");

        slcb.doIlPSimple2("departament_pkey", "departament_pkey");
        slcb.doEQPSimple2("departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");
        // //Departament 3
        slcb.doIlPSimple2("departament_pname", "departament_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.description as departament_job_description,departament_job.fast_key as departament_job_fast_key,departament_job.nhoras as departament_job_nhoras,departament_job.pname as departament_job_pname,departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname   FROM   departament_job,  departament as departament   WHERE  departament_job.departament_id = departament.id";
    private static String sqlCount = "SELECT  count(departament_job.id)   FROM   departament_job,  departament as departament   WHERE  departament_job.departament_id = departament.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
