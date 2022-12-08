/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.sql;

import io.vertx.reactivex.sqlclient.Tuple;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author l5
 */
public class SqlStructureBuilder {

    private final Set<String> fields;
    private final Set<String> names;
    private final Map<String, String> insertNames;

    private final Set<String> tables;
    private final Set<String> relations;
    private final Set<String> condiciones;

    final HashMap<String, String> sorts = new HashMap<>();

    public SqlStructureBuilder() {

        fields = new LinkedHashSet<>();
        names = new LinkedHashSet<>();
        insertNames = new HashMap<>();

        tables = new LinkedHashSet<>();
        relations = new LinkedHashSet<>();
        condiciones = new LinkedHashSet<>();

    }

    public SqlStructureBuilder addSort(final String paramName, final String tbl_field) {
        sorts.put(paramName, tbl_field);
        return this;
    }

    public Map<String, String> getSorts() {
        return sorts;
    }

    public SqlStructureBuilder addTable(final String t) {
        tables.add(t);
        return this;
    }

    public SqlStructureBuilder addTableWithIdAndPkey(final String t) {

        addTable(t)
                .addTableFieldAndSort(t, "id", "id")
                .addTableFieldAndSort(t, "pkey", "pkey");
        return this;
    }

    public SqlStructureBuilder addTable2WithIdAndPkey(final String t, final String dc) {

        addTable(t)
                .addTable2FieldAndSort(t, "id", dc + "_id")
                .addTable2FieldAndSort(t, "pkey", dc + "_pkey");

        return this;
    }

    public SqlStructureBuilder addField(final String f) {
        fields.add(f);
        return this;
    }

    public SqlStructureBuilder addName(final String f) {
        getNames().add(f);
        return this;
    }

    public SqlStructureBuilder addInsertName(final String tableField, final String propertyName) {
        getInsertNames().put(tableField, propertyName);
        return this;
    }

    public SqlStructureBuilder addRelation(final String r) {
        relations.add(r);
        return this;
    }

    public SqlStructureBuilder addCondition(final String c) {
        condiciones.add(c);
        return this;
    }

    public SqlStructureBuilder addTableAndId(final String t, final String id) {
        return addTable(t).addField(t + "." + id + " as " + id).addName(id);
    }

    public SqlStructureBuilder addTableField(final String t, final String f, final String n) {
        return addField(t + "." + f + " as " + f).addName(n);
    }

    public SqlStructureBuilder addTableFieldAndSort(final String t, final String f, final String n) {
        final String tblFld = t + "." + f;
        return addField(tblFld + " as " + f).addName(n).addSort(n, tblFld);
    }

    public SqlStructureBuilder addTable2Field(final String t, final String f, final String n) {
        return addField(t + "." + f + " as " + t + "_" + f).addName(n);
    }

    public SqlStructureBuilder addTable2FieldAndSort(final String t, final String f, final String n) {
        final String tblFld = t + "." + f;
        return addField(tblFld + " as " + t + "_" + f).addName(n).addSort(n, tblFld);
    }

    public String doSql1() {
        return doSql1(0, 10);
    }

    public String doSql1(@Min(0) Integer offset, @Min(0) @Max(10000) Integer max) {
        final Set<String> l00 = new HashSet<>();
        l00.addAll(relations);
        l00.addAll(condiciones);
        
        StringBuilder sql0 = new StringBuilder("SELECT ");
        sql0.append(fields.stream().collect(Collectors.joining(", ")));
        sql0.append(" FROM ");
        sql0.append(tables.stream().collect(Collectors.joining(", ")));

        if (l00.size() > 0) {
            sql0.append(" WHERE ");
            sql0.append(l00.stream().collect(Collectors.joining(" AND  ")));
        }
        sql0.append(" OFFSET " + offset);
        sql0.append(" LIMIT " + max);
        return sql0.toString();
    }

    private String sqlSelect = "";
    private String sqlSelectById = "";
    private String sqlSelectByPkey = "";

    public void buildListSqlSelect() {

        String sql0 = "SELECT ";
        sql0 += fields.stream().collect(Collectors.joining(", "));
        sql0 += " FROM ";
        sql0 += tables.stream().collect(Collectors.joining(", "));

        if (relations.size() > 0) {
            sql0 += " WHERE ";
            sql0 += relations.stream().collect(Collectors.joining(" AND  "));
        }
        this.sqlSelect = sql0;
    }

    public void buildListSqlSelectById() {

        String sql0 = "SELECT ";
        sql0 += fields.stream().collect(Collectors.joining(", "));
        sql0 += " FROM ";
        sql0 += tables.stream().collect(Collectors.joining(", "));

        if (relations.size() > 0) {
            sql0 += " WHERE ";
            sql0 += " id = $1";
        }
        this.sqlSelect = sql0;
    }

    private String sqlCount = "";

    public void buildSqlCount() {

        String sql0 = "SELECT count( " + tables.stream().findFirst().get() + ".id) as n0";
        sql0 += " FROM ";
        sql0 += tables.stream().collect(Collectors.joining(", "));

        if (relations.size() > 0) {
            sql0 += " WHERE ";
            sql0 += relations.stream().collect(Collectors.joining(" AND  "));
        }

        this.sqlCount = sql0;
    }

    public String uniqueForField(String fld) {
        String s = "";
        if (this.relations.size() > 0) {
            s += " AND ";
        } else {
            s += " WHERE ";
        }
        s += (tables.stream().findFirst().get() + "." + fld + " = $1 limit 1");
        return s;
    }

    public final String stringConditions(Set condiciones0) {
        String s = "";
        if (condiciones0.size() > 0) {
            if (this.relations.size() > 0) {
                s += " AND ";
            } else {
                s += " WHERE ";
            }

        }

        return s += condiciones0.stream().collect(Collectors.joining(" AND "));
    }

