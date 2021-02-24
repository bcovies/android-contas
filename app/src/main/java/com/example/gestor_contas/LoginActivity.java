package com.example.gestor_contas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editText_email;
    private EditText editText_senha;
    private FirebaseAuth firebaseAuth;
    private Button button_login;
    private Button button_register;

    private void irParaMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void irParaLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            finish();
            irParaMainActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        editText_email = findViewById(R.id.activity_login_editTextTextEmailAddress);
        editText_senha = findViewById(R.id.activity_login_editTextTextPassword);

        button_register = findViewById(R.id.activity_login_button_registrar);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        button_login = findViewById(R.id.activity_login_button_entrar);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrarUsuario();
            }
        });
    }

    private void entrarUsuario() {
        String email = editText_email.getText().toString();
        String password = editText_senha.getText().toString();
        if (email.isEmpty() && password.isEmpty()) {
            finish();
            irParaLoginActivity();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        irParaMainActivity();
                        finish();
                    }
                }
            });
        }

    }

    private void registrarUsuario() {
        String email = editText_email.getText().toString();
        String password = editText_senha.getText().toString();
        if (email.isEmpty() && password.isEmpty()) {
            finish();
            irParaLoginActivity();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                irParaLoginActivity();
                                finish();
                            } else {
                                irParaLoginActivity();
                            }
                        }
                    });
        }
    }

}