package com.example.gestor_contas.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.UserAuth;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterFinal;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterMes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFinalActivity extends AppCompatActivity {
    private DatabaseReference databaseReference_final;
    private UserAuth userAuth = new UserAuth();
    private ArrayList<String> arrayList_final = new ArrayList<>();
    private RecyclerView recyclerView_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_final);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF-TAG-ANO", MODE_PRIVATE);
        String ano = sharedPreferences.getString("PREF-STRING-ANO", "");

        String mes = (String) getIntent().getSerializableExtra("TAG-MES");

        databaseReference_final = userAuth.returnReference().child("/users/" + userAuth.getCurrentUserUID()+ "/notas/" + ano + "/" + mes );

        databaseReference_final.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    arrayList_final.add(dataSnapshot.getKey());
                }
                recyclerView_final = findViewById(R.id.activity_notifications_final_recyclerView);
                NotificationsRecyclerAdapterFinal notificationsRecyclerAdapterFinal = new NotificationsRecyclerAdapterFinal(NotificationsFinalActivity.this, arrayList_final);
                recyclerView_final.setAdapter(notificationsRecyclerAdapterFinal);
                recyclerView_final.setLayoutManager(new LinearLayoutManager(NotificationsFinalActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}