package com.geekbrains.notepad;
import android.os.Parcel;
import android.os.Parcelable;

public class Fields implements Parcelable {
    private int imageIndex;
    private String fieldName;

    public Fields(int imageIndex, String fieldName){
        this.imageIndex = imageIndex;
        this.fieldName = fieldName;
    }

    protected Fields (Parcel in) {
        imageIndex = in.readInt();
        fieldName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getImageIndex());
        dest.writeString(getFieldName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fields> CREATOR = new Creator<Fields>() {
        @Override
        public Fields createFromParcel(Parcel in) {
            return new Fields(in);
        }

        @Override
        public Fields[] newArray(int size) {
            return new Fields[size];
        }
    };

    public int getImageIndex() {
        return imageIndex;
    }

    public String getFieldName() {
        return fieldName;
    }
}
