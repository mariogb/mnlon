package org.lonpe.sql;

import java.util.LinkedHashSet;

/**
 *
 * @author mgb
 */
public class ConditionInfo {

    private LinkedHashSet<String> condiciones;
    private Integer numParam;

    private Integer max;
    private Integer offset;
    private boolean withCount;
    private String orden;

    public ConditionInfo(LinkedHashSet<String> condiciones, Integer numParam, Integer max, Integer offset, String orden, boolean withCount) {
        this.condiciones = condiciones;
        this.numParam = numParam;
        this.max = max;
        this.offset = offset;
        this.withCount = withCount;
        this.orden = orden;
    }

    public String getOrden() {
        return orden;
    }

    public LinkedHashSet<String> getCondiciones() {
        return condiciones;
    }

    public Integer getNumParam() {
        return numParam;
    }

    /**
     * @return the max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @return the withCount
     */
    public boolean isWithCount() {
        return withCount;
    }

}
