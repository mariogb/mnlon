/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.dcmodel;

import java.util.List;

/**
 *
 * @author mario
 */
public class SProperty {

    private String n;
    private String t;
    private Boolean rq = true;
    private Boolean uq = false;
    private String setBySys;
    private List<String> inList;
    private Integer min;
    private Integer max;
    private Boolean withIndex;

    public SProperty(String n, String t) {
        this.n = n;
        this.t = t;
    }

    public SProperty(String n, String t, Boolean rq, Boolean uq) {
        this.n = n;
        this.t = t;
        this.rq = rq;
        this.uq = uq;
    }

    /**
     * @return the n
     */
    public String getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(String n) {
        this.n = n;
    }

    /**
     * @return the t
     */
    public String getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(String t) {
        this.t = t;
    }

    /**
     * @return the rq
     */
    public Boolean getRq() {
        return rq;
    }

    /**
     * @param rq the rq to set
     */
    public void setRq(Boolean rq) {
        this.rq = rq;
    }

    /**
     * @return the uq
     */
    public Boolean getUq() {
        return uq;
    }

    /**
     * @param uq the uq to set
     */
    public void setUq(Boolean uq) {
        this.uq = uq;
    }

    /**
     * @return the setBySys
     */
    public String getSetBySys() {
        return setBySys;
    }

    /**
     * @param setBySys the setBySys to set
     */
    public void setSetBySys(String setBySys) {
        this.setBySys = setBySys;
    }

    /**
     * @return the inList
     */
    public List<String> getInList() {
        return inList;
    }

    /**
     * @param inList the inList to set
     */
    public void setInList(List<String> inList) {
        this.inList = inList;
    }

    /**
     * @return the max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * @return the withIndex
     */
    public Boolean getWithIndex() {
        return withIndex;
    }

    /**
     * @param withIndex the withIndex to set
     */
    public void setWithIndex(Boolean withIndex) {
        this.withIndex = withIndex;
    }

}
