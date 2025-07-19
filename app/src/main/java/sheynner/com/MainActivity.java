package sheynner.com;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nombre, edad, editTextObservaciones;
    RadioGroup radioGroupGenero;
    Spinner carrera, semestre;
    CheckBox matematicas, programacion, ingles, estadistica, checkBoxBeca;
    Button buttonRegistrar, buttonLimpiar;
    ListView listViewEstudiantes;

    ArrayAdapter<String> listaAdapter;
    ArrayList<String> listaEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        carrera = findViewById(R.id.Carrera);
        semestre = findViewById(R.id.Semestre);
        matematicas = findViewById(R.id.Matematicas);
        programacion = findViewById(R.id.Programación);
        ingles = findViewById(R.id.Inglés);
        estadistica = findViewById(R.id.Estadística);
        checkBoxBeca = findViewById(R.id.checkBoxBeca);
        editTextObservaciones = findViewById(R.id.editTextObservaciones);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);
        listViewEstudiantes = findViewById(R.id.listViewEstudiantes);

        listaEstudiantes = new ArrayList<>();
        listaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEstudiantes);
        listViewEstudiantes.setAdapter(listaAdapter);

        String[] carreras = {
                "Seleccione carrera",
                "Ingeniería de Sistemas",
                "Medicina",
                "Derecho",
                "Administración",
                "Psicología"
        };
        ArrayAdapter<String> adapterCarrera = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreras);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carrera.setAdapter(adapterCarrera);

        String[] semestres = {
                "Seleccione semestre",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        };
        ArrayAdapter<String> adapterSemestre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semestres);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semestre.setAdapter(adapterSemestre);

        buttonRegistrar.setOnClickListener(v -> {
            String nombreTexto = nombre.getText().toString().trim();
            if (nombreTexto.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            String edadTexto = edad.getText().toString().trim();
            int edadValor;
            try {
                edadValor = Integer.parseInt(edadTexto);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Edad inválida", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edadValor < 16 || edadValor > 50) {
                Toast.makeText(this, "La edad debe estar entre 16 y 50", Toast.LENGTH_SHORT).show();
                return;
            }

            int generoId = radioGroupGenero.getCheckedRadioButtonId();
            if (generoId == -1) {
                Toast.makeText(this, "Debes seleccionar un género", Toast.LENGTH_SHORT).show();
                return;
            }
            String genero = ((RadioButton) findViewById(generoId)).getText().toString();

            if (carrera.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Debes seleccionar una carrera", Toast.LENGTH_SHORT).show();
                return;
            }
            String carreraTexto = carrera.getSelectedItem().toString();

            if (semestre.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Debes seleccionar un semestre", Toast.LENGTH_SHORT).show();
                return;
            }
            String semestreTexto = semestre.getSelectedItem().toString();

            StringBuilder materias = new StringBuilder();
            if (matematicas.isChecked()) materias.append("Matemáticas ");
            if (programacion.isChecked()) materias.append("Programación ");
            if (ingles.isChecked()) materias.append("Inglés ");
            if (estadistica.isChecked()) materias.append("Estadística ");
            String beca = checkBoxBeca.isChecked() ? "Sí" : "No";
            String observaciones = editTextObservaciones.getText().toString().trim();

            String datos = "Nombre: " + nombreTexto +
                    "\nEdad: " + edadValor +
                    "\nGénero: " + genero +
                    "\nCarrera: " + carreraTexto +
                    "\nSemestre: " + semestreTexto +
                    "\nMaterias: " + materias +
                    "\nBeca: " + beca +
                    "\nObs: " + observaciones;

            listaEstudiantes.add(datos);
            listaAdapter.notifyDataSetChanged();
            limpiarCampos();
        });

        buttonLimpiar.setOnClickListener(v -> limpiarCampos());
    }

    private void limpiarCampos() {
        nombre.setText("");
        edad.setText("");
        radioGroupGenero.clearCheck();
        carrera.setSelection(0);
        semestre.setSelection(0);
        matematicas.setChecked(false);
        programacion.setChecked(false);
        ingles.setChecked(false);
        estadistica.setChecked(false);
        checkBoxBeca.setChecked(false);
        editTextObservaciones.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            new AlertDialog.Builder(this)
                    .setTitle("Acerca del desarrollador")
                    .setMessage(
                            "Sheynner Enrique Zavala Querevalú\n" +
                                    "Ingeniero de Software\n" +
                                    "SENATI - Talara\n" +
                                    "zavalasheynner@gmail.com"
                    )
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
