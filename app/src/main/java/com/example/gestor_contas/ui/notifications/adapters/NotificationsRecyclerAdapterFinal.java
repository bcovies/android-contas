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
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_contas.R;
import com.example.gestor_contas.model.Nota;
import com.example.gestor_contas.model.UserAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(holder);
                popupMenu.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList_nome.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

        private TextView textView_nome;
        private TextView textView_valor;
        private Button button;
        private UserAuth userAuth = new UserAuth();
        SharedPreferences sharedPreferencesAno = context.getSharedPreferences("PREF-TAG-ANO", context.MODE_PRIVATE);
        String ano = sharedPreferencesAno.getString("PREF-STRING-ANO", "");
        SharedPreferences sharedPreferencesMes = context.getSharedPreferences("PREF-TAG-MES", context.MODE_PRIVATE);
        String mes = sharedPreferencesMes.getString("PREF-STRING-MES", "");
        private DatabaseReference databaseReference_notas = userAuth.returnReference().child("/users" + "/" + userAuth.getCurrentUserUID() + "/notas/" + ano + "/" + mes);


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_nome = itemView.findViewById(R.id.adapter_notifications_final_textView_nome);
            textView_valor = itemView.findViewById(R.id.adapter_notifications_final_textView_valor);
            button = itemView.findViewById(R.id.adapter_notifications_final_button);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            AlertDialog.Builder alertDialog;
            LayoutInflater layoutInflater;
            alertDialog = new AlertDialog.Builder(context);
            layoutInflater = LayoutInflater.from(context);
            switch (item.getItemId()) {
                case R.id.action_popup_edit:
                    databaseReference_notas.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot pai : snapshot.getChildren()) {

                                if (pai.getValue(Nota.class).getNome().contains(textView_nome.getText().toString())) {

                                    View view;
                                    view = layoutInflater.inflate(R.layout.dialog_edit, null);

                                    System.out.println(pai.getValue().toString());
                                    pai.getValue(Nota.class).getNome();
                                    pai.getValue(Nota.class).getValor();

                                    EditText editText_rowRecyclerView_nome = view.findViewById(R.id.dialog_edit_editText_nome);
                                    EditText editText_rowRecyclerView_valor = view.findViewById(R.id.dialog_edit_editText_valor);

                                    alertDialog.setTitle("Editando o item: " + textView_nome.getText().toString());
                                    alertDialog.setView(view);

                                    editText_rowRecyclerView_nome.setText(textView_nome.getText().toString());
                                    editText_rowRecyclerView_valor.setText(textView_valor.getText().toString());


                                    alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            String string_rowRecyclerView_nome = editText_rowRecyclerView_nome.getText().toString();
                                            String string_rowRecyclerView_valor = editText_rowRecyclerView_valor.getText().toString();
                                            float string_rowRecyclerView_valor_float = Float.parseFloat(string_rowRecyclerView_valor);
                                            Nota nota = new Nota();
                                            nota.setNome(string_rowRecyclerView_nome);
                                            nota.setValor(string_rowRecyclerView_valor_float);
                                            pai.getRef().setValue(nota);
                                        }
                                    });

                                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.cancel();
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    return true;


                case R.id.action_popup_delete:

                    databaseReference_notas.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot pai : snapshot.getChildren()) {

                                if (pai.getValue(Nota.class).getNome().contains(textView_nome.getText().toString())) {

                                    View view;
                                    view = layoutInflater.inflate(R.layout.dialog_remove, null);

                                    System.out.println(pai.getValue().toString());
                                    pai.getValue(Nota.class).getNome();
                                    pai.getValue(Nota.class).getValor();



                                    alertDialog.setTitle("Removendo o item: " + textView_nome.getText().toString());
                                    alertDialog.setView(view);


                                    alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            pai.getRef().removeValue();
                                        }
                                    });

                                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.cancel();
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    return true;
                default:
                    return false;
            }
        }
    }

}




