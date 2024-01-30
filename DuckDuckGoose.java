
/**
 *The code below simulates a game of Duck,Duck,Goose.
 *
 *
 * There are some instances where the output is wrong , this is due to the reverse rotation function.
 * However,this functions needs to be used as I am unaware of how to create two duplicate instances of
 * the original list . i.e : if I rotate a list I called it pattern clockwise both the original list
 * and the goose pattern list rotate clockwise as well.
 */
import java.util.Scanner;

import java.util.Random;

public class DuckDuckGoose {

    public static void main(String[] args) {
        DCircLinkList<String> playerNames = new DCircLinkList<String>();


        Scanner scanner = new Scanner(System.in);
        System.out.print("How many players? \n");
        int numPlayers = scanner.nextInt();
        while (numPlayers < 4) {
            System.out.println("Enter a number above 4:");
            numPlayers = scanner.nextInt();
        }
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Please enter Player " + (i + 1) + "'s name: \n");
            String names = scanner.next();
            playerNames.addLast(names);
 
        }

        System.out.println("");
        System.out.println("How many rounds? ");
        int numRounds = scanner.nextInt();

        for (int round = 1; round <= numRounds + 1; round++) {
            playerNames = Round(round, playerNames, numRounds + 1);

        }

    }

    /**
     * The round method prints the end state of the game when the number of
     * rounds from the user matches the current round. If total number of rounds
     * is not done , the next round is printed.
     *
     *
     * @param round_num current round in game(int)
     * @param playerNames list of players in game
     * @param numRounds number of rounds user enters
     * @return
     */
    public static DCircLinkList<String> Round(int round_num, DCircLinkList<String> playerNames, int numRounds) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++\n");
        String it_player = ItName(playerNames);    //this stored the current it player
        DCircLinkList<String> updated_player_names = null;  // after each round it returns the current active players in the game

        if (round_num == numRounds) {
            System.out.println();
            System.out.println("Done. Final State.");
            System.out.println("Game circle: " + playerNames.toString());  // displays the playernames without the it player
            System.out.println("It-Person: " + it_player);                  // displayes the it player
            System.out.println("=======================================");

        } else {
            System.out.println("Round " + round_num);      // displays current active round
            System.out.println("Game circle: " + playerNames.toString());   // displays current active players
            System.out.println("It-Person:  " + it_player);       // displays the it player
            System.out.println("=======================================");
            int rand_num = print_randline();
            String goose_player = "";
            String[] goose_friends = new String[2];
            for (int i = 0; i <= rand_num; i++) {     //iterates over the playernames to display the random generated number of times
                if (i == rand_num) {
                    System.out.print(playerNames.getFirstElement() + " GOOSE");  // prints out the player from the rand_num + 1
                    System.out.println();
                    DNode<String> tempGoose = playerNames.getFirstNode();   //this is to take note of the position of the goose before it jumps up
                    goose_friends[0] = tempGoose.getNext().getElement();   //assign the next element of the goose as a friend 
                    goose_friends[1] = tempGoose.getPrevious().getElement(); //assign the previous element of the goose as a friend
                    goose_player = playerNames.removeFirstGetElement();  // finally remove the value of the goose player

                } else {
                    System.out.print(playerNames.getFirstElement() + " duck; ");
                    playerNames.rotateCCW();
                } // ife
            } // for
            // System.out.println(it_player);
            System.out.println();
            System.out.println("Up Jumps:  " + goose_player);  //displays goose player 
            System.out.println("Game circle: " + playerNames.toString());
            updated_player_names = print_speeds(it_player, goose_player, playerNames, goose_friends);  //stores new values of the parameters
            //in the updated linklist to be used for the next round

        }
        return updated_player_names;
    }

    /**
     * This method rotates the position of the playerNames to remove the last
     * player entry as the It player
     *
     * @param playerNames
     * @return
     */
    public static String ItName(DCircLinkList<String> playerNames) {
        playerNames.rotateCW();
        return playerNames.removeFirstGetElement();
    }

    /**
     * This method displays the random generated speeds for the goose and it
     * player and then displays the winner of each round
     *
     * @param it_player current it person
     * @param goose_player current goose person
     * @param playerNames
     * @param goose_friends elements prior and after goose
     * @return
     */
    public static DCircLinkList<String> print_speeds(String it_player, String goose_player,
            DCircLinkList<String> playerNames,
            String[] goose_friends) {
        Random rand = new Random();

        boolean winner_found = false;
        String who_wins = null;

        while (!winner_found) {
            int it_speed = rand.nextInt(1, playerNames.getSize() + 1);
            int goose_speed = rand.nextInt(1, playerNames.getSize() + 1);
            System.out.println("   **Speeds: It-Person " + it_speed + ", Goose " + goose_speed);
            if (it_speed > goose_speed) {                      //determines who runs first based on the number generated , so the higher umber runs first 
                who_wins = it_running_first(playerNames, goose_speed, it_speed, it_player, goose_player,
                        goose_friends);       // this method returns a string of the winner found after running past players in a clockwise direction
            } else {
                who_wins = goose_running_first(playerNames, goose_speed, it_speed, it_player, goose_player,
                        goose_friends);       // this method returns a string of the winner found after running past players in a counter-clockwise direction
            }
            if (who_wins != null) {  //if a winner is found after running  , the while loop is exited
                winner_found = true;
            }
            System.out.println();
        }
        if (who_wins == "It") {  //if the winner found is an It player the winner is declared or Goose is declared winner 
            System.out.println("\nIt-person (" + it_player + ") wins");
            playerNames.addFirst(it_player);  //it player is added back to players at the goose position
            playerNames.addLast(goose_player); // goose player is added last so that in the next round it would be removed and displayed as the it player
        } else {
            System.out.println("\nGoose (" + goose_player + ") wins");
            playerNames.addFirst(goose_player); //goose player is added back to players at its original position
            playerNames.addLast(it_player);  // it player is added last so that in the next round it would be removed and displayed as the it player
        }

        return playerNames;  // returns a new list of players in a new order for the next round 
    }

    /**
     * This method makes the it player run based on the random generated speed
     * and determines if there is a winner
     *
     * @param playerNames
     * @param goose_speed determines how many players run past
     * @param it_speed determines how many players run past
     * @param it_player
     * @param goose_player
     * @param goose_friends
     * @return
     */
    public static String it_running_first(DCircLinkList<String> playerNames,
            int goose_speed, int it_speed,
            String it_player, String goose_player, String[] goose_friends) {
        System.out.print("      It-Person running past: ");
        String who_wins = null;
        int rotations_made = 0;
        for (int i = 0; i < it_speed; i++) { //iterates over the random genrated speed to determine how many times the loop runs and display the people run past 
            System.out.print(playerNames.getFirstElement() + " ");
            if (playerNames.getFirstElement() == goose_friends[1]) {    // this state is for when the it player runs and reaches the point at which the goose was 
                who_wins = "It";  // since the it player reaches the goose position , the it player is the winner
                break;
            }
            playerNames.rotateCCW();  //direction in which it player runs
            rotations_made++;
        }
        reverseRotation(playerNames, rotations_made, false); // this function is made to reverse the playernames rotation back to default after the winner is found or not
        System.out.println();
        System.out.print("      Goose running past: ");
        for (int i = 0; i < goose_speed; i++) { //iterates over the random genrated speed to determine how many times the loop runs and display the people run past 
            System.out.print(playerNames.getLastElement() + " ");

            //direction in which goose player runs
            if (who_wins != null) {  //this checks if a winner is not determined yet and goes on to check if the goose may run to its original position
                if (playerNames.getLastElement() == goose_friends[0]) {  // this state is for when the goose player runs and reaches the point at which the goose was 
                    who_wins = "Goose";
                    break;
                }
            }
            playerNames.rotateCW();

        }
        System.out.println();
        return who_wins;
    }

    /**
     * This method makes the it player run based on the random generated speed
     * and determines if there is a winner
     *
     * @param playerNames
     * @param goose_speed determines how many players run past
     * @param it_speed determines how many players run past
     * @param it_player
     * @param goose_player
     * @param goose_friends
     * @return
     */
    public static String goose_running_first(DCircLinkList<String> playerNames,
            int goose_speed, int it_speed,
            String it_player, String goose_player, String[] goose_friends) {
        System.out.print("      Goose running past: ");
        String who_wins = null;
        int rotations_made = 0;
        for (int i = 0; i < goose_speed; i++) { //iterates over the random genrated speed to determine how many times the loop runs and display the people run past
            System.out.print(playerNames.getLastElement() + " ");
            if (playerNames.getLastElement() == goose_friends[0]) {  // this state is for when the goose player runs and reaches the point at which the goose was 
                who_wins = "Goose";
                break;
            }
            playerNames.rotateCW();  //direction in which goose player runs
            rotations_made++;
        }
        reverseRotation(playerNames, rotations_made, true); // this function is made to reverse the playernames rotation back to default after the winner is found or not

        System.out.println();
        System.out.print("      It-Person running past: ");
        for (int i = 0; i < it_speed; i++) {
            System.out.print(playerNames.getFirstElement() + " ");

            if (who_wins != null) {
                if (playerNames.getFirstElement() == goose_friends[1]) {
                    who_wins = "It";
                }
            }
            playerNames.rotateCCW();  //direction in which it player runs

        }
        System.out.println();
        return who_wins;
    }

    /**
     *
     * this method reverses the players back to the original order
     *
     * @param playerNames
     * @param rotations_made counts number of rotations the player who goes
     * first makes
     * @param rotatedClockwise
     */
    public static void reverseRotation(DCircLinkList<String> playerNames, int rotations_made,
            boolean rotatedClockwise) {
        for (int i = 0; i < rotations_made; i++) { //takes in the number of rotations and does the reverse those number of times 
            if (rotatedClockwise) { // for clockwise the player names would be reverse counter clockwise to return them to normal order and vice vers
                playerNames.rotateCCW();
            } else {
                playerNames.rotateCW();
            }
        }
    }

    public static int print_randline() {
        Random rand = new Random();
        int generated_num = rand.nextInt(1, 21);  // generates a random number between 1 - 20
        System.out.println("Random number generated is: " + generated_num);
        return generated_num;
    }

}
