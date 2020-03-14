package com.example.api_call_create.retrofit.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private  static Retrofit retrofit=null;

    public  static synchronized  Retrofit instance(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://dummy.restapiexample.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
