
        
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
public class InvoiceLineOutServiceLon extends AbstractLon<InvoiceLineOut> implements IServiceLon<InvoiceLineOut>{  
        
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
    
    private DCModel elModelo(){
        
    
        DCModel dcm = new DCModel("invoiceLineOut");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property askDate   
                
        final SProperty askDate = new SProperty("askDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(askDate);             //Create property askQuantity   
                
        final SProperty askQuantity = new SProperty("askQuantity", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(askQuantity);             //Create property createdDate   
                
        final SProperty createdDate = new SProperty("createdDate", LOCALDATETIME,true,false);   
            
             
//Set by system
   createdDate.setSetBySys("now");   
    dcm.addSProperty(createdDate);             //Create property descountPorcent   
                
        final SProperty descountPorcent = new SProperty("descountPorcent", BIGDECIMAL,true,false);   
            
                            
        descountPorcent.setMin(0); 
        descountPorcent.setMax(100);  
    dcm.addSProperty(descountPorcent);             //Create property orden   
                
        final SProperty orden = new SProperty("orden", INTEGER,true,false);   
            
             
    dcm.addSProperty(orden);             //Create property price   
                
        final SProperty price = new SProperty("price", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(price);             //Create property status   
                
        final SProperty status = new SProperty("status", STRING,true,false);   
            
            
        final List<String> statusInList = new ArrayList<>();
                
        statusInList.add("PENDENT"); 
        statusInList.add("SUPPLIED"); 
        statusInList.add("CANCEL"); 
        status.setInList(statusInList);                 
    dcm.addSProperty(status);             //Create property supplyDate   
                
        final SProperty supplyDate = new SProperty("supplyDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(supplyDate);             //Create property supplyQuantity   
                
        final SProperty supplyQuantity = new SProperty("supplyQuantity", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(supplyQuantity);             //Create property taxPorcent   
                
        final SProperty taxPorcent = new SProperty("taxPorcent", BIGDECIMAL,true,false);   
            
                            
        taxPorcent.setMin(0); 
        taxPorcent.setMax(100);  
    dcm.addSProperty(taxPorcent);             
    

    
//1
    final SMto sale = new SMto("sale","sale"); 
    sale.setPc("pname"); 
    dcm.addSMto(sale); 

//1
    final SMto product = new SMto("product","product"); 
    product.setPc("pname"); 
    dcm.addSMto(product); 

//1
    final SMto stockRackProduct = new SMto("stockRackProduct","stockRackProduct"); 
    stockRackProduct.setPc("pname"); 
    dcm.addSMto(stockRackProduct); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","invoice_line_out_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("invoice_line_out.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("invoice_line_out.invoice_line_out_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "invoice_line_out_pkey");
                    
//askDate -------------------------------------------
    names.add("askDate");
    insertMapFields.put("invoice_line_out.ask_date","askDate");  
    sortMapFields.put("askDate", "invoice_line_out_ask_date");
                
//askQuantity -------------------------------------------
    names.add("askQuantity");
    insertMapFields.put("invoice_line_out.ask_quantity","askQuantity");  
    sortMapFields.put("askQuantity", "invoice_line_out_ask_quantity");
                
//createdDate -------------------------------------------
    names.add("createdDate");
    insertMapFields.put("invoice_line_out.created_date","createdDate");  
    sortMapFields.put("createdDate", "invoice_line_out_created_date");
                
//descountPorcent -------------------------------------------
    names.add("descountPorcent");
    insertMapFields.put("invoice_line_out.descount_porcent","descountPorcent");  
    sortMapFields.put("descountPorcent", "invoice_line_out_descount_porcent");
                
//orden -------------------------------------------
    names.add("orden");
    insertMapFields.put("invoice_line_out.orden","orden");  
    sortMapFields.put("orden", "invoice_line_out_orden");
                
//price -------------------------------------------
    names.add("price");
    insertMapFields.put("invoice_line_out.price","price");  
    sortMapFields.put("price", "invoice_line_out_price");
                
//status -------------------------------------------
    names.add("status");
    insertMapFields.put("invoice_line_out.status","status");   
//supplyDate -------------------------------------------
    names.add("supplyDate");
    insertMapFields.put("invoice_line_out.supply_date","supplyDate");  
    sortMapFields.put("supplyDate", "invoice_line_out_supply_date");
                
//supplyQuantity -------------------------------------------
    names.add("supplyQuantity");
    insertMapFields.put("invoice_line_out.supply_quantity","supplyQuantity");  
    sortMapFields.put("supplyQuantity", "invoice_line_out_supply_quantity");
                
//taxPorcent -------------------------------------------
    names.add("taxPorcent");
    insertMapFields.put("invoice_line_out.tax_porcent","taxPorcent");  
    sortMapFields.put("taxPorcent", "invoice_line_out_tax_porcent");
               // sale --------------------
        names.add("sale_id");
        
        insertMapFields.put("invoice_line_out.sale_id","sale_id");
        
        
        names.add("sale_pkey");        
            sortMapFields.put( "sale_pkey", "sale_pkey");

        
   names.add("sale_pname");
            sortMapFields.put( "sale_pname", "sale_pname");
            //   saleContract 
        names.add("saleContract_id");
            
        names.add("saleContract_pkey");
   names.add("saleContract_pname");//   programBaseTimePeriod 
        names.add("programBaseTimePeriod_id");
            
        names.add("programBaseTimePeriod_pkey");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");//   userAutor 
        names.add("userAutor_id");
            
        names.add("userAutor_pkey");
   names.add("userAutor_pname");//   saleType 
        names.add("saleType_id");
            
        names.add("saleType_pkey");
   names.add("saleType_pname");// product --------------------
        names.add("product_id");
        
        insertMapFields.put("invoice_line_out.product_id","product_id");
        
        
        names.add("product_pkey");        
            sortMapFields.put( "product_pkey", "product_pkey");

        
   names.add("product_pname");
            sortMapFields.put( "product_pname", "product_pname");
            //   productType 
        names.add("productType_id");
            
        names.add("productType_pkey");
   names.add("productType_pname");// stockRackProduct --------------------
        names.add("stockRackProduct_id");
        
        insertMapFields.put("invoice_line_out.stock_rack_product_id","stockRackProduct_id");
        
        
        names.add("stockRackProduct_pkey");        
            sortMapFields.put( "stockRackProduct_pkey", "stock_rack_product_pkey");

        
   names.add("stockRackProduct_pname");
            sortMapFields.put( "stockRackProduct_pname", "stock_rack_product_pname");
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
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final InvoiceLineOut dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getAskDate());
        t.addBigDecimal(dc0.getAskQuantity());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addBigDecimal(dc0.getDescountPorcent());
        t.addInteger(dc0.getOrden());
        t.addBigDecimal(dc0.getPrice());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
        t.addBigDecimal(dc0.getSupplyQuantity());
        t.addBigDecimal(dc0.getTaxPorcent());
   
                if(dc0.getSale()!=null){
            t.addLong(dc0.getSale().getId());
                }
   
                if(dc0.getProduct()!=null){
            t.addLong(dc0.getProduct().getId());
                }
   
                if(dc0.getStockRackProduct()!=null){
            t.addLong(dc0.getStockRackProduct().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO invoice_line_out(pkey,ask_date,ask_quantity,created_date,descount_porcent,orden,price,status,supply_date,supply_quantity,tax_porcent,sale_id,product_id,stock_rack_product_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,(select nextval('seq_invoice_line_out'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(InvoiceLineOut invoiceLineOut) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(invoiceLineOut, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("invoice_line_out", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "invoice_line_out_pkey");
    slcb.doEqInPSimple( "pkey", "invoice_line_out_pkey");   
    slcb.doGEPSimpleInt( "orden", "invoice_line_out_orden");
    slcb.doLTPSimpleInt( "orden", "invoice_line_out_orden");                 
    slcb.doEqInPSimple( "status", "invoice_line_out_status");                     
        
        slcb.doIlPSimple2( "sale_pkey", "sale_pkey");
        slcb.doEQPSimple2( "sale_pkey", "sale_pkey");
        slcb.doInLongCondition("sale_id", "sale_id");        
    
// 1 Sale pname
        slcb.doIlPSimple2( "sale_pname", "sale_pname");
    

        slcb.doIlPSimple2( "product_pkey", "product_pkey");
        slcb.doEQPSimple2( "product_pkey", "product_pkey");
        slcb.doInLongCondition("product_id", "product_id");        
    
// 1 Product pname
        slcb.doIlPSimple2( "product_pname", "product_pname");
    

        slcb.doIlPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doEQPSimple2( "stockRackProduct_pkey", "stock_rack_product_pkey");
        slcb.doInLongCondition("stockRackProduct_id", "stock_rack_product_id");        
    
// 1 StockRackProduct pname
        slcb.doIlPSimple2( "stockRackProduct_pname", "stock_rack_product_pname");
    

        slcb.doIlPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doEQPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doInLongCondition("saleContract_id", "sale_contract_id");                  

//2  SaleContract pname

        slcb.doIlPSimple2( "saleContract_pname", "sale_contract_pname");                    

//3  ProgramBaseTimePeriod undefined                                
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");                            

//3  ThirdPerson pname                                
        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                            

        slcb.doIlPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userAutor_id", "user_lon_id");                  

//2  UserLon pname

        slcb.doIlPSimple2( "userAutor_pname", "user_lon_pname");                    

        slcb.doIlPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doEQPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doInLongCondition("saleType_id", "sale_type_id");                  

//2  SaleType pname

        slcb.doIlPSimple2( "saleType_pname", "sale_type_pname");                    

        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");                  

//2  ProductType pname

        slcb.doIlPSimple2( "productType_pname", "product_type_pname");                    

        slcb.doIlPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doEQPSimple2( "stockRack_pkey", "stock_rack_pkey");
        slcb.doInLongCondition("stockRack_id", "stock_rack_id");                  

//2  StockRack pname

        slcb.doIlPSimple2( "stockRack_pname", "stock_rack_pname");                    

//3  WorkSpace pname                                
        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");                            
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  invoice_line_out.id as invoice_line_out_id,invoice_line_out.pkey as invoice_line_out_pkey,invoice_line_out.ask_date as invoice_line_out_ask_date,invoice_line_out.ask_quantity as invoice_line_out_ask_quantity,invoice_line_out.created_date as invoice_line_out_created_date,invoice_line_out.descount_porcent as invoice_line_out_descount_porcent,invoice_line_out.orden as invoice_line_out_orden,invoice_line_out.price as invoice_line_out_price,invoice_line_out.status as invoice_line_out_status,invoice_line_out.supply_date as invoice_line_out_supply_date,invoice_line_out.supply_quantity as invoice_line_out_supply_quantity,invoice_line_out.tax_porcent as invoice_line_out_tax_porcent,sale.id as sale_id,sale.pkey as sale_pkey,sale.pname as sale_pname,sale_contract.id as sale_contract_id, sale_contract.pkey as sale_contract_pkey,sale_contract.pname as sale_contract_pname,program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id, user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,sale_type.id as sale_type_id, sale_type.pkey as sale_type_pkey,sale_type.pname as sale_type_pname,product.id as product_id,product.pkey as product_pkey,product.pname as product_pname,product_type.id as product_type_id, product_type.pkey as product_type_pkey,product_type.pname as product_type_pname,stock_rack_product.id as stock_rack_product_id,stock_rack_product.pkey as stock_rack_product_pkey,stock_rack_product.pname as stock_rack_product_pname,stock_rack.id as stock_rack_id, stock_rack.pkey as stock_rack_pkey,stock_rack.pname as stock_rack_pname,work_space.id as work_space_id, work_space.pkey as work_space_pkey,work_space.pname as work_space_pname   FROM   invoice_line_out,  sale as sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type,  product as product,  product_type as product_type,  stock_rack_product as stock_rack_product,  stock_rack as stock_rack,  work_space as work_space   WHERE  invoice_line_out.sale_id = sale.id AND sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id AND invoice_line_out.product_id = product.id AND product.product_type_id = product_type.id AND invoice_line_out.stock_rack_product_id = stock_rack_product.id AND stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id";
    private static String sqlCount = "SELECT  count(invoice_line_out.id)   FROM   invoice_line_out,  sale as sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type,  product as product,  product_type as product_type,  stock_rack_product as stock_rack_product,  stock_rack as stock_rack,  work_space as work_space   WHERE  invoice_line_out.sale_id = sale.id AND sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id AND invoice_line_out.product_id = product.id AND product.product_type_id = product_type.id AND invoice_line_out.stock_rack_product_id = stock_rack_product.id AND stock_rack_product.stock_rack_id = stock_rack.id AND stock_rack.work_space_id = work_space.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        