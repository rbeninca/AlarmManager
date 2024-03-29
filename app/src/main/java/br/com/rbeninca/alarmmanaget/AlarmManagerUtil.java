package br.com.rbeninca.alarmmanaget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class AlarmManagerUtil {
    private Context context;
    private AlarmManager alarmManager;
    private  PendingIntent alarmIntent;
    private Calendar calendar;

    public AlarmManagerUtil(Context context) {
        this.context = context;
    }

    public void programaAlarme(int milisegundos,boolean repetido){/*
        Metódo que programa uma alarme  utilizando a classe AlarmManager do sistema
         Configura uma intenção com a classe que deve ser chamada, esta classe deve implementar BroadCastReceiver.OnReceiver ou seja extenser BroadCast e sobreescreve o metódo mencionado
        Observe que esta classe BroadCastReceiverAlarm é que será execuda pois ela extende de BroadCastReceiver,
        para que o sistema reconheça o BroadCastReceiver é necessário adicionar no  Manifesto a sua declaração< Olhe o manifesto.xml>
     */
        Intent intent = new Intent(context, BroadcastReceiverAlarm.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        //Configurando um calendar java.util, para informar a data para o AlarmManaeger
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MILLISECOND, milisegundos);

        /* Registra o alarme definindo a propriedade
        Isso pode ser feito por diferentes metódos da classe AlarmManager entre eles os metodos set e setRepeating
        alarmManager.set(
            int type           - Podendo assumir valores definidos na documentação entr eles RTC,ELAPSED_REALTIME, RTC_WAKEUP
            carlendar trigguer - Parametro com a data e hora de execução que podem não ser precisos (ver documentação, mudanças api19)
            PedingItent intent - Parametro com a intenção, que configura a classe a ser chamada pelo BroadCastReceiver, por isso ela tem que ter o metódo receiver implementado.
        )
        alarmManager.setExact(
            int type           - Podendo assumir valores definidos na documentação entr eles RTC,ELAPSED_REALTIME, RTC_WAKEUP
            carlendar trigguer - Parametro com a data e hora de execução que podem não ser precisos (ver documentação, mudanças api19)
            PedingItent intent - Parametro com a intenção, que configura a classe a ser chamada pelo BroadCastReceiver, por isso ela tem que ter o metódo receiver implementado.
        )
        alarmManager.setRepeating (
         int type           - Podendo assumir valores definidos na documentação entr eles RTC,ELAPSED_REALTIME, RTC_WAKEUP
            carlendar trigguer - Parametro com a data e hora de execução que podem não ser precisos (ver documentação, mudanças api19)
            PedingItent intent - Parametro com a intenção, que configura a classe a ser chamada pelo BroadCastReceiver, por isso ela tem que ter o metódo receiver implementado.
        )
        Cabe ressaltar que o método Repeating e InexaticRepeating possuem sofreram mudanças no comportamento após o Android 5.1, alterando o valor de repeating para 60000miliseconds
        para valores inferiores a este valor. A justificativa para tal mudança é o excessivo consumo de recursos, de acordo com relatos esse tipo de ação faz com que alguns desenvolvedores
        busquem formas de driblar as politicas de controle de energia do sistema, mas essas ações podem causar medidas drásticas pela playStore e Google, por isso é altamente não recomendado.
        https://commonsware.com/blog/2015/03/23/alarmmanager-regression-android-5p1.html
        @see https://developer.android.com/reference/android/app/AlarmManager */
        //Recupera uma referencia para o serviço de alarme do android.
        this.alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (repetido) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60*1000, alarmIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            }
        Log.d("AlarmManagerTest", "Alarme Cadastrado " + (new Date().toString()));
    }



}
