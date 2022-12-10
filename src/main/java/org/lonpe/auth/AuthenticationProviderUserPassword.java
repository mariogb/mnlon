/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.auth;

/**
 *
 * @author mario
 */
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.vertx.reactivex.sqlclient.Tuple;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.lonpe.sql.CrudLon;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    CrudLon crudLon;

    @Inject
    PasswordEncoder passwordEncoder;

    private final String sqlBase = "select base.pkey from base_user_lon, base where base_user_lon.user_lon_id = $1 and base_user_lon.base_id = base.id";
    private final String sqlDepartament = "select departament.pkey from departament_user_lon, departament  where departament_user_lon.user_lon_id = $1 and departament_user_lon.departament_id = departament.id";
    private final String sqlProgram = "select program.pkey from program_user_lon, program  where program_user_lon.user_lon_id = $1 and program_user_lon.program_id = program.id";

    private void putMemebership(String sql, Long id0, Map attrs, String name) {
        final Map mBU = crudLon.doList(sql, Tuple.of(id0)).blockingGet();
        final List<List<String>> lo0 = (List<List<String>>) mBU.get("l");
        if (lo0 != null) {
            attrs.put(name, lo0.stream().map((List<String> t) -> t.get(0)).collect(Collectors.toList()));
        }

    }

    private static final String sql00 = "select id,pkey,pname,password,username,type_lon from user_lon where username = $1";

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        System.out.println("-------------------AUTH");
        final String username = authenticationRequest.getIdentity().toString();
        final Tuple tuple = Tuple.of(username);

        return Mono.<AuthenticationResponse>create(new Consumer<MonoSink<AuthenticationResponse>>() {

            @Override
            public void accept(MonoSink<AuthenticationResponse> emitter) {

                Disposable subscribe = crudLon.getOne(sql00, tuple).subscribe((Map<String, Object> mUser) -> {
                    boolean matches = false;
                    final String secret = authenticationRequest.getSecret().toString();
                    final Object password = mUser.get("password");
                    if (password != null) {
                        matches = passwordEncoder.matches(secret, password.toString());
                    }
                    if (!matches) {
                        emitter.error(AuthenticationResponse.exception("BAD LOGIN"));
                        return;
                    }

                    final List<String> roles = new ArrayList<>();
                    roles.add("A");
                    roles.add("B");
                    final Map attrs = new HashMap();

                    final String type_lon = (String) mUser.get("type_lon");
                    final Long id0 = (Long) (mUser.get("id"));
                    attrs.put("ID", id0);
                    attrs.put("TYPELON", type_lon);
                    if (!type_lon.equals("ADM")) {

                        putMemebership(sqlBase, id0, attrs, "baseIds");
                        putMemebership(sqlDepartament, id0, attrs, "departamentIds");
                        putMemebership(sqlProgram, id0, attrs, "programIds");
                    }

                    emitter.success(AuthenticationResponse.success("user", roles, attrs));

                }, (Throwable t) -> {

                    emitter.error(t);
                });

                System.out.println("Esta en estado" + subscribe.isDisposed());
                //
            }

        });
    }

}
/*

  curl -kv  -X "POST" "https://localhost:8333/login"      -H 'Content-Type: application/json; charset=utf-8' -d $'{"username": "user", "password": "password"  }'


 curl -kv -X "POST" "https://localhost:8333/login" -H 'Content-Type: application/json; charset=utf-8'      -d $'{       "username": "admin",  "password": "1234" }'
 

curl -kv -X "GET" "https://localhost:8333/pg/account" -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibmJmIjoxNjQ5NDU1OTU4LCJUWVBFTE9OIjoiQURNIiwicm9sZXMiOlsiQSIsIkIiXSwiaXNzIjoibW4zMyIsIklEIjoxLCJleHAiOjE2NDk0NTk1NTgsImlhdCI6MTY0OTQ1NTk1OH0.63DcIpWVboNLFOkmJuER3avi58B8VRMbEtV_nXFIn04'


curl -kv -X "PUT" "https://localhost:8333/pg/account/sou" -H 'Content-Type: application/json; charset=utf-8'  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibmJmIjoxNjUwNjcxNjM1LCJUWVBFTE9OIjoiQURNIiwicm9sZXMiOlsiQSIsIkIiXSwiaXNzIjoibW4zMyIsIklEIjoxLCJleHAiOjE2NTA2NzUyMzUsImlhdCI6MTY1MDY3MTYzNX0.vH2DAnv-89JtKqj47P1KZZELhrcd21x4gp9IRnFwDBw' -d $'{
      "pkey": "user",
      "pname": "password"
       }'



 */
