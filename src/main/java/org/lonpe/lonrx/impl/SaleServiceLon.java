
        
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
public class SaleServiceLon extends AbstractLon<Sale> implements IServiceLon<Sale>{  
        
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
        
    
        DCModel dcm = new DCModel("sale", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property createdDate   
                
        final SProperty createdDate = new SProperty("createdDate", LOCALDATETIME,true,false);   
            
             
//Set by system
   createdDate.setSetBySys("now");   
    dcm.addSProperty(createdDate);             //Create property documentDate   
                
        final SProperty documentDate = new SProperty("documentDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(documentDate);             //Create property folio   
                
        final SProperty folio = new SProperty("folio", STRING,true,false);   
            
             
    dcm.addSProperty(folio);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property status   
                
        final SProperty status = new SProperty("status", STRING,true,false);   
            
            
        final List<String> statusInList = new ArrayList<>();
                
        statusInList.add("PENDENT"); 
        statusInList.add("SUPPLIED"); 
        statusInList.add("CANCEL"); 
        status.setInList(statusInList);                 
    dcm.addSProperty(status);             //Create property supplyDate   
                
        final SProperty supplyDate = new SProperty("supplyDate", LOCALDATETIME,false,false);   
            
             
    dcm.addSProperty(supplyDate);             
    

    
//1
    final SMto saleContract = new SMto("saleContract","saleContract"); 
    saleContract.setPc("pname"); 
    dcm.addSMto(saleContract); 

//1
    final SMto userAutor = new SMto("userAutor","userLon"); 
    userAutor.setPc("pname"); 
    userAutor.setSetBySys("putCurrentUser");
    dcm.addSMto(userAutor); 

//1
    final SMto saleType = new SMto("saleType","saleType"); 
    saleType.setPc("pname"); 
    dcm.addSMto(saleType); 
    

    
//ON RELATION sale    
    final SOtm invoiceLines = new SOtm("invoiceLines","invoiceLineOut","sale");                    
    dcm.addSOtm(invoiceLines); 
//ON RELATION sale    
    final SOtm payments = new SOtm("payments","paymentIn","sale");                    
    dcm.addSOtm(payments); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","sale_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("sale.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("sale.sale_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "sale_pkey");
                    
//createdDate -------------------------------------------
    names.add("createdDate");
    insertMapFields.put("sale.created_date","createdDate");  
    sortMapFields.put("createdDate", "sale_created_date");
                
//documentDate -------------------------------------------
    names.add("documentDate");
    insertMapFields.put("sale.document_date","documentDate");  
    sortMapFields.put("documentDate", "sale_document_date");
                
//folio -------------------------------------------
    names.add("folio");
    insertMapFields.put("sale.folio","folio");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("sale.pname","pname");  
    sortMapFields.put("pname", "sale_pname");
                    
//status -------------------------------------------
    names.add("status");
    insertMapFields.put("sale.status","status");   
//supplyDate -------------------------------------------
    names.add("supplyDate");
    insertMapFields.put("sale.supply_date","supplyDate");  
    sortMapFields.put("supplyDate", "sale_supply_date");
               // saleContract --------------------
        names.add("saleContract_id");
        
        insertMapFields.put("sale.sale_contract_id","saleContract_id");
        
        
        names.add("saleContract_pkey");        
            sortMapFields.put( "saleContract_pkey", "sale_contract_pkey");

        
   names.add("saleContract_pname");
            sortMapFields.put( "saleContract_pname", "sale_contract_pname");
            //   programBaseTimePeriod 
        names.add("programBaseTimePeriod_id");
            
        names.add("programBaseTimePeriod_pkey");//   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   program 
        names.add("program_id");
            
        names.add("program_pkey");
   names.add("program_pname");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");// userAutor --------------------
        names.add("userAutor_id");
        
        insertMapFields.put("sale.user_autor_id","userAutor_id");
        
        
        names.add("userAutor_pkey");        
            sortMapFields.put( "userAutor_pkey", "user_autor_pkey");

        
   names.add("userAutor_pname");
            sortMapFields.put( "userAutor_pname", "user_autor_pname");
            // saleType --------------------
        names.add("saleType_id");
        
        insertMapFields.put("sale.sale_type_id","saleType_id");
        
        
        names.add("saleType_pkey");        
            sortMapFields.put( "saleType_pkey", "sale_type_pkey");

        
   names.add("saleType_pname");
            sortMapFields.put( "saleType_pname", "sale_type_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Sale dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getCreatedDate());
        t.addLocalDateTime(dc0.getDocumentDate());
        t.addString(dc0.getFolio());
        t.addString(dc0.getPname());
        t.addString(dc0.getStatus());
        t.addLocalDateTime(dc0.getSupplyDate());
   
                if(dc0.getSaleContract()!=null){
            t.addLong(dc0.getSaleContract().getId());
                }
   
                if(dc0.getUserAutor()!=null){
            t.addLong(dc0.getUserAutor().getId());
                }
   
                if(dc0.getSaleType()!=null){
            t.addLong(dc0.getSaleType().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO sale(pkey,created_date,document_date,folio,pname,status,supply_date,sale_contract_id,user_autor_id,sale_type_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,(select nextval('seq_sale'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Sale sale) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(sale, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("sale", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "sale_pkey");
    slcb.doEqInPSimple( "pkey", "sale_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "sale_pname");
     slcb.doEqInPSimple( "pname", "sale_pname");            
    slcb.doEqInPSimple( "status", "sale_status");                     
        
        slcb.doIlPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doEQPSimple2( "saleContract_pkey", "sale_contract_pkey");
        slcb.doInLongCondition("saleContract_id", "sale_contract_id");        
    
// 1 SaleContract pname
        slcb.doIlPSimple2( "saleContract_pname", "sale_contract_pname");
    

        slcb.doIlPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doEQPSimple2( "userAutor_pkey", "user_autor_pkey");
        slcb.doInLongCondition("userAutor_id", "user_autor_id");        
    
// 1 UserLon pname
        slcb.doIlPSimple2( "userAutor_pname", "user_autor_pname");
    

        slcb.doIlPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doEQPSimple2( "saleType_pkey", "sale_type_pkey");
        slcb.doInLongCondition("saleType_id", "sale_type_id");        
    
// 1 SaleType pname
        slcb.doIlPSimple2( "saleType_pname", "sale_type_pname");
    

        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");                  

//2  ProgramBaseTimePeriod undefined

//3  BaseTimePeriod undefined                                
        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");                            

//3  Program pname                                
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");                            

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                  

//2  ThirdPerson pname

        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  sale.id as sale_id,sale.pkey as sale_pkey,sale.created_date as sale_created_date,sale.document_date as sale_document_date,sale.folio as sale_folio,sale.pname as sale_pname,sale.status as sale_status,sale.supply_date as sale_supply_date,sale_contract.id as sale_contract_id,sale_contract.pkey as sale_contract_pkey,sale_contract.pname as sale_contract_pname,program_base_time_period.id as program_base_time_period_id, program_base_time_period.pkey as program_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,program.id as program_id, program.pkey as program_pkey,program.pname as program_pname,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,user_autor.id as user_autor_id,user_autor.pkey as user_autor_pkey,user_autor.pname as user_autor_pname,sale_type.id as sale_type_id,sale_type.pkey as sale_type_pkey,sale_type.pname as sale_type_pname   FROM   sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  program as program,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type   WHERE  sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND program_base_time_period.program_id = program.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id";
    private static String sqlCount = "SELECT  count(sale.id)   FROM   sale,  sale_contract as sale_contract,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  program as program,  third_person as third_person,  user_lon as user_autor,  sale_type as sale_type   WHERE  sale.sale_contract_id = sale_contract.id AND sale_contract.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND program_base_time_period.program_id = program.id AND sale_contract.third_person_id = third_person.id AND sale.user_autor_id = user_autor.id AND sale.sale_type_id = sale_type.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        