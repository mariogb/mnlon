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
public class EntityPermisionRoleServiceLon extends AbstractLon<EntityPermisionRole> implements IServiceLon<EntityPermisionRole> {

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

        DCModel dcm = new DCModel("entityPermisionRole");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property enabled   

        final SProperty enabled = new SProperty("enabled", BOOLEAN, true, false);

        dcm.addSProperty(enabled);             //Create property nombre   

        final SProperty nombre = new SProperty("nombre", STRING, true, false);

        dcm.addSProperty(nombre);             //Create property permission   

        final SProperty permission = new SProperty("permission", STRING, true, false);

        final List<String> permissionInList = new ArrayList<>();

        permissionInList.add("BROWSE");
        permissionInList.add("LIST");
        permissionInList.add("SAVE");
        permissionInList.add("UPDATE");
        permissionInList.add("DEL");
        permissionInList.add("LZTAT");
        permissionInList.add("EXXSLX");
        permissionInList.add("IMPORTEXCEL");
        permissionInList.add("TEMPLATEEXCEL");
        permission.setInList(permissionInList);
        dcm.addSProperty(permission);

        //1
        final SMto role = new SMto("role", "role");
        role.setPc("pname");
        dcm.addSMto(role);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "entity_permision_role_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("entity_permision_role.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("entity_permision_role.entity_permision_role_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "entity_permision_role_pkey");

//enabled -------------------------------------------
        names.add("enabled");
        insertMapFields.put("entity_permision_role.enabled", "enabled");
        sortMapFields.put("enabled", "entity_permision_role_enabled");

//nombre -------------------------------------------
        names.add("nombre");
        insertMapFields.put("entity_permision_role.nombre", "nombre");
        sortMapFields.put("nombre", "entity_permision_role_nombre");

//permission -------------------------------------------
        names.add("permission");
        insertMapFields.put("entity_permision_role.permission", "permission");
        sortMapFields.put("permission", "entity_permision_role_permission");
        // role --------------------
        names.add("role_id");

        insertMapFields.put("entity_permision_role.role_id", "role_id");

        names.add("role_pkey");
        sortMapFields.put("role_pkey", "role_pkey");

        names.add("role_pname");
        sortMapFields.put("role_pname", "role_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final EntityPermisionRole dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addBoolean(dc0.getEnabled());
        t.addString(dc0.getNombre());
        t.addString(dc0.getPermission());

        if (dc0.getRole() != null) {
            t.addLong(dc0.getRole().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO entity_permision_role(pkey,enabled,nombre,permission,role_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_entity_permision_role'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(EntityPermisionRole entityPermisionRole) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(entityPermisionRole, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "entity_permision_role", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "entity_permision_role_pkey");
        slcb.doEqInPSimple("pkey", "entity_permision_role_pkey");
        slcb.doEqInPSimple("permission", "entity_permision_role_permission");

        slcb.doIlPSimple2("role_pkey", "role_pkey");
        slcb.doEQPSimple2("role_pkey", "role_pkey");
        slcb.doInLongCondition("role_id", "role_id");
        // //Role 1
        slcb.doIlPSimple2("role_pname", "role_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  entity_permision_role.id as entity_permision_role_id,entity_permision_role.pkey as entity_permision_role_pkey,entity_permision_role.enabled as entity_permision_role_enabled,entity_permision_role.nombre as entity_permision_role_nombre,entity_permision_role.permission as entity_permision_role_permission,role.id as role_id,role.pkey as role_pkey,role.pname as role_pname   FROM   entity_permision_role,  role as role   WHERE  entity_permision_role.role_id = role.id";
    private static String sqlCount = "SELECT  count(entity_permision_role.id)   FROM   entity_permision_role,  role as role   WHERE  entity_permision_role.role_id = role.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
