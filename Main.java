import Lib.complex.*;

public class Main{
	public static void main(String[] args){
		ComplexUtil complex = new ComplexUtil();
		
		ComplexNumber x1 = new Cartesian(2,-4);
		ComplexNumber x2 = new Cartesian(3,2);

		complex.add(x1, x2).println();
	}
}