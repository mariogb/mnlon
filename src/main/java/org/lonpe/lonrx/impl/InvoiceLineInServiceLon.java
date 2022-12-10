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
public class InvoiceLineInServiceLon extends AbstractLon<InvoiceLineIn> implements IServiceLon<InvoiceLineIn> {

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

        DCModel dcm = new DCModel("invoiceLineIn");

        //Create property pkey   
        final SProperty pkey = new SProperty("pkey", STRING, true, true);

        dcm.addSProperty(pkey);             //Create property askQuantity   

        final SProperty askQuantity = new SProperty("askQuantity", BIGDECIMAL, true, false);

        dcm.addSProperty(askQuantity);             //Create property createdDate   

        final SProperty createdDate = new SProperty("createdDate", LOCALDATETIME, true, false);

//Set by system
        createdDate.setSetBySys("now");
        dcm.addSProperty(createdDate);             //Create property descount   

        final SProperty descount = new SProperty("descount", BIGDECIMAL, true, false);

        dcm.addSProperty(descount);             //Create property invoiceDate   

        final SProperty invoiceDate = new SProperty("invoiceDate", LOCALDATETIME, false, false);

        dcm.addSProperty(invoiceDate);             //Create property orden   

        final SProperty orden = new SProperty("orden", INTEGER, true, false);

        dcm.addSProperty(orden);             //Create property status   

        final SProperty status = new SProperty("status", STRING, true, false);

        final List<String> statusInList = new ArrayList<>();

        statusInList.add("PENDENT");
        statusInList.add("SUPPLIED");
        statusInList.add("CANCEL");
        status.setInList(statusInList);
        dcm.addSProperty(status);             //Create property supplyDate   

        final SProperty supplyDate = new SProperty("supplyDate", LOCALDATETIME, false, false);

        dcm.addSProperty(supplyDate);             //Create property supplyQuantity   

        final SProperty supplyQuantity = new SProperty("supplyQuantity", BIGDECIMAL, true, false);

        dcm.addSProperty(supplyQuantity);             //Create property taxPorcent   

        final SProperty taxPorcent = new SProperty("taxPorcent", BIGDECIMAL, true, false);

        dcm.addSProperty(taxPorcent);             //Create property total   

        final SProperty total = new SProperty("total", BIGDECIMAL, true, false);

        dcm.addSProperty(total);             //Create property totalCost   

        final SProperty totalCost = new SProperty("totalCost", BIGDECIMAL, true, false);

        dcm.addSProperty(totalCost);             //Create property unitCost   

        final SProperty unitCost = new SProperty("unitCost", BIGDECIMAL, true, false);

        dcm.addSProperty(unitCost);

        //1
        final SMto comercialDocument = new SMto("comercialDocument", "comercialDocumentOut");
        comercialDocument.setPc("pname");
        dcm.addSMto(comercialDocument);
//1
        final SMto product = new SMto("product", "product");
        product.setPc("pname");
        dcm.addSMto(product);
//1
        final SMto stockRackProduct = new SMto("stockRackProduct", "stockRackProduct");
        stockRackProduct.setPc("pname");
        dcm.addSMto(stockRackProduct);

