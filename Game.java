//***************************
//
//Blackjack - Game Class
//
//***************************

import java.util.Scanner;

public class Game {
	
	private final double MIN_BUY_IN = 100;
	private final double MIN_BET = 10;
	private final double MAX_BET = 1000;
	
	private final int DEALER_NUMBER_TO_STAND = 17;
	private final int MIN_NUMBER_OF_CARDS_IN_DECK = 12;
	
	private Deck deck;
	private Player player;
	private Dealer dealer;
	
	//We use a single scanner for all our inputs
	private Scanner input;
	
	public Game(){
		deck = new Deck();
		dealer = new Dealer();
		player = new Player();
		input = new Scanner(System.in);
	}
	
	//The play method is the only thing we will invoke in the tester class
	public void play(){
		getBalance();
		//Each iteration of this is a round
		while (player.stillPlaying() == true){
			initialDeal();
			playerTurn();
			if (player.getScore()<=21){
				dealerTurn();
			}
			result();
		}
	}
	
	//In this game, I allow the user to input the initial balance
	public void getBalance(){
		System.out.println("How much do you want to buy?");
		double amount;
		
		//This is to check that the user input balance has the right value
		while (true){
			if (input.hasNextInt()){
				amount = input.nextDouble();
				if (amount >= MIN_BUY_IN){
					break;
				}
				else {
					System.out.println("You have to buy more than $100.0. "
							+ "Please re-enter: ");
				}
			}
			else {
				System.out.println("That's not a number. Please re-enter: ");
				input.next();
			}
		}
		
		player.changeBalance(amount);
		player.printBalance();
		System.out.println("The game will now begin. May the odds be "
									+ "ever in your favour.\n");
	}
	
	//This is for dealing the first two cards to the dealer
	public void initialDeal(){
		System.out.println("The dealer and you will each be dealt two cards. "
				+ "For now, you will only see dealer's first card.");
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.calculateScore();
		System.out.print("Dealer's first card is ");
		System.out.println(dealer.getCardIndex(0));
	}
	
	//This comprises all the actions that the player will make in a round
	public void playerTurn(){
		getCurrentBet();
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		player.calculateAndPrintScore();
		player.hit();
		while (player.hitOrStand()==true){
			askHitOrStand();
			if (player.hitOrStand()==true){
				player.addCard(deck.deal());
				player.calculateAndPrintScore();
			}
		}
	}
	
	//Method for inputing a bet. Used in playerTurn()
	public void getCurrentBet(){
		System.out.println("How much do you want to bet for this round?");
		double amount;
		
		//Same as inputing initial balance. Check if it's the right value
		while (true){
			//Since the bet must be an integer, I check if the input is int
			if (input.hasNextInt()){
				amount = input.nextDouble();
				if (amount >= MIN_BET && amount <= MAX_BET 
							&& amount <= player.getBalance()){
					break;
				}
				else {
					System.out.println("You have to bet between $10.00 and "
						+"$1000.00 in $1.00 increments. You also cannot bet "
						+ "more than your balance. Please re-enter:");
				}
			}
			else {
				System.out.println("That's not a number. Please re-enter: ");
				input.next();
			}
		}
		
		player.setCurrentBet(amount);
		player.printCurrentBet();
	}
	
	//Method to ask after each card is dealt to the player
	public void askHitOrStand(){
		System.out.println("Do you want to hit or stand? Y if hit, N if stand");
		String status;
		
		while (true){
			status = input.next();
			if (status.equals("Y")){
				System.out.println("You choose to hit.");
				break;
			}
			else if (status.equals("N")){
				System.out.println("You choose to stand. Your turn is over.");
				player.stand();
				break;
			}
			else {
				System.out.println("Please re-enter. Y if hit, N if stand.");
			}
		}
	}
	
	//This will be called once the player is done and is not busted
	//Implement the "hit if below 17" rule
	public void dealerTurn(){
		while(dealer.getScore() < DEALER_NUMBER_TO_STAND){
			dealer.addCard(deck.deal());
			dealer.calculateScore();
		}
		for (int i=1; i<dealer.getHandSize(); i++){
			System.out.print("Dealer draws ");
			System.out.println(dealer.getCardIndex(i));
		}
		System.out.println("Dealer's score is " + dealer.getScore()+".");
		if (dealer.getScore()==21){
			System.out.println("Dealer has blackjack!");
		}
		if (dealer.getScore()>21){
			System.out.println("Dealer is busted!");
		}
	}
	
	//These are the things after both player and dealer make their turns
	public void result(){
		determineWinLose();
		if (player.getBalance()<MIN_BET){
			player.printBalance();
			System.out.println("You don't have enough money left. Game over!");
			player.stopPlaying();
		}
		else {
			askIfContinue();
		}
		if (player.stillPlaying()==true &&
				deck.getSize()<MIN_NUMBER_OF_CARDS_IN_DECK){
			System.out.println("There are less than 12 cards in deck. "
					+ "The deck will be shuffled.");
			deck = new Deck();
		}
	}
	
	//Method that determine if the player wins, loses, or ties.
	//Include the x1.5 rule for Blackjack
	public void determineWinLose(){
		if (player.getScore()>21){
			System.out.println("You lose!");
			player.changeBalance(-player.getCurrentBet());
		}
		else if (player.getScore()==21){
			if (dealer.getScore()==21){
				System.out.println("It's a tie!");
			}
			else {
				System.out.println("You win!");
				player.changeBalance(player.getCurrentBet()*1.5);
			}
		}
		else {
			if (dealer.getScore()>21){
				System.out.println("You win!");
				player.changeBalance(player.getCurrentBet());
			}
			else if (player.getScore()>dealer.getScore()){
				System.out.println("You win!");
				player.changeBalance(player.getCurrentBet());
			}
			else if (player.getScore()<dealer.getScore()){
				System.out.println("You lose!");
				player.changeBalance(-player.getCurrentBet());
			}
			else {
				System.out.println("It's a tie!");
			}
		}
		
		//After each round, we must reset these values
		player.setCurrentBet(0);
		player.resetScore();
		dealer.resetScore();
		player.resetHand();
		dealer.resetHand();
	}
	
	//Method to ask the player if he wants to play another round
	public void askIfContinue(){
		System.out.println("Do you want to play another round? "
					+ "Y if yes, N if no");
		String status;
		
		while (true){
			status = input.next();
			if (status.equals("Y")){
				System.out.println("You choose to continue.");
				player.printBalance();
				break;
			}
			else if (status.equals("N")){
				System.out.println("You choose to stop. The game is over.");
				player.stopPlaying();
				break;
			}
			else {
				System.out.println("Please re-enter. Y if yes, N if no");
				input.next();
			}
		}
	}
}
