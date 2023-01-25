
        
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
public class ProgramJobServiceLon extends AbstractLon<ProgramJob> implements IServiceLon<ProgramJob>{  
        
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
        
    
        DCModel dcm = new DCModel("programJob", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property description   
                
        final SProperty description = new SProperty("description", STRING,false,false);   
            
             
    dcm.addSProperty(description);             //Create property fastKey   
                
        final SProperty fastKey = new SProperty("fastKey", STRING,false,false);   
            
            
// hasIndex 
    fastKey.setWithIndex(true);   
    dcm.addSProperty(fastKey);             //Create property nhoras   
                
        final SProperty nhoras = new SProperty("nhoras", INTEGER,true,false);   
            
             
    dcm.addSProperty(nhoras);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,true);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
//1
    final SMto program = new SMto("program","program"); 
    program.setPc("pname"); 
    dcm.addSMto(program); 

//1
    final SMto departamentJob = new SMto("departamentJob","departamentJob"); 
    departamentJob.setPc("pname"); 
    dcm.addSMto(departamentJob); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","program_job_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("program_job.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("program_job.program_job_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "program_job_pkey");
                    
//description -------------------------------------------
    names.add("description");
    insertMapFields.put("program_job.description","description");   
//fastKey -------------------------------------------
    names.add("fastKey");
    insertMapFields.put("program_job.fast_key","fastKey");   
//nhoras -------------------------------------------
    names.add("nhoras");
    insertMapFields.put("program_job.nhoras","nhoras");  
    sortMapFields.put("nhoras", "program_job_nhoras");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("program_job.pname","pname");  
//Used to map error on index to source property
    insertMapFields.put("program_job.program_job_uidx_pname","pname");  
    sortMapFields.put("pname", "program_job_pname");
                   // program --------------------
        names.add("program_id");
        
        insertMapFields.put("program_job.program_id","program_id");
        
        
        names.add("program_pkey");        
            sortMapFields.put( "program_pkey", "program_pkey");

        
   names.add("program_pname");
            sortMapFields.put( "program_pname", "program_pname");
            // departamentJob --------------------
        names.add("departamentJob_id");
        
        insertMapFields.put("program_job.departament_job_id","departamentJob_id");
        
        
        names.add("departamentJob_pkey");        
            sortMapFields.put( "departamentJob_pkey", "departament_job_pkey");

        
   names.add("departamentJob_pname");
            sortMapFields.put( "departamentJob_pname", "departament_job_pname");
            //   departament 
        names.add("departament_id");
            
        names.add("departament_pkey");
   names.add("departament_pname");
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ProgramJob dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getDescription());
        t.addString(dc0.getFastKey());
        t.addInteger(dc0.getNhoras());
        t.addString(dc0.getPname());
   
                if(dc0.getProgram()!=null){
            t.addLong(dc0.getProgram().getId());
                }
   
                if(dc0.getDepartamentJob()!=null){
            t.addLong(dc0.getDepartamentJob().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO program_job(pkey,description,fast_key,nhoras,pname,program_id,departament_job_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,(select nextval('seq_program_job'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(ProgramJob programJob) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(programJob, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("program_job", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "program_job_pkey");
    slcb.doEqInPSimple( "pkey", "program_job_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "program_job_pname");
     slcb.doEqInPSimple( "pname", "program_job_pname");             
// withIndex true
    slcb.doIlPSimple2( "fastKey", "program_job_fast_key");
    slcb.doEqInPSimple( "fastKey", "program_job_fast_key");                       
    slcb.doGEPSimpleInt( "nhoras", "program_job_nhoras");
    slcb.doLTPSimpleInt( "nhoras", "program_job_nhoras");                 
        
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");        
    
// 1 Program pname
        slcb.doIlPSimple2( "program_pname", "program_pname");
    

        slcb.doIlPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");        
    
// 1 DepartamentJob pname
        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname");
    

        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");                  

//2  Departament pname

        slcb.doIlPSimple2( "departament_pname", "departament_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  program_job.id as program_job_id,program_job.pkey as program_job_pkey,program_job.description as program_job_description,program_job.fast_key as program_job_fast_key,program_job.nhoras as program_job_nhoras,program_job.pname as program_job_pname,program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,departament_job.id as departament_job_id,departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname   FROM   program_job,  program as program,  departament_job as departament_job,  departament as departament   WHERE  program_job.program_id = program.id AND program_job.departament_job_id = departament_job.id AND departament_job.departament_id = departament.id";
    private static String sqlCount = "SELECT  count(program_job.id)   FROM   program_job,  program as program,  departament_job as departament_job,  departament as departament   WHERE  program_job.program_id = program.id AND program_job.departament_job_id = departament_job.id AND departament_job.departament_id = departament.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        