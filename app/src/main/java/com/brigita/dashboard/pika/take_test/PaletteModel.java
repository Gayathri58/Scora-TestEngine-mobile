package com.brigita.dashboard.pika.take_test;

import android.os.Parcel;
import android.os.Parcelable;

public class PaletteModel implements Parcelable {

    public String data_format_value;
    public int data_format_id;


    public PaletteModel(String data_format_value, int data_format_id) {
        this.data_format_value = data_format_value;
        this.data_format_id = data_format_id;

    }

    public String getData_format_value() {
        return data_format_value;
    }

    public void setData_format_value(String data_format_value) {
        this.data_format_value = data_format_value;
    }


    public int getData_format_id() {
        return data_format_id;
    }

    public void setData_format_id(int data_format_id) {
        this.data_format_id = data_format_id;
    }


    public void readfromParcel(Parcel source) {
        data_format_id = source.readInt();
        data_format_value = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data_format_value);
        dest.writeInt(this.data_format_id);
    }

    protected PaletteModel(Parcel in) {
        this.data_format_value = in.readString();
        this.data_format_id = in.readInt();
    }

    public static final Creator<PaletteModel> CREATOR = new Creator<PaletteModel>() {
        @Override
        public PaletteModel createFromParcel(Parcel source) {
            return new PaletteModel(source);
        }

        @Override
        public PaletteModel[] newArray(int size) {
            return new PaletteModel[size];
        }
    };
}
