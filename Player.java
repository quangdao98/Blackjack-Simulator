//***************************
//
//Project 4 - Player Class
//
//Quang Dao - qvd2000
//
//***************************

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Player {
	
	private ArrayList<Card> playerHand;
	private int score;
	private double balance;
	private double currentBet;
	private boolean hitOrStand;
	private boolean stillPlaying;
	
	public Player(){
		playerHand = new ArrayList<Card>();
		score = 0;
		balance = 0;
		currentBet = 0;
		hitOrStand = true;
		stillPlaying = true;
	}
	
	//These are the methods for the player's hand
	public void addCard(Card card){
		playerHand.add(card);
		System.out.print("You draw ");
		System.out.println(getCardIndex(playerHand.size()-1));
	}
	
	public Card getCardIndex(int i){
		return playerHand.get(i);
	}
	
	public void resetHand(){
		playerHand.removeAll(playerHand);
	}
	
	//These are the methods for player's score
	public int getScore(){
		return score;
	}
	
	public void resetScore(){
		score=0;
	}
	
	public void calculateAndPrintScore(){
		int tempScore=0;
		int pos=0;
		for (int i=0; i<playerHand.size(); i++){
			tempScore += playerHand.get(i).getBlackjackValue();
		}
		while (tempScore>21 && pos<playerHand.size()){
			if (playerHand.get(pos).getBlackjackValue()==11){
				tempScore = tempScore - 10;
			}
			pos++;
		}
		score = tempScore;
		//I incorporate the printing here because during the game,
		//there is no instance in which the player is dealt a card
		//and not want to know what his/her card is
		System.out.println("Your current score is: " + score);
		if (score==21){
			System.out.println("You have blackjack!");
		}
		if (score>21){
			System.out.println("You are busted!");
			hitOrStand=false;
		}
	}
	
	//These are the methods for the player's balance
	public double getBalance(){
		return balance;
	}
	
	public void changeBalance(double amount){
		balance = balance + amount;
	}
	
	public void printBalance(){
		NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("Your balance is: " + fmt.format(balance));
	}
	
	//These are the methods for the player's bet for a certain round
	public double getCurrentBet(){
		return currentBet;
	}
	
	public void setCurrentBet(double amount){
		currentBet = amount;
	}
	
	public void printCurrentBet(){
		NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("Your bet is : " + fmt.format(currentBet));
	}
	
	//These are the methods for the player's status during a round,
	//whether he/she chooses to continue drawing cards, or to stop
	//(if the player is busted, this variable will also be set to false)
	public boolean hitOrStand(){
		return hitOrStand;
	}
	
	public void hit(){
		hitOrStand=true;
	}
	
	public void stand(){
		hitOrStand = false;
	}
	
	//These are the methods for the player's status after each round,
	//whether he/she wants to continue after a round (or if out of money,
	//then this variable will make sure the game stops)
	public boolean stillPlaying(){
		return stillPlaying;
	}
	
	public void stopPlaying(){
		stillPlaying = false;
	}
	
}
