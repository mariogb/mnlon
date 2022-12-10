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
public class UserLonServiceLon extends AbstractLon<UserLon> implements IServiceLon<UserLon> {

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

        DCModel dcm = new DCModel("userLon", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property email   

        final SProperty email = new SProperty("email", STRING, true, false);

// hasIndex 
        email.setWithIndex(true);
        dcm.addSProperty(email);             //Create property enabled   

        final SProperty enabled = new SProperty("enabled", BOOLEAN, true, false);

        dcm.addSProperty(enabled);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);             //Create property typeLon   

        final SProperty typeLon = new SProperty("typeLon", STRING, true, false);

        final List<String> typeLonInList = new ArrayList<>();

        typeLonInList.add("ADM");
        typeLonInList.add("SUBADM");
        typeLonInList.add("AGENT");
        typeLonInList.add("THIRD");
        typeLon.setInList(typeLonInList);
        dcm.addSProperty(typeLon);             //Create property username   

        final SProperty username = new SProperty("username", STRING, true, false);

// hasIndex 
        username.setWithIndex(true);
        dcm.addSProperty(username);

//Add password field type
        final SProperty password = new SProperty("password", "String", true, false);
        dcm.addSPropertyPW(password);

        final SOtm departamentUserLons = new SOtm("departamentUserLons", "departamentUserLon", "undefined");
        dcm.addSOtm(departamentUserLons);
        final SOtm programUserLons = new SOtm("programUserLons", "programUserLon", "undefined");
        dcm.addSOtm(programUserLons);
        final SOtm baseUserLons = new SOtm("baseUserLons", "baseUserLon", "undefined");
        dcm.addSOtm(baseUserLons);
        final SOtm comercialDocumentIns = new SOtm("comercialDocumentIns", "comercialDocumentIn", "undefined");
        dcm.addSOtm(comercialDocumentIns);
        final SOtm comercialDocumentOuts = new SOtm("comercialDocumentOuts", "comercialDocumentOut", "undefined");
        dcm.addSOtm(comercialDocumentOuts);
        final SOtm userRoles = new SOtm("userRoles", "userRole", "undefined");
        dcm.addSOtm(userRoles);
        final SOtm userThirdPersons = new SOtm("userThirdPersons", "userThirdPerson", "undefined");
        dcm.addSOtm(userThirdPersons);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "user_lon_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("user_lon.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("user_lon.user_lon_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "user_lon_pkey");

//email -------------------------------------------
        names.add("email");
        insertMapFields.put("user_lon.email", "email");
        sortMapFields.put("email", "user_lon_email");

//enabled -------------------------------------------
        names.add("enabled");
        insertMapFields.put("user_lon.enabled", "enabled");
        sortMapFields.put("enabled", "user_lon_enabled");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("user_lon.pname", "pname");
        sortMapFields.put("pname", "user_lon_pname");

//typeLon -------------------------------------------
        names.add("typeLon");
        insertMapFields.put("user_lon.type_lon", "typeLon");
        sortMapFields.put("typeLon", "user_lon_type_lon");

//username -------------------------------------------
        names.add("username");
        insertMapFields.put("user_lon.username", "username");
        sortMapFields.put("username", "user_lon_username");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final UserLon dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getEmail());
        t.addBoolean(dc0.getEnabled());
        t.addString(dc0.getPassword());
        t.addString(dc0.getPname());
        t.addString(dc0.getTypeLon());
        t.addString(dc0.getUsername());
    }

    private static final String SQLINSERT = "INSERT INTO user_lon(pkey,email,enabled,password,pname,type_lon,username,id) VALUES ($1,$2,$3,$4,$5,$6,$7,(select nextval('seq_user_lon'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(UserLon userLon) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(userLon, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "user_lon", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "user_lon_pkey");
        slcb.doEqInPSimple("pkey", "user_lon_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "user_lon_pname");
        slcb.doEqInPSimple("pname", "user_lon_pname");
// withIndex true
        slcb.doIlPSimple2("email", "user_lon_email");
        slcb.doEqInPSimple("email", "user_lon_email");
        slcb.doEqInPSimple("typeLon", "user_lon_type_lon");
// withIndex true
        slcb.doIlPSimple2("username", "user_lon_username");
        slcb.doEqInPSimple("username", "user_lon_username");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.email as user_lon_email,user_lon.enabled as user_lon_enabled,user_lon.pname as user_lon_pname,user_lon.type_lon as user_lon_type_lon,user_lon.username as user_lon_username   FROM   user_lon ";
    private static String sqlCount = "SELECT  count(user_lon.id)   FROM   user_lon ";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
