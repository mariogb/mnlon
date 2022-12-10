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
public class StockRackServiceLon extends AbstractLon<StockRack> implements IServiceLon<StockRack> {

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

        DCModel dcm = new DCModel("stockRack", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property fastKey   

        final SProperty fastKey = new SProperty("fastKey", STRING, false, false);

// hasIndex 
        fastKey.setWithIndex(true);
        dcm.addSProperty(fastKey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto workSpace = new SMto("workSpace", "workSpace");
        workSpace.setPc("pname");
        dcm.addSMto(workSpace);

        final SOtm stockRackProducts = new SOtm("stockRackProducts", "stockRackProduct", "undefined");
        dcm.addSOtm(stockRackProducts);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "stock_rack_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("stock_rack.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("stock_rack.stock_rack_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "stock_rack_pkey");

//fastKey -------------------------------------------
        names.add("fastKey");
        insertMapFields.put("stock_rack.fast_key", "fastKey");
//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("stock_rack.pname", "pname");
        sortMapFields.put("pname", "stock_rack_pname");
        // workSpace --------------------
        names.add("workSpace_id");

        insertMapFields.put("stock_rack.work_space_id", "workSpace_id");

        names.add("workSpace_pkey");
        sortMapFields.put("workSpace_pkey", "work_space_pkey");

        names.add("workSpace_pname");
        sortMapFields.put("workSpace_pname", "work_space_pname");
        //   workSpaceGroup 
        names.add("workSpaceGroup_id");

        names.add("workSpaceGroup_pkey");
        names.add("workSpaceGroup_pname");//   base 
        names.add("base_id");

        names.add("base_pkey");
        names.add("base_pname");
    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final StockRack dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());

        if (dc0.getWorkSpace() != null) {
            t.addLong(dc0.getWorkSpace().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO stock_rack(pkey,fast_key,pname,work_space_id,id) VALUES ($1,$2,$3,$4,(select nextval('seq_stock_rack'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(StockRack stockRack) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(stockRack, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "stock_rack", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "stock_rack_pkey");
        slcb.doEqInPSimple("pkey", "stock_rack_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "stock_rack_pname");
        slcb.doEqInPSimple("pname", "stock_rack_pname");
// withIndex true
        slcb.doIlPSimple2("fastKey", "stock_rack_fast_key");
        slcb.doEqInPSimple("fastKey", "stock_rack_fast_key");

        slcb.doIlPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");
        // //WorkSpace 2
        slcb.doIlPSimple2("workSpace_pname", "work_space_pname");

        slcb.doIlPSimple2("workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2("workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");
//WorkSpaceGroup 1

        slcb.doIlPSimple2("workSpaceGroup_pname", "work_space_group_pname");

        slcb.doIlPSimple2("base_pkey", "base_pkey");
        slcb.doEQPSimple2("base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  stock_rack.id as stock_rack_id,stock_rack.pkey as stock_rack_pkey,stock_rack.fast_key as stock_rack_fast_key,stock_rack.pname as stock_rack_pname,work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname   FROM   stock_rack,  work_space as work_space,  work_space_group as work_space_group,  base as base   WHERE  stock_rack.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id";
    private static String sqlCount = "SELECT  count(stock_rack.id)   FROM   stock_rack,  work_space as work_space,  work_space_group as work_space_group,  base as base   WHERE  stock_rack.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
