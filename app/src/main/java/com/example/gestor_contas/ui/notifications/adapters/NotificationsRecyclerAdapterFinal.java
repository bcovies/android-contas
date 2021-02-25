package com.example.gestor_contas.ui.notifications.adapters;

import android.content.Context;
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
    private ArrayList<String> arrayList_nome;
    private  ArrayList<Float> arrayList_valor;

    public NotificationsRecyclerAdapterFinal(Context context,  ArrayList<String> arrayList_nome, ArrayList<Float> arrayList_valor) {
        this.context = context;
        this.arrayList_nome = arrayList_nome;
        this.arrayList_valor = arrayList_valor;
    }
    

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_nome;
        private TextView textView_valor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_nome = itemView.findViewById(R.id.adapter_notifications_final_textView_nome);
            textView_valor = itemView.findViewById(R.id.adapter_notifications_final_textView_valor);
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
        holder.textView_nome.setText(arrayList_nome.get(position));
        String string_valor = String.valueOf(arrayList_valor.get(position));
        holder.textView_valor.setText(string_valor);
    }

    @Override
    public int getItemCount() {
        return arrayList_nome.size();
    }


}
