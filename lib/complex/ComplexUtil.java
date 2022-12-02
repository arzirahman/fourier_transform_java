package Lib.complex;

public class ComplexUtil{
	public ComplexUtil(){}
	
	public ComplexNumber add(ComplexNumber num1, ComplexNumber... num2){
		return this.multipleComplexOperation("add", num1, num2);
	}
	
	public ComplexNumber substract(ComplexNumber num1, ComplexNumber... num2){
		return this.multipleComplexOperation("sub", num1, num2);
	}
	
	public ComplexNumber multiply(ComplexNumber num1, ComplexNumber... num2){
		return this.multipleComplexOperation("mul", num1, num2);
	}
	
	public ComplexNumber divide(ComplexNumber num1, ComplexNumber... num2){
		return this.multipleComplexOperation("div", num1, num2);
	}
	
	public double abs(ComplexNumber num){
		double absolute = 0;
		switch(num.getType()){
			case "Cartesian":
				absolute = ((Polar)this.convert(num, "Polar")).getRhoNumber();
				break;
			case "Polar":
				absolute = ((Polar)num).getRhoNumber();
				break;
		}
		return absolute;
	}
	
	public ComplexNumber power(ComplexNumber num, int pow){
		ComplexNumber result = new Cartesian(0,0);
		switch(num.getType()){
			case "Cartesian":
				result = this.powCartesian(num, pow);
				break;
			case "Polar":
				num = this.powCartesian(this.convert(num, "Cartesian"), pow);
				result = this.convert(num, "Polar");
		}
		return result;
	}
	
	public ComplexNumber convert(ComplexNumber num, String type){
		ComplexNumber result = new Cartesian(0,0);
		switch(type){
			case "Cartesian":
				if(num.getType() == "Polar"){
					double real = ((Polar)num).getRhoNumber() * Math.cos(((Polar)num).getThetaNumber()*Math.PI/180);
					double imaj = ((Polar)num).getRhoNumber() * Math.sin(((Polar)num).getThetaNumber()*Math.PI/180);
					result = new Cartesian(real, imaj);
				}
				else{
					result = num;
				}
				break;
			case "Polar":
				if(num.getType() == "Cartesian"){
					double rho = Math.sqrt(Math.pow(((Cartesian)num).getRealNumber(),2) + Math.pow(((Cartesian)num).getImajNumber(),2));
					double theta = this.calculateTheta(((Cartesian)num).getRealNumber(), ((Cartesian)num).getImajNumber());
					result = new Polar(rho, theta);
				}
				else{
					result = num;
				}
				break;
		}
		return result;
	}
	
	
	
	private ComplexNumber multipleComplexOperation(String type, ComplexNumber num1, ComplexNumber... num2){
		ComplexNumber result = new Cartesian(0,0);
		if(num2.length > 1){
			ComplexNumber sum = this.convert(num1, num1.getType());
			for(ComplexNumber i : num2){
				sum = this.complexOperation(type, sum, i);
			}
			result = sum;
		}
		else{
			result = this.complexOperation(type, num1, num2[0]);
		}
		return result;
	}
	
	private ComplexNumber complexOperation(String type, ComplexNumber num1, ComplexNumber num2){
		ComplexNumber result = new Cartesian(0, 0);
		if(num1.getType() == num2.getType()){
			if(num1.getType() == "Cartesian"){
				switch(type){
					case "add": result = this.addCartesian(num1, num2, 1); break;
					case "sub": result = this.addCartesian(num1, num2, -1); break;
					case "mul": result = this.mulCartesian(num1, num2); break;
					case "div": result = this.divCartesian(num1, num2); break;
				}
			}
			else if(num1.getType() == "Polar"){
				switch(type){
					case "add": result = this.addPolar(num1, num2, 1); break;
					case "sub": result = this.addPolar(num1, num2, -1); break;
					case "mul": result = this.mulPolar(num1, num2); break;
					case "div": result = this.divPolar(num1, num2); break;
				}
			}
		}
		else{
			System.out.println("Different Data Type!!!");
			System.exit(0);
		}
		return result;
	}
	
	private ComplexNumber powCartesian(ComplexNumber num, int pow){
		ComplexNumber result = new Cartesian(1,0);
		if(pow == 1){
			result = num;
		}
		else if(pow > 1){
			for(int i = 0; i < pow; i++){
				result = this.multiply(result, num);
			}
		}
		else{
			System.out.println("Power Number Can't Be Negative");
			System.exit(0);
		}
		return result;
	}
	
	private ComplexNumber addCartesian(ComplexNumber num1, ComplexNumber num2, int operator){
		ComplexNumber result;
		double real = ((Cartesian)num1).getRealNumber() + ((Cartesian)num2).getRealNumber() * operator;
		double imaj = ((Cartesian)num1).getImajNumber() + ((Cartesian)num2).getImajNumber() * operator;
		result = new Cartesian(real, imaj);
		return result;
	}
	
	private ComplexNumber mulCartesian(ComplexNumber num1, ComplexNumber num2){
		ComplexNumber result;
		double realNum1 = ((Cartesian)num1).getRealNumber();
		double realNum2 = ((Cartesian)num2).getRealNumber();
		double imajNum1 = ((Cartesian)num1).getImajNumber();
		double imajNum2 = ((Cartesian)num2).getImajNumber();
		double real = realNum1 * realNum2 - imajNum1 * imajNum2;
		double imaj = realNum1 * imajNum2 + realNum2 * imajNum1;
		result = new Cartesian(real, imaj);
		return result;
	}
	
	private ComplexNumber divCartesian(ComplexNumber num1, ComplexNumber num2){
		num1 = this.convert(num1, "Polar");
		num2 = this.convert(num2, "Polar");
		ComplexNumber result = this.divPolar(num1, num2);
		result = this.convert(result, "Cartesian");
		return result;
	}
	
	private ComplexNumber divPolar(ComplexNumber num1, ComplexNumber num2){
		double rho = ((Polar)num1).getRhoNumber() / ((Polar)num2).getRhoNumber();
		double theta = ((Polar)num1).getThetaNumber() - ((Polar)num2).getThetaNumber();
		ComplexNumber result = new Polar(rho, theta);
		return result;
	}
	
	private ComplexNumber mulPolar(ComplexNumber num1, ComplexNumber num2){
		ComplexNumber result;
		double rho = ((Polar)num1).getRhoNumber() * ((Polar)num2).getRhoNumber();
		double theta = ((Polar)num1).getThetaNumber() + ((Polar)num2).getThetaNumber();
		result = new Polar(rho, theta);
		return result;
	}
	
	private ComplexNumber addPolar(ComplexNumber num1, ComplexNumber num2, int operator){
		num1 = this.convert(num1, "Cartesian");
		num2 = this.convert(num2, "Cartesian");
		ComplexNumber result = this.convert(this.addCartesian(num1, num2, operator), "Polar");
		return result;
	}
	
	private double calculateTheta(double real, double imaj){
		double theta;
		if(imaj == 0){
			theta = 0;
		}
		else{
			if(real >= 0 && imaj >= 0){
				theta = Math.atan(imaj / real);
			}
			else if(real < 0 && imaj >= 0){
				theta = Math.PI - Math.atan(imaj / Math.abs(real));
			}
			else if(real < 0 && imaj < 0){
				theta = Math.PI + Math.atan(Math.abs(imaj) / Math.abs(real));
			}
			else{
				theta = 2* Math.PI - Math.atan(Math.abs(imaj) / real);
			}
		}
		return theta * 180 / Math.PI;
	}
}