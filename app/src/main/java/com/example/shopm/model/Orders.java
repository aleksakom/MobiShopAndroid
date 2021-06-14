package com.example.shopm.model;

public class Orders {
    private String adresao, cityo, date, nameo, phoneo, postalcodeo, time, opid;

    public Orders(){

    }

    public Orders(String adresao, String cityo, String date, String nameo, String phoneo, String postalcodeo, String time, String opid) {
        this.adresao = adresao;
        this.cityo = cityo;
        this.date = date;
        this.nameo = nameo;
        this.phoneo = phoneo;
        this.postalcodeo = postalcodeo;
        this.time = time;
        this.opid = opid;
    }

    public String getAdresao() {
        return adresao;
    }

    public String getCityo() {
        return cityo;
    }

    public String getDate() {
        return date;
    }

    public String getNameo() {
        return nameo;
    }

    public String getPhoneo() {
        return phoneo;
    }

    public String getPostalcodeo() {
        return postalcodeo;
    }

    public String getTime() {
        return time;
    }

    public void setAdresao(String adresao) {
        this.adresao = adresao;
    }

    public void setCityo(String cityo) {
        this.cityo = cityo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNameo(String nameo) {
        this.nameo = nameo;
    }

    public void setPhoneo(String phoneo) {
        this.phoneo = phoneo;
    }

    public void setPostalcodeo(String postalcodeo) {
        this.postalcodeo = postalcodeo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getOpid() {
        return opid;
    }
}
