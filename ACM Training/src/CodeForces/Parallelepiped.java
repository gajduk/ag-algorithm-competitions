package CodeForces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Divisors {
	public int div1;
	public int div2;
	
	public Divisors(int div1, int div2) {
		super();
		this.div1 = div1;
		this.div2 = div2;
	}

	@Override
	public String toString() {
		return "Divisors [div1=" + div1 + ", div2=" + div2 + "]";
	}
	
	
	
}

public class Parallelepiped {
	
	static boolean is_prime[] = new boolean[10001];
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		/*
		for ( int i = 2 ; i < is_prime.length ; ++i ) {
			for ( int j = i+i ; j < is_prime.length ; j += i) {
				is_prime[j] = false;
			}
		}
		*/
		List<Divisors> divs_a = getDivisors(a);
		List<Divisors> divs_b = getDivisors(b);
		List<Divisors> divs_c = getDivisors(c);
		for ( Divisors div_a : divs_a ) {
			for ( Divisors div_b : divs_b ) {
				for ( Divisors div_c : divs_c ) {
					if ( div_a.div1 == div_b.div1 ) {
						if ( div_b.div2 == div_c.div1 ) {
							if ( div_c.div2 == div_a.div2 ) { System.out.println(f(div_a,div_b,div_c)); return; }
						}
						if ( div_b.div2 == div_c.div2 ) {
							if ( div_c.div1 == div_a.div2 ) { System.out.println(f(div_a,div_b,div_c)); return; }	
						}
					}
					
					
					if ( div_a.div2 == div_b.div1 ) {
						if ( div_b.div2 == div_c.div1 ) {
							if ( div_c.div2 == div_a.div1 ) { System.out.println(f(div_a,div_b,div_c)); return; }
						}
						if ( div_b.div2 == div_c.div2 ) {
							if ( div_c.div1 == div_a.div1 ) { System.out.println(f(div_a,div_b,div_c)); return; }	
						}
					}
					if ( div_a.div1 == div_b.div2 ) {
						if ( div_b.div1 == div_c.div1 ) {
							if ( div_c.div2 == div_a.div2 ) { System.out.println(f(div_a,div_b,div_c)); return; }
						}
						if ( div_b.div1 == div_c.div2 ) {
							if ( div_c.div1 == div_a.div2 ) { System.out.println(f(div_a,div_b,div_c)); return; }	
						}				
					}
					if ( div_a.div2 == div_b.div2 ) {
						if ( div_b.div1 == div_c.div1 ) {
							if ( div_c.div2 == div_a.div1 ) { System.out.println(f(div_a,div_b,div_c)); return; }
						}
						if ( div_b.div1 == div_c.div2 ) {
							if ( div_c.div1 == div_a.div1 ) { System.out.println(f(div_a,div_b,div_c)); return; }	
						}
					}
				}
			}
		}
	}


	private static int f(Divisors div_a, Divisors div_b, Divisors div_c) {
		//System.out.println(div_a+" "+div_b+" "+div_c);
		if ( div_a.div1 == div_b.div1 ) {
			return 4*(div_a.div1+div_a.div2+div_b.div2);
		}
		if ( div_a.div2 == div_b.div1 ) {
			return 4*(div_a.div1+div_a.div2+div_b.div2);
			
		}
		if ( div_a.div1 == div_b.div2 ) {
			return 4*(div_a.div1+div_a.div2+div_b.div1);
					
		}
		if ( div_a.div2 == div_b.div2 ) {
			return 4*(div_a.div1+div_a.div2+div_b.div1);
			
		}
		return 0;
	}


	private static List<Divisors> getDivisors(int a) {
		List<Divisors> res = new ArrayList<Divisors>();
		int sqrt_a = (int)Math.sqrt(a);
		for ( int j = 1 ; j <= sqrt_a ; ++j ) {
			if ( a % j == 0 ) {
				res.add(new Divisors(j, a/j));
			}
		}
		return res;
	}

}
