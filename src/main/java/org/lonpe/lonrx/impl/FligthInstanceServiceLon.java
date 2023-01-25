
        
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
public class FligthInstanceServiceLon extends AbstractLon<FligthInstance> implements IServiceLon<FligthInstance>{  
        
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
        
    
        DCModel dcm = new DCModel("fligthInstance", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property inLocalDateTime   
                
        final SProperty inLocalDateTime = new SProperty("inLocalDateTime", LOCALDATETIME,true,false);   
            
             
    dcm.addSProperty(inLocalDateTime);             //Create property outLocalDateTime   
                
        final SProperty outLocalDateTime = new SProperty("outLocalDateTime", LOCALDATETIME,true,false);   
            
             
    dcm.addSProperty(outLocalDateTime);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             
    

    
//1
    final SMto theFligth = new SMto("theFligth","fligth"); 
    theFligth.setPc("pname"); 
    dcm.addSMto(theFligth); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","fligth_instance_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("fligth_instance.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("fligth_instance.fligth_instance_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "fligth_instance_pkey");
                    
//inLocalDateTime -------------------------------------------
    names.add("inLocalDateTime");
    insertMapFields.put("fligth_instance.in_local_date_time","inLocalDateTime");  
    sortMapFields.put("inLocalDateTime", "fligth_instance_in_local_date_time");
                
//outLocalDateTime -------------------------------------------
    names.add("outLocalDateTime");
    insertMapFields.put("fligth_instance.out_local_date_time","outLocalDateTime");  
    sortMapFields.put("outLocalDateTime", "fligth_instance_out_local_date_time");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("fligth_instance.pname","pname");  
    sortMapFields.put("pname", "fligth_instance_pname");
                   // theFligth --------------------
        names.add("theFligth_id");
        
        insertMapFields.put("fligth_instance.the_fligth_id","theFligth_id");
        
        
        names.add("theFligth_pkey");        
            sortMapFields.put( "theFligth_pkey", "the_fligth_pkey");

        
   names.add("theFligth_pname");
            sortMapFields.put( "theFligth_pname", "the_fligth_pname");
            //   fromAirport 
        names.add("fromAirport_id");
            
        names.add("fromAirport_pkey");
   names.add("fromAirport_pname");//   toAirport 
        names.add("toAirport_id");
            
        names.add("toAirport_pkey");
   names.add("toAirport_pname");//   plane 
        names.add("plane_id");
            
        names.add("plane_pkey");
   names.add("plane_pname");//   laCompania 
        names.add("laCompania_id");
            
        names.add("laCompania_pkey");
   names.add("laCompania_pname");
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final FligthInstance dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLocalDateTime(dc0.getInLocalDateTime());
        t.addLocalDateTime(dc0.getOutLocalDateTime());
        t.addString(dc0.getPname());
   
                if(dc0.getTheFligth()!=null){
            t.addLong(dc0.getTheFligth().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO fligth_instance(pkey,in_local_date_time,out_local_date_time,pname,the_fligth_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_fligth_instance'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(FligthInstance fligthInstance) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(fligthInstance, tuple);
    return save00(SQLINSERT, tuple, insertReturnMapFields);    
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000("fligth_instance", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "fligth_instance_pkey");
    slcb.doEqInPSimple( "pkey", "fligth_instance_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "fligth_instance_pname");
     slcb.doEqInPSimple( "pname", "fligth_instance_pname");            
        
        slcb.doIlPSimple2( "theFligth_pkey", "the_fligth_pkey");
        slcb.doEQPSimple2( "theFligth_pkey", "the_fligth_pkey");
        slcb.doInLongCondition("theFligth_id", "the_fligth_id");        
    
// 1 Fligth pname
        slcb.doIlPSimple2( "theFligth_pname", "the_fligth_pname");
    

        slcb.doIlPSimple2( "fromAirport_pkey", "airport_pkey");
        slcb.doEQPSimple2( "fromAirport_pkey", "airport_pkey");
        slcb.doInLongCondition("fromAirport_id", "airport_id");                  

//2  Airport pname

        slcb.doIlPSimple2( "fromAirport_pname", "airport_pname");                    

        slcb.doIlPSimple2( "toAirport_pkey", "airport_pkey");
        slcb.doEQPSimple2( "toAirport_pkey", "airport_pkey");
        slcb.doInLongCondition("toAirport_id", "airport_id");                  

//2  Airport pname

        slcb.doIlPSimple2( "toAirport_pname", "airport_pname");                    

        slcb.doIlPSimple2( "plane_pkey", "plane_pkey");
        slcb.doEQPSimple2( "plane_pkey", "plane_pkey");
        slcb.doInLongCondition("plane_id", "plane_id");                  

//2  Plane pname

        slcb.doIlPSimple2( "plane_pname", "plane_pname");                    

//3  AirCompany pname                                
        slcb.doIlPSimple2( "laCompania_pkey", "air_company_pkey");
        slcb.doEQPSimple2( "laCompania_pkey", "air_company_pkey");
        slcb.doInLongCondition("laCompania_id", "air_company_id");                            
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  fligth_instance.id as fligth_instance_id,fligth_instance.pkey as fligth_instance_pkey,fligth_instance.in_local_date_time as fligth_instance_in_local_date_time,fligth_instance.out_local_date_time as fligth_instance_out_local_date_time,fligth_instance.pname as fligth_instance_pname,the_fligth.id as the_fligth_id,the_fligth.pkey as the_fligth_pkey,the_fligth.pname as the_fligth_pname,from_airport.id as from_airport_id, from_airport.pkey as from_airport_pkey,from_airport.pname as from_airport_pname,to_airport.id as to_airport_id, to_airport.pkey as to_airport_pkey,to_airport.pname as to_airport_pname,plane.id as plane_id, plane.pkey as plane_pkey,plane.pname as plane_pname,la_compania.id as la_compania_id, la_compania.pkey as la_compania_pkey,la_compania.pname as la_compania_pname   FROM   fligth_instance,  fligth as the_fligth,  airport as from_airport,  airport as to_airport,  plane as plane,  air_company as la_compania   WHERE  fligth_instance.the_fligth_id = the_fligth.id AND the_fligth.from_airport_id = from_airport.id AND the_fligth.to_airport_id = to_airport.id AND the_fligth.plane_id = plane.id AND plane.la_compania_id = la_compania.id";
    private static String sqlCount = "SELECT  count(fligth_instance.id)   FROM   fligth_instance,  fligth as the_fligth,  airport as from_airport,  airport as to_airport,  plane as plane,  air_company as la_compania   WHERE  fligth_instance.the_fligth_id = the_fligth.id AND the_fligth.from_airport_id = from_airport.id AND the_fligth.to_airport_id = to_airport.id AND the_fligth.plane_id = plane.id AND plane.la_compania_id = la_compania.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        