package com.example.gestor_contas.ui.notifications.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.ui.notifications.NotificationsFinalActivity;

import java.util.ArrayList;

public class NotificationsRecyclerAdapterFinal extends RecyclerView.Adapter<NotificationsRecyclerAdapterFinal.MyViewHolder> {
    private Context context;
    private ArrayList<String> arrayList_final;

    public NotificationsRecyclerAdapterFinal(Context context, ArrayList<String> arrayList_final) {
        this.context = context;
        this.arrayList_final = arrayList_final;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_notifications_final_textView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notifications_final, parent, false);
        return new NotificationsRecyclerAdapterFinal.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsRecyclerAdapterFinal.MyViewHolder holder, int position) {
            holder.textView.setText(arrayList_final.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList_final.size();
    }


}
