
        
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
public class ProgramUserLonServiceLon extends AbstractLon<ProgramUserLon> implements IServiceLon<ProgramUserLon>{  
        
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
        
    
        DCModel dcm = new DCModel("programUserLon");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    
//1
    final SMto program = new SMto("program","program"); 
    program.setPc("pname"); 
    dcm.addSMto(program); 

//1
    final SMto userLon = new SMto("userLon","userLon"); 
    userLon.setPc("pname"); 
    dcm.addSMto(userLon); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","program_user_lon_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("program_user_lon.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("program_user_lon.program_user_lon_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "program_user_lon_pkey");
                   // program --------------------
        names.add("program_id");
        
        insertMapFields.put("program_user_lon.program_id","program_id");
        
        
        names.add("program_pkey");        
            sortMapFields.put( "program_pkey", "program_pkey");

        
   names.add("program_pname");
            sortMapFields.put( "program_pname", "program_pname");
            // userLon --------------------
        names.add("userLon_id");
        
        insertMapFields.put("program_user_lon.user_lon_id","userLon_id");
        
        
        names.add("userLon_pkey");        
            sortMapFields.put( "userLon_pkey", "user_lon_pkey");

        
   names.add("userLon_pname");
            sortMapFields.put( "userLon_pname", "user_lon_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ProgramUserLon dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getProgram()!=null){
            t.addLong(dc0.getProgram().getId());
                }
   
                if(dc0.getUserLon()!=null){
            t.addLong(dc0.getUserLon().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO program_user_lon(pkey,program_id,user_lon_id,id) VALUES ($1,$2,$3,(select nextval('seq_program_user_lon'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(ProgramUserLon programUserLon) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(programUserLon, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("program_user_lon", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "program_user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "program_user_lon_pkey");
        
        slcb.doIlPSimple2( "program_pkey", "program_pkey");
        slcb.doEQPSimple2( "program_pkey", "program_pkey");
        slcb.doInLongCondition("program_id", "program_id");        
    
// 1 Program pname
        slcb.doIlPSimple2( "program_pname", "program_pname");
    

        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");        
    
// 1 UserLon pname
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  program_user_lon.id as program_user_lon_id,program_user_lon.pkey as program_user_lon_pkey,program.id as program_id,program.pkey as program_pkey,program.pname as program_pname,user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname   FROM   program_user_lon,  program as program,  user_lon as user_lon   WHERE  program_user_lon.program_id = program.id AND program_user_lon.user_lon_id = user_lon.id";
    private static String sqlCount = "SELECT  count(program_user_lon.id)   FROM   program_user_lon,  program as program,  user_lon as user_lon   WHERE  program_user_lon.program_id = program.id AND program_user_lon.user_lon_id = user_lon.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        