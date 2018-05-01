package com.jordanburke.weatherapplication.api_calls.dark_sky;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

        @SerializedName("summary")
        private String summary;

        @SerializedName("icon")
        private String icon;

        @SerializedName("temperature")
        private double temperature;

        public String getSummary() {
            return summary;
        }

        public String getIcon() {
            return icon;
        }

        public double getTemperature() {
            return temperature;
        }
    }

    class DailyProperties {
        @SerializedName("data")
        private List<DailyData> mDailyDataList;

        public List<DailyData> mDailyDataList() {
            return mDailyDataList;
        }

        class DailyData {

            @SerializedName("precipProbability")
            private String precipProbability;

            @SerializedName("temperatureHigh")
            private boolean temperatureHigh;

            @SerializedName("temperatureLow")
            private boolean temperatureLow;

            public String getPrecipProbability() {
                return precipProbability;
            }

            public boolean isTemperatureHigh() {
                return temperatureHigh;
            }

            public boolean isTemperatureLow() {
                return temperatureLow;
            }
        }

    }
}
