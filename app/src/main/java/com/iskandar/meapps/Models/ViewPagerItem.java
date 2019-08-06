package com.iskandar.meapps.Models;
/*
* tgl pengerjaan : 3 agustus 2019
* nim : 10116121
* nama : mohammad iskandar
* kelas : IF-3
* */
public class ViewPagerItem {

    String judul_vp, desc_vp;
    int img_vp;

    public ViewPagerItem(String judul_vp, String desc_vp, int img_vp) {
        this.judul_vp = judul_vp;
        this.desc_vp = desc_vp;
        this.img_vp = img_vp;
    }

    public String getJudul_vp() {
        return judul_vp;
    }

    public void setJudul_vp(String judul_vp) {
        this.judul_vp = judul_vp;
    }

    public String getDesc_vp() {
        return desc_vp;
    }

    public void setDesc_vp(String desc_vp) {
        this.desc_vp = desc_vp;
    }

    public int getImg_vp() {
        return img_vp;
    }

    public void setImg_vp(int img_vp) {
        this.img_vp = img_vp;
    }
}