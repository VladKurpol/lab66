package lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Controller {

    JSONArray jsonArray;
    Jokes jokes;
    ObservableList<Joke> Jokes;

    @FXML
    private TableView<Joke> table;

    @FXML
    private TableColumn<Joke, Long> id;

    @FXML
    private TableColumn<Joke, String> type;

    @FXML
    private TableColumn<Joke, String> setup;

    @FXML
    private TableColumn<Joke, String> punchline;


    public void read(ActionEvent actionEvent) {
        JasonGet jasonGet = new JasonGet();
        JasonGet.url = "https://official-joke-api.appspot.com/random_ten";
        jasonGet.run();

        System.out.println("Waiting for data...");
        String jsonString = jasonGet.jsonIn;
        System.out.println(jsonString);

        // Считываем json
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println();

        jsonArray = (JSONArray) obj;
        System.out.println(jsonArray.toJSONString());
        System.out.println();
    }

    public void display(ActionEvent actionEvent) {


        jokes = new Jokes();

        for (Object jsonObject : jsonArray)
        {
            JSONObject object = (JSONObject) jsonObject;
            Joke joke = new Joke((Long)object.get("id"), (String) object.get("type"),(String) object.get("setup"),(String) object.get("punchline"));
            jokes.add(joke);
        }

        System.out.println("Imported data after parsing:\n" + jokes);
        WriteArray();
    }

    public void WriteArray(){

        Jokes = FXCollections.observableArrayList(jokes.getJokes());

        id.setCellValueFactory(new PropertyValueFactory<Joke, Long>("id"));
        type.setCellValueFactory(new PropertyValueFactory<Joke, String>("type"));
        setup.setCellValueFactory(new PropertyValueFactory<Joke, String>("setup"));
        punchline.setCellValueFactory(new PropertyValueFactory<Joke, String>("punchline"));

        table.setItems(Jokes);
    }

    public void sort(ActionEvent actionEvent) {
        jokes.getJokes().sort(Joke.byIdAsc);
        System.out.println("After sorting by ID ascending:\n" + jokes);
        WriteArray();
    }

    public void remove(ActionEvent actionEvent) {
        jokes.getJokes().clear();
        Jokes.clear();
        table.refresh();
    }
}