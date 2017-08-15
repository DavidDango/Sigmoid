package Testing;

import Neural.Percept;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 

public class AdjustPerceptron extends ApplicationFrame {
	
	private static final long serialVersionUID = 1L;

	public AdjustPerceptron(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createScatterPlot(chartTitle ,"X axis" ,"Y axis" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);
		ChartPanel chartPanel = new ChartPanel( xylineChart );
	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    setContentPane( chartPanel ); 
	}
	private XYDataset createDataset() {
		Percept p = new Percept(0, 4, 1);
		double[] randomNumberi = new double[4000];
		for(int i = 0; i < randomNumberi.length; i++) {
			randomNumberi[i] = (double) ((Math.random()*100) - 50);
		}
		double[] randomNumberj = new double[4000];
		for(int i = 0; i < randomNumberj.length; i++) {
			randomNumberj[i] = (double) ((Math.random()*100) - 50);
		}
		for(int i = 0; i < randomNumberi.length; i++){
			p.adjust(randomNumberi[i], randomNumberj[i], 0.01);
		}
		System.out.println();
		System.out.println("X is " + p.getX());
		System.out.println("Y is " + p.getY());
		System.out.println("Bias is " + p.getBias());
		final XYSeries upper = new XYSeries( "Upper" );
		final XYSeries lower = new XYSeries( "Lower" ); 
		for(int i = 0; i < randomNumberi.length; i++) {
			double x = (double) ((Math.random()*100) - 50);
			double y = (double) ((Math.random()*100) - 50);
			if (p.process(x, y) < 1){
				upper.add(x, y);
			}
			else{
				lower.add(x, y);
			}
		}
		final XYSeriesCollection dataset = new XYSeriesCollection( ); 
		dataset.addSeries(upper);
		dataset.addSeries(lower);
		return dataset;
	}
	
	public static void main( String[ ] args ) {
	   AdjustPerceptron chart = new AdjustPerceptron("Leraning Perceptron", "Location of the line according to the adjusted perceptron");
	   chart.pack( );          
	   RefineryUtilities.centerFrameOnScreen( chart );          
	   chart.setVisible( true ); 
	}
}
