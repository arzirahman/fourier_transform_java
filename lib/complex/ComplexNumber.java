package Lib.complex;

public abstract class ComplexNumber{
	private String type;
	
	public ComplexNumber(){}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public abstract void print();
	public abstract void println();
}