package com.example.meditatetimer;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	public Button TenMinuteButton;
	public Button TwentyMinuteButton;
	public Button ThirtyMinuteButton;
	public Button pauseButton;
	public Button startButton;
	public NumberPicker numberPicker;
	public SeekBar seekBarUserTime;
	private TextView timeProgressTextLarge;
	private TextView quotes;
	
//	public TextToSpeech textToSpeech;
	
	long givenseekBarUserTime;
	public MediaPlayer mediaPlayer = new MediaPlayer();
	
	long timeRemainingTimer;
	int timeRemainingMP3 = 0;
	myTimer timer;
	
	private MeditationQuotes myMediationQuote = new MeditationQuotes();
	private String quoteString;
	Typeface face;
	
	private PendingIntent pendingIntent;
	
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TenMinuteButton = (Button)findViewById(R.id.TenMinButton);
		TwentyMinuteButton = (Button)findViewById(R.id.TwentyMinButton);
		ThirtyMinuteButton = (Button)findViewById(R.id.ThirtyMinButton);
		pauseButton = (Button)findViewById(R.id.pauseButton);
		startButton = (Button)findViewById(R.id.startButton);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		long minutesLogged = settings.getLong("seekBarMinutesLogged", 10/60000);
		long secondsLogged = settings.getLong("seekBarSecondsLogged", 0);
		
		System.out.println("Time Logged Minutes is " + minutesLogged);
		System.out.println("Time Logged Seconds is " + secondsLogged);
		
		seekBarUserTime = (SeekBar)findViewById(R.id.time_indicator);
		seekBarUserTime.setOnSeekBarChangeListener(this);
		
		//givenseekBarUserTime = seekBarUserTime.getProgress()*60*1000;
		givenseekBarUserTime = (minutesLogged*60*1000) + (60000 - secondsLogged*1000);
		timeRemainingTimer = givenseekBarUserTime;
		timeProgressTextLarge  = (TextView)findViewById(R.id.timerText_large);
		
		quotes = (TextView)findViewById(R.id.meditation_quote);
		face = Typeface.createFromAsset(getAssets(), "fonts/open_cosmetic_bold.ttf");
		
		defaultMenuSettings();

//		textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//			@Override
//			public void onInit(int status) {
//				if(status != TextToSpeech.ERROR){
//		             textToSpeech.setLanguage(Locale.getDefault());   
//		             textToSpeech.setPitch(1.1f);
//		             textToSpeech.setSpeechRate(.25f);
//		        }
//			}
//		});
		
		// START
		startButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {						
				if(startButton.getText().toString().equals("Start")) {
					setTimer(timeRemainingTimer);
					initMediaPlayer();
					playBells();
					timer.start();
					startButton.setText("Stop");	
				}
				else {
					timer.cancel();
					startButton.setText("Start");
					timeRemainingTimer = 10*60*1000;
					defaultMenuSettings();
					releaseMediaPlayer();
				}
			}
		});
		
		// Pause
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(pauseButton.getText().toString().equals("Pause")) {
					if(mediaPlayer.isPlaying()) {
						mediaPlayer.pause();
						timeRemainingMP3 = mediaPlayer.getCurrentPosition();
					}
					pauseButton.setText("Play");
					timer.cancel();
				}
				else{
					mediaPlayer.seekTo(timeRemainingMP3);
					mediaPlayer.start();
					pauseButton.setText("Pause");
					
					//System.out.println("GivenseekBarUserTime is " + givenseekBarUserTime);
					//System.out.println("Length is " + timeRemainingMP3);
					//System.out.println("Reset Time is " + timeRemainingTimer);
					setTimer((timeRemainingTimer));
					timer.start();
				}
			}
		});
			
		// TEN MINUITE 
		TenMinuteButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				playBells();
				setTimer(10*60*1000);
				timer.start();
			}
		});
		
		// TWENTY MINUITE 
		TwentyMinuteButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				playBells();
				setTimer(20*60*1000);
				timer.start();
			}
		});
				
		// THIRTY MINUITE 
		ThirtyMinuteButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				playBells();
				setTimer(30*60*1000);
				timer.start();
			}
		});
		
		defaultMenuSettings();
		startNotification();
	}
	
