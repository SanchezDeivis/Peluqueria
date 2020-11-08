package com.example.peluqueria.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peluqueria.Admin;
import com.example.peluqueria.MainActivity;
import com.example.peluqueria.MainActivityOlvidePassword;
import com.example.peluqueria.MainActivityRegister;
import com.example.peluqueria.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button register;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private TextView textViewOlvidepass;
    public static Usuarios ListUsuarios ;


    public static final String Adminpassword = "admin12345678";
    //dialog
    private ProgressDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(LoginActivity.this);
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        register = findViewById(R.id.buttonRegister);

        textViewOlvidepass = findViewById(R.id.btnOlvidarpasswor);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setError(null);
                passwordEditText.setError(null);

                String user = usernameEditText.getText().toString().trim();
                String pass = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    if (TextUtils.isEmpty(user)) {
                        usernameEditText.setError("Campo Fecha vacio");
                        usernameEditText.findFocus();
                        return;

                    } else if (TextUtils.isEmpty(pass)) {
                        passwordEditText.setError("Campo Hora vacio");
                        passwordEditText.findFocus();
                        return;
                    }

                } else {
                    loginViewModel(user, pass);
                }

            }
        });

        //registrar nuevo usuario
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivityRegister.class);
                startActivity(intent);
            }
        });

        //Accion olvidar contraseña
        textViewOlvidepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivityOlvidePassword.class));
            }
        });
    }

    private void loginViewModel(String usuario, String password) {

        dialog.setMessage("Espere un momento...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        mAuth.signInWithEmailAndPassword(usuario, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String iduser = task.getResult().getUser().getUid();
                    System.out.println("dygygy" + iduser);
                    // verificarUser_or_Admin(iduser);
                    getUserInfo();

                    dialog.dismiss();
                    finish();

                } else {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Verifique los datos o su Conexion ...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        // Bundle bundle = new Bundle();
        //   bundle.putString("username", usernameEditText.getText().toString() ); intent.putExtras(bundle);


    }

    //verifica si ya inicio secion para no volver a login
    protected void onStart() {

        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            getUserInfo();
            Toast.makeText(this, "No cerraste cesion la ultima ve...", Toast.LENGTH_LONG).show();
        }
    }

    //Recuperar la informacion del usuario logueado
    public void getUserInfo() {

        String id = mAuth.getCurrentUser().getUid();
        mDatabaseReference.child("usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
                   ListUsuarios =  new Usuarios(usuarios.getNombre(),usuarios.getCelular(),usuarios.getEmail(),usuarios.getPassword(),usuarios.getImagen());
                    String userloinpassword = dataSnapshot.child("password").getValue().toString();
                    verificarUser_or_Admin(userloinpassword);


                } else {
                    System.out.println("no se consulto nada del idmayusculo " + id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //System.out.println("ID "+usuarios.getPassword()+"  idmayusculo "+id);


    }

    //si login fue exitoso entonces verificamos si el usuario es admin para entrar como admin
    // de lo contrario entra como usuario normal
    private void verificarUser_or_Admin(String m) {

        if (m != null && m.equals("admin12345678")) {
            Intent intent = new Intent(LoginActivity.this, Admin.class);
            startActivity(intent);
            setResult(Activity.RESULT_OK);

        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            setResult(Activity.RESULT_OK);
        }
    }


}
