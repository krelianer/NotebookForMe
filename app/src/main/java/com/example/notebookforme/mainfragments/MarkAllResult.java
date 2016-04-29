package com.example.notebookforme.mainfragments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kreliou on 06/04/2016.
 */
public class MarkAllResult {
    @SerializedName("count")
    public int mCount;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }
}