    public String doSqlCount() {
        Set<String> l00 = new HashSet<>(5);
        l00.addAll(relations);
        l00.addAll(condiciones);

        String sql0 = "SELECT count( " + tables.iterator().next().toString() + ".id) as n0";
        sql0 += " FROM ";
        sql0 += tables.stream().collect(Collectors.joining(", "));

        if (l00.size() > 0) {
            sql0 += " WHERE ";
            sql0 += l00.stream().collect(Collectors.joining(" AND  "));
        }

        return sql0;
    }

    public Integer doIlPkey(final Map<String, List<String>> params, final String tbl, Integer numParam, Tuple tuple) {
        List<String> il_pkey = params.get("il_pkey");
        if (il_pkey != null && !il_pkey.isEmpty()) {
            addCondition(tbl + ".pkey ilike $" + numParam);
            tuple.addString(il_pkey.get(0) + "%");
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param tbl
     * @param pn
     * @param fld
     * @param params
     * @param numParam
     * @param tuple
     * @param condiciones2
     * @return
     */
    public Integer doIlPSimple(final String tbl, final String pn,
            final String fld, final Map<String, List<String>> params,
            final Integer numParam, final Tuple tuple, final Set<String> condiciones2) {

        final List<String> il_p = params.get("il_" + pn);
        if (il_p != null && !il_p.isEmpty()) {
            condiciones2.add(tbl + "." + fld + " ilike $" + numParam);
            tuple.addString(il_p.get(0) + "%");
            return 1;
        }
        return 0;
    }

    public Integer doEqBoolPSimple(final String tbl, final String pn,
            final String fld, final Map<String, List<String>> params,
            Integer numParam, Tuple tuple, Set<String> condiciones2) {
        final List<String> il_p = params.get("booleq_" + pn);
        if (il_p != null && !il_p.isEmpty()) {
            final String vbool = il_p.get(0);
            if (vbool.equalsIgnoreCase("true") || vbool.equalsIgnoreCase("false")) {
                condiciones2.add(tbl + "." + fld + " = $" + numParam);
                tuple.addBoolean(Boolean.parseBoolean(vbool));
                return 1;
            }
        }
        return 0;
    }

    public Integer doIdPSimple(final String tbl, final Map<String, List<String>> params,
            Integer numParam, Tuple tuple, Set<String> condiciones2) {
        final List<String> id = params.get("id");
        if (id != null && id.size() > 0) {
            if (id.get(0).matches("[0-9]+")) {
                condiciones2.add(tbl + ".id = $" + numParam);
                tuple.addLong(Long.parseLong(id.get(0)));
                return 1;
            }

        }
        return 0;
    }

    public SqlStructureBuilder propIdIn(final String tbl, final Map<String, List<String>> params) {
        final String c_id0 = "id";
        final String t_id0 = tbl + "_id";
        if (params.containsKey(c_id0)) {
            final String losIds = params.get(c_id0).stream().filter(n -> n.matches("[0-9]+") && n.length() > 0).map(n -> Long.parseLong(n)).map(n -> n.toString()).collect(Collectors.joining(","));
            return addCondition(t_id0 + " IN (" + losIds + ")");
        }
        return this;
    }

    public SqlStructureBuilder ver00(final String dc, final String tbl, final Map<String, List<String>> params) {
        final String c_id0 = dc + "_id";
        final String t_id0 = tbl + "_id";

        if (params.containsKey(c_id0)) {
            final String losIds = params.get(c_id0).stream().map(n -> Long.parseLong(n)).map(n -> n.toString()).collect(Collectors.joining(","));
            return addCondition(t_id0 + " IN (" + losIds + ")");
        }
        return this;
    }

    public void ver00a(final String dc, final String tbl, final Map<String, List<String>> params, Set conditions0) {
        final String c_id0 = dc + "_id";

        if (params.containsKey(c_id0)) {
            final String t_id0 = tbl + ".id";
            final String losIds = params.get(c_id0).stream().filter(n -> n.matches("[0-9]+") && n.length() > 0).map(n -> Long.parseLong(n)).map(n -> n.toString()).collect(Collectors.joining(","));
            conditions0.add(t_id0 + " IN (" + losIds + ")");
        }

    }

    /**
     * @return the names
     */
    public Set getNames() {
        return names;
    }

    /**
     * @return the sqlSelect
     */
    public String getSqlSelect() {
        return sqlSelect;
    }

    /**
     * @return the sqlCount
     */
    public String getSqlCount() {
        return sqlCount;
    }

    /**
     * @return the insertNames
     */
    public Map getInsertNames() {
        return insertNames;
    }

    protected static final String doIdsToSql(final List<String> l) {
        return l.stream().filter(n -> n.matches("[0-9]+") && n.length() > 0).map(Long::parseLong)
                .map(n -> n.toString()).collect(Collectors.joining(","));
    }

    private static final String PFX_ORDERBY = " ORDER BY ";

    public final String doSQLORDEN(final Map<String, List<String>> params, String tb0) {
        final List<String> orderby = params.get("orderby");
        final List<String> dir0 = params.get("sort");
        final String dir00 = dir0 != null && !dir0.isEmpty() && !dir0.get(0).equals("asc") ? "asc" : "desc";

        if (orderby != null && !orderby.isEmpty()) {
            final String orderByFld = getSorts().get(orderby.get(0));
            if (orderByFld != null) {
                return PFX_ORDERBY + orderByFld + " " + dir00 + " ";
            }
        }
        return PFX_ORDERBY + tb0 + ".id " + dir00 + " ";
    }

}
