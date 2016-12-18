//***************************
//
//Project 4 - Deck Class
//
//Quang Dao - qvd2000
//
//***************************

import java.util.ArrayList;

public class Deck {
	
	private final int NUMBER_OF_VALUES = 13;
	private final int NUMBER_OF_SUITS = 4;
	
	private ArrayList<Card> deck;
	
	//In the constructor, I first create all the cards in the deck,
	//but organized in a certain fashion. Then, I invoke the shuffle
	//method to make the deck random
	public Deck(){
		deck = new ArrayList<Card>();
		for (int i=1; i<=NUMBER_OF_VALUES; i++){
			for (int j=1; j<=NUMBER_OF_SUITS; j++){
					Card tempCard = new Card(i,j);
					deck.add(tempCard);
			}
		}
		shuffle();
	}
	
	//This method will shuffle the current deck
	//Shuffling is basically just creating a permutation of the current deck,
	//so this is exactly what I did here
	//I also don't have to create a new ArrayList, I just add the card
	//to the end of the deck and delete the current position of the card
	public void shuffle(){
		for (int i=deck.size()-1; i>=0; i--){
			int position = (int)(Math.random()*i);
			deck.add(deck.get(position));
			deck.remove(position);
		}
	}
	
	//This is a method that will deal a card to the player or the dealer
	//Of course, after dealing, the card is no longer in the deck
	public Card deal(){
		Card top = deck.get(0);
		deck.remove(0);
		return top;
	}
	
	public int getSize(){
		return deck.size();
	}
	
}
