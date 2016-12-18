//***************************
//
//Blackjack - Card Class
//
//***************************

public class Card {

	private final String[] SUITS = {"Spades", "Clubs", "Hearts", "Diamonds"};
	private final String[] VALUES = {"Ace", "2", "3", "4", "5", "6", "7",
							"8", "9", "10", "Jack", "Queen", "King"};
	
	//Ace = 1	2 to 10 = 2 to 10	Jack = 11	 Queen = 12		King = 13		
	private int value;
	
	//Spades = 1	Clubs = 2	Hearts = 3	Diamonds = 4
	private int suit;
	
	public Card(int number, int type){
		value = number;
		suit = type;
	}
	
	public int getBlackjackValue(){
		if (value>=11){
			return 10;
		}
		else if (value==1){
			return 11;
		}
		else {
			return value;
		}
	}
	
	public String toString(){
		return VALUES[value-1] + " of " + SUITS[suit-1]+".";
	}
}
