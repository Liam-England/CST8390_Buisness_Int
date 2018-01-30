
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.GaussianRandomGenerator;

public class Dashboard extends Application {
	
	/********General Members***********/
		VBox vbox;
		static HBox hbox;
		static Button go;
		static Label Lbl;
		static TextField meanText;
		static TextField meanText2;
		static TextField stDevText;
		static TextField stDevText2;
		static TextField genText;
		static double mean;
		static double stDev;
		static double gen;
		static double sum;
	
		static ArrayList<Double> genHolder;
		static NormalDistribution nDist;
		static ListView<Double>genLV;
		static GaussianRandomGenerator grg;
		
	@Override public void start(Stage stage) {
		
		vbox = new VBox();
		
		vbox.getChildren().addAll(getGo(), meanLine(), stDevLine(), genLine());
		Scene scene = new Scene(vbox, 600, 200);
		stage.setScene(scene);
		stage.setTitle("Lab 2");
		stage.show();
		
	}
	
	
	public static Button getGo() {
		go = new Button("Go!");
		go.setPrefWidth(Double.MAX_VALUE);
		
		go.setOnAction((ActionEvent e) -> getResults());
		
		return go;
		
	}
	
	private static void getResults() {
		mean = Double.parseDouble(meanText.getText());
		stDev = Double.parseDouble(stDevText.getText());
		
		gen = Double.parseDouble(genText.getText());
		
		genHolder = new ArrayList<Double>();
		nDist = new NormalDistribution(mean, stDev);
		
		
		for(int i = 0; i <= gen; i++) {
			genHolder.add(nDist.sample());
		}
		
		Collections.sort(genHolder);
		double max = genHolder.get(genHolder.size() -1 );
		double min = genHolder.get(0);
		double median = genHolder.get((genHolder.size() / 2));
		double sd = Math.sqrt( differenceOfSquares( genHolder, mean) / genHolder.size() ); 
		double nMean = calcMean(genHolder);
		
		//Second stage taken from example ~
		Stage newStage = new Stage();
		
		VBox nV = new VBox();
		
		//ObservableList<Double> placeholder = FXCollections.observableArrayList(genHolder);
		genLV = new ListView<>();
		genLV.getItems().addAll(genHolder);
		
		TextField tf = new TextField();
		tf.setText(String.format("Mean: %f  Max: %f  Min:%f  SD:%f Median:%f", nMean, max, min, sd, median));
		
		nV.getChildren().addAll(tf, genLV);
		
		Scene newScene = new Scene(nV, 500, 600);
		newStage.setScene(newScene);
		newStage.show();
	}


	public static HBox meanLine() {
		hbox = new HBox();
		Lbl = new Label("Enter Mean: ");
		Lbl.setPrefWidth(110);
		meanText = new TextField("Null Hypothesis");
		meanText2 = new TextField("Alternative");
		
		hbox.getChildren().addAll(Lbl, meanText);
		
		return hbox;	
	}
	
	public static HBox stDevLine() {
		hbox = new HBox();
		Lbl = new Label("Enter Deviation: ");
		stDevText = new TextField("Null Hypothesis");
		stDevText2 = new TextField("Alternative");
		
		hbox.getChildren().addAll(Lbl, stDevText);
		
		return hbox;
	}
	
	public static HBox genLine() {
		hbox = new HBox();
		Lbl = new Label("Enter N: ");
		Lbl.setPrefWidth(110);
		genText = new TextField("100");
		
		hbox.getChildren().addAll(Lbl, genText);
		
		return hbox;
	}
	
	static double differenceOfSquares( ArrayList<Double> list , double mean) {
		sum = 0;
		for(double db : list)
		sum += ((db - mean)*(db - mean) );
		return sum;

	}
	static double calcMean(ArrayList<Double> list) {
		 sum = 0;
		for (int i = 0; i < list.size(); i++) {
	        sum += list.get(i);
	    }
	    return sum / list.size();
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
