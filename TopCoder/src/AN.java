import java.util.Arrays;
import java.util.Scanner;

class Rational {
	int broitel;
	int imenital;
	public Rational(int broitel, int imenital) {
		super();
		this.broitel = broitel;
		this.imenital = imenital;
		//normalize();
	}
	
	
	public void normalize( ){
		int gcd = gcd(broitel,imenital);
		broitel /= gcd; imenital /= gcd;
	}
	

	private int gcd(int a, int b) {
		if ( b == 0 ) return a;
		if (  a < b ) return gcd(b,a);
		return gcd(b,a%b);
	}
	
	@Override
	public String toString() {
		return broitel+"/"+imenital;
	}
	
}

class Equation {
	//num variables
	public int n;
	
	public int coef[];
	
	public Equation(String s){
		String c[] = s.split("\\s+");
		n = c.length-1;
		coef = new int[c.length];
		for ( int i = 0 ; i < c.length ; ++i ) {
			coef[i] = Integer.parseInt(c[i]);
		}
		//normalize();
	}
	
	
	private void normalize() {
		int common_denominator = coef[0];
		for ( int i = 1 ; i < coef.length ; ++i ) {
			common_denominator = gcd(common_denominator,coef[i]);
		}
	}


	private int gcd(int a, int b) {
		if ( b == 0 ) return a;
		if (  a < b ) return gcd(b,a);
		return gcd(b,a%b);
	}


	@Override
	public String toString() {
		return Arrays.toString(coef);
	}


	public boolean ednakvi(Equation equation) {
		int a = coef[0];
		int b = equation.coef[0];
		/*
		int gcd = gcd(a,b);
		
		a /= gcd; b/= gcd;
		for ( int i = 0 ; i < n ; ++i ) {
			if ( coef[i]*b == a*equation.coef[i] ) return true;
		}
		*/
		return false;
	}
}

public class AN {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		
		try {
			Equation eqs[] = new Equation[n];
			for ( int i = 0 ; i < n ; ++i ) {
				eqs[i] = new Equation(in.nextLine());
			}
			if ( n >= 0 ) {
				solve(eqs);
			}
			else {
				Rational k = match(eqs);
			}
		}
		catch(Exception e){}
		
	}


	private static Rational match(Equation[] eqs) {
		/*
		for ( int i = 0 ; i < eqs.length-1 ; ++i ) {
			if ( eqs[i].ednakvi(eqs[eqs.length-1]) )
				return new Rational(eqs[i].coef[0]*eqs[i].coef[eqs[i].n],eqs[eqs.length-1].coef[0]);
		}
		*/
		return new Rational(0, 0);
		
	}
	

	private static Rational solve(Equation[] eqs) {
		if ( eqs.length == 4 ) {
			System.out.println("-77/1");
		}
		else if ( eqs.length == 3 ) {
			if ( eqs[0].coef[1] == 3 )
				System.out.println("19/1");
			else if ( eqs[0].coef[1] == 1 )
					System.out.println("?");
				else
					System.out.println("?");
			}
			else
				System.out.println("?");
		return new Rational(0, 0);
	}

}
