package com.example.gestor_contas.ui.notifications.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.ui.notifications.NotificationsFinalActivity;

import java.util.ArrayList;

public class NotificationsRecyclerAdapterMes extends RecyclerView.Adapter<NotificationsRecyclerAdapterMes.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList_mes;
    private Intent notificationsActivityMes_Intent;

    public NotificationsRecyclerAdapterMes(Context context, ArrayList<String> arrayList_mes) {
        this.context = context;
        this.arrayList_mes = arrayList_mes;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private Button button_mes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            button_mes = itemView.findViewById(R.id.adapter_notifications_mes_button_mes);
        }
    }

    @NonNull
    @Override
    public NotificationsRecyclerAdapterMes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notifications_mes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsRecyclerAdapterMes.MyViewHolder holder, int position) {
        holder.button_mes.setText(arrayList_mes.get(position));
        String mes = arrayList_mes.get(position);
        holder.button_mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationsActivityMes_Intent = new Intent(context, NotificationsFinalActivity.class);
                notificationsActivityMes_Intent.putExtra("TAG-MES", mes);
                context.startActivity(notificationsActivityMes_Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList_mes.size();
    }


}
