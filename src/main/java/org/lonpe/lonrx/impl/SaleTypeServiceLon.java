
        
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
public class SaleTypeServiceLon extends AbstractLon<SaleType> implements IServiceLon<SaleType>{  
        
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
        
    
        DCModel dcm = new DCModel("saleType", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property afectStock   
                
        final SProperty afectStock = new SProperty("afectStock", STRING,true,false);   
            
            
        final List<String> afectStockInList = new ArrayList<>();
                
        afectStockInList.add("NO"); 
        afectStockInList.add("RESERVE"); 
        afectStockInList.add("YES"); 
        afectStock.setInList(afectStockInList);                 
    dcm.addSProperty(afectStock);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
    

    
//ON RELATION saleType    
    final SOtm sales = new SOtm("sales","sale","saleType");                    
    dcm.addSOtm(sales); 
        //
            final SOtm invoiceLines = new SOtm("invoiceLines","invoiceLineOut","sale"); 
            dcm.addSOtm2(invoiceLines);
            

            final SOtm payments = new SOtm("payments","paymentIn","sale"); 
            dcm.addSOtm2(payments);
            
        //;
        
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","sale_type_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("sale_type.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("sale_type.sale_type_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "sale_type_pkey");
                    
//afectStock -------------------------------------------
    names.add("afectStock");
    insertMapFields.put("sale_type.afect_stock","afectStock");  
    sortMapFields.put("afectStock", "sale_type_afect_stock");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("sale_type.pname","pname");  
    sortMapFields.put("pname", "sale_type_pname");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final SaleType dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getAfectStock());
        t.addString(dc0.getPname());
    }

    

private static final String SQLINSERT = "INSERT INTO sale_type(pkey,afect_stock,pname,id) VALUES ($1,$2,$3,(select nextval('seq_sale_type'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(SaleType saleType) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(saleType, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("sale_type", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "sale_type_pkey");
    slcb.doEqInPSimple( "pkey", "sale_type_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "sale_type_pname");
     slcb.doEqInPSimple( "pname", "sale_type_pname");            
    slcb.doEqInPSimple( "afectStock", "sale_type_afect_stock");                     
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  sale_type.id as sale_type_id,sale_type.pkey as sale_type_pkey,sale_type.afect_stock as sale_type_afect_stock,sale_type.pname as sale_type_pname   FROM   sale_type ";
    private static String sqlCount = "SELECT  count(sale_type.id)   FROM   sale_type ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        