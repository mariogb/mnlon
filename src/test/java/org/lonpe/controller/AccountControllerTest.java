

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
public class AccountControllerTest extends AbstractControllerTest<Account> {


    @Inject
    EmbeddedServer embeddedServer;

    @Test
    public void testT() throws Exception {
        
        System.out.println("----testT -- Inicia login para probar AccountController");
        
        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " +accessToken);   
     
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/account/t").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        
        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth,Map.class);
        System.out.println("R" +exchange.body());     
        assertEquals(HttpStatus.OK, exchange.status());

    }
    
    
    @Test
    public void testSave() throws Exception {
    
        System.out.println("Inicia login para probar SAVE en AccountController");
    
        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " + accessToken);
    
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
    
        
    String ff = (System.currentTimeMillis()%1000)+"";
    Account account = new Account();     
    account.setPkey("X_"+ff);
    
    
        account.setDescription("description"+ff);
            
        account.setPname("pname"+ff);
            
        account.setType("type"+ff);
            
            
        final MutableHttpRequest<Account> bearerAuth = HttpRequest.POST("/pg/account",account).bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
    
        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("R" + exchange.body());
        assertEquals(HttpStatus.OK, exchange.status());
    
        }
        

    @Test
    public void testModel() throws Exception {

        System.out.println("\n\n\t **** Inicia login para probar Model en AccountControllerController");
        final String accessToken = doLogin0(embeddedServer);        
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/account/model").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("R MODEL" + exchange.body().toString());
        assertEquals(HttpStatus.OK, exchange.status());

    }

    
    @Test
    public void testList() throws Exception {
    
        System.out.println("Inicia login para probar LIST en AccountControllerController");
    
        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " + accessToken);
    
        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());
                    
        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/pg/account").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        System.out.println("---------------------------");

        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("R\n" + exchange.body());
        assertEquals(HttpStatus.OK, exchange.status());
    
        }
        








}



