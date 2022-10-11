package com.example.pmdm_04_creacionvistasporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.pmdm_04_creacionvistasporcodigo.modelos.AlumnoModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;


import com.example.pmdm_04_creacionvistasporcodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addAlumnoLauncher;

    //MOSTRAR LA INFORMACION QUE RECIBIMOS
    //Necesitamos mínimo 3 cosas
    //1.Un elemento para mostrar la información
    //2.El conjunto de datos(ArrayList<AlumnoModel>)
    //3.Un contenedor donde meter cada uno de los elementos (LinearLayout(vertical)-> ScrollView )
    //4.La lógica para mostrar los elementos.
    private ArrayList<AlumnoModel> alumnoModelArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        alumnoModelArrayList = new ArrayList<>();
        inicializaLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlumnoLauncher.launch(new Intent(MainActivity.this,AddAlumnoActivity.class));
            }
        });
    }

    private void inicializaLauncher() {
        addAlumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null && result.getData().getExtras() != null){
                                AlumnoModel alumnoModel =(AlumnoModel) result.getData().getExtras().getSerializable("ALUMNO");
                                if(alumnoModel != null){
                                    alumnoModelArrayList.add(alumnoModel);
                                    mostrarAlumnos();
                                }
                            }
                        }
                    }
                }
        );
    }

    private void mostrarAlumnos(){
        //Con esto quitaremos todas las vistas que se hayan añadido, limpiandolo para que no repitan
        binding.contentMain.contenedorMain.removeAllViews();

        for (AlumnoModel alumno: alumnoModelArrayList) {
            //tenemos que crear un layoutinflater para llamar al xml que hemos creado
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View alumnoView = layoutInflater.inflate(R.layout.alumno_model_view, null);
            //en este caso no podremos usar el binding porque no va asociado a una actividad, habrá que hacerlo a mano
            TextView txtNombre = alumnoView.findViewById(R.id.lblNombreAlumnoView);
            TextView txtApellidos = alumnoView.findViewById(R.id.lblApellidosAlumnoView);
            TextView txtCiclo = alumnoView.findViewById(R.id.lblCiclosAlumnoView);
            TextView txtGrupo = alumnoView.findViewById(R.id.lblGrupoAlumnoView);

            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getAppelidos());
            txtCiclo.setText(alumno.getCiclo());
            txtGrupo.setText(String.valueOf(alumno.getGrupo()));

            binding.contentMain.contenedorMain.addView(alumnoView);
        }

    }


}