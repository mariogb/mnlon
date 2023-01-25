
package org.lonpe.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;


@MicronautTest
public class DCMainControllerTest extends AbstractControllerTest<Object> {

    @Inject
    EmbeddedServer embeddedServer;

    @Test
    public void testlModels() throws Exception {

        System.out.println("Inicia login para probar testlModels en DCMainController");

        final String accessToken = doLogin0(embeddedServer);
        System.out.println("LOGIN CON " + accessToken);

        final Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());

        final MutableHttpRequest<Object> bearerAuth = HttpRequest.GET("/init0/l_models").bearerAuth(accessToken).accept(MediaType.APPLICATION_JSON);
        System.out.println("---------------------------");

        HttpResponse<Map> exchange = client0.toBlocking().exchange(bearerAuth, Map.class);
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        System.out.println(exchange.body());
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        System.out.println("                  -                     ");
        assertEquals(HttpStatus.OK, exchange.status());

    }

}

