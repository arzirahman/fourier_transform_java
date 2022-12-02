package Lib.fourier;
import Lib.complex.*;

public class Fourier{
	private ComplexUtil Complex = new ComplexUtil();

	public Fourier(){}
	
	public ComplexNumber[] fft(double[] arr){
		int N = arr.length;
		ComplexNumber[] result = new ComplexNumber[N];
		for(int i = 0; i < N; i++){
			ComplexNumber sum = new Polar(0,0);
			for(int j = 0; j < N; j++){
				double theta = (-360)*(i)*(j)/((double)N);
				sum = Complex.add(sum, new Polar(arr[j], theta));
			}
			result[i] = sum;
		}
		return result;
	}
	
	public double[] invFft(ComplexNumber[] arr){
		int N = arr.length;
		double[] result = new double[N];
		for(int i = 0; i < N; i++){
			ComplexNumber sum = new Polar(0,0);
			for(int j = 0; j < N; j++){
				double theta = (360)*(i)*(j)/((double)N);
				ComplexNumber temp = Complex.multiply(Complex.convert(arr[j], "Polar"), new Polar(1, theta));
				sum = Complex.add(sum, temp);
			}
			result[i] = Math.round(((Polar)sum).getRhoNumber() / (double)N);
		}
		return result;
	}
	
	public double[] abs(ComplexNumber[] arr){
		double[] result = new double[arr.length];
		for(int i = 0; i < arr.length; i++){
			result[i] = ((Polar)Complex.convert(arr[i], "Polar")).getRhoNumber();
		}
		return result;
	}
	
	public void print(ComplexNumber[] arr, String type){
		System.out.print("[");
		for(int i = 0; i < arr.length; i++){
			switch(type){
				case "Cartesian":
					Complex.convert(arr[i], "Cartesian").print();
					break;
				case "Polar":
					Complex.convert(arr[i], "Polar").print();
			}
			System.out.print(i < arr.length - 1 ? ", " : "");
		}
		System.out.print("]");
	}
	
	public void println(ComplexNumber[] arr, String type){
		this.print(arr, type);
		System.out.println();
	}
}