/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.authentication.Authentication;
import io.reactivex.Single;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.lonpe.forquery.HttpsParamsToQrys;
import org.lonpe.forquery.ObjForQuery;
import org.reactivestreams.Publisher;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

/**
 *
 * @author l5
 */
@Controller
public abstract class AbstractLonController<DC> {

    private final HttpsParamsToQrys httpsParamsToQrys = new HttpsParamsToQrys();
    
    public Publisher<HttpResponse<Map<String, Object>>> processSave(Single<Map<String, Object>> ss) {

        return RxJava2Adapter.singleToMono(ss).map(e -> {
            return HttpResponse.ok(e);
        });
    }
    
    public Mono<Map<String, Object>> convert(Single<Map<String, Object>> ss) {

        return RxJava2Adapter.singleToMono(ss);
    }
    
    

    @io.micronaut.http.annotation.Error(exception = IOException.class)
    public Map<String, Object> onIOException(HttpRequest request, IOException ex) {
        Map<String, Object> m = new HashMap<>();
        ex.printStackTrace();
        m.put("message", ex.getMessage());
        return m;
    }

    protected String upload0(CompletedFileUpload file) throws IOException {
        File tempFile = File.createTempFile(file.getFilename(), "temp");
        Path path = Paths.get(tempFile.getAbsolutePath());
        Files.write(path, file.getBytes());
        return tempFile.getPath();
    }

