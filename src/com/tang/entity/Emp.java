package com.tang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weepppp 2022/6/17 17:26
 * @version V1.0
 * @since
 **/
public class Emp implements Serializable {
    private int eid;
    private String photo;
    private String ename;
    private int eage;
    private String edesc;
    private Date edate;
    private int did;
    private String dname;

    public Emp() {
    }

    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", photo='" + photo + '\'' +
                ", ename='" + ename + '\'' +
                ", eage=" + eage +
                ", edesc='" + edesc + '\'' +
                ", edate=" + edate +
                ", did=" + did +
                ", dname='" + dname + '\'' +
                '}';
    }

    public Emp(int eid, String photo, String ename, int eage, String edesc, Date edate, int did, String dname) {
        this.eid = eid;
        this.photo = photo;
        this.ename = ename;
        this.eage = eage;
        this.edesc = edesc;
        this.edate = edate;
        this.did = did;
        this.dname = dname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getEage() {
        return eage;
    }

    public void setEage(int eage) {
        this.eage = eage;
    }

    public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
