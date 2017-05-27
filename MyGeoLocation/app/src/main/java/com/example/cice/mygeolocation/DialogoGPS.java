package com.example.cice.mygeolocation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.provider.Settings;

/**
 * Created by cice on 27/5/17.
 */

public class DialogoGPS extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Active el GPS(Localización fina) LA APP LO NECESITA").setTitle("Ajustes de localización")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewIntent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(viewIntent);
                                dialog.cancel();
                            }
                        }
                );
        return builder.create();

    }
}
