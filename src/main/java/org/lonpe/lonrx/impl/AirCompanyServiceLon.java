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
public class AirCompanyServiceLon extends AbstractLon<AirCompany> implements IServiceLon<AirCompany> {

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

        DCModel dcm = new DCModel("airCompany", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

//ON RELATION laCompania    
        final SOtm planes = new SOtm("planes", "plane", "laCompania");
        dcm.addSOtm(planes);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "air_company_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("air_company.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("air_company.air_company_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "air_company_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("air_company.pname", "pname");
        sortMapFields.put("pname", "air_company_pname");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final AirCompany dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
    }

    private static final String SQLINSERT = "INSERT INTO air_company(pkey,pname,id) VALUES ($1,$2,(select nextval('seq_air_company'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(AirCompany airCompany) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(airCompany, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "air_company", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "air_company_pkey");
        slcb.doEqInPSimple("pkey", "air_company_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "air_company_pname");
        slcb.doEqInPSimple("pname", "air_company_pname");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  air_company.id as air_company_id,air_company.pkey as air_company_pkey,air_company.pname as air_company_pname   FROM   air_company ";
    private static String sqlCount = "SELECT  count(air_company.id)   FROM   air_company ";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
