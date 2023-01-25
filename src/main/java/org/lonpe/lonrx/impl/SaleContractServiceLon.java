
        
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
public class SaleContractServiceLon extends AbstractLon<SaleContract> implements IServiceLon<SaleContract>{  
        
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
        
    
        DCModel dcm = new DCModel("saleContract", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
//1
    final SMto programBaseTimePeriod = new SMto("programBaseTimePeriod","programBaseTimePeriod"); 
    dcm.addSMto(programBaseTimePeriod); 

//1
    final SMto thirdPerson = new SMto("thirdPerson","thirdPerson"); 
    thirdPerson.setPc("pname"); 
    dcm.addSMto(thirdPerson); 
    

    
//ON RELATION saleContract    
    final SOtm sales = new SOtm("sales","sale","saleContract");                    
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
    sortMapFields.put("id","sale_contract_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("sale_contract.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("sale_contract.sale_contract_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "sale_contract_pkey");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("sale_contract.pname","pname");  
    sortMapFields.put("pname", "sale_contract_pname");
                   // programBaseTimePeriod --------------------
        names.add("programBaseTimePeriod_id");
        
        insertMapFields.put("sale_contract.program_base_time_period_id","programBaseTimePeriod_id");
        
        
        names.add("programBaseTimePeriod_pkey");        
            sortMapFields.put( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");

        //   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   base 
        names.add("base_id");
            
        names.add("base_pkey");
   names.add("base_pname");//   timePeriod 
        names.add("timePeriod_id");
            
        names.add("timePeriod_pkey");
   names.add("timePeriod_pname");//   program 
        names.add("program_id");
            
        names.add("program_pkey");
   names.add("program_pname");// thirdPerson --------------------
        names.add("thirdPerson_id");
        
        insertMapFields.put("sale_contract.third_person_id","thirdPerson_id");
        
        
        names.add("thirdPerson_pkey");        
            sortMapFields.put( "thirdPerson_pkey", "third_person_pkey");

        
   names.add("thirdPerson_pname");
            sortMapFields.put( "thirdPerson_pname", "third_person_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final SaleContract dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
   
                if(dc0.getProgramBaseTimePeriod()!=null){
            t.addLong(dc0.getProgramBaseTimePeriod().getId());
                }
   
                if(dc0.getThirdPerson()!=null){
            t.addLong(dc0.getThirdPerson().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO sale_contract(pkey,pname,program_base_time_period_id,third_person_id,id) VALUES ($1,$2,$3,$4,(select nextval('seq_sale_contract'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(SaleContract saleContract) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(saleContract, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("sale_contract", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "sale_contract_pkey");
    slcb.doEqInPSimple( "pkey", "sale_contract_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "sale_contract_pname");
     slcb.doEqInPSimple( "pname", "sale_contract_pname");            
        
        slcb.doIlPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doEQPSimple2( "programBaseTimePeriod_pkey", "program_base_time_period_pkey");
        slcb.doInLongCondition("programBaseTimePeriod_id", "program_base_time_period_id");        
    
// 1 ProgramBaseTimePeriod undefined

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");        
    
// 1 ThirdPerson pname
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");
    

        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");                  

//2  BaseTimePeriod undefined

//3  Base pname                                
        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");                            

//3  TimePeriod pname                                
        slcb.doIlPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doEQPSimple2( "timePeriod_pkey", "time_period_pkey");
        slcb.doInLongCondition("timePeriod_id", "time_period_id");                            

        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");                  

//2  Program pname

        slcb.doIlPSimple2( "program_pname", "program_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  sale_contract.id as sale_contract_id,sale_contract.pkey as sale_contract_pkey,sale_contract.pname as sale_contract_pname,program_base_time_period.id as program_base_time_period_id,program_base_time_period.pkey as program_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,time_period.id as time_period_id, time_period.pkey as time_period_pkey,time_period.pname as time_period_pname,program.id as program_id, program.pkey as program_pkey,program.pname as program_pname,third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname   FROM   sale_contract,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program,  third_person as third_person   WHERE  sale_contract.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id AND sale_contract.third_person_id = third_person.id";
    private static String sqlCount = "SELECT  count(sale_contract.id)   FROM   sale_contract,  program_base_time_period as program_base_time_period,  base_time_period as base_time_period,  base as base,  time_period as time_period,  program as program,  third_person as third_person   WHERE  sale_contract.program_base_time_period_id = program_base_time_period.id AND program_base_time_period.base_time_period_id = base_time_period.id AND base_time_period.base_id = base.id AND base_time_period.time_period_id = time_period.id AND program_base_time_period.program_id = program.id AND sale_contract.third_person_id = third_person.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        