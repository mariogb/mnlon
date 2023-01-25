
        
package org.lonpe.controller;            

            
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Post;
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
import org.lonpe.lonrx.impl.MeUsrInterfaceServiceLon;



@Secured("isAuthenticated()") 
@Controller("/pg/meUsrInterface")
public class MeUsrInterfaceController extends AbstractLonController<MeUsrInterface>{

    @Inject
    MeUsrInterfaceServiceLon meUsrInterfaceServiceLon;

    @Get(uri="/t", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<MeUsrInterface>> t(final Authentication authentication,final HttpRequest request) {    
         final MeUsrInterface meUsrInterface = new MeUsrInterface();            
         return Mono.just(meUsrInterface).map(e -> HttpResponse.ok(e));        
    }  

    @Get(uri="/model", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DCModel>> model(final Authentication authentication,final HttpRequest request) {        
         return Mono.just(meUsrInterfaceServiceLon.getdCModel()).map(e -> HttpResponse.ok(e));        
    }  

    @Get(produces = MediaType.APPLICATION_JSON)
    public Mono<Map<String, Object>> l(final Authentication authentication, final HttpRequest request) {        
        final ObjForQuery ofq = doObjForQuery(authentication, request);     
        return convert(meUsrInterfaceServiceLon.doList(ofq));    
    }   

    @Post(produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Map<String, Object>>> sou(final Authentication authentication, final @Body @Valid MeUsrInterface meUsrInterface) {
        System.out.println("Recibido " + meUsrInterface);

        /*
       if(!userLonPgLon.getDCModel().isInListValue(userLon.getTypeLon())){
           throw new Exception();
       }
         */
        return verifySave(authentication, meUsrInterface);
    }

    private Publisher<HttpResponse<Map<String, Object>>> verifySave(final Authentication authentication, final MeUsrInterface meUsrInterface) {
        
        final Map<String, Object> attrs = authentication.getAttributes();
        final String typelon = (String) attrs.get("TYPELON");
        final Long uid = (Long) attrs.get("ID");

        return processSave(meUsrInterfaceServiceLon.save(meUsrInterface));

     }



}


        