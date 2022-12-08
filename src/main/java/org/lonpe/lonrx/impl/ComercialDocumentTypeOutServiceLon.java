
        
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
public class ComercialDocumentTypeOutServiceLon extends AbstractLon<ComercialDocumentTypeOut> implements IServiceLon<ComercialDocumentTypeOut>{  
        
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
        
    
        DCModel dcm = new DCModel("comercialDocumentTypeOut", "pname");    
        

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
    

    
    

    
//ON RELATION comercialDocumentType    
    final SOtm comercialDocuments = new SOtm("comercialDocuments","comercialDocumentOut","comercialDocumentType");                    
    dcm.addSOtm(comercialDocuments); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","comercial_document_type_out_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("comercial_document_type_out.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("comercial_document_type_out.comercial_document_type_out_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "comercial_document_type_out_pkey");
                    
//afectStock -------------------------------------------
    names.add("afectStock");
    insertMapFields.put("comercial_document_type_out.afect_stock","afectStock");  
    sortMapFields.put("afectStock", "comercial_document_type_out_afect_stock");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("comercial_document_type_out.pname","pname");  
    sortMapFields.put("pname", "comercial_document_type_out_pname");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ComercialDocumentTypeOut dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getAfectStock());
        t.addString(dc0.getPname());
    }

    

private static final String SQLINSERT = "INSERT INTO comercial_document_type_out(pkey,afect_stock,pname,id) VALUES ($1,$2,$3,(select nextval('seq_comercial_document_type_out'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(ComercialDocumentTypeOut comercialDocumentTypeOut) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(comercialDocumentTypeOut, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "comercial_document_type_out", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "comercial_document_type_out_pkey");
    slcb.doEqInPSimple( "pkey", "comercial_document_type_out_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "comercial_document_type_out_pname");
     slcb.doEqInPSimple( "pname", "comercial_document_type_out_pname");            
    slcb.doEqInPSimple( "afectStock", "comercial_document_type_out_afect_stock");                     
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  comercial_document_type_out.id as comercial_document_type_out_id,comercial_document_type_out.pkey as comercial_document_type_out_pkey,comercial_document_type_out.afect_stock as comercial_document_type_out_afect_stock,comercial_document_type_out.pname as comercial_document_type_out_pname   FROM   comercial_document_type_out ";
    private static String sqlCount = "SELECT  count(comercial_document_type_out.id)   FROM   comercial_document_type_out ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        