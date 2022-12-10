package org.lonpe.sql;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import io.vertx.reactivex.pgclient.PgPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowIterator;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.Tuple;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mario
 */
@Singleton
public class CrudLon {

    @Inject
    PgPool pgPool;

    class TransformerMap implements Function<RowSet<Row>, Map<String, Object>> {

        final boolean withColumnNames;

        public TransformerMap(boolean withColumnNames) {
            this.withColumnNames = withColumnNames;
        }

        @Override
        public Map apply(final RowSet<Row> t) throws Exception {
            final Map<String, List> m = new HashMap<>();
            List<List> l = new ArrayList<>();
            final List<String> colNames = t.columnsNames();
            m.put("colnames", colNames);
            RowIterator<Row> iterator = t.iterator();
            final int nc = colNames != null ? colNames.size() : 0;
            while (iterator.hasNext()) {
                final Row row = iterator.next();
                final List l0 = new ArrayList();
                for (int i = 0; i < nc; i++) {
                    l0.add(row.getValue(i));
                }
                l.add(l0);
            }
            m.put("l", l);
            return m;
        }
    }

    class TransformerListMap implements Function<RowSet<Row>, List<Map<String, Object>>> {

        @Override
        public List<Map<String, Object>> apply(RowSet<Row> t) throws Exception {
            List<Map<String, Object>> l = new ArrayList<>();
            final List<String> colNames = t.columnsNames();
            RowIterator<Row> iterator = t.iterator();
            if (colNames == null) {
                return l;
            }
            while (iterator.hasNext()) {
                final Row row = iterator.next();
                final Map<String, Object> m = new HashMap<>();
                colNames.forEach(colN -> m.put(colN, row.getValue(colN)));
                l.add(m);
            }
            return l;
        }

    }

    public Single<Map<String, Object>> doList(String sql, Tuple t, boolean withColumnNames) {
        return preparedQuery0(sql, t).map(new TransformerMap(withColumnNames));
    }

    public Single<Map<String, Object>> doList(String sql, Tuple t) {
        return preparedQuery0(sql, t).map(new TransformerMap(true));
    }

    private Single<RowSet<Row>> preparedQuery0(String sql, Tuple tuple) {

        return pgPool.preparedQuery(sql).rxExecute(tuple);
    }

    public Single<Map<String, Object>> getOne(String sql, Tuple t) {

        return preparedQuery0(sql, t).map((RowSet<Row> t1) -> {
            final List<String> columnsNames = t1.columnsNames();
            final Map m = new HashMap(columnsNames.size());
            final RowIterator<Row> it0 = t1.iterator();
            if (it0.hasNext()) {
                Row r0 = it0.next();
                columnsNames.forEach((c) -> {
                    m.put(c, r0.getValue(c));
                });
            }
            return m;
        });

    }

    public Single<Map<String, Object>> saveOne(String sql, Tuple tuple) {

        return preparedQuery0(sql, tuple).map((RowSet<Row> t) -> {
            System.out.println("t.rowCount() " + t.rowCount());
            final List<String> columnsNames = t.columnsNames();
            final Map m = new HashMap(columnsNames.size());
            final RowIterator<Row> it0 = t.iterator();
            if (it0.hasNext()) {
                final Row r0 = it0.next();
                columnsNames.forEach((c) -> m.put(c, r0.getValue(c)));
            }
            return m;
        });

    }

    public Single<Map<String, Object>> executeQry(String sql, Tuple t) {
        return preparedQuery0(sql, t).map(new TransformerMap(true));
    }

    public Single<Map<String, Object>> saveOneWithNames(String sql, Tuple tuple, Map<String, String> insertNames) {
//Single<MutableHttpResponse<Map>> 
        return preparedQuery0(sql, tuple).map((final RowSet<Row> t) -> {
            final List<String> columnsNames = t.columnsNames();
            final Map m = new HashMap(columnsNames.size());
            final RowIterator<Row> it0 = t.iterator();
            if (it0.hasNext()) {
                Row r0 = it0.next();
                columnsNames.forEach(c -> {
                    final Object elName = insertNames.get(c);
                    if (elName != null) {
                        m.put(insertNames.get(c), r0.getValue(c));
                    }
                });
            }
            return m;
        });

    }

    public Single<List<Map<String, Object>>> doListM(String sql, Tuple t) {
        return preparedQuery0(sql, t).map(new TransformerListMap());
    }

    public Single<Map<String, Object>> doListAndNames(String sql, Tuple t, Set<String> names, Long offset, Long max) {
        return doList(sql, t, false)
                .map((Map<String, Object> m1) -> {
                    //m1.remove("colnames");
                    m1.put("names", names);
                    m1.put("offset", offset);
                    m1.put("max", max);
                    return m1;
                });
    }

    public Single<Map<String, Object>> doList2v2(String sql0, String sql2, Tuple tuple, Set<String> names, Long offset, Long max) {

        final Single<Map<String, Object>> l0 = doListAndNames(sql0, tuple, names, offset, max);
        final Single<Map<String, Object>> l2 = doList(sql2, tuple);

        final Observable<Map<String, Object>> concat = Observable.concat(l0.toObservable(), l2.toObservable());

        final Single<List<Map<String, Object>>> toList = concat.toList();

        return toList.map((List<Map<String, Object>> t) -> {
            final Map m0 = t.get(0);

            final List ll = (List) ((List) (t.get(1).get("l"))).get(0);
            m0.put("total", ll.get(0));
            return m0;
        });

    }

}
