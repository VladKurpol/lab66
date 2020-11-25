package lab6;

import java.io.Serializable;
import java.util.ArrayList;


public class Jokes implements Serializable {
    private ArrayList<Joke> jokes;

    public Jokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public Jokes(){
        jokes = new ArrayList<>();
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public void add(Joke joke) {
        this.jokes.add(joke);
    }

    @Override
    public String toString(){
        //return "Jokes:\n" + jokes;
        String result = "";
        for (Joke j : jokes) {
            result += j + System.lineSeparator();
        }
        return result;
    }

}
