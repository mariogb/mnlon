
        
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
public class ProductServiceLon extends AbstractLon<Product> implements IServiceLon<Product>{  
        
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
        
    
        DCModel dcm = new DCModel("product", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property description   
                
        final SProperty description = new SProperty("description", STRING,false,false);   
            
             
    dcm.addSProperty(description);             //Create property fastKey   
                
        final SProperty fastKey = new SProperty("fastKey", STRING,false,false);   
            
            
// hasIndex 
    fastKey.setWithIndex(true);   
    dcm.addSProperty(fastKey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property price   
                
        final SProperty price = new SProperty("price", BIGDECIMAL,true,false);   
            
             
    dcm.addSProperty(price);             //Create property sku   
                
        final SProperty sku = new SProperty("sku", STRING,false,false);   
            
            
// hasIndex 
    sku.setWithIndex(true);   
    dcm.addSProperty(sku);             //Create property taxPorcent   
                
        final SProperty taxPorcent = new SProperty("taxPorcent", BIGDECIMAL,true,false);   
            
                            
        taxPorcent.setMin(0); 
        taxPorcent.setMax(80);  
    dcm.addSProperty(taxPorcent);             
    

    
//1
    final SMto productType = new SMto("productType","productType"); 
    productType.setPc("pname"); 
    dcm.addSMto(productType); 
    

    
//ON RELATION product    
    final SOtm invoiceLineIns = new SOtm("invoiceLineIns","invoiceLineIn","product");                    
    dcm.addSOtm(invoiceLineIns); 
//ON RELATION product    
    final SOtm invoiceLineOuts = new SOtm("invoiceLineOuts","invoiceLineOut","product");                    
    dcm.addSOtm(invoiceLineOuts); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","product_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("product.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("product.product_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "product_pkey");
                    
//description -------------------------------------------
    names.add("description");
    insertMapFields.put("product.description","description");   
//fastKey -------------------------------------------
    names.add("fastKey");
    insertMapFields.put("product.fast_key","fastKey");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("product.pname","pname");  
    sortMapFields.put("pname", "product_pname");
                    
//price -------------------------------------------
    names.add("price");
    insertMapFields.put("product.price","price");  
    sortMapFields.put("price", "product_price");
                
//sku -------------------------------------------
    names.add("sku");
    insertMapFields.put("product.sku","sku");   
//taxPorcent -------------------------------------------
    names.add("taxPorcent");
    insertMapFields.put("product.tax_porcent","taxPorcent");  
    sortMapFields.put("taxPorcent", "product_tax_porcent");
               // productType --------------------
        names.add("productType_id");
        
        insertMapFields.put("product.product_type_id","productType_id");
        
        
        names.add("productType_pkey");        
            sortMapFields.put( "productType_pkey", "product_type_pkey");

        
   names.add("productType_pname");
            sortMapFields.put( "productType_pname", "product_type_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Product dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
        t.addBigDecimal(dc0.getPrice());
        t.addString(dc0.getSku());
        t.addBigDecimal(dc0.getTaxPorcent());
   
                if(dc0.getProductType()!=null){
            t.addLong(dc0.getProductType().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO product(pkey,description,fast_key,pname,price,sku,tax_porcent,product_type_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,(select nextval('seq_product'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Product product) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(product, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("product", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "product_pkey");
    slcb.doEqInPSimple( "pkey", "product_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "product_pname");
     slcb.doEqInPSimple( "pname", "product_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "product_fast_key");
    slcb.doEqInPSimple( "fastKey", "product_fast_key");                     
// withIndex true
    slcb.doIlPSimple2( "sku", "product_sku");
    slcb.doEqInPSimple( "sku", "product_sku");                    
        
        slcb.doIlPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doEQPSimple2( "productType_pkey", "product_type_pkey");
        slcb.doInLongCondition("productType_id", "product_type_id");        
    
// 1 ProductType pname
        slcb.doIlPSimple2( "productType_pname", "product_type_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  product.id as product_id,product.pkey as product_pkey,product.description as product_description,product.fast_key as product_fast_key,product.pname as product_pname,product.price as product_price,product.sku as product_sku,product.tax_porcent as product_tax_porcent,product_type.id as product_type_id,product_type.pkey as product_type_pkey,product_type.pname as product_type_pname   FROM   product,  product_type as product_type   WHERE  product.product_type_id = product_type.id";
    private static String sqlCount = "SELECT  count(product.id)   FROM   product,  product_type as product_type   WHERE  product.product_type_id = product_type.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        