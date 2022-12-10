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
public class FligthServiceLon extends AbstractLon<Fligth> implements IServiceLon<Fligth> {

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

        DCModel dcm = new DCModel("fligth", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);

        //1
        final SMto fromAirport = new SMto("fromAirport", "airport");
        fromAirport.setPc("pname");
        dcm.addSMto(fromAirport);
//1
        final SMto toAirport = new SMto("toAirport", "airport");
        toAirport.setPc("pname");
        dcm.addSMto(toAirport);
//1
        final SMto plane = new SMto("plane", "plane");
        plane.setPc("pname");
        dcm.addSMto(plane);

//ON RELATION theFligth    
        final SOtm fligthInstances = new SOtm("fligthInstances", "fligthInstance", "theFligth");
        dcm.addSOtm(fligthInstances);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "fligth_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("fligth.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("fligth.fligth_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "fligth_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("fligth.pname", "pname");
        sortMapFields.put("pname", "fligth_pname");
        // fromAirport --------------------
        names.add("fromAirport_id");

        insertMapFields.put("fligth.from_airport_id", "fromAirport_id");

        names.add("fromAirport_pkey");
        sortMapFields.put("fromAirport_pkey", "from_airport_pkey");

        names.add("fromAirport_pname");
        sortMapFields.put("fromAirport_pname", "from_airport_pname");
        // toAirport --------------------
        names.add("toAirport_id");

        insertMapFields.put("fligth.to_airport_id", "toAirport_id");

        names.add("toAirport_pkey");
        sortMapFields.put("toAirport_pkey", "to_airport_pkey");

        names.add("toAirport_pname");
        sortMapFields.put("toAirport_pname", "to_airport_pname");
        // plane --------------------
        names.add("plane_id");

        insertMapFields.put("fligth.plane_id", "plane_id");

        names.add("plane_pkey");
        sortMapFields.put("plane_pkey", "plane_pkey");

        names.add("plane_pname");
        sortMapFields.put("plane_pname", "plane_pname");
        //   laCompania 
        names.add("laCompania_id");

        names.add("laCompania_pkey");
        names.add("laCompania_pname");
    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Fligth dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());

        if (dc0.getFromAirport() != null) {
            t.addLong(dc0.getFromAirport().getId());
        }

        if (dc0.getToAirport() != null) {
            t.addLong(dc0.getToAirport().getId());
        }

        if (dc0.getPlane() != null) {
            t.addLong(dc0.getPlane().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO fligth(pkey,pname,from_airport_id,to_airport_id,plane_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_fligth'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(Fligth fligth) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(fligth, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "fligth", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "fligth_pkey");
        slcb.doEqInPSimple("pkey", "fligth_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "fligth_pname");
        slcb.doEqInPSimple("pname", "fligth_pname");

        slcb.doIlPSimple2("fromAirport_pkey", "from_airport_pkey");
        slcb.doEQPSimple2("fromAirport_pkey", "from_airport_pkey");
        slcb.doInLongCondition("fromAirport_id", "from_airport_id");
        // //Airport 1
        slcb.doIlPSimple2("fromAirport_pname", "from_airport_pname");

        slcb.doIlPSimple2("toAirport_pkey", "to_airport_pkey");
        slcb.doEQPSimple2("toAirport_pkey", "to_airport_pkey");
        slcb.doInLongCondition("toAirport_id", "to_airport_id");
        // //Airport 1
        slcb.doIlPSimple2("toAirport_pname", "to_airport_pname");

        slcb.doIlPSimple2("plane_pkey", "plane_pkey");
        slcb.doEQPSimple2("plane_pkey", "plane_pkey");
        slcb.doInLongCondition("plane_id", "plane_id");
        // //Plane 2
        slcb.doIlPSimple2("plane_pname", "plane_pname");

        slcb.doIlPSimple2("laCompania_pkey", "air_company_pkey");
        slcb.doEQPSimple2("laCompania_pkey", "air_company_pkey");
        slcb.doInLongCondition("laCompania_id", "air_company_id");
//AirCompany 1

        slcb.doIlPSimple2("laCompania_pname", "air_company_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  fligth.id as fligth_id,fligth.pkey as fligth_pkey,fligth.pname as fligth_pname,from_airport.id as from_airport_id,from_airport.pkey as from_airport_pkey,from_airport.pname as from_airport_pname,to_airport.id as to_airport_id,to_airport.pkey as to_airport_pkey,to_airport.pname as to_airport_pname,plane.id as plane_id,plane.pkey as plane_pkey,plane.pname as plane_pname,la_compania.id as la_compania_id, la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname   FROM   fligth,  airport as from_airport,  airport as to_airport,  plane as plane,  air_company as la_compania   WHERE  fligth.from_airport_id = from_airport.id AND fligth.to_airport_id = to_airport.id AND fligth.plane_id = plane.id AND plane.la_compania_id = la_compania.id";
    private static String sqlCount = "SELECT  count(fligth.id)   FROM   fligth,  airport as from_airport,  airport as to_airport,  plane as plane,  air_company as la_compania   WHERE  fligth.from_airport_id = from_airport.id AND fligth.to_airport_id = to_airport.id AND fligth.plane_id = plane.id AND plane.la_compania_id = la_compania.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
