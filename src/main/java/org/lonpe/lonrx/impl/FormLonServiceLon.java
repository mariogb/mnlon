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
public class FormLonServiceLon extends AbstractLon<FormLon> implements IServiceLon<FormLon> {

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

        DCModel dcm = new DCModel("formLon", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        final SOtm formLonFields = new SOtm("formLonFields", "formLonField", "undefined");
        dcm.addSOtm(formLonFields);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "form_lon_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("form_lon.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("form_lon.form_lon_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "form_lon_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("form_lon.pname", "pname");
        sortMapFields.put("pname", "form_lon_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final FormLon dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    private static final String SQLINSERT = "INSERT INTO form_lon(pkey,pname,id) VALUES ($1,$2,(select nextval('seq_form_lon'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(FormLon formLon) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(formLon, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "form_lon", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "form_lon_pkey");
        slcb.doEqInPSimple("pkey", "form_lon_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "form_lon_pname");
        slcb.doEqInPSimple("pname", "form_lon_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  form_lon.id as form_lon_id,form_lon.pkey as form_lon_pkey,form_lon.pname as form_lon_pname   FROM   form_lon ";
    private static String sqlCount = "SELECT  count(form_lon.id)   FROM   form_lon ";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
