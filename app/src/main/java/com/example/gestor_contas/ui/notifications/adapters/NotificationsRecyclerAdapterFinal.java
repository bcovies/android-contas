package com.example.gestor_contas.ui.notifications.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.UserAuth;
import com.example.gestor_contas.ui.notifications.NotificationsFinalActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationsRecyclerAdapterFinal extends RecyclerView.Adapter<NotificationsRecyclerAdapterFinal.MyViewHolder> {
    private Context context;
    private ArrayList<String> arrayList_nome;
    private ArrayList<Float> arrayList_valor;

    public NotificationsRecyclerAdapterFinal(Context context, ArrayList<String> arrayList_nome, ArrayList<Float> arrayList_valor) {
        this.context = context;
        this.arrayList_nome = arrayList_nome;
        this.arrayList_valor = arrayList_valor;
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

    class MyViewHolder extends RecyclerView.ViewHolder  {

        private TextView textView_nome;
        private TextView textView_valor;
        private Button button;
        private UserAuth userAuth = new UserAuth();
        SharedPreferences sharedPreferencesAno = context.getSharedPreferences("PREF-TAG-ANO", context.MODE_PRIVATE);
        String ano = sharedPreferencesAno.getString("PREF-STRING-ANO", "");
        SharedPreferences sharedPreferencesMes = context.getSharedPreferences("PREF-TAG-MES", context.MODE_PRIVATE);
        String mes = sharedPreferencesMes.getString("PREF-STRING-MES", "");
        private DatabaseReference databaseReference_mercado = userAuth.returnReference().child("/users" + "/" + userAuth.getCurrentUserUID() + "/notas/"+ ano + mes);


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_nome = itemView.findViewById(R.id.adapter_notifications_final_textView_nome);
            textView_valor = itemView.findViewById(R.id.adapter_notifications_final_textView_valor);
            button = itemView.findViewById(R.id.adapter_notifications_final_button);
        }
    }
}



