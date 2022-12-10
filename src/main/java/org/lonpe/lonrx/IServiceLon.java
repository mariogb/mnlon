/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.lonpe.lonrx;

import io.reactivex.Single;
import io.vertx.reactivex.sqlclient.Tuple;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.lonpe.dcmodel.DCModel;
import org.lonpe.forquery.ObjForQuery;
import org.lonpe.model.Account;
import org.lonpe.sql.ConditionInfo;
import org.lonpe.sql.SqlStructureBuilder;
import org.lonpe.model.IDcLon;

/**
 *
 * @author mgb
 */
public interface IServiceLon<T> {

    ConditionInfo doCondiciones(final Map<String, List<String>> params, Tuple tuple);

    Single<Map<String, Object>> doList(final ObjForQuery objForQuery);

    Set<String> getNames();

    String getSqlForList();

    //SqlStructureBuilder getSqlStructureBuiderLon();
    /**
     * @return the dCModel
     */
    DCModel getdCModel();

    Single<Map<String, Object>> save(T t);

}
