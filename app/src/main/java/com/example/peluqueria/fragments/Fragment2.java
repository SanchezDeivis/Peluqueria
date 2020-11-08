package com.example.peluqueria.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.peluqueria.Domicilios;
import com.example.peluqueria.MapActivity;
import com.example.peluqueria.R;
import com.example.peluqueria.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Fragment2 extends Fragment implements View.OnClickListener {


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
    private ImageButton buttonUbicacion;
    private EditText Lat, Long;

    //firebase
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) view.findViewById(R.id.edt_fechaDomicilio);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        btnfecha = (ImageButton) view.findViewById(R.id.btn_datePickerDomicilio);
        //Evento setOnClickListener - clic
        btnfecha.setOnClickListener(this);

        //Widget EditText donde se mostrara la hora obtenida
        etHora = (EditText) view.findViewById(R.id.edt_horaDomicilio);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        btnHora = (ImageButton) view.findViewById(R.id.btn_timePickerDomicilio);
        //Evento setOnClickListener - clic
        btnHora.setOnClickListener(this);

        btnChecket = view.findViewById(R.id.btn_GuardarDomicilio);
        //Evento setOnClickListener - clic
        btnChecket.setOnClickListener(this);
        buttonUbicacion = view.findViewById(R.id.btnUbicacion);

        Lat = view.findViewById(R.id.Latitud);
        Long = view.findViewById(R.id.Longitud);


        // Obtienes el Bundle del Intent
        Bundle bundle = getActivity().getIntent().getExtras();
        // Obtienes el text
       // Lat.setText(getActivity().getIntent().getExtras().getString("lat"));
       // Long.setText(bundle.getString("lon"));


        //buscar las coordenadas
        buttonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });


        //agendamos nueva cita
        btnChecket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        return view;

    }

    //Selector de eventos onClick
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

    private void validarCampos() {
        etFecha.setError(null);
        etHora.setError(null);

        String fech = etFecha.getText().toString().trim();
        String hora = etHora.getText().toString().trim();
        String Slat = Lat.getText().toString().trim();
        String Slog = Long.getText().toString().trim();

        if (TextUtils.isEmpty(fech) || TextUtils.isEmpty(hora) || TextUtils.isEmpty(Slat) || TextUtils.isEmpty(Slog)) {
            if (TextUtils.isEmpty(fech)) {
                etFecha.setError("Campo Fecha vacio");
                etFecha.findFocus();
                return;

            } else if (TextUtils.isEmpty(hora)) {
                etHora.setError("Campo Hora vacio");
                etHora.findFocus();
                return;
            } else if (TextUtils.isEmpty(Slat)) {
                Lat.setError("Campo Latitud vacio");
                Lat.findFocus();
                return;
            } else if (TextUtils.isEmpty(Slog)) {
                Long.setError("Campo Longitud vacio");
                Long.findFocus();
                return;
            }

        } else {
            agregarServicioDomicilio();
        }

    }

    //Agregar datos a firebase
    private void agregarServicioDomicilio() {
        String user;
        String id = mAuth.getCurrentUser().getUid();
        String idpush = mdatabase.push().getKey();
        if (LoginActivity.ListUsuarios.getNombre() != null) {
            user = LoginActivity.ListUsuarios.getNombre();
        }else {
             user = "NN";
        }

        Domicilios objeDomicilios = new Domicilios(etFecha.getText().toString(), etHora.getText().toString(), "corte de cabello", Lat.toString(), Long.toString(), user);

        mdatabase.child("domicilios").child(id).child("domicilio" + idpush).setValue(objeDomicilios).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Bien¡¡ Domicilio Pedido", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "No se Pudo Agendar tu cita", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
