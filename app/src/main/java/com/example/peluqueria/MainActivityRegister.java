package com.example.peluqueria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.peluqueria.ui.login.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityRegister extends AppCompatActivity {
    private EditText nombreuser, telefonouser, emailuser, passworduser;
    private CircleImageView imagenuser;
    private Button btnregistraruser;

    private String nombre = "", telefono = "", email = "", password = "";
    private int imagen = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    private StorageReference mStorage;
    private static final int GALLERY_INTEN = 1;
    private Uri Uriimagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();

        nombreuser = findViewById(R.id.nombreuser);
        telefonouser = findViewById(R.id.telefonoUsuario);
        emailuser = findViewById(R.id.emailUsuario);
        passworduser = findViewById(R.id.passwordUsuario);
        imagenuser = findViewById(R.id.imageUsuario);
        btnregistraruser = findViewById(R.id.btnRegistrarusuario);


        imagenuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTEN);
            }
        });


        btnregistraruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = nombreuser.getText().toString();
                telefono = telefonouser.getText().toString();
                email = emailuser.getText().toString();
                password = passworduser.getText().toString();

                if (!nombre.isEmpty() && !telefono.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    if (password.length() >= 6) {
                        RegistrarUsuario();

                    } else {
                        Toast.makeText(MainActivityRegister.this, "Agregue mas caracteres al passwor", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivityRegister.this, "Rellene todos los Campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTEN && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            // StorageReference filePath = mStorage.child("foto").child(uri.getLastPathSegment());

            String selectedPath = uri.getPath();

            if (selectedPath != null) {
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                // Ponemos nuestro bitmap en un ImageView que tengamos en la vista

                imagenuser.setImageBitmap(bmp);

            }


           /* filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });*/
        }
    }

    //Registrar usuario nuevo
    private void RegistrarUsuario() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("telefono", telefono);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("imagen", imagen);

                    Usuarios usuariosnuevos = new Usuarios(nombre, telefono, email, password, imagen);

                    String id = mAuth.getCurrentUser().getUid();
                    mdatabase.child("usuario").child(id).setValue(usuariosnuevos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {

                                if (usuariosnuevos.getPassword() == "admin12345678") {
                                    startActivity(new Intent(MainActivityRegister.this, MainActivityAdmin.class));
                                } else {
                                    startActivity(new Intent(MainActivityRegister.this, MainActivity.class));

                                }
                                finish();
                            } else {
                                Toast.makeText(MainActivityRegister.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    Toast.makeText(MainActivityRegister.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
