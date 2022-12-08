/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.dcmodel;

/**
 *
 * @author mgb
 */
public class SOtm {

    private String n;
    private String t;
    private String from;

    private String onRelation;
    private String onBiRelation;

    public SOtm(String n, String t, String from, String onRelation, String onBiRelation) {
        this.n = n;
        this.from = from;
        this.t = t;
        this.onRelation = onRelation;
        this.onBiRelation = onBiRelation;
    }

    public SOtm(String n, String t, String from, String onRelation) {
        this.n = n;
        this.from = from;
        this.t = t;
        this.onRelation = onRelation;
    }

    public SOtm(String n, String t, String from) {
        this.n = n;
        this.from = from;
        this.t = t;
    }

    public SOtm(String n, String t) {
        this.n = n;
        this.from = from;
    }

    /*
    
        onRelationSql?: string;
    tDw?: string;
    dcTable?: string | undefined;
    t: DCJsModelKey,
    n: string,
    onRelation?: string,
    nUp?: string,
    l?:string
     */
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
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
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
     * @return the onRelation
     */
    public String getOnRelation() {
        return onRelation;
    }

    /**
     * @param onRelation the onRelation to set
     */
    public void setOnRelation(String onRelation) {
        this.onRelation = onRelation;
    }

    /**
     * @return the onBiRelation
     */
    public String getOnBiRelation() {
        return onBiRelation;
    }

    /**
     * @param onBiRelation the onBiRelation to set
     */
    public void setOnBiRelation(String onBiRelation) {
        this.onBiRelation = onBiRelation;
    }

}
