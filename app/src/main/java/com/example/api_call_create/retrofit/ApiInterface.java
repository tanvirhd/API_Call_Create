package com.example.api_call_create.retrofit;

import com.example.api_call_create.model.Employee;
import com.example.api_call_create.model.ParentClass;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.POST;

public interface ApiInterface {

    //@GET("/api/users/")
    //Observable<ParentClass> getAllEmployee();

    @POST("get_data.php/")
    Observable<List<Employee>> getAllEmployee();
}
