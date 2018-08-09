import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class AIWordPlay {
    private String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int [ ] primeScore={}; //update the prime sequence here
    int wordCount;
    Map<String,Integer> guessList = new HashMap<>();
    String secretWord= new String();

    AIWordPlay(int difficulty){
        this.wordCount=difficulty;
        this.guessList=getGuessList();
        this.secretWord=selectSecretWord();
    }

    public int getPrimeProduct(String word){
        int primeProduct=1;

        for(int i = 0; i < word.length(); i++){
            word=word.toUpperCase();
            primeProduct*=primeScore[alphabets.indexOf(word.charAt(i))];
            System.out.println(primeProduct);
        }

        return primeProduct;
    }

    boolean uniqueCharacters(String str) {
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j < str.length(); j++) {
                if(str.charAt(i)== str.charAt(j) ){
                    return false;
                }
            }
        }
        return true;
    }

    public Map<String,Integer> getGuessList(){
        try{
            File file = new File("/sowpods.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            {
                System.out.println(st);
                if(st.length()==wordCount && uniqueCharacters(st))
                    guessList.put(st,getPrimeProduct(st));
            }
        }
        catch(Exception exception)
        {
            System.out.println("Exception");
        }
        return guessList;
    }

    public String selectSecretWord(){
        Object[] words = guessList.keySet().toArray();
        Object key = words[new Random().nextInt(words.length)];
        return key.toString();
    }

    public String selectRandomWord(){
        Object[] words = guessList.keySet().toArray();
        Object key = words[new Random().nextInt(words.length)];
        return key.toString();
    }

    public void updateGuessList(String word,int score){
        // main part of the program
    }

    public int getScore(String word){
        Set<Character> characters1 = new TreeSet<Character>();
        for(int i = 0; i < this.secretWord.length(); i++) {
            characters1.add(this.secretWord.charAt(i));
        }
        Set<Character> characters2 = new TreeSet<Character>();
        for(int i = 0; i < word.length(); i++) {
            characters2.add(word.charAt(i));
        }
        characters1.retainAll(characters2);
        return characters1.size();
    }

    public boolean isCorrectGuess(String word){
        return this.secretWord.equalsIgnoreCase(word);
    }


}
