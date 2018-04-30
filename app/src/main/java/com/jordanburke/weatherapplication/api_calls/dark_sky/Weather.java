package com.jordanburke.weatherapplication.api_calls.dark_sky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("currently")
    private CurrentProperties currentProperties;

    @SerializedName("daily")
    private DailyProperties mDailyProperties;

    protected Weather(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public CurrentProperties getCurrentProperties() {
        return currentProperties;
    }

    public DailyProperties getmDailyProperties() {
        return mDailyProperties;
    }

    public static Creator<Weather> getCREATOR() {
        return CREATOR;
    }

    class CurrentProperties {

    }

    class DailyProperties {

    }
}
