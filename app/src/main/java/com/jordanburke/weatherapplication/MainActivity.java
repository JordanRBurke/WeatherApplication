package com.jordanburke.weatherapplication;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jordanburke.weatherapplication.api_calls.dark_sky.DarkSkyApi;
import com.jordanburke.weatherapplication.api_calls.dark_sky.Weather;
import com.jordanburke.weatherapplication.api_calls.google.GoogleAddress;
import com.jordanburke.weatherapplication.api_calls.google.GoogleGeoApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private GoogleGeoApi googleGeoApi;
    private String googleBaseUrl;
    private String googleApiKey;
    private Retrofit googleRetrofit;
    private DarkSkyApi darkSkyApi;
    private String darkSkyUrl;
    private String darkSkyApiKey;
    private Retrofit darkSkyRetrofit;
    @BindView(R.id.location_edit_text)
    protected TextInputEditText location;
    private Bundle bundle = new Bundle();
    private WeatherFragment weatherFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherFragment = WeatherFragment.newInstance();
         bundle = new Bundle();

    }

    @Override
    protected void onStart() {
        super.onStart();
        darkSkyUrl = getString(R.string.dark_sky_base_url);
        googleBaseUrl = getString(R.string.google_base_url);
        googleApiKey = getString(R.string.google_key);
        darkSkyApiKey = getString(R.string.dark_sky_key);
        googleGeoApi = getGoogleRetrofit().create(GoogleGeoApi.class);
        darkSkyApi = getDarkSkyRetrofit().create(DarkSkyApi.class);

    }

    private Retrofit getGoogleRetrofit() {
        if (googleRetrofit == null) {
            googleRetrofit = new Retrofit.Builder().baseUrl(googleBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }


        return googleRetrofit;

    }

    private Retrofit getDarkSkyRetrofit() {
        if (darkSkyRetrofit == null) {

            darkSkyRetrofit = new Retrofit.Builder().baseUrl(darkSkyUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }


        return darkSkyRetrofit;

    }
    @OnClick(R.id.submit_button)
    protected void submitClicked() {

//        getLocation(location.getText().toString());
        location.setText("");
    }

//

    private void getLocation(String address) {
        googleGeoApi.getAddress(address, googleApiKey).enqueue(new Callback<GoogleAddress>() {
            @Override
            public void onResponse(Call<GoogleAddress> call, Response<GoogleAddress> response) {
                try {
                    if (response.isSuccessful()) {
                        bundle.putString(PLACE, response.body().getResultsList().get(0).getAddressName());
                        getWeather(response.body().getResultsList().get(0).getGeometry().getGoogleLocation().getLatitude(), response.body().getResultsList().get(0).getGeometry().getGoogleLocation().getLongitude());
                    } else {
                        Toast.makeText(MainActivity.this, "Call made, unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GoogleAddress> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWeather(double lat, double lng) {
        darkSkyApi.getWeather(darkSkyApiKey, lat, lng).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                try {
                    if (response.isSuccessful()) {

                        bundle.putParcelable(WEATHER, response.body());
                        weatherFragment.setArguments(bundle);

                        transitionToWeatherFragment();
                    } else {
                        Toast.makeText(MainActivity.this, "Weather call made, unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Weather Call Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setWeather() {


    }

    private void transitionToWeatherFragment() {


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, weatherFragment).commit();
    }


}
