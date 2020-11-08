package com.example.peluqueria.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.peluqueria.Adapters.GridAdapterCitas;
import com.example.peluqueria.Cita;
import com.example.peluqueria.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Fragment1 extends Fragment implements View.OnClickListener {

    private GridAdapterCitas adaptador;
    private GridView gridView;
    private List<Cita> gridViewCitaList = new ArrayList<>();

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText etFecha;
    ImageButton btnfecha;
    EditText etHora;
    ImageButton btnHora;
    ImageButton btnChecket;
    GridView gridViewListasCitas;

    //firebase
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    //dialog
    private ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(getContext());

        gridView = view.findViewById(R.id.gridviewCitas);
        getCitaInfo();

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) view.findViewById(R.id.edt_fecha);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        btnfecha = (ImageButton) view.findViewById(R.id.btn_datePicker);
        //Evento setOnClickListener - clic
        btnfecha.setOnClickListener(this);

        //Widget EditText donde se mostrara la hora obtenida
        etHora = (EditText) view.findViewById(R.id.edt_hora);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        btnHora = (ImageButton) view.findViewById(R.id.btn_timePicker);
        //Evento setOnClickListener - clic
        btnHora.setOnClickListener(this);

        btnChecket = view.findViewById(R.id.btn_cheksave);
        //Evento setOnClickListener - clic
        btnChecket.setOnClickListener(this);

        //agendamos nueva cita
        btnChecket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(vista.getContext());

                View mView = getLayoutInflater().inflate(R.layout.dialog_cancelarcita, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

/*TextView titulotextViewcita = mView.findViewById(R.id.tituloCancelarcita);
                TextView textoTexViewcita = mView.findViewById(R.id.textocancelarcita);
                mBuilder.setTitle("Cita Agendada" );

                mBuilder.setMessage("¿Desea canselar la cita?");
                titulotextViewcita.setText("Cita Agendada");
                textoTexViewcita.setText("¿Desea canselar la cita");*/
                mBuilder.setTitle("Cita Agendada");
                mBuilder.setMessage("¿Desea canselar la cita?");

                mBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                /*mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();*/
            }

        });

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_datePicker:
                obtenerFecha();
                break;
            case R.id.btn_timePicker:
                obtenerHora();
                break;
            /*case R.id.btn_cheksave:
                agregarCita();
                break;*/
        }
    }


    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void obtenerHora() {
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    //Agregar datos a firebase
    private void agregarCita() {
        String id = mAuth.getCurrentUser().getUid();
        String idpush = mdatabase.push().getKey();
        Cita objetocita = new Cita(etFecha.getText().toString(), etHora.getText().toString(), "corte de cabello");

        mdatabase.child("citas").child(id).child("angendada" + idpush).setValue(objetocita).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    getCitaInfo();


                } else {
                    Toast.makeText(getActivity(), "No se Pudo Agendar tu cita", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //traer informacion de la base firebase
    private void getCitaInfo() {
        dialog.setMessage("Espere un momento...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        String id = mAuth.getCurrentUser().getUid();
        try {
            System.out.println("idminusculo "+id);

            mdatabase.child("citas").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        gridViewCitaList.clear();
                        for (DataSnapshot objDataSnapshot : dataSnapshot.getChildren()) {
                            Cita verCita = objDataSnapshot.getValue(Cita.class);
                        /*
                        String fch = dataSnapshot.child("fecha").getValue().toString();
                        String hor = dataSnapshot.child("hora").getValue().toString();
                        String serv = dataSnapshot.child("tipoServicio").getValue().toString();*/

                            gridViewCitaList.add(new Cita(verCita.getFecha(), verCita.getHora(), verCita.getTipoServicio()));
                            //  gridViewCitaList.add(new Cita(fch, hor, serv));
                            adaptador = new GridAdapterCitas(getContext(), (ArrayList<Cita>) gridViewCitaList);
                            gridView.setAdapter(adaptador);

                        }


                    } else {

                        Toast.makeText(getActivity(), "No tiene citas Pendientes", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), "esto esta mal", Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();

    }

    //eliminar citas
    public void EliminarCitas() {
        String id = mAuth.getCurrentUser().getUid();
        mdatabase.child("profesor").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Cancelada", Toast.LENGTH_SHORT).show();
                    gridViewCitaList.clear();
                    getCitaInfo();

                }
            }
        });

    }

    private void validarCampos() {
        etFecha.setError(null);
        etHora.setError(null);

        String fech = etFecha.getText().toString().trim();
        String hora = etHora.getText().toString().trim();

        if (TextUtils.isEmpty(fech) || TextUtils.isEmpty(hora)) {
            if (TextUtils.isEmpty(fech)) {
                etFecha.setError("Campo Fecha vacio");
                etFecha.findFocus();
                return;

            } else if (TextUtils.isEmpty(hora)) {
                etHora.setError("Campo Hora vacio");
                etHora.findFocus();
                return;

            }

        } else {
            agregarCita();
        }

    }


}
