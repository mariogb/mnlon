
        
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
public class ThirdPersonServiceLon extends AbstractLon<ThirdPerson> implements IServiceLon<ThirdPerson>{  
        
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
        
    
        DCModel dcm = new DCModel("thirdPerson", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property rfc   
                
        final SProperty rfc = new SProperty("rfc", STRING,true,true);   
            
            
// hasIndex 
    rfc.setWithIndex(true);   
    dcm.addSProperty(rfc);             //Create property tipo   
                
        final SProperty tipo = new SProperty("tipo", STRING,true,false);   
            
            
        final List<String> tipoInList = new ArrayList<>();
                
        tipoInList.add("FISICA"); 
        tipoInList.add("MORAL"); 
        tipo.setInList(tipoInList);                 
    dcm.addSProperty(tipo);             
    

    
    

    
    final SOtm contractIns = new SOtm("contractIns","contractIn","undefined");                     
    dcm.addSOtm(contractIns); 
    final SOtm contractOuts = new SOtm("contractOuts","contractOut","undefined");                     
    dcm.addSOtm(contractOuts); 
    final SOtm userThirdPersons = new SOtm("userThirdPersons","userThirdPerson","undefined");                     
    dcm.addSOtm(userThirdPersons); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","third_person_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("third_person.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("third_person.third_person_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "third_person_pkey");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("third_person.pname","pname");  
    sortMapFields.put("pname", "third_person_pname");
                    
//rfc -------------------------------------------
    names.add("rfc");
    insertMapFields.put("third_person.rfc","rfc");  
//Used to map error on index to source property
    insertMapFields.put("third_person.third_person_uidx_rfc","rfc");  
    sortMapFields.put("rfc", "third_person_rfc");
                    
//tipo -------------------------------------------
    names.add("tipo");
    insertMapFields.put("third_person.tipo","tipo");  
    sortMapFields.put("tipo", "third_person_tipo");
                   
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final ThirdPerson dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPname());
        t.addString(dc0.getRfc());
        t.addString(dc0.getTipo());
    }

    

private static final String SQLINSERT = "INSERT INTO third_person(pkey,pname,rfc,tipo,id) VALUES ($1,$2,$3,$4,(select nextval('seq_third_person'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(ThirdPerson thirdPerson) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(thirdPerson, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "third_person", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "third_person_pkey");
    slcb.doEqInPSimple( "pkey", "third_person_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "third_person_pname");
     slcb.doEqInPSimple( "pname", "third_person_pname");             
// withIndex true
    slcb.doIlPSimple2( "rfc", "third_person_rfc");
    slcb.doEqInPSimple( "rfc", "third_person_rfc");                    
    slcb.doEqInPSimple( "tipo", "third_person_tipo");                     
        
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  third_person.id as third_person_id,third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,third_person.rfc as third_person_rfc,third_person.tipo as third_person_tipo   FROM   third_person ";
    private static String sqlCount = "SELECT  count(third_person.id)   FROM   third_person ";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        