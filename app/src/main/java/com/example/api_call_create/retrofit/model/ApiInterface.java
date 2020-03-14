package com.example.api_call_create.retrofit.model;

import com.example.api_call_create.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/v1/employees")
    Call<List<Employee>> getAllEmploye();
}
