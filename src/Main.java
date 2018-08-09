import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AIWordPlay computer = new AIWordPlay(4);
        String input = new String();
        String guessWord = new String();
        String score = new String();
        Scanner in = new Scanner(System.in);
        System.out.println("Please select your Secret Word...");
        /*input = in.nextLine();
        UserWordPlay user = new UserWordPlay(input);*/
        System.out.println("Computer : Enter you guess...");
        input= in.nextLine();
        while(!computer.isCorrectGuess(input)){
            System.out.println(computer.getScore(input));
            guessWord = computer.selectRandomWord();
            System.out.println("COMPUTER : My guess is " + guessWord);
            score = in.nextLine();
            if(score.equalsIgnoreCase("correct")) {
                System.out.println("COMPUTER WINS!!!");
                break;
            }
            computer.updateGuessList(guessWord,Integer.parseInt(score));
            System.out.println("COMPUTER : Enter you guess...");
            input= in.nextLine();
        }
        System.out.println("Congrats the game is over!!!");


    }
}
