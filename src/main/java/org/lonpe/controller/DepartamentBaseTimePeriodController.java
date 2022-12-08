
        
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
import org.lonpe.lonrx.impl.DepartamentBaseTimePeriodServiceLon;



@Secured("isAuthenticated()") 
@Controller("/pg/departamentBaseTimePeriod")
public class DepartamentBaseTimePeriodController extends AbstractLonController<DepartamentBaseTimePeriod>{

    @Inject
    DepartamentBaseTimePeriodServiceLon departamentBaseTimePeriodServiceLon;

    @Get(uri="/t", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DepartamentBaseTimePeriod>> t(final Authentication authentication,final HttpRequest request) {    
         final DepartamentBaseTimePeriod departamentBaseTimePeriod = new DepartamentBaseTimePeriod();            
         return Mono.just(departamentBaseTimePeriod).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/model", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DCModel>> model(final Authentication authentication,final HttpRequest request) {        
         return Mono.just(departamentBaseTimePeriodServiceLon.getdCModel()).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/l", produces = MediaType.APPLICATION_JSON)
    public Mono<Map<String, Object>> l(final Authentication authentication, final HttpRequest request) {        
        final ObjForQuery ofq = doObjForQuery(authentication, request);     
        return convert(departamentBaseTimePeriodServiceLon.doList(ofq));    
    }   

    @Put(uri = "/sou", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Map<String, Object>>> sou(final Authentication authentication, final @Body @Valid DepartamentBaseTimePeriod departamentBaseTimePeriod) {
        System.out.println("Recibido " + departamentBaseTimePeriod);

        /*
       if(!userLonPgLon.getDCModel().isInListValue(userLon.getTypeLon())){
           throw new Exception();
       }
         */
        return verifySave(authentication, departamentBaseTimePeriod);

    }

    private Publisher<HttpResponse<Map<String, Object>>> verifySave(final Authentication authentication, final DepartamentBaseTimePeriod departamentBaseTimePeriod) {
        
        final Map<String, Object> attrs = authentication.getAttributes();
        final String typelon = (String) attrs.get("TYPELON");
        final Long uid = (Long) attrs.get("ID");

        return processSave(departamentBaseTimePeriodServiceLon.save(departamentBaseTimePeriod));

     }



}


        