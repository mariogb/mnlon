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
public class MeUsrInterfaceServiceLon extends AbstractLon<MeUsrInterface> implements IServiceLon<MeUsrInterface> {

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

        DCModel dcm = new DCModel("meUsrInterface", "label");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property dc   

        final SProperty dc = new SProperty("dc", STRING, true, false);

        dcm.addSProperty(dc);             //Create property label   

        final SProperty label = new SProperty("label", STRING, true, false);

//PC
        dcm.setPc("label");
        dcm.addSProperty(label);             //Create property level   

        final SProperty level = new SProperty("level", INTEGER, true, false);

        dcm.addSProperty(level);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "me_usr_interface_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("me_usr_interface.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("me_usr_interface.me_usr_interface_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "me_usr_interface_pkey");

//dc -------------------------------------------
        names.add("dc");
        insertMapFields.put("me_usr_interface.dc", "dc");
//label -------------------------------------------
        names.add("label");
        insertMapFields.put("me_usr_interface.label", "label");
        sortMapFields.put("label", "me_usr_interface_label");

//level -------------------------------------------
        names.add("level");
        insertMapFields.put("me_usr_interface.level", "level");
        sortMapFields.put("level", "me_usr_interface_level");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final MeUsrInterface dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getDc());
        t.addString(dc0.getLabel());
        t.addInteger(dc0.getLevel());
    }

    private static final String SQLINSERT = "INSERT INTO me_usr_interface(pkey,dc,label,level,id) VALUES ($1,$2,$3,$4,(select nextval('seq_me_usr_interface'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(MeUsrInterface meUsrInterface) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(meUsrInterface, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "me_usr_interface", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "me_usr_interface_pkey");
        slcb.doEqInPSimple("pkey", "me_usr_interface_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("label", "me_usr_interface_label");
        slcb.doEqInPSimple("label", "me_usr_interface_label");
        slcb.doGEPSimpleInt("level", "me_usr_interface_level");
        slcb.doLTPSimpleInt("level", "me_usr_interface_level");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  me_usr_interface.id as me_usr_interface_id,me_usr_interface.pkey as me_usr_interface_pkey,me_usr_interface.dc as me_usr_interface_dc,me_usr_interface.label as me_usr_interface_label,me_usr_interface.level as me_usr_interface_level   FROM   me_usr_interface ";
    private static String sqlCount = "SELECT  count(me_usr_interface.id)   FROM   me_usr_interface ";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
