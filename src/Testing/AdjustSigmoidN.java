package Testing;

import Neural.SigmoidN;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 

public class AdjustSigmoidN extends ApplicationFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdjustSigmoidN(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createScatterPlot(chartTitle ,"X axis" ,"Y axis" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);
		ChartPanel chartPanel = new ChartPanel( xylineChart );
	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    
	    setContentPane( chartPanel ); 
	}
	private XYDataset createDataset() {
		double[] x0 = new double[2];
		x0[0] = 0;
		x0[1] = 8;
 		SigmoidN p = new SigmoidN(x0, 1);
		double[] randomNumberi = new double[4000];
		for(int i = 0; i < randomNumberi.length; i++) {
			randomNumberi[i] = (double) ((Math.random()*10) - 5);
		}
		double[] randomNumberj = new double[4000];
		for(int i = 0; i < randomNumberj.length; i++) {
			randomNumberj[i] = (double) ((Math.random()*10) - 5);
		}
		for(int i = 0; i < randomNumberi.length; i++){
			double[] temp = new double[2];
			temp[0] = randomNumberi[i];
			temp[1] = randomNumberj[i];
			p.adjust(temp, 0.01);
		}
		System.out.println();
		System.out.println("X is " + p.getX());
		System.out.println("Y is " + p.getY());
		System.out.println("Bias is " + p.getBias());
		final XYSeries upper = new XYSeries( "Upper" );
		final XYSeries lower = new XYSeries( "Lower" );
		for(int i = 0; i < randomNumberi.length; i++) {
			double[] temp = new double[2];
			temp[0] = randomNumberi[i];
			temp[1] = randomNumberj[i];
			if (p.process_t(temp) < 1){
				upper.add(randomNumberi[i], randomNumberj[i]);
			}
			else{
				lower.add(randomNumberi[i], randomNumberj[i]);
			}
		}
		final XYSeriesCollection dataset = new XYSeriesCollection( ); 
		dataset.addSeries(upper);
		dataset.addSeries(lower);
		return dataset;
	}
	
	public static void main( String[ ] args ) {
	   AdjustSigmoidN chart = new AdjustSigmoidN("Leraning Sigmoid Neuron", "Location of the line according to the adjusted neuron	");
	   chart.pack( );          
	   RefineryUtilities.centerFrameOnScreen( chart );          
	   chart.setVisible( true ); 
	}
}