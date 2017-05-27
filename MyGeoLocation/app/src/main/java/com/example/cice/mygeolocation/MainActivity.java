package com.example.cice.mygeolocation;

import android.Manifest;
import android.app.FragmentManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LocationManager location_Manager;
    private String provider;
    private MyLocationListener myLocationListener;


    public void mostrarLocalizacion(Location location){
        //TODO MOSTRAR LOCALIZACION
        double latitud=0;
        double longitud=0;
        double altura=0;

        TextView texto_hora=(TextView)findViewById(R.id.texthora);
        TextView texto_pos=(TextView)findViewById(R.id.textposicion);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("E dd/MM/yyyy 'a las ' hh:mm:ss");

        texto_hora.setText(dateFormat.format(calendar.getTime()));

        if(null!=location){
            latitud=location.getLatitude();
            longitud=location.getLongitude();
            altura=location.getAltitude();
            texto_pos.setText("lat "+latitud+"lng "+longitud+ "alt "+altura);
        }
    }

    private void solicitarAcitivacionGPS(){

        FragmentManager fm=getFragmentManager();
        DialogoGPS dialogo=new DialogoGPS();
        dialogo.show(fm,"AVISO");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /** AQUI SUPONEMOS QUE EL USUARIO ACEPTA**/
        Log.d("MENSAJE","PERMISOS CONCEDIDOS");

        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);

        if(location_Manager.isProviderEnabled(provider)){
            Log.d("MENSAJE","TENGO EL PROVEEDOR GPS ACTIVADO");
            //Si el GPS está activado obtenemos la última localización conocida por el proovedor.
            Location location=(Location)location_Manager.getLastKnownLocation(provider);
            mostrarLocalizacion(location);
        }else{
            Log.d("MENSAJE","TENGO EL PROVEEDOR GPS DESACTIVADO");
            solicitarAcitivacionGPS();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (location_Manager.isProviderEnabled(provider)) {
            Log.d("MENSAJE", "TENGO EL PROVEEDOR GPS ACTIVADO");
            //Si el GPS está activado obtenemos la última localización conocida por el proovedor.
            Location location = (Location) location_Manager.getLastKnownLocation(provider);
            location_Manager.requestLocationUpdates(provider,5000,0,myLocationListener);
            mostrarLocalizacion(location);
        } else {
            Log.d("MENSAJE","El usuario tenía desactivado el GPS y no ha querido activarlo.");
            finish();
        }

*/
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        location_Manager.requestLocationUpdates(provider,5000,0,myLocationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MENSAJE","La app deja de estar visible y desactivo el seguimiento GPS");
        //Desasociar el listener de cambios de posición.
        location_Manager.removeUpdates(myLocationListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location_Manager=(LocationManager)getSystemService(LOCATION_SERVICE);
        provider=LocationManager.GPS_PROVIDER;
        myLocationListener=new MyLocationListener(this);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
    }
}
