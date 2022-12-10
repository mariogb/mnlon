/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.lonrx;

import io.micronaut.http.HttpParameters;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.reactivex.sqlclient.Tuple;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.lonpe.dcmodel.DCModel;
import org.lonpe.forquery.ObjForQuery;
import org.lonpe.sql.ConditionInfo;
import org.lonpe.sql.CrudLon;
import org.lonpe.sql.SqlStructureBuilder;

/**
 *
 * @author l5
 * @param <DC>
 */
public abstract class AbstractLon<DC> {

    protected static final String STRING = "String";
    protected static final String LONG = "Long";
    protected static final String INTEGER = "Integer";
    protected static final String LOCALDATETIME = "LocalDateTime";
    protected static final String BIGDECIMAL = "BigDecimal";
    protected static final String BOOLEAN = "Boolean";
    protected static final String LOCALDATE = "LocalDate";

    public abstract Single<Map<String, Object>> save(DC dc);

    public Single<Map<String, Object>> processCheckParent(final Observable<List<Map<String, Object>>> finalObs, List<String> names) {
        return finalObs.toList().map((List<List<Map<String, Object>>> t) -> {
            final Map<String, Object> m0 = new HashMap<>();
            final Map<String, String> errMap = new HashMap<>();
            final int n = t.size();
            for (int i = 0; i < n; i++) {
                final List<Map<String, Object>> l_ = t.get(i);
                final String n00 = names.get(i);
                if (l_.isEmpty()) {
                    errMap.put(n00, "NO VALID FOUND");
                } else {
                    m0.put("l_" + n00, l_);
                }
            }
            if (!errMap.isEmpty()) {
                m0.put("ERROR", errMap);
            }
            return m0;
        });
    }

    public abstract DCModel getdCModel();

    public abstract Single<Map<String, Object>> doList(final ObjForQuery objForQuery);

    public String doPreSql(String typelon) {
        if (!typelon.equals("ADM")) {
            return "select rest_dpbtp.dbtp_id from rest_dpbtp where rest_dpbtp.uid = $2";
        }
        return "select rest_dpbtp00.dbtp_id from rest_dpbtp00";
    }

    public Tuple doTuple00(String typelon, Long id, Long uid) {
        final Tuple t = Tuple.of(id);
        if (!typelon.equals("ADM")) {
            t.addLong(uid);
        }
        return t;
    }

    protected abstract String getSqlForList();

    protected abstract String getSqlForCount();

    public abstract Set<String> getNames();

    // abstract protected SqlStructureBuilder getSqlStructureBuiderLon();
    protected Single<Map<String, Object>> doList000(final CrudLon crudLon, final String dc, final ObjForQuery objForQuery) {

        Single<Map<String, Object>> rfinal;

        final Tuple tuple = Tuple.tuple();

        ConditionInfo ci = doCondiciones(objForQuery.getParams(), tuple);

        final String ord2 = ci.getOrden();
        final String sqlConds = ci.getCondiciones().stream().collect(Collectors.joining(" AND "));
        String sqlConds00 = doSqlConds00(sqlConds);
        final String sql1 = getSqlForList() + sqlConds00 + ord2 + " offset " + objForQuery.getOffset() + " limit " + objForQuery.getMax();

        System.out.println(sql1);

        final Set<String> names = getNames();

        if (objForQuery.isWithCount()) {
            final String sql2 = getSqlForCount() + sqlConds00;
            System.out.println("sql 2" + sql2);
            rfinal = crudLon.doList2v2(sql1, sql2, tuple, names, objForQuery.getOffset(), objForQuery.getMax());
        } else {
            rfinal = crudLon.doListAndNames(sql1, tuple, names, objForQuery.getOffset(), objForQuery.getMax());
        }

        return rfinal.map((Map m1) -> {
            m1.put("dc", dc);
            return m1;
        });
    }

    public abstract ConditionInfo doCondiciones(Map<String, List<String>> params, Tuple tuple);

    private String doSqlConds00(final String sqlConds) {
        return sqlConds.length() > 0 ? " WHERE " + sqlConds + " " : "";
    }

}
