

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class Dashboard extends Application{
	
	/******* General Members **********/
	GridPane root;
	ObservableList<PieChart.Data> pieChartData;
	PieChart chart;
	
	
	
	@Override public void start(Stage stage) {
        
		root = new GridPane();
    	root.add( getPieChart(), 0, 0);
    	root.add( getBubbleChart(), 1, 0);
    	root.add( getScatterChart(), 0, 1);
    	root.add( getLineChart(), 1, 1);
    	Scene scene = new Scene(root, 1024, 768);
    	stage.setScene(scene);
    	stage.setTitle("Business Int. Lab1");
    	stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public PieChart getPieChart() {
    	 ObservableList<PieChart.Data> pieChartData =
                 FXCollections.observableArrayList(
                 new PieChart.Data("Grapefruit", 13),
                 new PieChart.Data("Oranges", 25),
                 new PieChart.Data("Plums", 10),
                 new PieChart.Data("Pears", 22),
                 new PieChart.Data("Apples", 30),
    	 		 new PieChart.Data("Watermelons", 17));
         chart = new PieChart(pieChartData);
         chart.setTitle("Imported Fruits");
         chart.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
         
         return chart;
    	
    }
    
    public BubbleChart<Number, Number> getBubbleChart() {
    	
    	 final NumberAxis xAxis = new NumberAxis(1, 53, 4);
         final NumberAxis yAxis = new NumberAxis(0, 80, 10);
         final BubbleChart<Number,Number> blc = new
             BubbleChart<>(xAxis,yAxis);
         xAxis.setLabel("Week");
         yAxis.setLabel("Product Budget");
         blc.setTitle("Budget Monitoring");
         blc.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
         XYChart.Series series1 = new XYChart.Series();
         series1.setName("Product 1");
         series1.getData().add(new XYChart.Data(3, 35));
         series1.getData().add(new XYChart.Data(12, 60));
         series1.getData().add(new XYChart.Data(15, 15));
         series1.getData().add(new XYChart.Data(22, 30));
         series1.getData().add(new XYChart.Data(28, 20));
         series1.getData().add(new XYChart.Data(35, 41));
         series1.getData().add(new XYChart.Data(42, 17));
         series1.getData().add(new XYChart.Data(49, 30));
                 
         XYChart.Series series2 = new XYChart.Series();
         series2.setName("Product 2");
         series2.getData().add(new XYChart.Data(8, 15));
         series2.getData().add(new XYChart.Data(13, 23));
         series2.getData().add(new XYChart.Data(15, 45));
         series2.getData().add(new XYChart.Data(24, 30));
         series2.getData().add(new XYChart.Data(38, 78));
         series2.getData().add(new XYChart.Data(40, 41));
         series2.getData().add(new XYChart.Data(45, 57));
         series2.getData().add(new XYChart.Data(47, 23));
         
         XYChart.Series series3 = new XYChart.Series();
         series3.setName("Product 3");
         series3.getData().add(new XYChart.Data(9, 13));
         series3.getData().add(new XYChart.Data(11, 15));
         series3.getData().add(new XYChart.Data(10, 38));
         series3.getData().add(new XYChart.Data(18, 37));
         series3.getData().add(new XYChart.Data(43, 78));
         series3.getData().add(new XYChart.Data(42, 13));
         series3.getData().add(new XYChart.Data(44, 12));
         series3.getData().add(new XYChart.Data(53, 19));
                       
         
         blc.getData().addAll(series1, series2, series3);
         
         return blc;
    }
    
    public ScatterChart<Number, Number> getScatterChart() {
    	final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<>(xAxis,yAxis);
        xAxis.setLabel("Age (years)");                
        yAxis.setLabel("Returns to date");
        sc.setTitle("Investment Overview");
       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Equities");
        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.8, 33.6));
        series1.getData().add(new XYChart.Data(6.2, 24.8));
        series1.getData().add(new XYChart.Data(1, 14));
        series1.getData().add(new XYChart.Data(1.2, 26.4));
        series1.getData().add(new XYChart.Data(4.4, 114.4));
        series1.getData().add(new XYChart.Data(8.5, 323));
        series1.getData().add(new XYChart.Data(6.9, 289.8));
        series1.getData().add(new XYChart.Data(9.9, 287.1));
        series1.getData().add(new XYChart.Data(0.9, -9));
        series1.getData().add(new XYChart.Data(3.2, 150.8));
        series1.getData().add(new XYChart.Data(4.8, 20.8));
        series1.getData().add(new XYChart.Data(7.3, -42.3));
        series1.getData().add(new XYChart.Data(1.8, 81.4));
        series1.getData().add(new XYChart.Data(7.3, 110.3));
        series1.getData().add(new XYChart.Data(2.7, 41.2));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Mutual funds");
        series2.getData().add(new XYChart.Data(5.2, 229.2));
        series2.getData().add(new XYChart.Data(2.4, 37.6));
        series2.getData().add(new XYChart.Data(3.2, 49.8));
        series2.getData().add(new XYChart.Data(1.8, 134));
        series2.getData().add(new XYChart.Data(3.2, 236.2));
        series2.getData().add(new XYChart.Data(7.4, 114.1));
        series2.getData().add(new XYChart.Data(3.5, 323));
        series2.getData().add(new XYChart.Data(9.3, 29.9));
        series2.getData().add(new XYChart.Data(8.1, 287.4));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Piggy Bank");
        series3.getData().add(new XYChart.Data(2.0, 13.6));
        series3.getData().add(new XYChart.Data(3.7, 28.8));
        series3.getData().add(new XYChart.Data(2.3, 33.5));
        series3.getData().add(new XYChart.Data(9.3, 137.5));
        series3.getData().add(new XYChart.Data(6.7, 98.25));
        series3.getData().add(new XYChart.Data(5.6, 143.2));
        series3.getData().add(new XYChart.Data(1.3, 188.3));
        series3.getData().add(new XYChart.Data(4.9, 288.3));
 
        sc.getData().addAll(series1, series2, series3);
        sc.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return sc;
    	
    }
    
    public LineChart<Number, Number> getLineChart() {
    	//defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        XYChart.Series newSeries = new XYChart.Series();
        
        	newSeries.setName("Second Portfolio");
        	
        	  newSeries.getData().add(new XYChart.Data(1, 19));
              newSeries.getData().add(new XYChart.Data(2, 14));
              newSeries.getData().add(new XYChart.Data(3, 17));
              newSeries.getData().add(new XYChart.Data(4, 23));
              newSeries.getData().add(new XYChart.Data(5, 32));
              newSeries.getData().add(new XYChart.Data(6, 36));
              newSeries.getData().add(new XYChart.Data(7, 29));
              newSeries.getData().add(new XYChart.Data(8, 47));
              newSeries.getData().add(new XYChart.Data(9, 49));
              newSeries.getData().add(new XYChart.Data(10, 23));
              newSeries.getData().add(new XYChart.Data(11, 28));
              newSeries.getData().add(new XYChart.Data(12, 47));
              
        	
        lineChart.getData().add(series);
        lineChart.getData().add(newSeries);
        lineChart.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    	
        return lineChart;
        
    }
}
