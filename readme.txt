Quang Dao - qvd2000

These are my notes for Programming Project 4:

1. I follow only the rules that the project says
I must follow in the assignment. This means that
there is no variation, either surrender, splitting,
or doubling down.

2. I allow the player to input the initial balance.
Just for convenience, I want that balance to also be
an integer.
For inputing balance and bet, I add
multiple checks to know that they have the desired
input type and fall within range.
Same goes with inputing "Y" or "N" response.

3. This is the hierarchy of my class:
First, there is the Card class. Each instance of
a Card class is a playing card with its own value
and suit.
Then, the Deck class constructs a typical 52-card
deck from instances of the Card class.
The Dealer class keeps track of what the dealer
keep track of (it's a tautology, but well, you can
see the code to know what it keeps track of).
Also, the methods in the Dealer class are just used
to assist in the running of the Game class.
The Player class is pretty much the same as the
Dealer class, except that the player has a balance
and we will be given the input from the player during
the course of the game.
Again, the methods in the Player class are there
to be invoked by the Game class.

Finally, there is the Game class. This is the class
that combines all other classes and dictates how the
game will proceed. A typical turn will be the dealer
dealing two cards to himself and revealing the first card;
the player placing a bet and is revealed his/her two cards;
the player making moves before he/she wishes to stand;
the dealer automatically make decisions based on its algorithm;
and finally the game will announce the result of the turn.

The above process of a turn is from what I read in the rules
and from my experience playing Blackjack. I'm not entirely sure
if there is some parts that I need to do differently, but I
hope that explaining my version of the game here will get me 
full credit if I manage to implement it in my code.