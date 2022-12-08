/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.DefaultHttpClientConfiguration;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.disposables.Disposable;
import java.net.URL;
import java.time.Duration;

/**
 *
 * @author mgb
 */
public abstract class AbstractControllerTest<T> {

    protected Rx3HttpClient createHttpClient(URL url) {
        DefaultHttpClientConfiguration defaultHttpClientConfiguration = new DefaultHttpClientConfiguration(); //  HttpClientConfiguration()
        defaultHttpClientConfiguration.setReadTimeout(Duration.ofSeconds(28));

        return Rx3HttpClient.create(url, defaultHttpClientConfiguration);
    }

    protected String doLogin0(EmbeddedServer embeddedServer) throws Exception {
        System.out.println("haciendo login!!!");
        String username = "admin";
        String password = "1234";
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);

        DefaultHttpClientConfiguration defaultHttpClientConfiguration = new DefaultHttpClientConfiguration(); //  HttpClientConfiguration()
        defaultHttpClientConfiguration.setReadTimeout(Duration.ofSeconds(28));

        Rx3HttpClient client0 = createHttpClient(embeddedServer.getURL());

        System.out.println("embeddedServer.getURL()" + embeddedServer.getURL());

        MutableHttpRequest<UsernamePasswordCredentials> requestLogin = HttpRequest.POST("/login", creds);

        //Flowable<HttpResponse<BearerAccessRefreshToken>> exchange = client0.exchange(requestLogin, BearerAccessRefreshToken.class);
        Maybe<HttpResponse<BearerAccessRefreshToken>> firstElement = client0.exchange(requestLogin, BearerAccessRefreshToken.class).firstElement();

        String accessToken = firstElement.toSingle().blockingGet().body().getAccessToken();

        //HttpResponse<BearerAccessRefreshToken> rsp = client0.exchange(requestLogin, BearerAccessRefreshToken.class).blockingSingle();
        //String accessToken = rsp.body().getAccessToken();
        System.out.println("accessToken = " + accessToken);
        return accessToken;

    }
}
