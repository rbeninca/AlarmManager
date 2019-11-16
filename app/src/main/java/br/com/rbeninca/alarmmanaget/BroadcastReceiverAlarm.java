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
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
           /*Ao reinicar  todas alarmes são perdidos, mas podemos recria-los com a seguintes alterações
           1)Manifesto:
                adicionando a permissão para ouvirmos intent de boot completo e adicionamos a
               "android.intent.action.BOOT_COMPLETED" para o nosso BroadcastReceiver.

           2)Adicionando uma verificação na nossa classe Receiver,AQUI, para que quando a inteção que disparar
           seja recriada os Alarmes.
            */
            Log.d("AlarmManagerTest", "StartUpBootReceiver BOOT_COMPLETED");
            recriaAlarme(context);
        }
        Log.d("AlarmManagerTest","Alarme disparado "+ (new Date().toString()) );
    }



    public  void recriaAlarme(Context context){
        AlarmManagerUtil alarmManagerUtil = new AlarmManagerUtil(context);
        alarmManagerUtil.programaAlarme(5000,true);
    }

}
