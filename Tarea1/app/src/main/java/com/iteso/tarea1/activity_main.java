package com.iteso.tarea1;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class activity_main extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    public static String escolaridad;

    Button limpiar;
    Spinner s_escolaridad;
    EditText nombre;
    EditText telefono;
    String genero_persona = "Femenino";
    String deporte_string = "No practica deporte";
    RadioGroup genero;
    CheckBox deporte;
    AutoCompleteTextView libro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        limpiar = findViewById(R.id.activity_main_limpiar);
        s_escolaridad = findViewById(R.id.activity_main_escolaridad);
        nombre = findViewById(R.id.activity_main_name_editar_texto);
        telefono = findViewById(R.id.activity_main_phone_editar_texto);
        genero = findViewById(R.id.activity_main_genero);
        deporte = findViewById(R.id.activity_main_deporte);
        libro = findViewById(R.id.activity_main_libro);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity_main.this, R.array.Escolaridades,  android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_escolaridad.setAdapter(adapter);
        s_escolaridad.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterComplete = ArrayAdapter.createFromResource(activity_main.this, R.array.Libros  , android.R.layout.simple_dropdown_item_1line);
        libro.setAdapter(adapterComplete);
        libro.setOnItemSelectedListener(this);
        limpiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_main.this);
                builder.setMessage(R.string.activity_main_texto_dialogo).setPositiveButton(R.string.activity_main_texto_aceptar, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nombre.setText("");
                        telefono.setText("");
                        libro   .setText("");
                        deporte.setChecked(false);
                    }
                })
                        .setNegativeButton(R.string.activity_main_texto_cancelar,
                                new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){}}).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){ MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_main, menu);
    return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        escolaridad = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case(R.id.menu_guardar):

                Toast.makeText(activity_main.this,
                        "Nombre: "
                                + nombre.getText().toString()
                                + "\nTelefono: "
                                + telefono.getText().toString()
                                + "\nEscolaridad: "
                                + escolaridad
                                + "\nGénero: "
                                + genero_persona
                                + "\nLibro favorito: "
                                + libro.getText().toString()
                                + "\n"
                                + deporte_string,
                        Toast.LENGTH_SHORT).show();
                Log.d("save menu","contenido de name : " + nombre.toString());
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
                    public void RbtnClick(View view){
        switch
                (view.getId()){
            case (R.id.activity_main_genero_f):
                genero_persona = "Femenino";
            break;
            case (R.id.activity_main_genero_m):
                genero_persona = "Masculino";
            break;
        }
    }
    public void onCheckBoxClicked (View view){ boolean checked = deporte.isChecked();
    switch (view.getId()){
        case (R.id.activity_main_deporte):
            if(checked) deporte_string = "Si practica deporte";
            else deporte_string = "No practica deporte";
            break;
            default: deporte_string = "No practica deporte";
    }
    }


}