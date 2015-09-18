package com.example.meditatetimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeditationQuotes {

	private List<String> quotesList;
	
	MeditationQuotes() {
		quotesList = new ArrayList<String>();
		
		initQuote();
	}
	
	public void initQuote() {
		quotesList.add("As long as you are breathing, there is more right with you than wrong with you," +
				"no matter how ill or hopeless you may feel. - Dr. Jon Kabat Zinn.");
		quotesList.add("The whole of meditation practice can be essentialized into these 3 crucial points:\nBring your mind home.\nRelease.\nRelax! - Sogyal Rinpoche");
		quotesList.add("The mind is its own place, and in it self can make a heav'n of hell, a hell of heav'n.– John Milton");
		quotesList.add("If you mind is empty, it is always ready for anything; it is open to everything.  In the beginner’s mind there are many possibilities, in the expert’s mind there are few. - Shunryu Suzuki");
		quotesList.add("It’s good to have an end in mind but in the end what counts is how you travel. ― Orna Ross");
		quotesList.add("Be happy in the moment, that’s enough. Each moment is all we need, not more. ― Mother Teresa");
		quotesList.add("\"The future is always beginning now.\" — Mark Strand");
		quotesList.add("\"If you surrender completely to the moments as they pass, you live more richly those moments.\" — Anne Morrow Lindbergh");
		quotesList.add("\"Life is a preparation for the future; and the best preparation for the future is to live as if there were none.\" ― Albert Einstein");
		quotesList.add("\"Respond; don't react.\nListen; don't talk.\nThink; don't assume.\"― Raji Lukkoor");
		
		quotesList.add("There is no enlightenment outside of daily life.\" - Thich Nhat Hanh");
		quotesList.add("If we learn to open our hearts, anyone, including the people who drive us crazy, can be our teacher.\" - Pema Chodron");
		quotesList.add("Realize that this very body, with its aches and it pleasures… is exactly what we need to be fully human, fully awake, fully alive.\" - Pema Chodron");
		quotesList.add("\"Those who are free of resentful thoughts surely find peace.\" - Buddha");
		quotesList.add("\"All that we are is the result of what we have thought. The mind is everything. What we think we become.\" - Buddha");
		quotesList.add("\"We are shaped by our thoughts; we become what we think. When the mind is pure, joy follows like a shadow that never leaves.\" - Buddha");
		quotesList.add("\"Each day is a different one, each day brings a miracle of its own. It’s just a matter of paying attention to this miracle.\" - Paulo Coelho");
		quotesList.add("\"Each minute of life should be a divine quest.\" - Buddha Paramahansa Yogananda");
		quotesList.add("\"Each day we are born again. What we do today is what matters most.\" - Buddha");
		quotesList.add("\"Go with the flow.\" - Ken Kesey");
		
		quotesList.add("\"If you take care of each moment, you will take care of all time.\" - Unknown");
		quotesList.add("\"Milk every moment for all the pleasure you can get from it.\" – Abraham-Hicks");
		quotesList.add("\"Mindfulness is simply being aware of what is happening right now without wishing it were different.\" - James Baraz");
		quotesList.add("\"Nothing is worth more than this day. You cannot relive yesterday. Tomorrow is still beyond your reach.\" - Goethe");
		quotesList.add("\"What we get from each moment depends on the attention we give it, and the quality of our experience reflects the quality of our awareness.\" – Roger Walsh");
		quotesList.add("\"If you surrender completely to the moments as they pass, you live more richly those moments.\" - Anne Morrow Lindbergh");
		quotesList.add("\"Whatever the present moment contains, accept it as if you had chosen it. Always work with it, not against it.\" - Eckhart Tolle");
		quotesList.add("\"I promise myself that I will enjoy every minute of the day that is given me to live.\" - Thich Nhat Hanh");
		quotesList.add("\"Remember then: there is only one time that is important – Now! It is the most important time because it is the only time when we have any power.\" - Leo Tolstoy");
		quotesList.add("\"The only time we suffer is when we believe a thought that argues with what is. When the mind is perfectly clear, \"what is\" is what we want.\" – Byron Katie");
	
		quotesList.add("\"An oak tree is an oak tree. That is all it has to do. If an oak tree is less than an oak tree, then we are all in trouble.\"- Thich Nhat Hanh (Being Peace)");
		quotesList.add("\"Each thought, each action in the sunlight of awareness becomes sacred.\" - Thich Nhat Hanh (Peace Is Every Step: The Path of Mindfulness in Everyday Life)");
		quotesList.add("\"Feelings come and go like clouds in a windy sky. Conscious breathing is my anchor.\" - Thich Nhat Hanh");
	
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
