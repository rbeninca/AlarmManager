package br.com.rbeninca.alarmmanaget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivityAlarmManager extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void programaAlarmeCincoSegundos(View view){
        AlarmManagerUtil alarmManagerController = new AlarmManagerUtil(getApplicationContext());
        alarmManagerController.programaAlarme(5000,false);
    }

    public void programaAlarmeRepetido1Min(View view){
        AlarmManagerUtil alarmManagerController = new AlarmManagerUtil(getApplicationContext());
        alarmManagerController.programaAlarme(5000,true);
    }

}
