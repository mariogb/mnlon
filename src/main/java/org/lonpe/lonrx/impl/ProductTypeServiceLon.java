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
public class ProductTypeServiceLon extends AbstractLon<ProductType> implements IServiceLon<ProductType> {

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

        DCModel dcm = new DCModel("productType", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property afectStock   

        final SProperty afectStock = new SProperty("afectStock", BOOLEAN, true, false);

        dcm.addSProperty(afectStock);             //Create property description   

        final SProperty description = new SProperty("description", STRING, false, false);

        dcm.addSProperty(description);             //Create property fastKey   

        final SProperty fastKey = new SProperty("fastKey", STRING, false, false);

// hasIndex 
        fastKey.setWithIndex(true);
        dcm.addSProperty(fastKey);             //Create property isService   

        final SProperty isService = new SProperty("isService", BOOLEAN, true, false);

        dcm.addSProperty(isService);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);             //Create property taxable   

        final SProperty taxable = new SProperty("taxable", BOOLEAN, true, false);

        dcm.addSProperty(taxable);             //Create property withSerialNumber   

        final SProperty withSerialNumber = new SProperty("withSerialNumber", BOOLEAN, true, false);

        dcm.addSProperty(withSerialNumber);

        final SOtm products = new SOtm("products", "product", "undefined");
        dcm.addSOtm(products);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "product_type_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("product_type.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("product_type.product_type_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "product_type_pkey");

//afectStock -------------------------------------------
        names.add("afectStock");
        insertMapFields.put("product_type.afect_stock", "afectStock");
        sortMapFields.put("afectStock", "product_type_afect_stock");

//description -------------------------------------------
        names.add("description");
        insertMapFields.put("product_type.description", "description");
//fastKey -------------------------------------------
        names.add("fastKey");
        insertMapFields.put("product_type.fast_key", "fastKey");
//isService -------------------------------------------
        names.add("isService");
        insertMapFields.put("product_type.is_service", "isService");
        sortMapFields.put("isService", "product_type_is_service");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("product_type.pname", "pname");
        sortMapFields.put("pname", "product_type_pname");

//taxable -------------------------------------------
        names.add("taxable");
        insertMapFields.put("product_type.taxable", "taxable");
        sortMapFields.put("taxable", "product_type_taxable");

//withSerialNumber -------------------------------------------
        names.add("withSerialNumber");
        insertMapFields.put("product_type.with_serial_number", "withSerialNumber");
        sortMapFields.put("withSerialNumber", "product_type_with_serial_number");

    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ProductType dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addBoolean(dc0.getAfectStock());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addBoolean(dc0.getIsService());
        t.addString(dc0.getPname());
        t.addBoolean(dc0.getTaxable());
        t.addBoolean(dc0.getWithSerialNumber());
    }

    private static final String SQLINSERT = "INSERT INTO product_type(pkey,afect_stock,description,fast_key,is_service,pname,taxable,with_serial_number,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,(select nextval('seq_product_type'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(ProductType productType) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(productType, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "product_type", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "product_type_pkey");
        slcb.doEqInPSimple("pkey", "product_type_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "product_type_pname");
        slcb.doEqInPSimple("pname", "product_type_pname");
// withIndex true
        slcb.doIlPSimple2("fastKey", "product_type_fast_key");
        slcb.doEqInPSimple("fastKey", "product_type_fast_key");

        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  product_type.id as product_type_id,product_type.pkey as product_type_pkey,product_type.afect_stock as product_type_afect_stock,product_type.description as product_type_description,product_type.fast_key as product_type_fast_key,product_type.is_service as product_type_is_service,product_type.pname as product_type_pname,product_type.taxable as product_type_taxable,product_type.with_serial_number as product_type_with_serial_number   FROM   product_type ";
    private static String sqlCount = "SELECT  count(product_type.id)   FROM   product_type ";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
