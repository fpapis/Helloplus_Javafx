package fr.ul.miage.papis.florent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloCSV extends Application {
	
	private static final Logger Log = Logger.getLogger(HelloCSV.class.getName());

	Button btn = new Button("Lecture du fichier CSV");
	TextArea ta = new TextArea();
	static FileReader file = null;
	BufferedReader tampon = null;

	@Override
	public void start(Stage primaryStage) {
		//Button btn = new Button("Lecture du fichier CSV");
		//TextArea ta = new TextArea();
		//btn.setOnAction((e)->{ta.appendText("La MIAGE, c'est vraiment trop bien ! \n");;});
		btn.setOnAction(this::handleButtonAction);
		
		BorderPane root = new BorderPane();
		root.setBottom(btn);
		BorderPane.setAlignment(btn, Pos.BOTTOM_CENTER);
		root.setCenter(ta);
		Scene scene = new Scene(root, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Application de l'exercice 4");
		primaryStage.show();
	}
	
	//action realisee si clic sur bouton
	private void handleButtonAction(ActionEvent event) {
	    //ta.appendText("La MIAGE, c'est vraiment trop bien ! \n");
		
		
		try {
			file = new FileReader("samples\\exemple.csv");
			CSVParser p = HelloCSV.buildCSVParser();
			for(CSVRecord r : p) {
				String nom = r.get(0);
				String prenom = r.get(1);
				//System.out.println("Hello " +nom+" "+prenom+" !");
				ta.appendText("Hello " +nom+" "+prenom+" ! \n");
			}
		}
		catch (IOException e) {
			Log.severe("Erreur de lecture dans le ficier CSV");
		}
	}
	
	public static CSVParser buildCSVParser() throws IOException{
		CSVParser res = null;
		CSVFormat csvf = CSVFormat.DEFAULT.withCommentMarker('#').withDelimiter(';');
		res = new CSVParser(file, csvf);
		return res;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
