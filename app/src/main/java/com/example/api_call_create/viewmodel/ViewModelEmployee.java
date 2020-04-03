package com.example.api_call_create.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.api_call_create.model.Employee;
import com.example.api_call_create.model.ParentClass;
import com.example.api_call_create.repository.RepositoryEmployee;

import java.util.List;

public class ViewModelEmployee extends AndroidViewModel {
    public static RepositoryEmployee repositoryEmployee;

    public ViewModelEmployee(@NonNull Application application) {
        super(application);
        if(repositoryEmployee==null)
        { repositoryEmployee=new RepositoryEmployee(); }
    }

    public LiveData<List<Employee>>  getAllEmployeeInfo(){
        return repositoryEmployee.getEmployee();
    }

    public LiveData<Employee> addEmployee(Employee employee){
        if(employee==null){
            Log.d("ViewModelEmployee", "addEmployee: yes null");
        }
        return repositoryEmployee.addEmployee(employee);
    }

}
