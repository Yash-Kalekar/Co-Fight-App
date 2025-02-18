package com.example.cofight_app.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    static final String BASE_URL = "https://disease.sh/v2/";

    @GET("countries")
    Call<List<CountryData>> getCountryData();

}
