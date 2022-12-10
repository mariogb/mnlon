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
public class WorkSpaceGroupServiceLon extends AbstractLon<WorkSpaceGroup> implements IServiceLon<WorkSpaceGroup> {

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

        DCModel dcm = new DCModel("workSpaceGroup", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);             //Create property typeLon   

        final SProperty typeLon = new SProperty("typeLon", STRING, true, false);

        final List<String> typeLonInList = new ArrayList<>();

        typeLonInList.add("BUILD");
        typeLonInList.add("MOVIL");
        typeLon.setInList(typeLonInList);
        dcm.addSProperty(typeLon);

        //1
        final SMto base = new SMto("base", "base");
        base.setPc("pname");
        dcm.addSMto(base);

        final SOtm workSpaces = new SOtm("workSpaces", "workSpace", "undefined");
        dcm.addSOtm(workSpaces);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "work_space_group_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("work_space_group.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("work_space_group.work_space_group_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "work_space_group_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("work_space_group.pname", "pname");
        sortMapFields.put("pname", "work_space_group_pname");

//typeLon -------------------------------------------
        names.add("typeLon");
        insertMapFields.put("work_space_group.type_lon", "typeLon");
        sortMapFields.put("typeLon", "work_space_group_type_lon");
        // base --------------------
        names.add("base_id");

        insertMapFields.put("work_space_group.base_id", "base_id");

        names.add("base_pkey");
        sortMapFields.put("base_pkey", "base_pkey");

        names.add("base_pname");
        sortMapFields.put("base_pname", "base_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final WorkSpaceGroup dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());

        if (dc0.getBase() != null) {
            t.addLong(dc0.getBase().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO work_space_group(pkey,pname,type_lon,base_id,id) VALUES ($1,$2,$3,$4,(select nextval('seq_work_space_group'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(WorkSpaceGroup workSpaceGroup) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(workSpaceGroup, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "work_space_group", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "work_space_group_pkey");
        slcb.doEqInPSimple("pkey", "work_space_group_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "work_space_group_pname");
        slcb.doEqInPSimple("pname", "work_space_group_pname");
        slcb.doEqInPSimple("typeLon", "work_space_group_type_lon");

        slcb.doIlPSimple2("base_pkey", "base_pkey");
        slcb.doEQPSimple2("base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");
        // //Base 1
        slcb.doIlPSimple2("base_pname", "base_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  work_space_group.id as work_space_group_id,work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,work_space_group.type_lon as work_space_group_type_lon,base.id as base_id,base.pkey as base_pkey,base.pname as base_pname   FROM   work_space_group,  base as base   WHERE  work_space_group.base_id = base.id";
    private static String sqlCount = "SELECT  count(work_space_group.id)   FROM   work_space_group,  base as base   WHERE  work_space_group.base_id = base.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
