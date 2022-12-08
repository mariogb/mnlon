
        
package org.lonpe.controller;            

            
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.reactivex.Single;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import org.lonpe.dcmodel.DCModel;
import org.lonpe.forquery.ObjForQuery;

import org.lonpe.model.*;
import org.lonpe.lonrx.impl.DepartamentJobInstanceServiceLon;



@Secured("isAuthenticated()") 
@Controller("/pg/departamentJobInstance")
public class DepartamentJobInstanceController extends AbstractLonController<DepartamentJobInstance>{

    @Inject
    DepartamentJobInstanceServiceLon departamentJobInstanceServiceLon;

    @Get(uri="/t", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DepartamentJobInstance>> t(final Authentication authentication,final HttpRequest request) {    
         final DepartamentJobInstance departamentJobInstance = new DepartamentJobInstance();            
         return Mono.just(departamentJobInstance).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/model", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DCModel>> model(final Authentication authentication,final HttpRequest request) {        
         return Mono.just(departamentJobInstanceServiceLon.getdCModel()).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/l", produces = MediaType.APPLICATION_JSON)
    public Mono<Map<String, Object>> l(final Authentication authentication, final HttpRequest request) {        
        final ObjForQuery ofq = doObjForQuery(authentication, request);     
        return convert(departamentJobInstanceServiceLon.doList(ofq));    
    }   

    @Put(uri = "/sou", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Map<String, Object>>> sou(final Authentication authentication, final @Body @Valid DepartamentJobInstance departamentJobInstance) {
        System.out.println("Recibido " + departamentJobInstance);

        /*
       if(!userLonPgLon.getDCModel().isInListValue(userLon.getTypeLon())){
           throw new Exception();
       }
         */
        return verifySave(authentication, departamentJobInstance);

    }

    private Publisher<HttpResponse<Map<String, Object>>> verifySave(final Authentication authentication, final DepartamentJobInstance departamentJobInstance) {
        
        final Map<String, Object> attrs = authentication.getAttributes();
        final String typelon = (String) attrs.get("TYPELON");
        final Long uid = (Long) attrs.get("ID");

        return processSave(departamentJobInstanceServiceLon.save(departamentJobInstance));

     }



}


        