package com.example.api_call_create.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.api_call_create.model.Employee;
import com.example.api_call_create.model.ParentClass;
import com.example.api_call_create.retrofit.ApiClient;
import com.example.api_call_create.retrofit.ApiInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositoryEmployee {
    private ApiInterface apiRequest;

    public RepositoryEmployee() {
        apiRequest=ApiClient.getApiInterface();
    }

    public LiveData<ParentClass> getEmployee(){
        final MutableLiveData<ParentClass> response=new MutableLiveData<>();
        apiRequest.getAllEmployee()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ParentClass>() {
                    @Override
                    public void accept(ParentClass parentClass) throws Exception {
                        if(parentClass!=null)
                            response.postValue(parentClass);
                        else
                            response.postValue(null);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("RepositoryEmployee", "accept:=======> "+throwable);
                    }
                } );

        return response;
    }

}