        return dcm;

    }

    @PostConstruct
    private void init0() {
        this.dCModel = elModelo();
        insertReturnMapFields.put("id", "id");
        insertReturnMapFields.put("pkey", "pkey");

        //ID ----------------------------------
        names.add("id");
        sortMapFields.put("id", "invoice_line_in_id");
//pkey -------------------------------------------
        names.add("pkey");
        insertMapFields.put("invoice_line_in.pkey", "pkey");
//Used to map error on index to source property
        insertMapFields.put("invoice_line_in.invoice_line_in_uidx_pkey", "pkey");
        sortMapFields.put("pkey", "invoice_line_in_pkey");

//askQuantity -------------------------------------------
        names.add("askQuantity");
        insertMapFields.put("invoice_line_in.ask_quantity", "askQuantity");
        sortMapFields.put("askQuantity", "invoice_line_in_ask_quantity");

//createdDate -------------------------------------------
        names.add("createdDate");
        insertMapFields.put("invoice_line_in.created_date", "createdDate");
        sortMapFields.put("createdDate", "invoice_line_in_created_date");

//descount -------------------------------------------
        names.add("descount");
        insertMapFields.put("invoice_line_in.descount", "descount");
        sortMapFields.put("descount", "invoice_line_in_descount");

//invoiceDate -------------------------------------------
        names.add("invoiceDate");
        insertMapFields.put("invoice_line_in.invoice_date", "invoiceDate");
        sortMapFields.put("invoiceDate", "invoice_line_in_invoice_date");

//orden -------------------------------------------
        names.add("orden");
        insertMapFields.put("invoice_line_in.orden", "orden");
        sortMapFields.put("orden", "invoice_line_in_orden");

//status -------------------------------------------
        names.add("status");
        insertMapFields.put("invoice_line_in.status", "status");
//supplyDate -------------------------------------------
        names.add("supplyDate");
        insertMapFields.put("invoice_line_in.supply_date", "supplyDate");
        sortMapFields.put("supplyDate", "invoice_line_in_supply_date");

//supplyQuantity -------------------------------------------
        names.add("supplyQuantity");
        insertMapFields.put("invoice_line_in.supply_quantity", "supplyQuantity");
        sortMapFields.put("supplyQuantity", "invoice_line_in_supply_quantity");

//taxPorcent -------------------------------------------
        names.add("taxPorcent");
        insertMapFields.put("invoice_line_in.tax_porcent", "taxPorcent");
        sortMapFields.put("taxPorcent", "invoice_line_in_tax_porcent");

//total -------------------------------------------
        names.add("total");
        insertMapFields.put("invoice_line_in.total", "total");
        sortMapFields.put("total", "invoice_line_in_total");

//totalCost -------------------------------------------
        names.add("totalCost");
        insertMapFields.put("invoice_line_in.total_cost", "totalCost");
        sortMapFields.put("totalCost", "invoice_line_in_total_cost");

//unitCost -------------------------------------------
        names.add("unitCost");
        insertMapFields.put("invoice_line_in.unit_cost", "unitCost");
        sortMapFields.put("unitCost", "invoice_line_in_unit_cost");
        // comercialDocument --------------------
        names.add("comercialDocument_id");

        insertMapFields.put("invoice_line_in.comercial_document_id", "comercialDocument_id");

        names.add("comercialDocument_pkey");
        sortMapFields.put("comercialDocument_pkey", "comercial_document_pkey");

        names.add("comercialDocument_pname");
        sortMapFields.put("comercialDocument_pname", "comercial_document_pname");
        //   contract 
        names.add("contract_id");

        names.add("contract_pkey");
        names.add("contract_pname");//   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");

        names.add("departamentBaseTimePeriod_pkey");//   thirdPerson 
        names.add("thirdPerson_id");

        names.add("thirdPerson_pkey");
        names.add("thirdPerson_pname");//   userAutor 
        names.add("userAutor_id");

        names.add("userAutor_pkey");
        names.add("userAutor_pname");//   comercialDocumentType 
        names.add("comercialDocumentType_id");

        names.add("comercialDocumentType_pkey");
        names.add("comercialDocumentType_pname");// product --------------------
        names.add("product_id");

        insertMapFields.put("invoice_line_in.product_id", "product_id");

        names.add("product_pkey");
        sortMapFields.put("product_pkey", "product_pkey");

        names.add("product_pname");
        sortMapFields.put("product_pname", "product_pname");
        //   productType 
        names.add("productType_id");

        names.add("productType_pkey");
        names.add("productType_pname");// stockRackProduct --------------------
        names.add("stockRackProduct_id");

        insertMapFields.put("invoice_line_in.stock_rack_product_id", "stockRackProduct_id");

        names.add("stockRackProduct_pkey");
        sortMapFields.put("stockRackProduct_pkey", "stock_rack_product_pkey");

        names.add("stockRackProduct_pname");
        sortMapFields.put("stockRackProduct_pname", "stock_rack_product_pname");
        //   stockRack 
        names.add("stockRack_id");

        names.add("stockRack_pkey");
        names.add("stockRack_pname");//   workSpace 
        names.add("workSpace_id");

        names.add("workSpace_pkey");
        names.add("workSpace_pname");//   product 
        names.add("product_id");

        names.add("product_pkey");
        names.add("product_pname");//   productType 
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

    protected void fillTupleInsert(final InvoiceLineIn dc0, final Tuple t) {
        t.addString(dc0.getPkey());
        t.addBigDecimal(dc0.getAskQuantity());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addBigDecimal(dc0.getDescount());
        t.addLocalDateTime(dc0.getInvoiceDate());
        t.addInteger(dc0.getOrden());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
        t.addBigDecimal(dc0.getSupplyQuantity());
        t.addBigDecimal(dc0.getTaxPorcent());
        t.addBigDecimal(dc0.getTotal());
        t.addBigDecimal(dc0.getTotalCost());
        t.addBigDecimal(dc0.getUnitCost());

        if (dc0.getComercialDocument() != null) {
            t.addLong(dc0.getComercialDocument().getId());
        }

        if (dc0.getProduct() != null) {
            t.addLong(dc0.getProduct().getId());
        }

        if (dc0.getStockRackProduct() != null) {
            t.addLong(dc0.getStockRackProduct().getId());
        }
    }

    private static final String SQLINSERT = "INSERT INTO invoice_line_in(pkey,ask_quantity,created_date,descount,invoice_date,orden,status,supply_date,supply_quantity,tax_porcent,total,total_cost,unit_cost,comercial_document_id,product_id,stock_rack_product_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16,(select nextval('seq_invoice_line_in'))) returning id,pkey";

    @Override
    public Single<Map<String, Object>> save(InvoiceLineIn invoiceLineIn) {
        final Tuple tuple = Tuple.tuple();
        fillTupleInsert(invoiceLineIn, tuple);
        return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
    }

    @Override
    public Single<Map<String, Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "invoice_line_in", objForQuery);

    }

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple) {
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields, tuple.getDelegate());
        //*---PKEY ---

        slcb.doIlPSimple2("pkey", "invoice_line_in_pkey");
        slcb.doEqInPSimple("pkey", "invoice_line_in_pkey");
        slcb.doGEPSimpleInt("orden", "invoice_line_in_orden");
        slcb.doLTPSimpleInt("orden", "invoice_line_in_orden");
        slcb.doEqInPSimple("status", "invoice_line_in_status");

        slcb.doIlPSimple2("comercialDocument_pkey", "comercial_document_pkey");
        slcb.doEQPSimple2("comercialDocument_pkey", "comercial_document_pkey");
        slcb.doInLongCondition("comercialDocument_id", "comercial_document_id");
        // //ComercialDocumentOut 4
        slcb.doIlPSimple2("comercialDocument_pname", "comercial_document_pname");

        slcb.doIlPSimple2("product_pkey", "product_pkey");
        slcb.doEQPSimple2("product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");
        // //Product 3
        slcb.doIlPSimple2("product_pname", "product_pname");

        slcb.doIlPSimple2("stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doEQPSimple2("stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doInLongCondition("stockRackProduct_id", "stock_rack_product_id");
        // //StockRackProduct 1
        slcb.doIlPSimple2("stockRackProduct_pname", "stock_rack_product_pname");

        slcb.doIlPSimple2("contract_pkey", "contract_out_pkey");
        slcb.doEQPSimple2("contract_pkey", "contract_out_pkey");
        slcb.doInLongCondition("contract_id", "contract_out_id");
//ContractOut 1

        slcb.doIlPSimple2("contract_pname", "contract_out_pname");

        slcb.doIlPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2("departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");

        slcb.doIlPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2("thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");

        slcb.doIlPSimple2("userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2("userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");
//UserLon 4

        slcb.doIlPSimple2("userAutor_pname", "user_lon_pname");

        slcb.doIlPSimple2("comercialDocumentType_pkey", "comercial_document_type_out_pkey");
        slcb.doEQPSimple2("comercialDocumentType_pkey", "comercial_document_type_out_pkey");
        slcb.doInLongCondition("comercialDocumentType_id", "comercial_document_type_out_id");
//ComercialDocumentTypeOut 2

        slcb.doIlPSimple2("comercialDocumentType_pname", "comercial_document_type_out_pname");

        slcb.doIlPSimple2("productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2("productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");
//ProductType 5

        slcb.doIlPSimple2("productType_pname", "product_type_pname");

        slcb.doIlPSimple2("stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2("stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");
//StockRack 2

        slcb.doIlPSimple2("stockRack_pname", "stock_rack_pname");

        slcb.doIlPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2("workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");
        return slcb.getConditionInfo();
    }

    public Set<String> getNames() {
        return names;
    }

    private static String sqlList = "SELECT  invoice_line_in.id as invoice_line_in_id,invoice_line_in.pkey as invoice_line_in_pkey,invoice_line_in.ask_quantity as invoice_line_in_ask_quantity,invoice_line_in.created_date as invoice_line_in_created_date,invoice_line_in.descount as invoice_line_in_descount,invoice_line_in.invoice_date as invoice_line_in_invoice_date,invoice_line_in.orden as invoice_line_in_orden,invoice_line_in.status as invoice_line_in_status,invoice_line_in.supply_date as invoice_line_in_supply_date,invoice_line_in.supply_quantity as invoice_line_in_supply_quantity,invoice_line_in.tax_porcent as invoice_line_in_tax_porcent,invoice_line_in.total as invoice_line_in_total,invoice_line_in.total_cost as invoice_line_in_total_cost,invoice_line_in.unit_cost as invoice_line_in_unit_cost,comercial_document.id as comercial_document_id,comercial_document.pkey as comercial_document_pkey,comercial_document.pname as comercial_document_pname,contract.id as contract_id, contract.pkey as contract_pkey,contract.pname as contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,comercial_document_type.id as comercial_document_type_id, comercial_document_type.pkey as comercial_document_type_pkey,comercial_document_type.pname as comercial_document_type_pname,product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname   FROM   invoice_line_in,  comercial_document_out as comercial_document,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type,  product as product,  product_type as product_type,  stock_rack_product as stock_rack_product,  stock_rack as stock_rack,  work_space as work_space   WHERE  invoice_line_in.comercial_document_id = comercial_document.id AND comercial_document.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document.user_autor_id = user_autor.id AND comercial_document.comercial_document_type_id = comercial_document_type.id AND invoice_line_in.product_id = product.id AND product.product_type_id = product_type.id AND invoice_line_in.stock_rack_product_id = stock_rack_product.id AND stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id";
    private static String sqlCount = "SELECT  count(invoice_line_in.id)   FROM   invoice_line_in,  comercial_document_out as comercial_document,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  third_person as third_person,  user_lon as user_autor,  comercial_document_type_out as comercial_document_type,  product as product,  product_type as product_type,  stock_rack_product as stock_rack_product,  stock_rack as stock_rack,  work_space as work_space   WHERE  invoice_line_in.comercial_document_id = comercial_document.id AND comercial_document.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND contract.third_person_id = third_person.id AND comercial_document.user_autor_id = user_autor.id AND comercial_document.comercial_document_type_id = comercial_document_type.id AND invoice_line_in.product_id = product.id AND product.product_type_id = product_type.id AND invoice_line_in.stock_rack_product_id = stock_rack_product.id AND stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id";

    @Override
    public String getSqlForList() {
        return sqlList;
    }

    @Override
    public String getSqlForCount() {
        return sqlCount;
    }

}