    public ObjForQuery doObjForQuery(Authentication authentication, HttpRequest request) {
       
        return httpsParamsToQrys.doObjForQuery(authentication, request);
    }
////    @Inject
////    EntityPermisionRolePgLon entityPermisionRolePgLon;
//    
//    private HttpsParamsToQrys httpsParamsToQrys = new HttpsParamsToQrys();
//    
//    
//    
//    
//    
////    
//    public Single<MutableHttpResponse<Map>> processSave(Single<Map> ss) {
//        return ss.map((Map t) -> HttpResponse.created(t));
//    }
//
//    public Single<MutableHttpResponse<Map>> processUpdate(Single<Map> ss) {
//        return ss.map((Map t) -> HttpResponse.created(t));
//    }
//
////    public boolean isAuthPathByEntityPermissionRole(Authentication authentication){
////         final Map<String, Object> attributes = authentication.getAttributes();
////         final String typelon = (String) attributes.get("TYPELON");
////         if (!typelon.equals("ADM")) {
////              final Object ID0 = attributes.get("ID");
////             if(ID0==null){
////                 return false;
////             }
////             final Long ID = Long.parseLong(ID0.toString());
////             
////               final Single<Map> userLonPermissionUnique = entityPermisionRolePgLon.userLonPermissionUnique(ID, "aa", "ddd");
////               userLonPermissionUnique.flatMap(mapper)
////               
////         }
////        return true;
////    
////    }
////    public void verifySave(Authentication authentication, HttpRequest request, CrudLon crudLon){
////        final HttpParameters parameters = request.getParameters();
////        //final Map<String, List<String>> params = parameters.asMap();
////        final Map<String, Object> attributes = authentication.getAttributes();
////        final String typelon = (String) attributes.get("TYPELON");
////        
////    
////        crudLon.executeQry("select contract_out.id from contract_out, departamenBaseTime, baseTimePeriod, base, timePeriod "
////                + " where "
////                + "contract_out.departamentBaseTimePeriod_id = departamentBaseTimePeriod.id  "
////                + "and departamentBaseTimePeriod.baseTime_id = baseTime.id  "
////                + "and baseTimePeriod.base_id = base_id "
////                + "and baseTimePeriod.timePeriod_id =  timePeriod.id  "
////                + "and timePeriod.type = 'AAA'"
////                + "and departamentBaseTimePeriod.departament_id = departament.id"
////                + "and base.id in ()  and departament.id in ()", null);
////               
////            crudLon.executeQry("select document_out.id from document_out, contract_out, departamenBaseTime, baseTimePeriod, base, timePeriod "
////                + " where"
////                + " document_out.id = contract.id "
////                + "and contract_out.departamentBaseTimePeriod_id = departamentBaseTimePeriod.id  "
////                + "and departamentBaseTimePeriod.baseTime_id = baseTime.id  "
////                + "and baseTimePeriod.base_id = base_id "
////                + "and baseTimePeriod.timePeriod_id =  timePeriod.id  "
////                + "and timePeriod.type = 'AAA'"
////                + "and departamentBaseTimePeriod.departament_id = departament.id"
////                + "and base.id in ()  and departament.id in ()", null);
////                
////        
////        
////
////    }
////    
//    
////    private ObjForQuery doObjForQuery0(final Map<String, Object> attributes ,Map<String, List<String>> params){
////        
////    }
////    
//    
//    public ObjForQuery doObjForQuery(Authentication authentication, HttpRequest request) {
//       
//        return httpsParamsToQrys.doObjForQuery(authentication, request);
////        final HttpParameters parameters = request.getParameters();
////        final Map<String, List<String>> params = parameters.asMap();
////        final Long max = doMax(parameters);
////        final Long offset = doOffset(parameters);
////        final Map<String, Object> attributes = authentication.getAttributes();
////        final String typelon = (String) attributes.get("TYPELON");
////        if (!typelon.equals("ADM")) {
////
////            applyCont00(attributes, params, "base");
////            applyCont00(attributes, params, "departament");
////            List<String> luu = new ArrayList<>(1);
////            luu.add(attributes.get("ID").toString());
////            params.put("userLon_id", luu);
////        }
////        return   new ObjForQuery(params, max, offset, withCount(parameters));
//
//    }
//
//
////    public ObjForQuery doObjForQuer_yParams(Authentication authentication, HttpRequest request) {
////        final HttpParameters parameters = request.getParameters();
////        final Map<String, List<String>> params = parameters.asMap();
////
////        final Long max = doMax(parameters);
////        final Long offset = doOffset(parameters);
////        final Map<String, Object> attributes = authentication.getAttributes();
////        final String typelon = (String) attributes.get("TYPELON");
////        if (!typelon.equals("ADM")) {
////
////            applyCont00(attributes, params, "base");
////            applyCont00(attributes, params, "departament");
////            List<String> luu = new ArrayList<>(1);
////            luu.add(attributes.get("ID").toString());
////            params.put("userLon_id", luu);
////        }
////        return new ObjForQuery(params, max, offset, withCount(parameters));
////
////    }
//
//    protected ObjForQuery doObjForQuery00(Authentication authentication, HttpRequest request, String dc) {
//        return httpsParamsToQrys.doObjForQuery00(authentication, request, dc);
////        final HttpParameters parameters = request.getParameters();
////        final Map<String, List<String>> params = parameters.asMap();
////        final Long max = doMax(parameters);
////        final Long offset = doOffset(parameters);
////        final Map<String, Object> attributes = authentication.getAttributes();
////        final String typelon = (String) attributes.get("TYPELON");
////        if (!typelon.equals("ADM")) {
////            applyCont00UP(attributes, params, dc);
////            List<String> luu = new ArrayList<>(1);
////            luu.add(attributes.get("ID").toString());
////            params.put("userLon_id", luu);
////        }
////        return new ObjForQuery(params, max, offset, withCount(parameters));
//
//    }
//
////    public Single<Map> applyForList(AbstractPgLon pgLon, Authentication authentication, HttpRequest request) {
////        final HttpParameters parameters = request.getParameters();
////        final Map<String, List<String>> params = parameters.asMap();
////        final Long max = doMax(parameters);
////        final Long offset = doOffset(parameters);
////
////        applyCont(authentication, params, "base");
////        applyCont(authentication, params, "departament");
////
////        return pgLon.doList(params, offset, max, withCount(parameters));
////
////    }
////    public Single<Map> applyForListFsull(AbstractPgLon pgLon, Authentication authentication, HttpRequest request) {
////        final HttpParameters parameters = request.getParameters();
////        final Map<String, List<String>> params = parameters.asMap();
////        
////        applyCont(authentication, params, "base");
////        applyCont(authentication, params, "departament");
////        return pgLon.doList(params, 0L, 10000L,false);
////
////    }
//
//
//
////    public void applyCont(final Authentication authentication, Map<String, List<String>> params, String dc) {
////        final Map<String, Object> attributes = authentication.getAttributes();
////
////        final String typelon = (String) attributes.get("TYPELON");
////        if (typelon.equals("ADM")) {
////            return;
////        }
////
////        final List<Long> memberIds = (List<Long>) attributes.get(dc + "Ids");
////
////        System.out.println(Cte.ANSI_PURPLE);
////        System.out.println("\nTamaño " + dc + "Ids \n" + memberIds.size());
////        System.out.println(Cte.ANSI_RESET);
////
////        final String k0 = dc + "_id";
////        //            final String losIds = params.get(c_id0).stream().filter(n -> n.matches("[0-9]+") && n.length()>0).map(n ->  Long.parseLong(n)).map(n -> n.toString()).collect(Collectors.joining(","));
////        List<String> l_dcid = null;
////
////        List<String> l00 = params.get(k0);
////        System.out.println(Cte.ANSI_PURPLE);
////        System.out.println("\n \t obtenemos el parametro " + k0 + " con valor" + l00 + " --");
////        System.out.println(Cte.ANSI_RESET);
////
////        if (l00 != null) {
////            l_dcid = params.get(dc + "_id").stream()
////                    .filter(n -> n.length() > 0 && n.matches("[0-9]+"))
////                    .map(n -> Long.parseLong(n))
////                    .filter(n -> memberIds.contains(n)).map(n -> n.toString()).collect(Collectors.toList());
////
////        }
////        if (l_dcid != null && l_dcid.size() > 0) {
////            params.put(k0, l_dcid);
////        } else {
////            l_dcid = memberIds.stream().map(n -> n.toString()).collect(Collectors.toList());
////        }
////        params.put(k0, l_dcid);
////
////        System.out.println(Cte.ANSI_PURPLE);
////        System.out.println("\n \t params " + params + "****");
////        System.out.println(Cte.ANSI_RESET);
////    }
////
////    private void doList00Adm(Map<String, List<String>> params, String k0,String k1 /*,final List<Long> memberIds*/) {
////        List<String> l_dcid = null;
////        List<String> l00 = params.get(k0);
////        System.out.println(Cte.ANSI_PURPLE);
////        System.out.println("\n \t obtenemos el parametro " + k0 + " con valor" + l00 + " --");
////        System.out.println(Cte.ANSI_RESET);
////
////        if (l00 != null) {
////            l_dcid = params.get(k0).stream()
////                    .filter(n -> n.length() > 0 && n.matches("[0-9]+"))
////                    .map(n -> Long.parseLong(n))
////                    //.filter(n -> memberIds.contains(n))
////                    .map(n -> n.toString()).collect(Collectors.toList());
////
////        }
////        if (l_dcid != null && l_dcid.size() > 0) {
////            params.put(k0, l_dcid);
////        } 
////    }
//
//   
//
////    private void doList00(Map<String, List<String>> params, String k0, final List<Long> memberIds) {
////        
////    }
//
//
//    
////        public void applyCont00XAdm(Map<String, List<String>> params, String dc, String dc_param_name) {
////               //  final List<Long> memberIds = (List<Long>) attributes.get(dc + "Ids");
////
//////        System.out.println(Cte.ANSI_PURPLE);
//////        System.out.println("\nTamaño " + dc + "Ids \n" + memberIds.size());
//////        System.out.println(Cte.ANSI_RESET);
////
////        final String k0 = dc_param_name + "_id";
////        final String k1 = dc + "_id";
////        doList00Adm(params, k0, k1);
////
////        System.out.println(Cte.ANSI_PURPLE);
////        System.out.println("\n \t params " + params + "****");
////        System.out.println(Cte.ANSI_RESET);
////        }
////    
//   
//
//
//
//    @Error(exception = PgException.class)
//    public Map<String, Object> onSavedFailed(HttpRequest request, PgException pgex) {
//        Map<String, Object> m = new HashMap<>();
//        m.put("type", "pgex");
//        m.put("detail", pgex.getDetail());
//        m.put("message", pgex.getMessage());
//        return m;
//
//    }
//
//    @Error(exception = ConstraintViolationException.class) // <2>
//    public Map<String, Object> onSavedFailed2(HttpRequest request, ConstraintViolationException ex) {
//        Map<String, Object> m = new HashMap<>();
//        m.put("type", "cvex");
//        m.put("message", ex.getMessage());
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//        constraintViolations.forEach((constraintViolation) -> {
//            final String path = constraintViolation.getPropertyPath().toString();
//            m.put(path + "_attrs", constraintViolation.getConstraintDescriptor().getAttributes());
//            m.put(path + "_msg", constraintViolation.getMessage());
//            m.put(path + "_invalid_", constraintViolation.getInvalidValue());
//
//        });
//
//        return m;
//
//    }
//
//    @Error(exception = IOException.class)
//    public Map<String, Object> onIOException(HttpRequest request, IOException ex) {
//        Map<String, Object> m = new HashMap<>();
//        m.put("message", ex.getMessage());
//        return m;
//    }
//
//    protected String upload0(CompletedFileUpload file) throws IOException {
//        File tempFile = File.createTempFile(file.getFilename(), "temp");
//        Path path = Paths.get(tempFile.getAbsolutePath());
//        Files.write(path, file.getBytes());
//        return tempFile.getPath();
//    }
}
