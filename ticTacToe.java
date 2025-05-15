//import the scanner function 
import java.util.Scanner;

//class of the game
public class ticTacToe{
    //global variables with the first player, second player and what player's playing in the moment
    private static String playerOne, playerTwo;
    
    //array with the positions of the game
    private static String positions[][] = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
        };
    //instance a Scanner 
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) { //main method of the class
        String playerTime; //string with the player that is playing in the moment 
        char playAgain; //character that receive if the game will start again

        do { //do..while that starts the game
            //for loop with 2 local variables (i and j) that resets positions (if the game was reseted)
            for(int i = 0, j = 0; i < positions.length; i++){
                //loop while that check if each row of positions was checked
                while(j < positions.length){
                    //transform the column in " "
                    positions[i][j] = " ";
                    j++; //increment in j
                } 
                //return j to 0
                j = 0;
            }

            //capture the 1° player's name
            System.out.print("Insert the 1° player's name: ");
            playerOne = scan.next();
            
            //capture the 2° player's name
            System.out.print("Insert the 2° player's name: ");
            playerTwo = scan.next();
            
            //the first player starts playing
            playerTime = playerOne;

            //do..while of the game 
            do {
                //check what player is playing
                if(playerTime == playerOne){ //if is the first player
                    play(playerOne); //calls the method play with the playerOne in the argument

                    playerTime = playerTwo; //switch the playerTime
                } else {//if is the second player
                    play(playerTwo); //calls the method play with the playerOne in the argument

                    playerTime = playerOne;//switch the playerTime
                }

                //check if the game is finished
                if(checkEnd() == "full"){ //if finished  ´cause all parts of positions is in using
                    drawGame(); //draw the game 
                    System.out.println("Full game!"); //says that the game is full
                    break; //restart the game 
                } else if(checkEnd() == "playerOne" || checkEnd() == "playerTwo"){//if some player winned
                    System.out.printf("%s wins!\n", checkEnd()); //show this message
                    break; //stop the do..hwile
                }
            }while(true);
            
            do{
                System.out.print("You want to play again (type 'y' or 'n')?");
                playAgain = scan.next().charAt(0);

                if(playAgain != 'n' && playAgain != 'y'){
                    System.out.println("INSERT 'y' OR 'n'");
                }
            }while(playAgain != 'n' && playAgain != 'y');

            if(playAgain == 'n') {
                System.out.println("Ok, thanks!");
                break;
            }
        }while(true);
    
        scan.close();
    }

    static void drawGame(){
        String aux[] = {"a", "b", "c"};

        for(int i = 0; i < positions.length; i++){
            if(i == 0)System.out.print("  1   2   3\n");

            for(int j = 0; j < positions[i].length; j++){

                if(j == 0) System.out.print(aux[i] + " ");

                System.out.print(positions[i][j]);

                if(j < positions.length - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }

    static void play(String player){
        String choose;
        int posChoosed[] = {0, 0};

        System.out.println("Actual game:");
        drawGame();

        do{
            System.out.printf("\n%s, insert where you'll insert (type the letter and after the number):", player);
            choose = scan.next();

            if(choose.length() != 2){
                System.out.println("Insert a letter and, after, a number!");
                continue;
            } else if(choose.charAt(0) != 'a' && choose.charAt(0) != 'b' && choose.charAt(0) != 'c'){
                System.out.println("Insert an valid letter on first char!");
                continue;
            } else if(choose.charAt(1) != '1' && choose.charAt(1) != '2' && choose.charAt(1) != '3'){
                System.out.println("Insert an valid number on second char!");
                continue;
            } 

            switch (choose.charAt(0)) {
                case 'a':
                    posChoosed[0] = 0;
                    break;
                case 'b':
                    posChoosed[0] = 1;
                    break;
                case 'c':
                    posChoosed[0] = 2;
                    break;
            }
            switch (choose.charAt(1)) {
                case '1':
                    posChoosed[1] = 0;
                    break;
                case '2':
                    posChoosed[1] = 1;
                    break;
                case '3':
                    posChoosed[1] = 2;
                    break;
            }

            if(checkInsertable(posChoosed)){
                if(player == playerOne) {
                    positions[posChoosed[0]][posChoosed[1]] = "X";
                } else {
                    positions[posChoosed[0]][posChoosed[1]] = "O";
                }
                break;   
            } else {
                System.out.println("Insert an valid place!");
            }
        }while(true);
    }

    static boolean checkInsertable(int[] posChoosed){
        if(positions[posChoosed[0]][posChoosed[1]] != " "){
            return false;
        } else {
            return true;
        }
    }

    static String checkEnd(){
        if(checkFull()) return "full";
        if(checkWin(playerOne)) return "playerOne";
        if(checkWin(playerTwo)) return "playerTwo";

        return " ";
    }
    
    static boolean checkFull(){
        int aux = 0;
        for (String[] row : positions) {
            for (String column : row) {
                if(column == "X" || column == "O") aux++;
            }
        }

        if(aux == 9) return true;
        return false;
    }

    static boolean checkWin(String player){
        String playerChar = "X";

        if(player == playerTwo) playerChar = "O";

        //horizontal checking
        if((positions[0][0] == playerChar && positions[0][1] == playerChar && positions[0][2] == playerChar) || (positions[1][0] == playerChar && positions[1][1] == playerChar && positions[1][2] == playerChar) || (positions[2][0] == playerChar && positions[2][1] == playerChar && positions[2][2] == playerChar)){
            return true;
        }

        //horizontal checking
        if((positions[0][0] == playerChar && positions[1][0] == playerChar && positions[2][0] == playerChar) || (positions[0][1] == playerChar && positions[1][1] == playerChar && positions[2][1] == playerChar) || (positions[0][2] == playerChar && positions[1][2] == playerChar && positions[2][2] == playerChar)){
            return true;
        }

        //X checking
        if((positions[0][0] == playerChar && positions[1][1] == playerChar && positions[2][2] == playerChar) || (positions[0][2] == playerChar && positions[1][1] == playerChar && positions[2][0] == playerChar)){
            return true;
        }

        return false;
    }
}