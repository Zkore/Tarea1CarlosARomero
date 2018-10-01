package com.iteso.tarea1;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class activity_main extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    public static String s_escolaridad;
    EditText nombre, telefono;
    Spinner escolaridad;
    RadioGroup genero;
    AutoCompleteTextView libro;
    CheckBox deporte_chk;
    String genero_persona = "Femenino", deporte = "No practica deporte";
    Button limpiar;
    RadioButton Femenino, Masculino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Femenino = findViewById(R.id.activity_main_genero_f);
        Masculino = findViewById(R.id.activity_main_genero_m);
        nombre = findViewById(R.id.activity_main_name_editar_texto);
        telefono = findViewById(R.id.activity_main_telefono_editar_texto);
        escolaridad = findViewById(R.id.activity_main_escolaridad);
        genero = findViewById(R.id.activity_main_genero);
        libro = findViewById(R.id.activity_main_libro);
        deporte_chk = findViewById(R.id.activity_main_deporte);
        limpiar = findViewById(R.id.activity_main_limpiar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity_main.this, R.array.Escolaridades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        escolaridad.setAdapter(adapter);
        escolaridad.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterComplete = ArrayAdapter.createFromResource(activity_main.this, R.array.Libros, android.R.layout.simple_dropdown_item_1line);
        libro.setAdapter(adapterComplete);
        libro.setOnItemSelectedListener(this);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_main.this);
                builder.setMessage(R.string.activity_main_texto_dialogo).setPositiveButton(R.string.activity_main_texto_aceptar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                nombre.setText("");
                                telefono.setText("");
                                libro.setText("");
                                deporte_chk.setChecked(false);
                                Femenino.setChecked(true);
                                Masculino.setChecked(false);
                                escolaridad.setSelection(0);
                            }
                        })
                        .setNegativeButton(R.string.activity_main_texto_cancelar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        }).show();

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
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
                                + s_escolaridad
                                + "\nGénero: "
                                + genero_persona
                                + "\nLibro favorito: "
                                + libro.getText().toString() + "\n"
                                + deporte.toString()
                        , Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void ChkClicked(View view){
        boolean checked = deporte_chk.isChecked();
        switch (view.getId()){
            case (R.id.activity_main_deporte):
                if(checked)
                    deporte  = "Si practica deporte";
                else
                    deporte= "No practica deporte";
                break;
            default:
                deporte = "No practica deporte";
        }
    }
    public void RbtnClicked(View view){
        switch (view.getId()){
            case (R.id.activity_main_genero_m):
                genero_persona = "Masculino";
                break;
            case (R.id.activity_main_genero_f):
                genero_persona = "Femenino";
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s_escolaridad = adapterView.getItemAtPosition(i).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}