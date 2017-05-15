package com.conformiz.milkconsumerapp.fragments;

/**
 * Created by Fahad.Munir on 20-Apr-17.
 */


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponseData;

import java.util.ArrayList;
import java.util.Arrays;

public class SingleChoiceListFragment extends DialogFragment {



    String[] values;

    public SingleChoiceListFragment(){

    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Preferred Time")
                .setItems(values, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Toast.makeText(getActivity(), "You clicked " + values[which], Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }


    public void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the alert dialog title
        builder.setTitle("Choose a flower.");

        // Initializing an array of flowers
        final String[] flowers = new String[]{
                "Daffodil",
                "Dahlia",
                "Day Lily",
                "Delphinium",
                "Desert Rose"
        };



        // Set a single choice items list for alert dialog
        builder.setSingleChoiceItems(
                flowers, // Items list
                -1, // Index of checked item (-1 = no selection)
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Get the alert dialog selected item's text
                        String selectedItem = Arrays.asList(flowers).get(i);

                        // Display the selected item's text on snack bar
//                        Snackbar.make(
//                                mCLayout,
//                                "Checked : " + selectedItem,
//                                Snackbar.LENGTH_INDEFINITE
//                        ).show();
                    }
                });

        // Set the a;ert dialog positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Just dismiss the alert dialog after selection
                // Or do something now
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }


    public void setListData(ArrayList<PreferredTimeListRootResponseData> data){

        values = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            values[i] = data.get(i).getPreferred_time_name();
        }

    }


}