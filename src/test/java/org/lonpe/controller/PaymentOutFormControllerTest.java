

package org.lonpe.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

import org.lonpe.model.*;

@MicronautTest
public class PaymentOutFormControllerTest extends AbstractControllerTest<PaymentOutForm> {


    @Inject
    EmbeddedServer embeddedServer;

    @Test
    public void testT() throws Exception {
        
        System.out.println("----testT -- Inicia login para probar PaymentOutFormController");
        
        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " +accessToken);   
     
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/paymentOutForm/t").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        
        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth,Map.class);
        System.out.println("R" +exchange.body());     
        assertEquals(HttpStatus.OK, exchange.status());

    }
    
    

    @Test
    public void testModel() throws Exception {

        System.out.println("\n\n\t **** Inicia login para probar Model en PaymentOutFormControllerController");
        final String accessToken = doLogin0(embeddedServer);        
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/paymentOutForm/model").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("R MODEL" + exchange.body().toString());
        assertEquals(HttpStatus.OK, exchange.status());

    }

    
    @Test
    public void testList() throws Exception {
    
        System.out.println("Inicia login para probar LIST en PaymentOutFormControllerController");
    
        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " + accessToken);
    
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
                    
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/paymentOutForm/l").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        System.out.println("---------------------------");

        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("R\n" + exchange.body());
        assertEquals(HttpStatus.OK, exchange.status());
    
        }
        








}



