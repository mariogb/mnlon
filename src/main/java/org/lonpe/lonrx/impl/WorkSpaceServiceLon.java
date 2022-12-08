
        
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
public class WorkSpaceServiceLon extends AbstractLon<WorkSpace> implements IServiceLon<WorkSpace>{  
        
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
        
    
        DCModel dcm = new DCModel("workSpace", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property capacity   
                
        final SProperty capacity = new SProperty("capacity", LONG,true,false);   
            
             
    dcm.addSProperty(capacity);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property type   
                
        final SProperty type = new SProperty("type", STRING,true,false);   
            
            
        final List<String> typeInList = new ArrayList<>();
                
        typeInList.add("Caja"); 
        typeInList.add("Bodega"); 
        typeInList.add("Oficina"); 
        typeInList.add("Aula"); 
        typeInList.add("Transporte"); 
        type.setInList(typeInList);                 
    dcm.addSProperty(type);             
    

    //1
    final SMto workSpaceGroup = new SMto("workSpaceGroup","workSpaceGroup"); 
    workSpaceGroup.setPc("pname"); 
    dcm.addSMto(workSpaceGroup); 
    

    
    final SOtm appointments = new SOtm("appointments","appointment","undefined");                     
    dcm.addSOtm(appointments); 
    final SOtm stockRack = new SOtm("stockRack","stockRack","undefined");                     
    dcm.addSOtm(stockRack); 
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","work_space_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("work_space.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("work_space.work_space_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "work_space_pkey");
                    
//capacity -------------------------------------------
    names.add("capacity");
    insertMapFields.put("work_space.capacity","capacity");  
    sortMapFields.put("capacity", "work_space_capacity");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("work_space.pname","pname");  
    sortMapFields.put("pname", "work_space_pname");
                    
//type -------------------------------------------
    names.add("type");
    insertMapFields.put("work_space.type","type");  // workSpaceGroup --------------------
        names.add("workSpaceGroup_id");
        
        insertMapFields.put("work_space.work_space_group_id","workSpaceGroup_id");
        
        
        names.add("workSpaceGroup_pkey");        
            sortMapFields.put( "workSpaceGroup_pkey", "work_space_group_pkey");

        
   names.add("workSpaceGroup_pname");
            sortMapFields.put( "workSpaceGroup_pname", "work_space_group_pname");
            //   base 
        names.add("base_id");
            
        names.add("base_pkey");
   names.add("base_pname");
    }


    private static final LinkedHashSet<String> names = new LinkedHashSet<>();
    //Map field insert/update to property 
    private static final HashMap<String,String> insertMapFields = new HashMap<>() ; 
    //Map property to field order 
    private static final Map<String, String> sortMapFields = new HashMap<>();
    private static final Map<String, String> insertReturnMapFields = new HashMap<>();

    protected void fillTupleInsert(final WorkSpace dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addLong(dc0.getCapacity());
        t.addString(dc0.getPname());
        t.addString(dc0.getType());
   
                if(dc0.getWorkSpaceGroup()!=null){
            t.addLong(dc0.getWorkSpaceGroup().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO work_space(pkey,capacity,pname,type,work_space_group_id,id) VALUES ($1,$2,$3,$4,$5,(select nextval('seq_work_space'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(WorkSpace workSpace) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(workSpace, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "work_space", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "work_space_pkey");
    slcb.doEqInPSimple( "pkey", "work_space_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "work_space_pname");
     slcb.doEqInPSimple( "pname", "work_space_pname");               
    slcb.doGEPSimpleLong( "capacity", "work_space_capacity");
    slcb.doLPSimpleLong( "capacity", "work_space_capacity");                 
    slcb.doEqInPSimple( "type", "work_space_type");                     
        
        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");        
    // //WorkSpaceGroup 1
        slcb.doIlPSimple2( "workSpaceGroup_pname", "work_space_group_pname");
    

        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");                  
//Base 1

        slcb.doIlPSimple2( "base_pname", "base_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.capacity as work_space_capacity,work_space.pname as work_space_pname,work_space.type as work_space_type,work_space_group.id as work_space_group_id,work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname   FROM   work_space,  work_space_group as work_space_group,  base as base   WHERE  work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id";
    private static String sqlCount = "SELECT  count(work_space.id)   FROM   work_space,  work_space_group as work_space_group,  base as base   WHERE  work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        