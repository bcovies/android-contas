package com.example.gestor_contas.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gestor_contas.model.Nota;
import com.example.gestor_contas.R;
import com.example.gestor_contas.model.UserAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private UserAuth userAuth = new UserAuth();
    private Button button_adicionar;
    private EditText editText_nome;
    private EditText editText_valor;
    private Calendar calendar = Calendar.getInstance();
    private int ano = calendar.get(Calendar.YEAR);
    private int mes = calendar.get(Calendar.MONTH) + 1 ;
    private DatabaseReference databaseReference_MesAno;

    private void criarNovaNota(String name, float value) {
        Nota note = new Nota(name,value);
        String key = databaseReference_MesAno.push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, note);
        databaseReference_MesAno.updateChildren(childUpdates);
        Toast.makeText(getContext(), "Adicionado!", Toast.LENGTH_SHORT).show();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        editText_nome = view.findViewById(R.id.fragment_dashboard_editTextTextPersonName);
        editText_valor = view.findViewById(R.id.fragment_dashboard_editTextNumberDecimal);
        databaseReference_MesAno = userAuth.returnReference().child("/users/" + userAuth.getCurrentUserUID() + "/notas/" + ano + "/" + mes + "/");
        button_adicionar = view.findViewById(R.id.fragment_dashboard_button_adicionar);
        button_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editText_nome.getText().toString();
                float valor = Float.parseFloat(editText_valor.getText().toString());
                criarNovaNota(nome,valor);
            }
        });
        return view;
    }
}