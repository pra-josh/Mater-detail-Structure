package com.pra.interview.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pra.myapplication.UI.Util.DateCustomUtils;


public class MediaDate implements Parcelable {

    String dateString;
    String year;

    MediaDate() {

    }

    protected MediaDate(Parcel in) {
        dateString = in.readString();
        year = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateString);
        dest.writeString(year);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaDate> CREATOR = new Creator<MediaDate>() {
        @Override
        public MediaDate createFromParcel(Parcel in) {
            return new MediaDate(in);
        }

        @Override
        public MediaDate[] newArray(int size) {
            return new MediaDate[size];
        }
    };

    public String getDateString() {
      return   DateCustomUtils.Companion.
                getDateFromStringForInvoice(dateString);

    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
