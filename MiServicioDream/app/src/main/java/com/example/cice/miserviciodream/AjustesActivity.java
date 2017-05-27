package com.example.cice.miserviciodream;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
    }

    public void botonSel(View v){

        Log.d("MENSAJE","BOTON SELECCIONADO");
        int id_boton=v.getId();

        SharedPreferences sp=getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        switch (id_boton){
            case R.id.animales:
                Log.d("MENSAJE","Botón de categoria animales seleccionado");
                editor.putString("categoria","animales");
                break;
            case R.id.paisajes:
                Log.d("MENSAJE","Botón de categoria paisajes seleccionado");
                editor.putString("categoria","paisajes");
                break;
            case R.id.futbol:
                Log.d("MENSAJE","Botón de categoria futbol seleccionado");
                editor.putString("categoria","futbol");
                break;
        }

        editor.commit();
    }
}
