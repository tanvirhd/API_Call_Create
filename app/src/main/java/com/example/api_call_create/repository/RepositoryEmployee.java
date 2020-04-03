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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RepositoryEmployee {
    private ApiInterface apiRequest;

    public RepositoryEmployee() {
        apiRequest=ApiClient.getApiInterface();
    }

    public LiveData<List<Employee>> getEmployee(){
        final MutableLiveData<List<Employee>> response=new MutableLiveData<>();
        apiRequest.getAllEmployee()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {
                        if(employees!=null)
                            response.postValue(employees);
                        else
                            response.postValue(null);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("RepositoryEmployee", "getEmployee() => "+throwable);
                    }
                } );

        return response;
    }

    public LiveData<Employee> addEmployee(Employee employee){
        final MutableLiveData<Employee> response =new MutableLiveData<>();
        apiRequest.addEmployee(employee).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Employee>() {
                    @Override
                    public void accept(Employee employee) throws Exception {
                        Log.d("MyError", "accept: "+employee);
                        //response.postValue(employee);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("RepositoryEmployee", "addEmployee() => "+throwable);
                    }
                });
        return response;
    }

}

