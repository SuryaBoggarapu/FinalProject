package com.apps.foodrepo;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

public class Food  implements Parcelable {

    String name;
    String img;
    String desc,quantity,unit,Alcohol,barcode;


    public Food() {
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(String alcohol) {
        Alcohol = alcohol;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Food(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(desc);
        dest.writeString(quantity);
        dest.writeString(unit);
        dest.writeString(Alcohol);
        dest.writeString(barcode);
    }

    protected Food(Parcel in) {
        name = in.readString();

        img = in.readString();
        desc = in.readString();
        quantity = in.readString();
        unit = in.readString();
        Alcohol = in.readString();
        barcode = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

}