//	@Override
//	public void onPause() {
//	    if(textToSpeech !=null){
//	    	textToSpeech.stop();
//	    	textToSpeech.shutdown();
//	      }
//	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	      SharedPreferences.Editor editor = settings.edit();
	      
	      long minutesLeft = (timeRemainingTimer/60000);
		  long secondsLeft = (timeRemainingTimer/1000) - (minutesLeft*60);
	      
	      editor.putLong("seekBarMinutesLogged", minutesLeft);
	      editor.putLong("seekBarSecondsLogged", secondsLeft);

	      // Commit the edits!
	      editor.commit();
		
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		givenseekBarUserTime = progress*60*1000;
		timeRemainingTimer = givenseekBarUserTime;
		displayTimeRemaining(timeRemainingTimer);
	}
	
	public void displayTimeRemaining( long givenTimemillisecs) {
		long minutesLeft = (givenTimemillisecs/60000);
		long secondsLeft = (givenTimemillisecs/1000) - (minutesLeft*60);
		
		timeProgressTextLarge.setText("Be Awake:: " + minutesLeft + " Mins" + "-" + secondsLeft + " Secs.");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		seekBar.setSecondaryProgress(seekBar.getProgress());
		
	}
	
	public void initMediaPlayer() {
		mediaPlayer = MediaPlayer.create(this, R.raw.meditation_bells);	
		mediaPlayer.setLooping(false);
	}
	
	public void playBells() {
		//System.out.println("Playing Bells.... ");
		initMediaPlayer();
		mediaPlayer.start();
		//System.out.println("After mediaPlayer.start().... ");
	}
	
	protected void releaseMediaPlayer(){
	    mediaPlayer.release();
	    mediaPlayer = null;
	}

	public void setTimer(long millisUntilFinished) {
		givenseekBarUserTime = (millisUntilFinished)/60000;
		timer = new myTimer(millisUntilFinished, 1000);		
	}
	
	public class myTimer extends CountDownTimer{		
		public myTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onTick(long millisUntilFinished) {
			timeRemainingTimer = millisUntilFinished;
			playerSettings();
		}

		@Override
		public void onFinish() {
			timeRemainingTimer = 0;
			playBells();	
			defaultMenuSettings();	
		}
	}
	
	public void defaultMenuSettings() {
		timeProgressTextLarge.setVisibility(View.VISIBLE);
		displayTimeRemaining(timeRemainingTimer);
		
		seekBarUserTime.setVisibility(View.VISIBLE);
		
		startButton.setVisibility(View.VISIBLE);
		startButton.setText("Start");
		
		pauseButton.setVisibility(View.GONE);
		pauseButton.setText("Pause");
		
		TenMinuteButton.setVisibility(View.VISIBLE);
		TwentyMinuteButton.setVisibility(View.VISIBLE);
		ThirtyMinuteButton.setVisibility(View.VISIBLE);
		
		quotes.setVisibility(View.VISIBLE);
		quotes.setTextColor(Color.rgb(0, 250, 154));
		
		updateQuote();
		displayQuote();
	}	
	
	public void playerSettings() {
		timeProgressTextLarge.setVisibility(View.VISIBLE);
		displayTimeRemaining(timeRemainingTimer);
		
		seekBarUserTime.setVisibility(View.INVISIBLE);
		
		startButton.setVisibility(View.VISIBLE);
		startButton.setText("Stop");
		
		pauseButton.setVisibility(View.VISIBLE);
		pauseButton.setText("Pause");
		
		TenMinuteButton.setVisibility(View.INVISIBLE);
		TwentyMinuteButton.setVisibility(View.INVISIBLE);
		ThirtyMinuteButton.setVisibility(View.GONE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void updateQuote() {
		quoteString = myMediationQuote.getQuote();
	}
	
	public void displayQuote() {
		quotes.setTypeface(face);
		quotes.setText(quoteString);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
//	 public void speakText(String givenString){
//	      textToSpeech.speak(givenString, TextToSpeech.QUEUE_FLUSH, null);
//	   }
	
	public void startNotification() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.set(2015, 1, 14, 00, 00, 00); calendar.getTime();
        
      	 Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
         pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
         
         long trigger = calendar.getTimeInMillis();
         // set delay between each call : 1 Hour
         long delay = 4*60*60*1000; // 24 * 60 * 60 * 1000;
        
         AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
         alarmManager.cancel(pendingIntent);
         alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, trigger, delay, pendingIntent);
         
	}
}
