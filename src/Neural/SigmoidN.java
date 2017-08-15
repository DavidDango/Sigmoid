package Neural;

public class SigmoidN {
	private double[] w;
	private double bias;
	private double threshold;
	
	public SigmoidN(double[] w0, double b, double t){
		w = w0;
		bias = b;
		threshold = t;
	}
	
	public SigmoidN(double[] w0, double b){
		this(w0, b, 0.5);
	}
	
	public double process(double[] x){
		if(x.length == w.length){
			double temp = 0;
			double aux = 0;
			for(int i = 0; i < x.length; i++){
				temp = w[i]*x[i];
				aux = aux + temp;
			}
			aux = aux + bias;
			aux = aux*(-1);
			aux = Math.exp(aux);
			aux = aux + 1;
			aux = 1/aux;
			return aux;
		}
		return -1;
	}
	
	public int process_t(double[] x){
		if(x.length == w.length){
			if(process(x) > threshold){
				return 1;
			}
			return 0;
		}
		return -1;
	}

	public void adjust(double[] x, double c){
		if(x.length == w.length){
			if(process_t(x) == 1 && x[1] > 2*x[0] + 1){
				w[0] = w[0] - c*x[0];
				w[1] = w[1] - c*x[1];
			}
			else if(process_t(x) == 0 && x[1] < 2*x[0] + 1){
				w[0] = w[0] + c*x[0];
				w[1] = w[1] + c*x[1];
			}
		}
	}

	public double getX() {
		return w[0];
	}

	public double getY() {
		return w[1];
	}

	public double getBias() {
		return bias;
	}
}
