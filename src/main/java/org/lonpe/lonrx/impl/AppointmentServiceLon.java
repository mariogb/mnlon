
        
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
public class AppointmentServiceLon extends AbstractLon<Appointment> implements IServiceLon<Appointment>{  
        
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
        
    
        DCModel dcm = new DCModel("appointment", "pname");    
        

        //Create property pkey   
                
        final SProperty pkey = new SProperty("pkey", STRING,true,true);   
            
             
    dcm.addSProperty(pkey);             //Create property endHour   
                
        final SProperty endHour = new SProperty("endHour", INTEGER,true,false);   
            
                            
        endHour.setMin(0); 
        endHour.setMax(23);  
    dcm.addSProperty(endHour);             //Create property endMinute   
                
        final SProperty endMinute = new SProperty("endMinute", INTEGER,true,false);   
            
                            
        endMinute.setMin(0); 
        endMinute.setMax(59);  
    dcm.addSProperty(endMinute);             //Create property pname   
                
        final SProperty pname = new SProperty("pname", STRING,true,false);   
            
              
//PC
        dcm.setPc("pname");   
    dcm.addSProperty(pname);             //Create property startHour   
                
        final SProperty startHour = new SProperty("startHour", INTEGER,true,false);   
            
                            
        startHour.setMin(0); 
        startHour.setMax(23);  
    dcm.addSProperty(startHour);             //Create property startMinute   
                
        final SProperty startMinute = new SProperty("startMinute", INTEGER,true,false);   
            
                            
        startMinute.setMin(0); 
        startMinute.setMax(59);  
    dcm.addSProperty(startMinute);             //Create property weekDay   
                
        final SProperty weekDay = new SProperty("weekDay", INTEGER,true,false);   
            
            
        final List<String> weekDayInList = new ArrayList<>();
                
        weekDayInList.add("0"); 
        weekDayInList.add("1"); 
        weekDayInList.add("2"); 
        weekDayInList.add("3"); 
        weekDayInList.add("4"); 
        weekDayInList.add("5"); 
        weekDayInList.add("6"); 
        weekDay.setInList(weekDayInList);                 
    dcm.addSProperty(weekDay);             
    

