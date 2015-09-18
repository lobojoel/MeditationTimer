package com.example.meditatetimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoveQuotes {

private List<String> quotesList;
	
	LoveQuotes() {
		quotesList = new ArrayList<String>();
		
		initQuote();
	}
	
	public void initQuote() {
		quotesList.add("Oh, you little girl, you stole my heart. Never mind, I will steal you a kiss. That will make us even.");
		quotesList.add("You are the inspiration behind all that I do, and the source of all that is good in my life.");
		quotesList.add("Someone up there must be watching out for me, because they sent heaven’s most beautiful angel into my life.");
		quotesList.add("My gratitude for having met you is surpassed only by my amazement at the joy you bring to my life.");
		quotesList.add("When you love me like that I turn into ten grams of honey. So we can be sweet together.");
		quotesList.add("In my hands is this heart. I want you to have it, because I’m so clumsy, so I’m afraid I’ll lose it or easily give it to someone else.");
		quotesList.add("I love you and that's the beginning and end of everything.");
		quotesList.add("If I knew I would be falling in love with an angel, I would have searched for you harder and found you sooner.");
		quotesList.add("I seem to have loved you in numberless forms, numberless times, in life after life, in age after age forever.");
		quotesList.add("To be your friend was all I ever wanted; to be your lover was all I ever dreamed.");
		
		quotesList.add("I love you not only for what you are, but for what I am when I am with you.");
		quotesList.add("Within you, I lose myself. Without you, I find myself wanting to be lost again.");
		quotesList.add("To the world, you may be one person, but to one person you are the world.");
		quotesList.add("You may hold my hand for a while, but you hold my heart forever");
		quotesList.add("Someone up there must be watching out for me, because they sent heaven’s most beautiful angel into my life.");
		quotesList.add("You're special to me in every way. Thank you for being who you are and for letting me be myself.");
		quotesList.add("I will always care for you, even if we're not together and even if we're far, far away from each other");
		quotesList.add("If I had a flower for every time I thought of you, I could walk in my garden forever");
		quotesList.add("My six word love story: I can’t imagine life without you");
		quotesList.add("If I had to choose between breathing and loving you I would use my last breath to tell you I love you");
		
		quotesList.add("My love for you is a journey, starting at forever and ending at never.");
		quotesList.add("By night, Love, tie your heart to mine, and the two together in their sleep will defeat the darkness.");
		quotesList.add("A friend loves at all times.");
		quotesList.add("Love bears all things, believes all things, hopes all things, endures all things.");
		quotesList.add("There are only two times that I want to be with you, now and forever");
		quotesList.add("Our bond is stronger than the sun and sweeter than the birds song.");
		quotesList.add("Love is an emotion experienced by the many and enjoyed by the few");
		quotesList.add("Love is that condition in which the happiness of another person is essential to your own");
		quotesList.add("Loving you is both my biggest weakness and greatest strength");
		quotesList.add("Love’s gift cannot be given, it waits to be accepted.");
	
		quotesList.add("If music be the food of love, play on.");
		quotesList.add("Love is a game that two can play and both win");
		quotesList.add("A flower cannot blossom without sunshine, and man cannot live without love");
	
	}
	
	public String getQuote() {
		return quotesList.get(randInt(0, 32));
	}
	
	public  int randInt(int min, int max) {
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
