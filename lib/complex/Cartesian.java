package Lib.complex;

public class Cartesian extends ComplexNumber{
	private double realNumber, imajNumber;
	
	public Cartesian(double real, double imaj){
		setType("Cartesian");
		this.realNumber = real;
		this.imajNumber = imaj;
	}
	
	public void setImajNumber(double imaj){
		this.imajNumber = imaj;
	}
	public void setRealNumber(double real){
		this.realNumber = real;
	}
	
	public double getImajNumber(){
		return this.imajNumber;
	}
	
	public double getRealNumber(){
		return this.realNumber;
	}
	
	public void print(){
		double realRound = Math.round(this.realNumber * 100)/100.0;
		double imajRound = Math.abs(Math.round(this.imajNumber * 100)/100.0);
		boolean checkOne = imajRound == 1;
		if(realRound == 0 && imajRound == 0){
			System.out.print("0");
		}
		else if(imajRound == 0){
			System.out.print(realRound);
		}
		else if(realRound == 0){
			System.out.print(this.imajNumber < 0 ? "-" : "");
			System.out.print(checkOne ? "i" : (imajRound + "i"));
		}
		else{
			System.out.print(realRound + " ");
			System.out.print(this.imajNumber < 0 ? "- " : "+ ");
			System.out.print(checkOne ? "i" : (imajRound + "i"));
		}
	}
	
	public void println(){
		this.print();
		System.out.println();
	}
}