/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lonpe.forquery;

import java.util.List;
import java.util.Map;

/**
 *
 * @author l5
 */
public class ObjForQuery {
    
    private Map<String, List<String>> params;
    private Long max;
    private Long offset;
    private boolean withCount;

    public ObjForQuery(Map<String, List<String>> params, Long max, Long offset, boolean withCount) {
        this.params = params;
        this.max = max;
        this.offset = offset;
        this.withCount = withCount;
    }

    /**
     * @return the params
     */
    public Map<String, List<String>> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String, List<String>> params) {
        this.params = params;
    }

    /**
     * @return the max
     */
    public Long getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Long max) {
        this.max = max;
    }

    /**
     * @return the offset
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * @return the withCount
     */
    public boolean isWithCount() {
        return withCount;
    }

    /**
     * @param withCount the withCount to set
     */
    public void setWithCount(boolean withCount) {
        this.withCount = withCount;
    }
    
    
    
    
}
