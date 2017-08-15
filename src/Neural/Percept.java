package Neural;

public class Percept {
	private double x;
	private double y;
	private double bias;
	
	public Percept(double x0, double y0, double bias0){
		x = x0;
		y = y0;
		bias = bias0;
	}
	
	public int process(double x0, double y0){
		if ((x*x0) + (y*y0) + bias > 0){
			return 1;
		}
		return 0;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getBias(){
		return bias;
	}
	
	public void adjust(double x0, double y0, double c){
		if(process(x0, y0) == 1 && y0 > 2*x0 + 1){
			x = x - c*x0;
			y = y - c*y0;
		}
		else if(process(x0, y0) == 0 && y0 < 2*x0 + 1){
			x = x + c*x0;
			y = y + c*y0;
		}	
	}
}