### Descrição do Alarm Manager
AlarmManager é uma classe do android que permite que a aplicação realize o agendamento de ações(Intent) a serem disparadas pelo sistema em *backgroud* . Estas ações são disparadas pelo sistema e não pela aplicação. Para disparar a ação o sistema envia para o ContentProvider a PedingIntent agendada que pode é interceptada pela classe BroadcastReceiver,  assim as ações a serem executadas no agendamento devem estar encapsuladas por uma classe BroadcastReceiver, que implementa o metódo onReceive.


estas intenções são então disparadas pelo sistema no horário agendado e podem ser escutadas pela classe BroadcastReceiver.
o agendamento de intent é feito por meio dos metódos
- set(type, Calendar,PedingIntent)
- setInexactRepeating(type, Calendar,milisegundos,PedingIntent)
- setRepeating(type, Calendar,milisegundos,PedingIntent)


Mas antes de executar o agendamento com um dos métodos acimas é preciso criar uma PedingItent, para criar esta intent é necessário passar para classe o construtor de Itente qual é o nome do BroadcastReceiver que será executado, ou seja a classe que implementa o metódo on receive e estará habilitada a ouvir Intents.

```java
  Intent   intent                     = new Intent(context, BroadcastReceiverAlarm.class);
  PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
```
Para que a classe BroadcastReceiverAlarm.class possa receber intents do sistema deve-se informar no mafinesto da aplicação que ela é um Receiver isso é feito como exemplo exposto no trecho de código xml a seguir:
```xml
<application >
		...
      <receiver  android:name=".BroadcastReceiverAlarm">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </receiver>
<application >
```
Observe que no XML declardo além de declarar a classe BroadcastReceiverAlarm como um receiver fora adicionado uma ação de filtro para ="android.intent.action.BOOT_COMPLETED", esta intent é disparada no sistema ao termino do boot informando as aplicações que o sistema terminou o boot do sistema, para ter acesso a esta intenção também deve-se declarar a seguinte permissão no manifesto
```xml
  <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
 <application >
 	....
 </application>
```
Além da PedingItente é necessário definir o horário do agendamento por meio de um Calendar como apresentado no código a baixo:
```java
 //Configurando um calendar java.util,  com a data e hora atual e adicionando  5 segundos.
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MILLISECOND, 5);
```



so então deve-se obter uma referência para o AlarmManager , que é um serviço do sistema e então registrar o agendamento.
```java
	 //Recupera uma referencia para o serviço de alarme do android.
	 this.alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
	alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

```


Além disso é necessário definir uma permissão


###Sequence Diagram
### Diagrama de Sequência cadastro de alarme
```seq
MainActivity->AlarmManagerUtil:AlarmManagerUtil(context)

Note right of AlarmManagerUtil:Criando uma IntentPeding \n Utilizada para criação de uma alarme, \n ela deve  ter entre a classe que implementa o metódo onReceive\n que será executado no agendamento
AlarmManagerUtil->Intent:Intent(context,BroadCastReceiverAlarm ):Intent
AlarmManagerUtil->PendingIntente:PedingIntent(context):PedingIntent

Note right of AlarmManagerUtil:Definindo Hora e data\n para o alarme utilizando Calendar
AlarmManagerUtil->Calendar:getInstance():Calendar
AlarmManagerUtil->Calendar:setTimeInMillis(System.currentTimeMillis()):void

Note right of AlarmManagerUtil:Configuração do alarme\n
AlarmManagerUtil->SystemService: getSystemService(ALARM_SERVICE):AlarmManager
AlarmManagerUtil-->>alarmManager:serRepeting(type,calendar,interval,1,alarmIntent):void

```

### Diagrama de Sequência disparo de um agendamento

```seq
Note left of  AlarmManager:No horário programado\n  O serviço de alarme dispara a\n PedingIntent pelo ContentProvider

AlarmManager-->>ContentProvider:sendIntent(PedingIntent)
ContentProvider-->>AlarmManagerUtil:onReceive

```


