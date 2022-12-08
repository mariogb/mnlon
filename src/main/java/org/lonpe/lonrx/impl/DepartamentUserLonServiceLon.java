
        
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
public class DepartamentUserLonServiceLon extends AbstractLon<DepartamentUserLon> implements IServiceLon<DepartamentUserLon>{  
        
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
        
    
        DCModel dcm = new DCModel("departamentUserLon");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    //1
    final SMto departament = new SMto("departament","departament"); 
    departament.setPc("pname"); 
    dcm.addSMto(departament); 
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
    sortMapFields.put("id","departament_user_lon_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("departament_user_lon.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("departament_user_lon.departament_user_lon_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "departament_user_lon_pkey");
                   // departament --------------------
        names.add("departament_id");
        
        insertMapFields.put("departament_user_lon.departament_id","departament_id");
        
        
        names.add("departament_pkey");        
            sortMapFields.put( "departament_pkey", "departament_pkey");

        
   names.add("departament_pname");
            sortMapFields.put( "departament_pname", "departament_pname");
            // userLon --------------------
        names.add("userLon_id");
        
        insertMapFields.put("departament_user_lon.user_lon_id","userLon_id");
        
        
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

    protected void fillTupleInsert(final DepartamentUserLon dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getDepartament()!=null){
            t.addLong(dc0.getDepartament().getId());
                }
   
                if(dc0.getUserLon()!=null){
            t.addLong(dc0.getUserLon().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO departament_user_lon(pkey,departament_id,user_lon_id,id) VALUES ($1,$2,$3,(select nextval('seq_departament_user_lon'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(DepartamentUserLon departamentUserLon) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(departamentUserLon, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "departament_user_lon", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "departament_user_lon_pkey");
    slcb.doEqInPSimple( "pkey", "departament_user_lon_pkey");
        
        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");        
    // //Departament 3
        slcb.doIlPSimple2( "departament_pname", "departament_pname");
    

        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");        
    // //UserLon 4
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  departament_user_lon.id as departament_user_lon_id,departament_user_lon.pkey as departament_user_lon_pkey,departament.id as departament_id,departament.pkey as departament_pkey,departament.pname as departament_pname,user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname   FROM   departament_user_lon,  departament as departament,  user_lon as user_lon   WHERE  departament_user_lon.departament_id = departament.id AND departament_user_lon.user_lon_id = user_lon.id";
    private static String sqlCount = "SELECT  count(departament_user_lon.id)   FROM   departament_user_lon,  departament as departament,  user_lon as user_lon   WHERE  departament_user_lon.departament_id = departament.id AND departament_user_lon.user_lon_id = user_lon.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        