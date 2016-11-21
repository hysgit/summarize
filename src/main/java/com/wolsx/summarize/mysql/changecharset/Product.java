package com.wolsx.summarize.mysql.changecharset;

import java.util.Date;

/**
 * Created by hy on 10/20/16.
 */
public class Product {
    private long pid;
    private long did;
    private String pname;
    private String ptitle;
    private String pstitle;
    private String pprice;
    private String plogo;
    private long psort;
    private String pcontent;
    private int ptype;
    private int snum;
    private int delflag;
    private Date addtimes;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPstitle() {
        return pstitle;
    }

    public void setPstitle(String pstitle) {
        this.pstitle = pstitle;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPlogo() {
        return plogo;
    }

    public void setPlogo(String plogo) {
        this.plogo = plogo;
    }

    public long getPsort() {
        return psort;
    }

    public void setPsort(long psort) {
        this.psort = psort;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public int getDelflag() {
        return delflag;
    }

    public void setDelflag(int delflag) {
        this.delflag = delflag;
    }

    public Date getAddtimes() {
        return addtimes;
    }

    public void setAddtimes(Date addtimes) {
        this.addtimes = addtimes;
    }
}
