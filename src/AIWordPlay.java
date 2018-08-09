import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class AIWordPlay {
    private String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int [ ] primeScore={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    int wordCount;
    Map<String,Integer> guessList = new HashMap<>();
    String secretWord= new String();

    AIWordPlay(int difficulty){
        this.wordCount=difficulty;
        getGuessList();
        this.secretWord=selectSecretWord();
        System.out.println(secretWord);
    }

    public int getPrimeProduct(String word){
        int primeProduct=1;
        word=word.toUpperCase();
        for(int i = 0; i < word.length(); i++){
            primeProduct*=primeScore[alphabets.indexOf(word.charAt(i))];
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

    public void getGuessList(){
        try{
            File file = new File("./sowpods.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            {
                //System.out.println(st);
                if(st.length()==wordCount && uniqueCharacters(st))
                    guessList.put(st,getPrimeProduct(st));
            }
        }
        catch(Exception exception)
        {
            System.out.println("Exception");
        }
        //return guessList;
    }

    public String selectSecretWord(){
        Random generator = new Random();
        Object[] values = guessList.keySet().toArray();
        return (String)values[generator.nextInt(values.length)];
    }

    private int gcd(int a, int b)
    {
        if (a == 0 || b == 0)
            return 0;
        if (a == b)
            return a;
        if (a > b)
            return gcd(a-b, b);
        return gcd(a, b-a);
    }

    public String selectRandomWord(){
        Random generator = new Random();
        Object[] values = guessList.keySet().toArray();
        return (String)values[generator.nextInt(values.length)];
    }

    public int findCommonOccurance(int value,List<Integer> primeList) {
        int count = 0;
        for (int i = 0; i < primeList.size(); i++) {
            if (value % primeList.get(i) == 0) {
                count++;
            }
        }
        return count;
    }

    public void updateGuessList(String word,int score){
        Map<String,Integer> updatedList= new HashMap<>();
        int common;
        List<Integer> primeList = new ArrayList<>();
        List<String> removalList = new ArrayList<>();
        for(int i=0;i<word.length();i++){
            primeList.add(primeScore[alphabets.indexOf(word.charAt(i))]);
        }

        if(score==0) {
            for (Map.Entry<String, Integer> entry : guessList.entrySet()) {
                for (int i = 0; i < primeList.size(); i++) {
                    if (entry.getValue() % primeList.get(i) == 0) {
                        removalList.add(entry.getKey());
                    }
                }
            }
        }
        else if(score == wordCount){
            for (Map.Entry<String, Integer> entry : guessList.entrySet()){
                if(getPrimeProduct(word) != getPrimeProduct(entry.getKey()))
                    removalList.add(entry.getKey());
            }
        }
        else {
            for (Map.Entry<String, Integer> entry : guessList.entrySet()) {
                common = findCommonOccurance(entry.getValue(), primeList);
                if (common < score || getPrimeProduct(word) == getPrimeProduct(entry.getKey())) {
                    removalList.add(entry.getKey());
                }
            }
        }
        for (int i=0;i<removalList.size();i++){
            this.guessList.remove(removalList.get(i));
        }
        System.out.println("size = "+guessList.size());
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
