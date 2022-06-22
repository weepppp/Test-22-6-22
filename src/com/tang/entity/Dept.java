package com.tang.entity;

/**
 * @author weepppp 2022/6/17 22:57
 * @version V1.0
 * @since
 **/
public class Dept {
    private int did;
    private String dname;
    private String dec;

    public Dept() {
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", dec='" + dec + '\'' +
                '}';
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

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public Dept(int did, String dname, String dec) {
        this.did = did;
        this.dname = dname;
        this.dec = dec;
    }
}
