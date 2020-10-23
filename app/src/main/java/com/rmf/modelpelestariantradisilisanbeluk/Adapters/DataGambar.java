package com.rmf.modelpelestariantradisilisanbeluk.Adapters;

public class DataGambar {
    private int image;
    private int widthCard;
    private int heightCard;

    public DataGambar(int image, int widthCard, int heightCard) {
        this.image = image;
        this.widthCard = widthCard;
        this.heightCard = heightCard;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getWidthCard() {
        return widthCard;
    }

    public void setWidthCard(int widthCard) {
        this.widthCard = widthCard;
    }

    public int getHeightCard() {
        return heightCard;
    }

    public void setHeightCard(int heightCard) {
        this.heightCard = heightCard;
    }
}
