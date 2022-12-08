
        
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
import org.lonpe.lonrx.impl.ComercialDocumentTypeInServiceLon;



@Secured("isAuthenticated()") 
@Controller("/pg/comercialDocumentTypeIn")
public class ComercialDocumentTypeInController extends AbstractLonController<ComercialDocumentTypeIn>{

    @Inject
    ComercialDocumentTypeInServiceLon comercialDocumentTypeInServiceLon;

    @Get(uri="/t", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<ComercialDocumentTypeIn>> t(final Authentication authentication,final HttpRequest request) {    
         final ComercialDocumentTypeIn comercialDocumentTypeIn = new ComercialDocumentTypeIn();            
         return Mono.just(comercialDocumentTypeIn).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/model", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DCModel>> model(final Authentication authentication,final HttpRequest request) {        
         return Mono.just(comercialDocumentTypeInServiceLon.getdCModel()).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/l", produces = MediaType.APPLICATION_JSON)
    public Mono<Map<String, Object>> l(final Authentication authentication, final HttpRequest request) {        
        final ObjForQuery ofq = doObjForQuery(authentication, request);     
        return convert(comercialDocumentTypeInServiceLon.doList(ofq));    
    }   

    @Put(uri = "/sou", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Map<String, Object>>> sou(final Authentication authentication, final @Body @Valid ComercialDocumentTypeIn comercialDocumentTypeIn) {
        System.out.println("Recibido " + comercialDocumentTypeIn);

        /*
       if(!userLonPgLon.getDCModel().isInListValue(userLon.getTypeLon())){
           throw new Exception();
       }
         */
        return verifySave(authentication, comercialDocumentTypeIn);

    }

    private Publisher<HttpResponse<Map<String, Object>>> verifySave(final Authentication authentication, final ComercialDocumentTypeIn comercialDocumentTypeIn) {
        
        final Map<String, Object> attrs = authentication.getAttributes();
        final String typelon = (String) attrs.get("TYPELON");
        final Long uid = (Long) attrs.get("ID");

        return processSave(comercialDocumentTypeInServiceLon.save(comercialDocumentTypeIn));

     }



}


        