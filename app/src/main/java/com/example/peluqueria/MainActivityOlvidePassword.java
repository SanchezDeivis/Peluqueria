package com.example.peluqueria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peluqueria.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityOlvidePassword extends AppCompatActivity {
private EditText editTextEmail;
private Button buttonResetPassword;
private String email;
private FirebaseAuth mAuth;
private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_olvide_password);

        mAuth=FirebaseAuth.getInstance();
        dialog= new ProgressDialog(this);
        editTextEmail=findViewById(R.id.resetemail);
        buttonResetPassword=findViewById(R.id.btnResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email = editTextEmail.getText().toString();
                if (!email.isEmpty()){
                    dialog.setMessage("Espere un momento...");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    resetPassword();
                }else {
                    Toast.makeText(MainActivityOlvidePassword.this, "Agregue un email Valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void resetPassword(){

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivityOlvidePassword.this, "Revisa tu correo  para restablecer tu password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivityOlvidePassword.this, LoginActivity.class));
                }else {
                    Toast.makeText(MainActivityOlvidePassword.this, "No se pudo enviar el correo para restablecer tu password", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
}
