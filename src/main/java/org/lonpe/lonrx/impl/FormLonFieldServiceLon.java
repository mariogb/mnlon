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
public class FormLonFieldServiceLon extends AbstractLon<FormLonField> implements IServiceLon<FormLonField> {

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

        DCModel dcm = new DCModel("formLonField", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto formLon = new SMto("formLon", "formLon");
        formLon.setPc("pname");
        dcm.addSMto(formLon);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "form_lon_field_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("form_lon_field.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("form_lon_field.form_lon_field_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "form_lon_field_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("form_lon_field.pname", "pname");
        sortMapFields.put("pname", "form_lon_field_pname");
        // formLon --------------------
        names.add("formLon_id");

        insertMapFields.put("form_lon_field.form_lon_id", "formLon_id");

        names.add("formLon_pkey");
        sortMapFields.put("formLon_pkey", "form_lon_pkey");

        names.add("formLon_pname");
        sortMapFields.put("formLon_pname", "form_lon_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final FormLonField dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());

        if (dc0.getFormLon() != null) {
            t.addLong(dc0.getFormLon().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO form_lon_field(pkey,pname,form_lon_id,id) VALUES ($1,$2,$3,(select nextval('seq_form_lon_field'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(FormLonField formLonField) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(formLonField, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "form_lon_field", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "form_lon_field_pkey");
        slcb.doEqInPSimple("pkey", "form_lon_field_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "form_lon_field_pname");
        slcb.doEqInPSimple("pname", "form_lon_field_pname");

        slcb.doIlPSimple2("formLon_pkey", "form_lon_pkey");
        slcb.doEQPSimple2("formLon_pkey", "form_lon_pkey");
        slcb.doInLongCondition("formLon_id", "form_lon_id");
        // //FormLon 1
        slcb.doIlPSimple2("formLon_pname", "form_lon_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  form_lon_field.id as form_lon_field_id,form_lon_field.pkey as form_lon_field_pkey,form_lon_field.pname as form_lon_field_pname,form_lon.id as form_lon_id,form_lon.pkey as form_lon_pkey,form_lon.pname as form_lon_pname   FROM   form_lon_field,  form_lon as form_lon   WHERE  form_lon_field.form_lon_id = form_lon.id";
    private static String sqlCount = "SELECT  count(form_lon_field.id)   FROM   form_lon_field,  form_lon as form_lon   WHERE  form_lon_field.form_lon_id = form_lon.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
