package com.example.api_call_create.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.api_call_create.R;
import com.example.api_call_create.adapter.EmployeeClassAdapter;
import com.example.api_call_create.model.Employee;
import com.example.api_call_create.model.ParentClass;
import com.example.api_call_create.viewmodel.ViewModelEmployee;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    ViewModelEmployee viewModelEmployee;
    List<Employee> allEmployee=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);

        final EmployeeClassAdapter adapter=new EmployeeClassAdapter(this,allEmployee);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        viewModelEmployee=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelEmployee.class);

        viewModelEmployee.getAllEmployeeInfo().observe(this, new Observer<List<Employee>>() {
                 @Override
                 public void onChanged(List<Employee> employeeList) {
                     adapter.isShimmer=false;
                     allEmployee.addAll(employeeList);
                     adapter.notifyDataSetChanged();
                     Log.d(TAG, "onChanged: size="+allEmployee.get(1).getEmail());
                 }
             });

        Employee e=new Employee("163-15E4","Minhajul","Arefin","mihaj@mail.com","https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
        Log.d(TAG, "onCreate: viewModelEmployee is null=" + viewModelEmployee==null?"yes":"nop");
        viewModelEmployee.addEmployee(e)
                .observe(this, new Observer<Employee>() {
                    @Override
                    public void onChanged(Employee employee) {
                        Log.d(TAG, "onChanged: "+employee.getMessage());
                    }
                });

    }
}
