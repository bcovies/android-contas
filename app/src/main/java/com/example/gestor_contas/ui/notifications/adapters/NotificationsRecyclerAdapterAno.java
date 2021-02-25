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
import com.example.gestor_contas.ui.notifications.NotificationsActivityMes;

import java.util.ArrayList;

public class NotificationsRecyclerAdapterAno extends RecyclerView.Adapter<NotificationsRecyclerAdapterAno.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList_anos;
    private Intent notificationsActivityMes_Intent;

    public NotificationsRecyclerAdapterAno(Context context, ArrayList<String> arrayList_anos) {
        this.context = context;
        this.arrayList_anos = arrayList_anos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private Button button_ano;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            button_ano = itemView.findViewById(R.id.adapter_notifications_mes_button_mes);
        }
    }

    @NonNull
    @Override
    public NotificationsRecyclerAdapterAno.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notifications_ano, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsRecyclerAdapterAno.MyViewHolder holder, int position) {
        holder.button_ano.setText(arrayList_anos.get(position));
        String ano = arrayList_anos.get(position);
        holder.button_ano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationsActivityMes_Intent = new Intent(context, NotificationsActivityMes.class);
                notificationsActivityMes_Intent.putExtra("TAG-ANO", ano);
                context.startActivity(notificationsActivityMes_Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList_anos.size();
    }


}
