package com.example.gestor_contas.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.UserAuth;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterAno;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterMes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsActivityMes extends AppCompatActivity {
    private Button button_mes;
    private UserAuth userAuth = new UserAuth();
    private ArrayList<String> arrayList_mes = new ArrayList<>();
    private DatabaseReference databaseReference_mes;
    private RecyclerView recyclerView_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_mes);

        String ano = (String) getIntent().getSerializableExtra("TAG-ANO");
        databaseReference_mes = userAuth.returnReference().child("/users/" + userAuth.getCurrentUserUID()+ "/notas/" + ano);
        databaseReference_mes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    arrayList_mes.add(dataSnapshot.getKey());
                }
                recyclerView_mes = findViewById(R.id.activity_notifications_mes_recyclerView);
                NotificationsRecyclerAdapterMes notificationsRecyclerAdapterMes = new NotificationsRecyclerAdapterMes(NotificationsActivityMes.this, arrayList_mes);
                recyclerView_mes.setAdapter(notificationsRecyclerAdapterMes);
                recyclerView_mes.setLayoutManager(new LinearLayoutManager(NotificationsActivityMes.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
    }
}