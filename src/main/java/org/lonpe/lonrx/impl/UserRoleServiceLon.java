
        
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
public class UserRoleServiceLon extends AbstractLon<UserRole> implements IServiceLon<UserRole>{  
        
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
        
    
        DCModel dcm = new DCModel("userRole");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    
//1
    final SMto userLon = new SMto("userLon","userLon"); 
    userLon.setPc("pname"); 
    dcm.addSMto(userLon); 

//1
    final SMto role = new SMto("role","role"); 
    role.setPc("pname"); 
    dcm.addSMto(role); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","user_role_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("user_role.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("user_role.user_role_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "user_role_pkey");
                   // userLon --------------------
        names.add("userLon_id");
        
        insertMapFields.put("user_role.user_lon_id","userLon_id");
        
        
        names.add("userLon_pkey");        
            sortMapFields.put( "userLon_pkey", "user_lon_pkey");

        
   names.add("userLon_pname");
            sortMapFields.put( "userLon_pname", "user_lon_pname");
            // role --------------------
        names.add("role_id");
        
        insertMapFields.put("user_role.role_id","role_id");
        
        
        names.add("role_pkey");        
            sortMapFields.put( "role_pkey", "role_pkey");

        
   names.add("role_pname");
            sortMapFields.put( "role_pname", "role_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final UserRole dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getUserLon()!=null){
            t.addLong(dc0.getUserLon().getId());
                }
   
                if(dc0.getRole()!=null){
            t.addLong(dc0.getRole().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO user_role(pkey,user_lon_id,role_id,id) VALUES ($1,$2,$3,(select nextval('seq_user_role'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(UserRole userRole) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(userRole, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("user_role", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "user_role_pkey");
    slcb.doEqInPSimple( "pkey", "user_role_pkey");
        
        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");        
    
// 1 UserLon pname
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");
    

        slcb.doIlPSimple2( "role_pkey", "role_pkey");
        slcb.doEQPSimple2( "role_pkey", "role_pkey");
        slcb.doInLongCondition("role_id", "role_id");        
    
// 1 Role pname
        slcb.doIlPSimple2( "role_pname", "role_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  user_role.id as user_role_id,user_role.pkey as user_role_pkey,user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,role.id as role_id,role.pkey as role_pkey,role.pname as role_pname   FROM   user_role,  user_lon as user_lon,  role as role   WHERE  user_role.user_lon_id = user_lon.id AND user_role.role_id = role.id";
    private static String sqlCount = "SELECT  count(user_role.id)   FROM   user_role,  user_lon as user_lon,  role as role   WHERE  user_role.user_lon_id = user_lon.id AND user_role.role_id = role.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        