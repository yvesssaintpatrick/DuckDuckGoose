Duck, Duck, Goose Game Simulator

This Java program simulates a game of Duck, Duck, Goose.
The game involves players sitting in a circle, with one player ("It") tapping others on the head, calling them "Duck" until finally calling "Goose" and chasing the chosen player around the circle. 
The chosen player must then attempt to return to the vacant spot before being tagged by "It."

Important Note

The program acknowledges instances where the output might be incorrect due to the reverse rotation function.
This function is essential as creating duplicate instances of the original list raised challenges.
Specifically, if a list is rotated (e.g., clockwise), both the original list and the goose pattern list rotate in the same direction.
The program relies on the reverse rotation function to bring the player names back to their original order.

Game Simulation

The program will simulate the specified number of rounds, displaying the state of the game after each round.
It determines the winner of each round based on randomly generated speeds for the "It" player and the "Goose" player.

Classes and Methods

DuckDuckGoose Class

* main: Handles user input, initializes the game, and runs the specified number of rounds.
* Round: Simulates a round of the game, displaying the game state, the "It" person, and the winner.
* ItName: Rotates the player names to determine the "It" person for the current round.
* print_speeds: Displays randomly generated speeds for the "It" person and the "Goose," determining the winner of the round.
* it_running_first and goose_running_first: Simulates the running sequence for the "It" person and the "Goose," respectively.
* reverseRotation: Reverses the player names back to their original order after determining the winner.
* print_randline: Generates a random number between 1 and 20.

How to Run
Compile and run the program using your preferred Java development environment.
Follow the on-screen prompts to input the number of players, player names, and the number of rounds.
