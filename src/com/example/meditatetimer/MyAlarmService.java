package com.example.meditatetimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

public class MyAlarmService extends Service {

	  private NotificationManager mManager;
	  private MeditationQuotes myQuote;
	  private LoveQuotes myLoveQuote;
	  
	    @Override
	    public IBinder onBind(Intent arg0) {
	       // TODO Auto-generated method stub
	        return null;
	    }
	 
	    @Override
	    public void onCreate() {
	       // TODO Auto-generated method stub  
	       super.onCreate();
	    }
	 
	   @SuppressWarnings({ "static-access", "deprecation" })
	   @Override
	   public void onStart(Intent intent, int startId)
	   {
	       super.onStart(intent, startId);
	      
	       myQuote = new MeditationQuotes();
	       String quoteString = myQuote.getQuote();
	       
	       myLoveQuote = new LoveQuotes();
	       String sloveQuote = myLoveQuote.getQuote();
	       
	       Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
	       mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
	   	     
	       Notification notification = new Notification.Builder(this)
	      	 .setAutoCancel(true)
	      	 .setContentIntent(PendingIntent.getActivity( this.getApplicationContext(), 0, intent1,
	      			 		   PendingIntent.FLAG_UPDATE_CURRENT))
	      	 .setContentText(quoteString)
	       	 .setStyle(new Notification.BigTextStyle().bigText(quoteString))
	         .setContentTitle("Being Awake ")
	         .setSmallIcon(getNotificationIcon())
	         .setWhen(System.currentTimeMillis())
	         .build();

	       intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	       
	       mManager.notify(0, notification);
	       
	       // phone number to which SMS to be sent
	       //String phoneNumberReciver1="5712156368";
	       //String phoneNumberReciver2="5712189291";
	       //SmsManager sms = SmsManager.getDefault(); 
	       //sms.sendTextMessage(phoneNumberReciver1, null, quoteString, null, null);
	       //sms.sendTextMessage(phoneNumberReciver2, null, quoteString, null, null);
	       
	       long seconds;
	       long minutes;
	       long currentTime;
	       
	       currentTime = System.currentTimeMillis();
	       seconds = (currentTime / 1000) % 60 ;
	       minutes =  ((currentTime / (1000*60)) % 60);
	       //System.out.println(minutes + "mm " + seconds + " sec.");
	       		   
	    }
	 
	    @Override
	    public void onDestroy() 
	    {
	        // TODO Auto-generated method stub
	        super.onDestroy();
	    }
	    
	    private int getNotificationIcon() {
	        boolean whiteIcon = (android.os.Build.VERSION.SDK_INT >= 21 ); // android.os.Build.VERSION_CODES.KITKAT_WATCH);
	        return whiteIcon ? R.drawable.bell : R.drawable.ic_launcher;
	    }
	
	
}
