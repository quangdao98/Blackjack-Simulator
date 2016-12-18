//***************************
//
//Blackjack - Dealer Class
//
//***************************

import java.util.ArrayList;

public class Dealer {
	
	private ArrayList<Card> dealerHand;
	private int score;
	
	public Dealer(){
		dealerHand = new ArrayList<Card>();
		score=0;
	}
	
	//Methods for the dealer's hand
	public void addCard(Card card){
		dealerHand.add(card);
	}
	
	public Card getCardIndex(int i){
		return dealerHand.get(i);
	}
	
	public int getHandSize(){
		return dealerHand.size();
	}
	
	public void resetHand(){
		dealerHand.removeAll(dealerHand);
	}
	
	//Methods for the dealer's score
	public int getScore(){
		return score;
	}
	
	public void resetScore(){
		score=0;
	}
	
	public void calculateScore(){
		int tempScore=0;
		int pos=0;
		for (int i=0; i<dealerHand.size(); i++){
			tempScore += dealerHand.get(i).getBlackjackValue();
		}
		//If the score is more than 21, then I will check if there are aces
		//in the dealer's hand. For each ace that I spot, I will set its
		//value to 1 instead of 11. After changing the value, if the score
		//is still greater than 21, I will keep looking for another ace
		while (tempScore>21 && pos<dealerHand.size()){
			if (dealerHand.get(pos).getBlackjackValue()==11){
				tempScore = tempScore - 10;
			}
			pos++;
		}
		score = tempScore;
	}

}
