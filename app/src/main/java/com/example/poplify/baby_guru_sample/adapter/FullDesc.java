package com.example.poplify.baby_guru_sample.adapter;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FullDesc implements Parcelable {

    ArrayList<String> fullDescList;

    public List<String> getFullDescList() {
        return fullDescList;
    }

    public void setFullDescList(ArrayList<String> fullDescList) {
        this.fullDescList = fullDescList;
    }

    public  FullDesc()
    {

    }

    public FullDesc(ArrayList<String> fullDescList) {
        this.fullDescList = fullDescList;
    }

    protected FullDesc(Parcel in) {
    }

    public static final Creator<FullDesc> CREATOR = new Creator<FullDesc>() {
        @Override
        public FullDesc createFromParcel(Parcel in) {
            FullDesc fullDesc = new FullDesc();
            Bundle b =in.readBundle();
            fullDesc.fullDescList = (ArrayList<String>) b.getParcelableArrayList("fullDesc");
            return new FullDesc(in);
        }

        @Override
        public FullDesc[] newArray(int size)     {
            return new FullDesc[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle b = new Bundle();
        b.putParcelableArrayList("descList",fullDescList);
        parcel.writeBundle(b);
    }
}
