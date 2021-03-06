package br.iesb.grupo1.projetofinal.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.iesb.grupo1.projetofinal.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnlogin;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private EditText txtEmail;
    private EditText txtSenha;
    private TextView txtRegister;
    private TextView txtRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);

        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((txtEmail != null) && (txtSenha != null)) {
                    if(txtEmail.getText() != null) {
                        if (txtSenha.getText() != null) {
                            signIn(txtEmail.getText().toString(), txtSenha.getText().toString());
                        }else{
                            Toast.makeText(LoginActivity.this, "Campos senha é obrigatório", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Campos email é obrigatório", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(t);
            }
        });

        txtRetrieve = findViewById(R.id.txtRetrieve);
        txtRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RetrieveActivity.class));
            }
        });

    }

    public void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();
    }

    private void signIn(String email, String password) {
        Log.d("Tag asasasas",email);
        if(email=="" || password==""){
            Toast.makeText(LoginActivity.this,"Todos os campos devem ser preenchidos",Toast.LENGTH_SHORT).show();
        }else{
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent t = new Intent(LoginActivity.this, JobListActivity.class);
                            startActivity(t);
                        } else {
                            Toast.makeText(LoginActivity.this, "A tentativa de Login falhou, tente novamente",
                                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }

}
