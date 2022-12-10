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
public class StockRackProductServiceLon extends AbstractLon<StockRackProduct> implements IServiceLon<StockRackProduct> {

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

        DCModel dcm = new DCModel("stockRackProduct", "pname");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property pname   

        final SProperty pname = new SProperty("pname", STRING, true, false);

//PC
        dcm.setPc("pname");
        dcm.addSProperty(pname);             //Create property quantity   

        final SProperty quantity = new SProperty("quantity", LONG, true, false);

        dcm.addSProperty(quantity);             //Create property serialNumber   

        final SProperty serialNumber = new SProperty("serialNumber", STRING, false, false);

        dcm.addSProperty(serialNumber);

        //1
        final SMto stockRack = new SMto("stockRack", "stockRack");
        stockRack.setPc("pname");
        dcm.addSMto(stockRack);
//1
        final SMto product = new SMto("product", "product");
        product.setPc("pname");
        dcm.addSMto(product);

        final SOtm InvoiceLineIns = new SOtm("InvoiceLineIns", "invoiceLineIn", "undefined");
        dcm.addSOtm(InvoiceLineIns);
        final SOtm InvoiceLineOuts = new SOtm("InvoiceLineOuts", "invoiceLineOut", "undefined");
        dcm.addSOtm(InvoiceLineOuts);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "stock_rack_product_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("stock_rack_product.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("stock_rack_product.stock_rack_product_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "stock_rack_product_pkey");

//pname -------------------------------------------
        names.add("pname");
        insertMapFields.put("stock_rack_product.pname", "pname");
        sortMapFields.put("pname", "stock_rack_product_pname");

//quantity -------------------------------------------
        names.add("quantity");
        insertMapFields.put("stock_rack_product.quantity", "quantity");
        sortMapFields.put("quantity", "stock_rack_product_quantity");

//serialNumber -------------------------------------------
        names.add("serialNumber");
        insertMapFields.put("stock_rack_product.serial_number", "serialNumber");  // stockRack --------------------
        names.add("stockRack_id");

        insertMapFields.put("stock_rack_product.stock_rack_id", "stockRack_id");

        names.add("stockRack_pkey");
        sortMapFields.put("stockRack_pkey", "stock_rack_pkey");

        names.add("stockRack_pname");
        sortMapFields.put("stockRack_pname", "stock_rack_pname");
        //   workSpace 
        names.add("workSpace_id");

        names.add("workSpace_pkey");
        names.add("workSpace_pname");//   workSpaceGroup 
        names.add("workSpaceGroup_id");

        names.add("workSpaceGroup_pkey");
        names.add("workSpaceGroup_pname");// product --------------------
        names.add("product_id");

        insertMapFields.put("stock_rack_product.product_id", "product_id");

        names.add("product_pkey");
        sortMapFields.put("product_pkey", "product_pkey");

        names.add("product_pname");
        sortMapFields.put("product_pname", "product_pname");
        //   productType 
        names.add("productType_id");

        names.add("productType_pkey");
        names.add("productType_pname");
    }

    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String, String> insertMapFields = new HashMap<>();
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final StockRackProduct dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
        t.addLong(dc0.getQuantity());
        t.addString(dc0.getSerialNumber());

        if (dc0.getStockRack() != null) {
            t.addLong(dc0.getStockRack().getId());
        }

        if (dc0.getProduct() != null) {
            t.addLong(dc0.getProduct().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO stock_rack_product(pkey,pname,quantity,serial_number,stock_rack_id,product_id,id) VALUES ($1,$2,$3,$4,$5,$6,(select nextval('seq_stock_rack_product'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(StockRackProduct stockRackProduct) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(stockRackProduct, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "stock_rack_product", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "stock_rack_product_pkey");
        slcb.doEqInPSimple("pkey", "stock_rack_product_pkey");
//*---PC ---" + pc.n
        slcb.doIlPSimple2("pname", "stock_rack_product_pname");
        slcb.doEqInPSimple("pname", "stock_rack_product_pname");
        slcb.doGEPSimpleLong("quantity", "stock_rack_product_quantity");
        slcb.doLPSimpleLong("quantity", "stock_rack_product_quantity");

        slcb.doIlPSimple2("stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2("stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");
        // //StockRack 2
        slcb.doIlPSimple2("stockRack_pname", "stock_rack_pname");

        slcb.doIlPSimple2("product_pkey", "product_pkey");
        slcb.doEQPSimple2("product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");
        // //Product 3
        slcb.doIlPSimple2("product_pname", "product_pname");

        slcb.doIlPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");
//WorkSpace 2

        slcb.doIlPSimple2("workSpace_pname", "work_space_pname");

        slcb.doIlPSimple2("workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2("workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");

        slcb.doIlPSimple2("productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2("productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");
//ProductType 5

        slcb.doIlPSimple2("productType_pname", "product_type_pname");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,stock_rack_product.quantity as stock_rack_product_quantity,stock_rack_product.serial_number as stock_rack_product_serial_number,stock_rack.id as stock_rack_id,stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname   FROM   stock_rack_product,  stock_rack as stock_rack,  work_space as work_space,  work_space_group as work_space_group,  product as product,  product_type as product_type   WHERE  stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND stock_rack_product.product_id = product.id AND product.product_type_id = product_type.id";
    private static String sqlCount = "SELECT  count(stock_rack_product.id)   FROM   stock_rack_product,  stock_rack as stock_rack,  work_space as work_space,  work_space_group as work_space_group,  product as product,  product_type as product_type   WHERE  stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND stock_rack_product.product_id = product.id AND product.product_type_id = product_type.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
