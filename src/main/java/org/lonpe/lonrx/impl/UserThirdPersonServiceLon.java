
        
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
public class UserThirdPersonServiceLon extends AbstractLon<UserThirdPerson> implements IServiceLon<UserThirdPerson>{  
        
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
        
    
        DCModel dcm = new DCModel("userThirdPerson");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             
    

    //1
    final SMto userLon = new SMto("userLon","userLon"); 
    userLon.setPc("pname"); 
    dcm.addSMto(userLon); 
//1
    final SMto thirdPerson = new SMto("thirdPerson","thirdPerson"); 
    thirdPerson.setPc("pname"); 
    dcm.addSMto(thirdPerson); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","user_third_person_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("user_third_person.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("user_third_person.user_third_person_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "user_third_person_pkey");
                   // userLon --------------------
        names.add("userLon_id");
        
        insertMapFields.put("user_third_person.user_lon_id","userLon_id");
        
        
        names.add("userLon_pkey");        
            sortMapFields.put( "userLon_pkey", "user_lon_pkey");

        
   names.add("userLon_pname");
            sortMapFields.put( "userLon_pname", "user_lon_pname");
            // thirdPerson --------------------
        names.add("thirdPerson_id");
        
        insertMapFields.put("user_third_person.third_person_id","thirdPerson_id");
        
        
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

    protected void fillTupleInsert(final UserThirdPerson dc0, final Tuple t){
                t.addString(dc0.getPkey());
   
                if(dc0.getUserLon()!=null){
            t.addLong(dc0.getUserLon().getId());
                }
   
                if(dc0.getThirdPerson()!=null){
            t.addLong(dc0.getThirdPerson().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO user_third_person(pkey,user_lon_id,third_person_id,id) VALUES ($1,$2,$3,(select nextval('seq_user_third_person'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(UserThirdPerson userThirdPerson) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(userThirdPerson, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "user_third_person", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "user_third_person_pkey");
    slcb.doEqInPSimple( "pkey", "user_third_person_pkey");
        
        slcb.doIlPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doEQPSimple2( "userLon_pkey", "user_lon_pkey");
        slcb.doInLongCondition("userLon_id", "user_lon_id");        
    // //UserLon 4
        slcb.doIlPSimple2( "userLon_pname", "user_lon_pname");
    

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");        
    // //ThirdPerson 1
        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  user_third_person.id as user_third_person_id,user_third_person.pkey as user_third_person_pkey,user_lon.id as user_lon_id,user_lon.pkey as user_lon_pkey,user_lon.pname as user_lon_pname,third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname   FROM   user_third_person,  user_lon as user_lon,  third_person as third_person   WHERE  user_third_person.user_lon_id = user_lon.id AND user_third_person.third_person_id = third_person.id";
    private static String sqlCount = "SELECT  count(user_third_person.id)   FROM   user_third_person,  user_lon as user_lon,  third_person as third_person   WHERE  user_third_person.user_lon_id = user_lon.id AND user_third_person.third_person_id = third_person.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        