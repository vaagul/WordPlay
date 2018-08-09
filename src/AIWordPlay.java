import java.util.HashMap;
import java.util.Map;

public class AIWordPlay {
    private String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int [ ] primeScore={}; //update the prime sequence here
    int wordCount;
    Map<String,Integer> guessList = new HashMap<>();
    String secretWord= new String();

    AIWordPlay(int difficulty){
        this.wordCount=difficulty;
    }

    public int getPrimeProduct(String word){
        return 0;
    }

    public Map<String,Integer> getGuessList(){
        //code for retrieving from sowpods and updating
        return new HashMap<>();
    }

    public void selectSecretList(){
        //random word to be selected after the first guesslist is generated and stored in the string secret word i.e., this.secretword
    }

    public void updateGuessList(String word,int score){
        // main part of the program
    }

    public int getScore(String word){
        //to return the common letters
        return 0;
    }



}
