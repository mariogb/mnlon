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
import org.lonpe.lonrx.impl.ProductServiceLon;

@Secured("isAuthenticated()")
@Controller("/pg/product")
public class ProductController extends AbstractLonController<Product> {

    @Inject
    ProductServiceLon productServiceLon;

    @Get(uri = "/t", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Product>> t(final Authentication authentication, final HttpRequest request) {
        final Product product = new Product();
        return Mono.just(product).map(e -> HttpResponse.ok(e));
    }

    @Get(uri = "/model", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<DCModel>> model(final Authentication authentication, final HttpRequest request) {
        return Mono.just(productServiceLon.getdCModel()).map(e -> HttpResponse.ok(e));
    }

    @Get(uri = "/l", produces = MediaType.APPLICATION_JSON)
    public Mono<Map<String, Object>> l(final Authentication authentication, final HttpRequest request) {
        final ObjForQuery ofq = doObjForQuery(authentication, request);
        return convert(productServiceLon.doList(ofq));
    }

    @Put(uri = "/sou", produces = MediaType.APPLICATION_JSON)
    public Publisher<HttpResponse<Map<String, Object>>> sou(final Authentication authentication, final @Body @Valid Product product) {
        System.out.println("Recibido " + product);

        /*
       if(!userLonPgLon.getDCModel().isInListValue(userLon.getTypeLon())){
           throw new Exception();
       }
         */
        return verifySave(authentication, product);

    }

    private Publisher<HttpResponse<Map<String, Object>>> verifySave(final Authentication authentication, final Product product) {

        final Map<String, Object> attrs = authentication.getAttributes();
        final String typelon = (String) attrs.get("TYPELON");
        final Long uid = (Long) attrs.get("ID");

        return processSave(productServiceLon.save(product));

    }

}
