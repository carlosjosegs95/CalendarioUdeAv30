package com.example.carlosjose95.calendarioudeav20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment implements CalendarView.OnDateChangeListener{

    private CalendarView calendarView;

    public CalendarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

        return view;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int day, int month, int year) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        CharSequence [] items = new CharSequence[3];
        items[0] = "Agregar recordatorio";
        items[1] = "Ver eventos";
        items[2] = "Cancelar";

        month++;

        builder.setTitle("Seleccione una opción").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    Intent intent = new Intent(getContext(), RecordatorioActivity.class);
                    startActivity(intent);
                }else if(i==1){
                    Intent intent = new Intent(getContext(), EventosActivity.class);
                    startActivity(intent);
                }else if(i==2){
                    //Cancelar
                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
