/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lonpe.dcmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class DCModel {

    private String dc;
    private String pc;

    private List<SProperty> ps;
    private List<SProperty> pspw;
    private List<SMto> mto;
    private List<SMto> mto2;
    private List<SMto> mto3;
    private List<SMto> mto4;

    private List<SOtm> otm;
    private List<SOtm> otm2;
    private List<SOtm> otm3;
    private List<SOtm> otm4;

    public DCModel(String dc, String pc) {
        this.dc = dc;
        this.pc = pc;
    }

    public DCModel(String dc) {
        this.dc = dc;
    }

    /**
     * @return the dc
     */
    public String getDc() {
        return dc;
    }

    /**
     * @param dc the dc to set
     */
    public void setDc(String dc) {
        this.dc = dc;
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
     * @return the ps
     */
    public List<SProperty> getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(List<SProperty> ps) {
        this.ps = ps;
    }

    public DCModel addSProperty(SProperty p) {
        if (this.ps == null) {
            this.ps = new ArrayList<>(8);
        }
        this.ps.add(p);
        return this;
    }

    public DCModel addSPropertyPW(SProperty p) {
        if (this.pspw == null) {
            this.pspw = new ArrayList<>(8);
        }
        this.pspw.add(p);
        return this;
    }

    /**
     * @return the mto
     */
    public List<SMto> getMto() {
        return mto;
    }

    /**
     * @param mto the mto to set
     */
    public void setMto(List<SMto> mto) {
        this.mto = mto;
    }

    public DCModel addSMto(SMto sMto) {
        if (this.mto == null) {
            this.mto = new ArrayList<>();
        }
        this.mto.add(sMto);
        return this;
    }

    public DCModel addSMto2(SMto sMto) {
        if (this.mto2 == null) {
            this.mto2 = new ArrayList<>();
        }
        this.mto2.add(sMto);
        return this;
    }

    public DCModel addSMto3(SMto sMto) {
        if (this.mto3 == null) {
            this.mto3 = new ArrayList<>();
        }
        this.mto3.add(sMto);
        return this;
    }

    public DCModel addSMto4(SMto sMto) {
        if (this.mto4 == null) {
            this.mto4 = new ArrayList<>();
        }
        this.mto4.add(sMto);
        return this;
    }

    /**
     * @return the mto2
     */
    public List<SMto> getMto2() {
        return mto2;
    }

    /**
     * @param mto2 the mto2 to set
     */
    public void setMto2(List<SMto> mto2) {
        this.mto2 = mto2;
    }

    /**
     * @return the mto3
     */
    public List<SMto> getMto3() {
        return mto3;
    }

    /**
     * @param mto3 the mto3 to set
     */
    public void setMto3(List<SMto> mto3) {
        this.mto3 = mto3;
    }

    /**
     * @return the mto4
     */
    public List<SMto> getMto4() {
        return mto4;
    }

    /**
     * @param mto4 the mto4 to set
     */
    public void setMto4(List<SMto> mto4) {
        this.mto4 = mto4;
    }

    /**
     * @return the pspw
     */
    public List<SProperty> getPspw() {
        return pspw;
    }

    /**
     * @param pspw the pspw to set
     */
    public void setPspw(List<SProperty> pspw) {
        this.pspw = pspw;
    }

    public DCModel addSOtm(SOtm p) {
        if (this.otm == null) {
            this.otm = new ArrayList<>(4);
        }
        this.otm.add(p);
        return this;
    }

    public DCModel addSOtm2(SOtm p) {
        if (this.otm2 == null) {
            this.otm2 = new ArrayList<>(4);
        }
        this.otm2.add(p);
        return this;
    }

    public DCModel addSOtm3(SOtm p) {
        if (this.otm3 == null) {
            this.otm3 = new ArrayList<>(4);
        }
        this.otm3.add(p);
        return this;
    }
    

}
