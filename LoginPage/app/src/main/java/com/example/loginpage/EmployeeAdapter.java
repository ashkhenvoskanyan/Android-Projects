package com.example.loginpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    ArrayList<EmployeeInfo> employeeData = new ArrayList<>();
    Context context;

    public EmployeeAdapter(ArrayList<EmployeeInfo> employeeData, EmployeList activity) {
        this.employeeData = employeeData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final EmployeeInfo employeeDataList = employeeData.get(position);
        holder.imageView.setImageResource(employeeDataList.getImage());
        holder.textView.setText(employeeDataList.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("image", employeeDataList.getImage());
                intent.putExtra("name", employeeDataList.getName());
                context.startActivity(intent);


             }
        });
    }

    @Override
    public int getItemCount() {
        return employeeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.name_surname);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<EmployeeInfo> filteredList){
        employeeData = filteredList;
        notifyDataSetChanged();
    }
}
