
        
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
public class AccountServiceLon extends AbstractLon<Account> implements IServiceLon<Account>{  
        
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
        
    
        DCModel dcm = new DCModel("account", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property description   
                
        final SProperty description = new SProperty("description", STRING,false,false);   
            
             
    dcm.addSProperty(description);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property type   
                
        final SProperty type = new SProperty("type", STRING,true,false);   
            
            
        final List<String> typeInList = new ArrayList<>();
                
        typeInList.add("ACTIVE"); 
        typeInList.add("PASIVE"); 
        type.setInList(typeInList);                 
    dcm.addSProperty(type);             
    

    
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","account_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("account.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("account.account_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "account_pkey");
                    
//description -------------------------------------------
    names.add("description");
    insertMapFields.put("account.description","description");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("account.pname","pname");  
    sortMapFields.put("pname", "account_pname");
                    
//type -------------------------------------------
    names.add("type");
    insertMapFields.put("account.type","type");  
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Account dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getPname());
        t.addString(dc0.getType());
    }

    

private static final String SQLINSERT = "INSERT INTO account(pkey,description,pname,type,id) VALUES ($1,$2,$3,$4,(select nextval('seq_account'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Account account) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(account, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("account", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "account_pkey");
    slcb.doEqInPSimple( "pkey", "account_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "account_pname");
     slcb.doEqInPSimple( "pname", "account_pname");            
    slcb.doEqInPSimple( "type", "account_type");                     
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  account.id as account_id,account.pkey as account_pkey,account.description as account_description,account.pname as account_pname,account.type as account_type   FROM   account ";
    private static String sqlCount = "SELECT  count(account.id)   FROM   account ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        