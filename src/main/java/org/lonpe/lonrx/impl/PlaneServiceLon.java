
        
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
public class PlaneServiceLon extends AbstractLon<Plane> implements IServiceLon<Plane>{  
        
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
        
    
        DCModel dcm = new DCModel("plane", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property plate   
                
        final SProperty plate = new SProperty("plate", STRING,true,false);   
            
             
    dcm.addSProperty(plate);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property seats   
                
        final SProperty seats = new SProperty("seats", INTEGER,true,false);   
            
                            
        seats.setMin(0);  
    dcm.addSProperty(seats);             
    

    
//1
    final SMto laCompania = new SMto("laCompania","airCompany"); 
    laCompania.setPc("pname"); 
    dcm.addSMto(laCompania); 
    

    
//ON RELATION plane    
    final SOtm fligths = new SOtm("fligths","fligth","plane");                    
    dcm.addSOtm(fligths); 
        //
            final SOtm fligthInstances = new SOtm("fligthInstances","fligthInstance","theFligth"); 
            dcm.addSOtm2(fligthInstances);
            
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
    sortMapFields.put("id","plane_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("plane.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("plane.plane_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "plane_pkey");
                    
//plate -------------------------------------------
    names.add("plate");
    insertMapFields.put("plane.plate","plate");  
    sortMapFields.put("plate", "plane_plate");
                    
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("plane.pname","pname");  
    sortMapFields.put("pname", "plane_pname");
                    
//seats -------------------------------------------
    names.add("seats");
    insertMapFields.put("plane.seats","seats");  
    sortMapFields.put("seats", "plane_seats");
               // laCompania --------------------
        names.add("laCompania_id");
        
        insertMapFields.put("plane.la_compania_id","laCompania_id");
        
        
        names.add("laCompania_pkey");        
            sortMapFields.put( "laCompania_pkey", "la_compania_pkey");

        
   names.add("laCompania_pname");
            sortMapFields.put( "laCompania_pname", "la_compania_pname");
            
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final Plane dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addString(dc0.getPlate());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getSeats());
   
                if(dc0.getLaCompania()!=null){
            t.addLong(dc0.getLaCompania().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO plane(pkey,plate,pname,seats,la_compania_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_plane'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Plane plane) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(plane, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("plane", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "plane_pkey");
    slcb.doEqInPSimple( "pkey", "plane_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "plane_pname");
     slcb.doEqInPSimple( "pname", "plane_pname");               
    slcb.doGEPSimpleInt( "seats", "plane_seats");
    slcb.doLTPSimpleInt( "seats", "plane_seats");                 
        
        slcb.doIlPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doEQPSimple2( "laCompania_pkey", "la_compania_pkey");
        slcb.doInLongCondition("laCompania_id", "la_compania_id");        
    
// 1 AirCompany pname
        slcb.doIlPSimple2( "laCompania_pname", "la_compania_pname");
    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  plane.id as plane_id,plane.pkey as plane_pkey,plane.plate as plane_plate,plane.pname as plane_pname,plane.seats as plane_seats,la_compania.id as la_compania_id,la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname   FROM   plane,  air_company as la_compania   WHERE  plane.la_compania_id = la_compania.id";
    private static String sqlCount = "SELECT  count(plane.id)   FROM   plane,  air_company as la_compania   WHERE  plane.la_compania_id = la_compania.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        