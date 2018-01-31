
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.inference.OneWayAnova;

public class Dashboard extends Application {
	
	/********General Members***********/
		VBox vbox;
		static HBox hbox;
		static Button go;
		static Label Lbl;
		static TextField meanTextNull;
		static TextField meanTextAlt;
		static TextField sdTextNull;
		static TextField sdTextAlt;
		static TextField genText;
		
		
		static double meanNull;
		static double meanAlt;
		static double stDevNull;
		static double stDevAlt;
		static double pVal;
		static int gen;
		static double sum;
	
		static ArrayList<double[]> doubleList;
		static double[] dbNull;
		static double[] dbAlt;
		
		static NormalDistribution nDistNull;
		static NormalDistribution nDistAlt;
		
		static OneWayAnova owa;
		
	@Override public void start(Stage stage) {
		
		vbox = new VBox();
		
		vbox.getChildren().addAll(getGo(), meanLine(), stDevLine(), genLine());
		Scene scene = new Scene(vbox, 600, 200);
		stage.setScene(scene);
		stage.setTitle("Lab 3");
		stage.show();
		
	}
	
	
	public static Button getGo() {
		go = new Button("Go!");
		go.setPrefWidth(Double.MAX_VALUE);
		
		go.setOnAction((ActionEvent e) -> getResults());
		return go;
		
	}
	
	private static void getResults() {
		meanNull = Double.parseDouble(meanTextNull.getText());
		stDevNull = Double.parseDouble(sdTextNull.getText());
		
		meanAlt = Double.parseDouble(meanTextAlt.getText());
		stDevAlt = Double.parseDouble(sdTextAlt.getText());
		
		gen = Integer.parseInt(genText.getText());
		
		doubleList = new ArrayList<double[]>();
		
		
		nDistNull = new NormalDistribution(meanNull, stDevNull);
		nDistAlt = new NormalDistribution(meanAlt, stDevAlt);
		
		dbNull = new double[gen];
		dbAlt = new double[gen];
		
		for(int i = gen; i < gen; i++) {
			dbNull[i] = nDistNull.sample();
			dbAlt[i] = nDistAlt.sample();
		}
		
		owa = new OneWayAnova();
		
		doubleList.add(dbNull);
		doubleList.add(dbAlt);

		
		pVal = owa.anovaPValue(doubleList);
		double nMeanNull = calcMean(dbNull);
		double nMeanAlt  = calcMean(dbAlt);
		double sd		 = differenceOfSquares(dbNull, nMeanNull);
		
		
		//Second stage taken from example ~
		Stage newStage = new Stage();
		
		VBox nV = new VBox();
		
				
		TextField tf = new TextField();
		tf.setText(String.format("Mean1: %f  Sigma: %f  Mean2: %f pValue: %f", nMeanNull, sd, nMeanAlt, pVal));
		
		nV.getChildren().addAll(tf, getLineChart());
		
		Scene newScene = new Scene(nV, 500, 600);
		newStage.setScene(newScene);
		newStage.show();
	}


	public static HBox meanLine() {
		hbox = new HBox();
		Lbl = new Label("Enter Mean: ");
		Lbl.setPrefWidth(110);
		meanTextNull = new TextField("10");
		meanTextAlt = new TextField("8");
		
		
		hbox.getChildren().addAll(Lbl, meanTextNull, meanTextAlt);
		
		return hbox;	
	}
	
	public static HBox stDevLine() {
		hbox = new HBox();
		Lbl = new Label("Enter Deviation: ");
		sdTextNull = new TextField("5");
		sdTextAlt = new TextField("5");
		
		hbox.getChildren().addAll(Lbl, sdTextNull, sdTextAlt);
		
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
	
	static double differenceOfSquares( double[] list , double mean) {
		sum = 0;
		for(double db : list)
		sum += ((db - mean)*(db - mean) );
		return sum;

	}
	static double calcMean(double[] db) {
		 sum = 0;
		for (int i = 0; i < db.length; i++) {
	        sum += db.length;
	    }
	    return sum / db.length;
	}
	
	static LineChart getLineChart() {
		
		/********       Set Up LineChart       *********/
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        
        //Create the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Lab 3");
        
        //Define Series
        XYChart.Series seriesN = new XYChart.Series();
        seriesN.setName("Null Entry");
        
        XYChart.Series seriesA = new XYChart.Series();
        seriesA.setName("Alt Entry");
        
        
        fillData(seriesN, nDistNull);
        fillData(seriesA, nDistAlt);
        
        lineChart.getData().addAll(seriesN, seriesA);
        
        
		if (pVal < 0.05) {
			Node line1 = seriesN.getNode().lookup(".chart-series-line");
				line1.setStyle("-fx-stroke: #0000FFFF; -fx-stroke-width: 10px");
			Node line2 = seriesA.getNode().lookup(".chart-series-line");
				line2.setStyle("-fx-stroke: #FF0000B0; -fx-stroke-width: 2px");
		} else {
			Node line1 = seriesN.getNode().lookup(".chart-series-line");
			line1.setStyle("-fx-stroke: #FF00FFB0; -fx-stroke-width: 2px");
			Node line2 = seriesA.getNode().lookup(".chart-series-line");
			line2.setStyle("-fx-stroke: #00FF00FF; -fx-stroke-width: 10px");
		}
		
		return lineChart;
		
	}
	
	public static void fillData(XYChart.Series s, NormalDistribution nd) {
		
		double lower = nd.getMean() - (3 * nd.getStandardDeviation());
        double upper = nd.getMean() + (3 * nd.getStandardDeviation());
       
        for (double i = lower; i < upper; i += (upper - lower) / 30) {
        	s.getData().add(new XYChart.Data(i, nd.density(i)));
        }
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }

}
