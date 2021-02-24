package com.example.gestor_contas.ui.notifications.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;

import java.util.ArrayList;

public class NotificationsRecyclerAdapterAno extends RecyclerView.Adapter<NotificationsRecyclerAdapterAno.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList_anos;

    public NotificationsRecyclerAdapterAno(Context context, ArrayList<String> arrayList_anos) {
        this.context = context;
        this.arrayList_anos = arrayList_anos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private Button button_ano;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            button_ano = itemView.findViewById(R.id.adapter_notifications_ano_button_ano);
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
    }

    @Override
    public int getItemCount() {
        return arrayList_anos.size();
    }


}
