package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Classes.User;

import java.util.ArrayList;
import java.util.Objects;

public class adapterAdminHome extends RecyclerView.Adapter<adapterAdminHome.MyViewHolder>{
    Context context;
    ArrayList<User> uList;

    public adapterAdminHome(Context context, ArrayList<User> uList){
        this.context = context;
        this.uList = uList;
    }

    @NonNull
    @Override
    public adapterAdminHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_admin_rv_card,parent,false);

        return new adapterAdminHome.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterAdminHome.MyViewHolder holder, int position) {
        holder.uname.setText(/*"UserName:\n"+*/uList.get(position).getName());
        holder.uid.setText(/*"UserID:\n"+*/uList.get(position).getUserId());
        holder.email.setText(/*"Email:\n"+*/uList.get(position).getEmail());
        holder.type.setText(/*"Type:\n"+*/uList.get(position).getType());
        holder.status.setText(/*"Status:\n"+*/uList.get(position).getStatus());

        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(Objects.equals(uList.get(position).getType(), "Student")) {
                    Intent intent = new Intent(context, adminViewUserProfile.class);
                    intent.putExtra("uid", uList.get(position).getUserId());
                    intent.putExtra("type", "Student");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if(Objects.equals(uList.get(position).getType(), "University")){
                    Intent intent = new Intent(context, adminViewUserProfile.class);
                    intent.putExtra("uid", uList.get(position).getUserId());
                    intent.putExtra("type", "University");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return uList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView uid , uname , email ,status, type;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uid = itemView.findViewById(R.id._uid);
            uname = itemView.findViewById(R.id._uname);
            email = itemView.findViewById(R.id._email);
            status = itemView.findViewById(R.id._status);
            type = itemView.findViewById(R.id._type);
            btn = itemView.findViewById(R.id.admin_view);

        }
    }
}
