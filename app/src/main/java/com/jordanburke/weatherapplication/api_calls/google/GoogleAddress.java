package com.jordanburke.weatherapplication.api_calls.google;

import com.google.gson.annotations.SerializedName;

import java.sql.ResultSet;
import java.util.List;

public class GoogleAddress {


    @SerializedName("results")
    private List<Results> resultsList;

    public List<Results> getResultsList() {
        return resultsList;
    }

    public class Results {

        @SerializedName("formatted_address")
        private String mAddressName;

        @SerializedName("geometry")
        private Geometry mGeometry;

        public String getmAddressName() {
            return mAddressName;
        }

        public Geometry getmGeometry() {
            return mGeometry;
        }

        public class Geometry {
            @SerializedName("location")
            private GoogleLocation mGoogleLocation;

            class GoogleLocation {



            }
        }
    }

}
