package com.jordanburke.weatherapplication.api_calls.google;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleGeoApi {

    @GET("json")
    Call<GoogleAddress> getAddress(@Query("address") String address, @Query("api_key") String ApiKey);

}
