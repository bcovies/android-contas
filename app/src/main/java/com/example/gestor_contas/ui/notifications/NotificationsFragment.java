package com.example.gestor_contas.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.UserAuth;
import com.example.gestor_contas.ui.notifications.adapters.NotificationsRecyclerAdapterAno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private ArrayList<String> arrayList_Anos = new ArrayList<>();
    private UserAuth userAuth = new UserAuth();
    private DatabaseReference databaseReference_Ano;
    private RecyclerView recyclerView_ano;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        databaseReference_Ano = userAuth.returnReference().child("/users/" + userAuth.getCurrentUserUID()+ "/notas/");
        databaseReference_Ano.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    arrayList_Anos.add(dataSnapshot.getKey());
                }
                recyclerView_ano = view.findViewById(R.id.fragment_notifications_recyclerView_ano);

                NotificationsRecyclerAdapterAno notificationsRecyclerAdapterAno = new NotificationsRecyclerAdapterAno(getActivity(), arrayList_Anos);
                recyclerView_ano.setAdapter(notificationsRecyclerAdapterAno);
                recyclerView_ano.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}