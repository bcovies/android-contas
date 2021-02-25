package com.example.gestor_contas.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.Nota;
import com.example.gestor_contas.model.UserAuth;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterFinal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFinalActivity extends AppCompatActivity {
    private DatabaseReference databaseReference_final;
    private UserAuth userAuth = new UserAuth();
    private ArrayList<String> arrayList_nome = new ArrayList<>();
    private ArrayList<Float> arrayList_valor = new ArrayList<Float>();
    private RecyclerView recyclerView_final;
    private float total = 0;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_final);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF-TAG-ANO", MODE_PRIVATE);
        String ano = sharedPreferences.getString("PREF-STRING-ANO", "");

        String mes = (String) getIntent().getSerializableExtra("TAG-MES");
        textView = findViewById(R.id.activity_notifications_final_textView);

        databaseReference_final = userAuth.returnReference().child("/users/" + userAuth.getCurrentUserUID()+ "/notas/" + ano + "/" + mes );
        databaseReference_final.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Nota nota = new Nota();
                    nota.setNome(dataSnapshot.getValue(Nota.class).getNome());
                    nota.setValor(dataSnapshot.getValue(Nota.class).getValor());

                    float valor = nota.getValor();
                    arrayList_nome.add(nota.getNome());
                    arrayList_valor.add(nota.getValor());
                    total = total + valor;
                }
                String totalD = String.valueOf(total);
                textView.setText("TOTAL: "+totalD);
                inicializarRecyclerView();

            }

            private void inicializarRecyclerView() {
                recyclerView_final = findViewById(R.id.activity_notifications_final_recyclerView);
                NotificationsRecyclerAdapterFinal notificationsRecyclerAdapterFinal = new NotificationsRecyclerAdapterFinal(NotificationsFinalActivity.this,arrayList_nome,arrayList_valor );
                recyclerView_final.setAdapter(notificationsRecyclerAdapterFinal);
                recyclerView_final.setLayoutManager(new LinearLayoutManager(NotificationsFinalActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}