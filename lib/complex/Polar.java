package Lib.complex;

public class Polar extends ComplexNumber{
	private double rhoNumber, thetaNumber;
	
	public Polar(double rho, double theta){
		setType("Polar");
		this.rhoNumber = rho;
		this.thetaNumber = theta;
	}
	
	public void setRhoNumber(double rho){
		this.rhoNumber = rho;
	}
	public void setThetaNumber(double theta){
		this.thetaNumber = theta;
	}
	
	public double getRhoNumber(){
		return this.rhoNumber;
	}
	
	public double getThetaNumber(){
		return this.thetaNumber;
	}
	
	public void print(){
		if(this.rhoNumber == 0){
			System.out.print("0");
		}
		else if(this.thetaNumber == 0){
			System.out.print((Math.round(this.rhoNumber * 100)/100.0));
		}
		else{
			System.out.print((Math.round(this.rhoNumber * 100)/100.0) + "e^(");
			System.out.print(this.thetaNumber < 0 ? "-" : "");
			double thetaRound = Math.abs(Math.round(this.thetaNumber * 100)/100.0);
			boolean checkOne = thetaRound == 1;
			System.out.print(checkOne ? "i)" : (thetaRound + "i)"));
		}
	}
	
	public void println(){
		this.print();
		System.out.println();
	}
}