import java.util.Arrays;


public class Entropija {

	
	public static void main(String[] args) {
		System.out.println(log2(3));
		double p[] = {0.25,0.11,0.3,0.08,0.07,0.05,0.07,0.03,0.02,0.02};
		double res = 0;
		String s = "";
		for ( int i = 0 ; i < p.length ; ++i ) {
			System.out.print(log2(1.0/p[i])+" , ");
			double pr = log2(1.0/p[i])*p[i];
			
			res += pr;
			s +=pr +" ";
		}
		System.out.println();
		System.out.println(s);
		System.out.println(res);
		
		System.out.println();
		Arrays.sort(p);
		System.out.println(Arrays.toString(p));
		double q = p[0]+p[1];
		p = Arrays.copyOfRange(p, 1, p.length);
		p[0] = q;
		Arrays.sort(p);
		System.out.println(Arrays.toString(p));
		while ( p.length > 3 ) {
			double qw = p[0]+p[1]+p[2];
			p = Arrays.copyOfRange(p, 2, p.length);
			p[0] = qw;
			Arrays.sort(p);
			System.out.println(Arrays.toString(p));
		}
	}
	
	public static double log2( double a ) {
		return Math.log(a)/Math.log(2);
	}
}
