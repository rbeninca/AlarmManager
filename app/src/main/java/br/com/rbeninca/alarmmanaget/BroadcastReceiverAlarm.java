package br.com.rbeninca.alarmmanaget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;


/*Classe  que implementa o metódo onReceive e é do subtipo de BroadcastReceiver, esta classe é deve
ser repasada ao AlarmManager, possibilitando o agendamento de execução do trecho de código em onReceive.

 */
public class BroadcastReceiverAlarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmManagerTest","Alarme disparado "+ (new Date().toString()) );
    }

}
