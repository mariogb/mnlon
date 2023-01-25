
        
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
public class ProgramServiceLon extends AbstractLon<Program> implements IServiceLon<Program>{  
        
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
        
    
        DCModel dcm = new DCModel("program", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property description   
                
        final SProperty description = new SProperty("description", STRING,false,false);   
            
             
    dcm.addSProperty(description);             //Create property fastKey   
                
        final SProperty fastKey = new SProperty("fastKey", STRING,false,false);   
            
            
// hasIndex 
    fastKey.setWithIndex(true);   
    dcm.addSProperty(fastKey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,true);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
    

    
//ON RELATION program    
    final SOtm programBaseTimePeriods = new SOtm("programBaseTimePeriods","programBaseTimePeriod","program");                    
    dcm.addSOtm(programBaseTimePeriods); 
//ON RELATION program    
    final SOtm programUserLons = new SOtm("programUserLons","programUserLon","program");                    
    dcm.addSOtm(programUserLons); 
//ON RELATION program    
    final SOtm programJobs = new SOtm("programJobs","programJob","program");                    
    dcm.addSOtm(programJobs); 
        //
            final SOtm saleContracts = new SOtm("saleContracts","saleContract","programBaseTimePeriod"); 
            dcm.addSOtm2(saleContracts);
            
        //;
        
        //
            final SOtm sales = new SOtm("sales","sale","saleContract"); 
            dcm.addSOtm3(sales);            
        //
        
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","program_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("program.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("program.program_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "program_pkey");
                    
//description -------------------------------------------
    names.add("description");
    insertMapFields.put("program.description","description");   
//fastKey -------------------------------------------
    names.add("fastKey");
    insertMapFields.put("program.fast_key","fastKey");   
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("program.pname","pname");  
//Used to map error on index to source property
    insertMapFields.put("program.program_uidx_pname","pname");  
    sortMapFields.put("pname", "program_pname");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Program dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addString(dc0.getPname());
    }

    

private static final String SQLINSERT = "INSERT INTO program(pkey,description,fast_key,pname,id) VALUES ($1,$2,$3,$4,(select nextval('seq_program'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Program program) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(program, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("program", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "program_pkey");
    slcb.doEqInPSimple( "pkey", "program_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "program_pname");
     slcb.doEqInPSimple( "pname", "program_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "program_fast_key");
    slcb.doEqInPSimple( "fastKey", "program_fast_key");                    
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  program.id as program_id,program.pkey as program_pkey,program.description as program_description,program.fast_key as program_fast_key,program.pname as program_pname   FROM   program ";
    private static String sqlCount = "SELECT  count(program.id)   FROM   program ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        