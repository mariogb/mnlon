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
public class BaseUserLonServiceLon extends AbstractLon<BaseUserLon> implements IServiceLon<BaseUserLon> {

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

        DCModel dcm = new DCModel("baseUserLon");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);

        //1
        final SMto base = new SMto("base", "base");
        base.setPc("pname");
        dcm.addSMto(base);
//1
        final SMto userLon = new SMto("userLon", "userLon");
        userLon.setPc("pname");
        dcm.addSMto(userLon);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "base_user_lon_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("base_user_lon.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("base_user_lon.base_user_lon_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "base_user_lon_pkey");
        // base --------------------
        names.add("base_id");

        insertMapFields.put("base_user_lon.base_id", "base_id");

        names.add("base_pkey");
        sortMapFields.put("base_pkey", "base_pkey");

        names.add("base_pname");
        sortMapFields.put("base_pname", "base_pname");
        // userLon --------------------
        names.add("userLon_id");

        insertMapFields.put("base_user_lon.user_lon_id", "userLon_id");

        names.add("userLon_pkey");
        sortMapFields.put("userLon_pkey", "user_lon_pkey");

        names.add("userLon_pname");
        sortMapFields.put("userLon_pname", "user_lon_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final BaseUserLon dc0, final Tuple t) {
        t.addString(dc0.getPkey());

        if (dc0.getBase() != null) {
            t.addLong(dc0.getBase().getId());
        }

        if (dc0.getUserLon() != null) {
            t.addLong(dc0.getUserLon().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO base_user_lon(pkey,base_id,user_lon_id,id) VALUES ($1,$2,$3,(select nextval('seq_base_user_lon'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(BaseUserLon baseUserLon) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(baseUserLon, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "base_user_lon", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "base_user_lon_pkey");
        slcb.doEqInPSimple("pkey", "base_user_lon_pkey");

        slcb.doIlPSimple2("base_pkey", "base_pkey");
        slcb.doEQPSimple2("base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");
        // //Base 1
        slcb.doIlPSimple2("base_pname", "base_pname");

        slcb.doIlPSimple2("userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2("userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");
        // //UserLon 4
        slcb.doIlPSimple2("userLon_pname", "user_lon_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  base_user_lon.id as base_user_lon_id,base_user_lon.pkey as base_user_lon_pkey,base.id as base_id,base.pkey as base_pkey,base.pname as base_pname,user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname   FROM   base_user_lon,  base as base,  user_lon as user_lon   WHERE  base_user_lon.base_id = base.id AND base_user_lon.user_lon_id = user_lon.id";
    private static String sqlCount = "SELECT  count(base_user_lon.id)   FROM   base_user_lon,  base as base,  user_lon as user_lon   WHERE  base_user_lon.base_id = base.id AND base_user_lon.user_lon_id = user_lon.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
