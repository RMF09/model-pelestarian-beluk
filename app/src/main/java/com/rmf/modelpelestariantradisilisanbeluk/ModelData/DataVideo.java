package com.rmf.modelpelestariantradisilisanbeluk.ModelData;

public class DataVideo {
    private String videoId;
    private String judul;
    private String deskripsi;
    private int image;

    public DataVideo(String videoId, String judul, String deskripsi, int image) {
        this.videoId = videoId;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
