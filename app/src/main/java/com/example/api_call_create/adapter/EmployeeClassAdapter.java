package com.example.api_call_create.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api_call_create.R;
import com.example.api_call_create.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClassAdapter extends RecyclerView.Adapter<EmployeeClassAdapter.VH> {
    private String TAG="EmployeeClassAdapter";
    private Context context;
    List<Employee> allEmploye=new ArrayList<>();

    public EmployeeClassAdapter(Context context, List<Employee> allEmploye) {
        this.context = context;
        this.allEmploye = allEmploye;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.employee_list,parent,false);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
          holder.name.setText(allEmploye.get(position).getFirstName()+" "+allEmploye.get(position).getLastName());
          holder.email.setText("Email: "+allEmploye.get(position).getEmail());

          try{
              Glide.with(context).load(allEmploye.get(position).getPic_url()).into(holder.image);
          }catch (Exception e){
              Log.d(TAG, "onBindViewHolder: "+e);
          }
    }

    @Override
    public int getItemCount() {
        return allEmploye.size();
    }

    public  class VH extends RecyclerView.ViewHolder{
       ImageView image;
       TextView name,email;

       public VH(@NonNull View itemView) {
           super(itemView);
           image=itemView.findViewById(R.id.employee_image);
           name=itemView.findViewById(R.id.employee_name);
           email=itemView.findViewById(R.id.employee_email);
       }
   }
}
