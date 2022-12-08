/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.forquery;

import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.lonpe.Cte;

/**
 *
 * @author l5
 */
public class HttpsParamsToQrys {

    public ObjForQuery doObjForQuery(final Authentication authentication, final HttpRequest request) {
        final HttpParameters parameters = request.getParameters();
        final Map<String, List<String>> params = parameters.asMap();
        final Long max = doMax(parameters);
        final Long offset = doOffset(parameters);
        final Map<String, Object> attributes = authentication.getAttributes();
        final String typelon = (String) attributes.get("TYPELON");
        if (!typelon.equals("ADM")) {

            applyCont00(attributes, params, "base");
            applyCont00(attributes, params, "departament");
            List<String> luu = new ArrayList<>(1);
            luu.add(attributes.get("ID").toString());
            params.put("userLon_id", luu);
        }
        return new ObjForQuery(params, max, offset, withCount(parameters));

    }

    //?dc=base&dc=departament&dc=otro&otro.ilpkey=aa34&otro.padre_otro_id=3
//?dc=base&dc=departament&base.max=12&base.offset=13&    
    public Map<String, ObjForQuery> doObjForQueryMMM(Authentication authentication, HttpRequest request) {
        final HttpParameters parameters = request.getParameters();
        final Map<String, List<String>> params = parameters.asMap();
        final List<String> lsDc = params.get("dc");
        final Map<String, Map<String, List<String>>> mapOfParams = new HashMap<>();
        final Map<String, ObjForQuery> mapOFQ = new HashMap<>();

        if (lsDc == null) {
            return mapOFQ;
        }

        final Map<String, Object> attributes = authentication.getAttributes();
        final String typelon = (String) attributes.get("TYPELON");
        final boolean isAdm = typelon.equals("ADM");

        lsDc.forEach(dc0 -> mapOfParams.put(dc0, new HashMap<>()));
        params.forEach((final String dc0, final List<String> vdc0) -> {
            final String[] dcSplit = dc0.split(".");
            if (dcSplit.length == 2) {
                final String dc0_key = dcSplit[0];
                if (mapOfParams.containsKey(dc0_key)) {
                    mapOfParams.get(dc0_key).put(dcSplit[1], vdc0);
                }
            }

        });

        mapOfParams.forEach((String dc0, Map<String, List<String>> params2) -> {
            final Long max = doMax(parameters, dc0 + ".max");
            final Long offset = doOffset(parameters, dc0 + ".offset");
            if (!isAdm) {
                applyCont00(attributes, params2, "base");
                applyCont00(attributes, params2, "departament");
                final List<String> luu = new ArrayList<>(1);
                luu.add(attributes.get("ID").toString());
                params2.put("userLon_id", luu);

            }
            final ObjForQuery objQry1 = new ObjForQuery(params2, max, offset, withCount(parameters, dc0 + ".withCount"));
            mapOFQ.put(dc0, objQry1);

        });
        return mapOFQ;

    }

    protected ObjForQuery doObjForQuery00(Authentication authentication, HttpRequest request, String dc) {
        final HttpParameters parameters = request.getParameters();
        final Map<String, List<String>> params = parameters.asMap();
        final Long max = doMax(parameters);
        final Long offset = doOffset(parameters);
        final Map<String, Object> attributes = authentication.getAttributes();
        final String typelon = (String) attributes.get("TYPELON");
        if (!typelon.equals("ADM")) {
            applyCont00UP(attributes, params, dc);
            List<String> luu = new ArrayList<>(1);
            luu.add(attributes.get("ID").toString());
            params.put("userLon_id", luu);
        }
        return new ObjForQuery(params, max, offset, withCount(parameters));

    }

    public boolean withCount(final HttpParameters parameters) {
        return withCount(parameters, "withCount");
    }

    public boolean withCount(final HttpParameters parameters, String name) {
        return parameters.getFirst(name, Boolean.class, true);
    }

    public Long doMax(final HttpParameters parameters) {
        return doMax(parameters, "max");
    }

    public Long doMax(final HttpParameters parameters, final String nmax) {
        final Long max = parameters.get(nmax, Long.class, 10L);
        if (max < 0) {
            return 1L;
        }
        return max;
    }

    public Long doOffset(final HttpParameters parameters) {
        return doOffset(parameters, "offset");
    }

    public Long doOffset(final HttpParameters parameters, final String noffset) {
        final Long offset = parameters.get(noffset, Long.class, 0L);
        if (offset < 0) {
            return 0L;
        }
        return offset;
    }

    public void applyCont00(final Map<String, Object> attributes, Map<String, List<String>> params, String dc, String dc_param_name) {

        final List<Long> memberIds = (List<Long>) attributes.get(dc + "Ids");

        mLog("Tamaño " + dc + "Ids \n" + memberIds.size());

        final String k0 = dc_param_name + "_id";
        doList00(params, k0, memberIds);

        mLog("params " + params + "****");

    }

    public void applyCont00(final Map<String, Object> attributes, Map<String, List<String>> params, String dc) {

        applyCont00(attributes, params, dc, dc);

    }

    private void doList00(Map<String, List<String>> params, String k0, final List<Long> memberIds) {
        doList00(params, k0, k0, memberIds);
    }

    private void doList00(Map<String, List<String>> params, String k0, String k1, final List<Long> memberIds) {
        List<String> listDcid = null;

        final List<String> l00 = params.get(k0);

        mLog("\n \t obtenemos el parametro " + k0 + " con valor" + l00 + " --");

        if (l00 != null) {
            listDcid = params.get(k0).stream()
                    .filter(n -> n.length() > 0 && n.matches("[0-9]+"))
                    .map(n -> Long.parseLong(n))
                    .filter(n -> memberIds.contains(n)).map(n -> n.toString()).collect(Collectors.toList());

        }
        if (listDcid != null && listDcid.size() > 0) {
            params.put(k1, listDcid);
        } else {
            params.put(k1, memberIds.stream().map(n -> n.toString()).collect(Collectors.toList()));
        }

    }

    public void applyCont00UP(final Map<String, Object> attributes, Map<String, List<String>> params, String dc) {

        final List<Long> memberIds = (List<Long>) attributes.get(dc + "Ids");

        mLog("\nTamaño " + dc + "Ids \n" + memberIds.size());

        doList00(params, "id", memberIds);

        mLog("\n \t params " + params + "****");

    }

    private void mLog(String a) {
        System.out.println(Cte.ANSI_PURPLE);
        System.out.println("\n" + a + "\n");
        System.out.println(Cte.ANSI_RESET);
    }

}
