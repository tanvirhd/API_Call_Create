package com.example.api_call_create.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_call_create.R;
import com.example.api_call_create.model.Employee;
import com.example.api_call_create.retrofit.model.ApiClient;
import com.example.api_call_create.retrofit.model.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static  String TAG="MainActivity";
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textview);

        Retrofit instance= ApiClient.instance();
        final ApiInterface apiInterface=instance.create(ApiInterface.class);

        apiInterface.getAllEmploye().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(response!=null){
                    Toast.makeText(MainActivity.this, "got response", Toast.LENGTH_SHORT).show();
                    tv.setText(""+response.body().size());
                }else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
            }
        });

    }
}