    //1
    final SMto contract = new SMto("contract","contractOut"); 
    contract.setPc("pname"); 
    dcm.addSMto(contract); 
//1
    final SMto workSpace = new SMto("workSpace","workSpace"); 
    workSpace.setPc("pname"); 
    dcm.addSMto(workSpace); 
//1
    final SMto departamentJobInstance = new SMto("departamentJobInstance","departamentJobInstance"); 
    departamentJobInstance.setPc("pname"); 
    dcm.addSMto(departamentJobInstance); 
    

    
    
        
    return dcm;
    
    
    }  

        
    @PostConstruct
    private void init0(){  
        this.dCModel = elModelo();
        insertReturnMapFields.put("id","id");
        insertReturnMapFields.put("pkey","pkey");
        
    //ID ----------------------------------
    names.add("id");
    sortMapFields.put("id","appointment_id"); 
//pkey -------------------------------------------
    names.add("pkey");
    insertMapFields.put("appointment.pkey","pkey");  
//Used to map error on index to source property
    insertMapFields.put("appointment.appointment_uidx_pkey","pkey");  
    sortMapFields.put("pkey", "appointment_pkey");
                    
//endHour -------------------------------------------
    names.add("endHour");
    insertMapFields.put("appointment.end_hour","endHour");  
    sortMapFields.put("endHour", "appointment_end_hour");
                
//endMinute -------------------------------------------
    names.add("endMinute");
    insertMapFields.put("appointment.end_minute","endMinute");  
    sortMapFields.put("endMinute", "appointment_end_minute");
                
//pname -------------------------------------------
    names.add("pname");
    insertMapFields.put("appointment.pname","pname");  
    sortMapFields.put("pname", "appointment_pname");
                    
//startHour -------------------------------------------
    names.add("startHour");
    insertMapFields.put("appointment.start_hour","startHour");  
    sortMapFields.put("startHour", "appointment_start_hour");
                
//startMinute -------------------------------------------
    names.add("startMinute");
    insertMapFields.put("appointment.start_minute","startMinute");  
    sortMapFields.put("startMinute", "appointment_start_minute");
                
//weekDay -------------------------------------------
    names.add("weekDay");
    insertMapFields.put("appointment.week_day","weekDay");  
    sortMapFields.put("weekDay", "appointment_week_day");
               // contract --------------------
        names.add("contract_id");
        
        insertMapFields.put("appointment.contract_id","contract_id");
        
        
        names.add("contract_pkey");        
            sortMapFields.put( "contract_pkey", "contract_pkey");

        
   names.add("contract_pname");
            sortMapFields.put( "contract_pname", "contract_pname");
            //   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");
            
        names.add("departamentBaseTimePeriod_pkey");//   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   departament 
        names.add("departament_id");
            
        names.add("departament_pkey");
   names.add("departament_pname");//   thirdPerson 
        names.add("thirdPerson_id");
            
        names.add("thirdPerson_pkey");
   names.add("thirdPerson_pname");// workSpace --------------------
        names.add("workSpace_id");
        
        insertMapFields.put("appointment.work_space_id","workSpace_id");
        
        
        names.add("workSpace_pkey");        
            sortMapFields.put( "workSpace_pkey", "work_space_pkey");

        
   names.add("workSpace_pname");
            sortMapFields.put( "workSpace_pname", "work_space_pname");
            //   workSpaceGroup 
        names.add("workSpaceGroup_id");
            
        names.add("workSpaceGroup_pkey");
   names.add("workSpaceGroup_pname");//   base 
        names.add("base_id");
            
        names.add("base_pkey");
   names.add("base_pname");// departamentJobInstance --------------------
        names.add("departamentJobInstance_id");
        
        insertMapFields.put("appointment.departament_job_instance_id","departamentJobInstance_id");
        
        
        names.add("departamentJobInstance_pkey");        
            sortMapFields.put( "departamentJobInstance_pkey", "departament_job_instance_pkey");

        
   names.add("departamentJobInstance_pname");
            sortMapFields.put( "departamentJobInstance_pname", "departament_job_instance_pname");
            //   departamentJob 
        names.add("departamentJob_id");
            
        names.add("departamentJob_pkey");
   names.add("departamentJob_pname");//   departament 
        names.add("departament_id");
            
        names.add("departament_pkey");
   names.add("departament_pname");//   departamentBaseTimePeriod 
        names.add("departamentBaseTimePeriod_id");
            
        names.add("departamentBaseTimePeriod_pkey");//   baseTimePeriod 
        names.add("baseTimePeriod_id");
            
        names.add("baseTimePeriod_pkey");//   departament 
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

    protected void fillTupleInsert(final Appointment dc0, final Tuple t){
                t.addString(dc0.getPkey());
        t.addInteger(dc0.getEndHour());
        t.addInteger(dc0.getEndMinute());
        t.addString(dc0.getPname());
        t.addInteger(dc0.getStartHour());
        t.addInteger(dc0.getStartMinute());
        t.addInteger(dc0.getWeekDay());
   
                if(dc0.getContract()!=null){
            t.addLong(dc0.getContract().getId());
                }
   
                if(dc0.getWorkSpace()!=null){
            t.addLong(dc0.getWorkSpace().getId());
                }
   
                if(dc0.getDepartamentJobInstance()!=null){
            t.addLong(dc0.getDepartamentJobInstance().getId());
                }
    }

    

private static final String SQLINSERT = "INSERT INTO appointment(pkey,end_hour,end_minute,pname,start_hour,start_minute,week_day,contract_id,work_space_id,departament_job_instance_id,id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,(select nextval('seq_appointment'))) returning id,pkey";



@Override
public Single<Map<String, Object>>  save(Appointment appointment) {
    final Tuple tuple = Tuple.tuple();
    fillTupleInsert(appointment, tuple);
    return crudLon.saveOneWithNames(SQLINSERT, tuple, insertReturnMapFields);
}




    @Override
    public Single<Map<String,Object>> doList(final ObjForQuery objForQuery) {
        return doList000(crudLon, "appointment", objForQuery);
        
    }

  

    @Override
    public ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple){
        final SqlLonConditionsBuilder slcb = new SqlLonConditionsBuilder(params, sortMapFields,tuple.getDelegate());
        //*---PKEY ---

    slcb.doIlPSimple2( "pkey", "appointment_pkey");
    slcb.doEqInPSimple( "pkey", "appointment_pkey");
//*---PC ---" + pc.n
     slcb.doIlPSimple2( "pname", "appointment_pname");
     slcb.doEqInPSimple( "pname", "appointment_pname");               
    slcb.doGEPSimpleInt( "endHour", "appointment_end_hour");
    slcb.doLTPSimpleInt( "endHour", "appointment_end_hour");                    
    slcb.doGEPSimpleInt( "endMinute", "appointment_end_minute");
    slcb.doLTPSimpleInt( "endMinute", "appointment_end_minute");                    
    slcb.doGEPSimpleInt( "startHour", "appointment_start_hour");
    slcb.doLTPSimpleInt( "startHour", "appointment_start_hour");                    
    slcb.doGEPSimpleInt( "startMinute", "appointment_start_minute");
    slcb.doLTPSimpleInt( "startMinute", "appointment_start_minute");                    
    slcb.doGEPSimpleInt( "weekDay", "appointment_week_day");
    slcb.doLTPSimpleInt( "weekDay", "appointment_week_day");                 
        
        slcb.doIlPSimple2( "contract_pkey", "contract_pkey");
        slcb.doEQPSimple2( "contract_pkey", "contract_pkey");
        slcb.doInLongCondition("contract_id", "contract_id");        
    // //ContractOut 1
        slcb.doIlPSimple2( "contract_pname", "contract_pname");
    

        slcb.doIlPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doEQPSimple2( "workSpace_pkey", "work_space_pkey");
        slcb.doInLongCondition("workSpace_id", "work_space_id");        
    // //WorkSpace 2
        slcb.doIlPSimple2( "workSpace_pname", "work_space_pname");
    

        slcb.doIlPSimple2( "departamentJobInstance_pkey", "departament_job_instance_pkey");
        slcb.doEQPSimple2( "departamentJobInstance_pkey", "departament_job_instance_pkey");
        slcb.doInLongCondition("departamentJobInstance_id", "departament_job_instance_id");        
    // //DepartamentJobInstance 3
        slcb.doIlPSimple2( "departamentJobInstance_pname", "departament_job_instance_pname");
    

        slcb.doIlPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doEQPSimple2( "departamentBaseTimePeriod_pkey", "departament_base_time_period_pkey");
        slcb.doInLongCondition("departamentBaseTimePeriod_id", "departament_base_time_period_id");                  
//DepartamentBaseTimePeriod undefined

        slcb.doIlPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doEQPSimple2( "baseTimePeriod_pkey", "base_time_period_pkey");
        slcb.doInLongCondition("baseTimePeriod_id", "base_time_period_id");                            

        slcb.doIlPSimple2( "departament_pkey", "departament_pkey");
        slcb.doEQPSimple2( "departament_pkey", "departament_pkey");
        slcb.doInLongCondition("departament_id", "departament_id");                            

        slcb.doIlPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doEQPSimple2( "thirdPerson_pkey", "third_person_pkey");
        slcb.doInLongCondition("thirdPerson_id", "third_person_id");                  
//ThirdPerson 1

        slcb.doIlPSimple2( "thirdPerson_pname", "third_person_pname");                    

        slcb.doIlPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doEQPSimple2( "workSpaceGroup_pkey", "work_space_group_pkey");
        slcb.doInLongCondition("workSpaceGroup_id", "work_space_group_id");                  
//WorkSpaceGroup 1

        slcb.doIlPSimple2( "workSpaceGroup_pname", "work_space_group_pname");                    

        slcb.doIlPSimple2( "base_pkey", "base_pkey");
        slcb.doEQPSimple2( "base_pkey", "base_pkey");
        slcb.doInLongCondition("base_id", "base_id");                            

        slcb.doIlPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doEQPSimple2( "departamentJob_pkey", "departament_job_pkey");
        slcb.doInLongCondition("departamentJob_id", "departament_job_id");                  
//DepartamentJob 4

        slcb.doIlPSimple2( "departamentJob_pname", "departament_job_pname");                    
        return slcb.getConditionInfo();
    }

    public Set<String> getNames(){
        return names;
    }
   
    private static String sqlList = "SELECT  appointment.id as appointment_id,appointment.pkey as appointment_pkey,appointment.end_hour as appointment_end_hour,appointment.end_minute as appointment_end_minute,appointment.pname as appointment_pname,appointment.start_hour as appointment_start_hour,appointment.start_minute as appointment_start_minute,appointment.week_day as appointment_week_day,contract.id as contract_id,contract.pkey as contract_pkey,contract.pname as contract_pname,departament_base_time_period.id as departament_base_time_period_id, departament_base_time_period.pkey as departament_base_time_period_pkey,base_time_period.id as base_time_period_id, base_time_period.pkey as base_time_period_pkey,departament.id as departament_id, departament.pkey as departament_pkey,departament.pname as departament_pname,third_person.id as third_person_id, third_person.pkey as third_person_pkey,third_person.pname as third_person_pname,work_space.id as work_space_id,work_space.pkey as work_space_pkey,work_space.pname as work_space_pname,work_space_group.id as work_space_group_id, work_space_group.pkey as work_space_group_pkey,work_space_group.pname as work_space_group_pname,base.id as base_id, base.pkey as base_pkey,base.pname as base_pname,departament_job_instance.id as departament_job_instance_id,departament_job_instance.pkey as departament_job_instance_pkey,departament_job_instance.pname as departament_job_instance_pname,departament_job.id as departament_job_id, departament_job.pkey as departament_job_pkey,departament_job.pname as departament_job_pname   FROM   appointment,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  work_space as work_space,  work_space_group as work_space_group,  base as base,  departament_job_instance as departament_job_instance,  departament_job as departament_job   WHERE  appointment.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND contract.third_person_id = third_person.id AND appointment.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id AND appointment.departament_job_instance_id = departament_job_instance.id AND departament_job_instance.departament_job_id = departament_job.id";
    private static String sqlCount = "SELECT  count(appointment.id)   FROM   appointment,  contract_out as contract,  departament_base_time_period as departament_base_time_period,  base_time_period as base_time_period,  departament as departament,  third_person as third_person,  work_space as work_space,  work_space_group as work_space_group,  base as base,  departament_job_instance as departament_job_instance,  departament_job as departament_job   WHERE  appointment.contract_id = contract.id AND contract.departament_base_time_period_id = departament_base_time_period.id AND departament_base_time_period.base_time_period_id = base_time_period.id AND departament_base_time_period.departament_id = departament.id AND contract.third_person_id = third_person.id AND appointment.work_space_id = work_space.id AND work_space.work_space_group_id = work_space_group.id AND work_space_group.base_id = base.id AND appointment.departament_job_instance_id = departament_job_instance.id AND departament_job_instance.departament_job_id = departament_job.id";
    

    @Override
    public String getSqlForList() {
        return sqlList;
    }
    @Override
    public String getSqlForCount() {
        return sqlCount;
    }



}   
    
    
    
        