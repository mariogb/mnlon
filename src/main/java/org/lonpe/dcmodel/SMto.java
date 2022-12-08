/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.dcmodel;

/**
 *
 * @author mario
 */
public class SMto {
    private String n;
    private String t;
    private String pc;
    private String setBySys;
    private String onForm;
    private String from;
    

    public SMto(String n, String t) {
        this.n = n;
        this.t = t;
    }
    public SMto(String n, String t, String from) {
        this.n = n;
        this.t = t;        
        this.from = from;
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
     * @return the pc
     */
    public String getPc() {
        return pc;
    }

    /**
     * @param pc the pc to set
     */
    public void setPc(String pc) {
        this.pc = pc;
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
     * @return the onForm
     */
    public String getOnForm() {
        return onForm;
    }

    /**
     * @param onForm the onForm to set
     */
    public void setOnForm(String onForm) {
        this.onForm = onForm;
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


    
    
    
}
