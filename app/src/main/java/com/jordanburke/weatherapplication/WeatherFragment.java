package com.jordanburke.weatherapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jordanburke.weatherapplication.api_calls.dark_sky.Weather;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {

    private Weather weather;
    private String place;
    @BindView(R.id.layout_background)
    protected ConstraintLayout fragmentBackground;
    @BindView(R.id.main_constraint_layout)
    protected ConstraintLayout mainLayout;
    @BindView(R.id.submit_button)
    protected Button submitButton;
    @BindView(R.id.location_edit_text)
    protected TextInputEditText locationEdit;
    @BindView(R.id.location_text_view)
    protected TextView locationView;
    @BindView(R.id.precip_percentage_view)
    protected TextView precipPercentView;
    @BindView(R.id.precip_title_view)
    protected TextView precipTitle;
    @BindView(R.id.summary_text_view)
    protected TextView summaryView;
    @BindView(R.id.degree_view_bottom)
    protected TextView degreeBottom;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        return view;
//        weather = getArguments().getParcelable();
//          place = getArguments().toString();
    }

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        locationView.setText(place);

    }

    private void setWeatherIcon() {



    }

}
