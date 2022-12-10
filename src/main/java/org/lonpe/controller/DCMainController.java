package org.lonpe.controller;

import io.reactivex.Single;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import org.lonpe.lonrx.DCMainService;

@Secured("isAuthenticated()")
@Controller("/init0")
public class DCMainController {

    @Inject
    DCMainService dCMainService;

    @Get(uri = "/l_models", produces = MediaType.APPLICATION_JSON)
    Single<LinkedHashMap> listModels(Authentication authentication, HttpRequest<?> request) {

        final Map<String, Object> attributes = authentication.getAttributes();

        final String typelon = (String) attributes.get("TYPELON");
        Long uid0 = null;
        if (!typelon.equals("ADM")) {
            uid0 = Long.parseLong(attributes.get("ID").toString());
        }
        return dCMainService.listModels(uid0);
    }

}
