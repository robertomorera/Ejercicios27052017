package com.example.cice.miserviciodream;


import android.content.SharedPreferences;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyDreamService extends DreamService implements View.OnClickListener,Animation.AnimationListener {

    private Animation animation;

    public MyDreamService() {
    }

    @Override
    public void onClick(View v) {
        //Gestionar el click del usuario
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //setting del servicio.
        setInteractive(true);
        setFullscreen(true);

        setContentView(R.layout.dream);

        SharedPreferences sp=getSharedPreferences("prefs",MODE_PRIVATE);
        String categoria=sp.getString("categoria","animales");

        Log.d("MENSAJE","Categoria almacenada"+categoria);

        ImageView i1=(ImageView)findViewById(R.id.imagen1);
        ImageView i2=(ImageView)findViewById(R.id.imagen2);

        switch(categoria){
            case "animales":
                i1.setImageResource(R.drawable.animales1);
                i2.setImageResource(R.drawable.animales2);
                break;
            case "paisajes":
                i1.setImageResource(R.drawable.paisajes1);
                i2.setImageResource(R.drawable.paisajes2);
                break;
            case "futbol":
                i1.setImageResource(R.drawable.deporte1);
                i2.setImageResource(R.drawable.deporte2);
                break;
        }

    }

    @Override
    public void onDreamingStarted() {

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.layout_padre);
        //TODO cargar e iniciar animación
        this.animation= AnimationUtils.loadAnimation(this,R.anim.animacion_dream);
        animation.reset();
        linearLayout.startAnimation(animation);
        animation.setAnimationListener(this);
        super.onDreamingStarted();

    }

    public void toque(View v){

        Log.d("MENSAJE","Han tocado el salvapantallas");

        ImageView i1=(ImageView)findViewById(R.id.imagen1);
        ImageView i2=(ImageView)findViewById(R.id.imagen2);
        i1.setImageResource(R.mipmap.ic_launcher_round);
        i2.setImageResource(R.mipmap.ic_launcher);
        //Reiniciamos la aplicación.
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.layout_padre);
        linearLayout.startAnimation(animation);

    }


    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("MENSAJE","ANIMATION STARTED");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("MENSAJE","ANIMATION ENDED");
        ImageView i1=(ImageView)findViewById(R.id.imagen1);
        ImageView i2=(ImageView)findViewById(R.id.imagen2);
        i1.setImageResource(R.drawable.animales1);
        i2.setImageResource(R.drawable.animales2);
        //Reiniciamos la aplicación.
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.layout_padre);
        linearLayout.startAnimation(animation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("MENSAJE","ANIMATION REPEATED");
    }
}
