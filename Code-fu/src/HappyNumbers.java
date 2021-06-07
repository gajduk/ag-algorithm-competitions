import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

class Rule {
	
	public int n1;
	public int n2;
	public Rule(int n1, int n2) {
		super();
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public Rule() {}
	
	@Override
	public boolean equals(Object arg0) {
		Rule r = (Rule) arg0;
		return n1 == r.n1 && n2 == r.n2;
	}

	@Override
	public String toString() {
		return n1 + "," + n2 ;
	}
	
	
	
}

public class HappyNumbers {
	
	public static void main(String[] args) {
		HappyNumbers hm = new HappyNumbers();
		hm.countNumbers("800983188600295", "4:7,9:8,5:5,6:5,7:5,1:4,4:3,9:6,4:5,4:4,4:4,9:8,8:2,8:7,8:8,8:6,7:8,5:1,4:8,2:5,6:7,5:2,1:1,1:1,7:7,8:2,2:3,3:5,7:4,6:8,1:3,7:7,6:8,7:6,6:1,8:4,5:6,7:9,1:2,1:6,2:2,6:2,6:1,3:7,4:6,9:3,3:6,2:4,2:1,6:4,1:2,8:3,1:8,4:5,6:3,4:8,9:9,5:9,3:1,6:5,9:7,7:3,1:6,6:8,9:6,8:7,5:1,2:1,2:5,9:9,9:9,2:3");
	}
	
	String mod = "1000000007";

	
	 public int countNumbers(String m, String rules) {
		BigInteger bmod = new BigInteger(mod);
		BigInteger bm = new BigInteger(m);
		BigInteger bten = new BigInteger("10");
		BigInteger bone = new BigInteger("1");
		BigInteger ten_pow_m = bten.modPow(bm,bmod);
		HashSet<Rule> rules_set = new HashSet<Rule>();
		StringTokenizer tkr = new StringTokenizer(rules,",");
		while ( tkr.hasMoreTokens() ) {
			String rule = tkr.nextToken();
			String r1 = rule.substring(0, rule.indexOf(':'));
			String r2 = rule.substring(rule.indexOf(':')+1);
			Rule r = new Rule(Integer.parseInt(r1),Integer.parseInt(r2));
			rules_set.add(r);
		}
		BigInteger count[] = new BigInteger[10];
		Arrays.fill(count, new BigInteger("0"));
		for ( Rule e : rules_set ) {
			count[e.n1] = count[e.n1].add(new BigInteger("1"));
		}
		BigInteger doubles = new BigInteger("0");
		for ( int i = 1 ; i < count.length ; ++i ) {
			doubles = doubles.add(count[i].multiply(bm.subtract(bone)));
		}
		BigInteger wth_doubles = ten_pow_m.subtract(doubles.multiply(bten.modPow(bm.subtract(new BigInteger("2")),bmod)));
		BigInteger res = wth_doubles.subtract(bone).mod(bmod);
		BigInteger triples = new BigInteger("0");
		BigInteger bmm3 = bm.subtract(new BigInteger("2"));
		if ( bmm3.compareTo(BigInteger.ZERO) > 0 ) {
			BigInteger counts[][] = new BigInteger[10][10];
			for ( int i = 0 ; i < 10 ; ++i ) for ( int k = 0 ; k < 10 ; ++k ) counts[i][k] = new BigInteger("0");
			for ( Rule e : rules_set ) {
				for ( Rule w : rules_set ) {
					if ( e != w ) {
						if ( e.n2 == w.n1 ) {
							counts[e.n1][e.n2] =  counts[e.n1][e.n2].add(bone);
						}
					}
				}
				if ( e.n1 == e.n2 ) {
					counts[e.n1][e.n2] =  counts[e.n1][e.n2].add(bone);
				}
			}
			for ( int i = 0 ; i < 10 ; ++i ) for ( int k = 0 ; k < 10 ; ++k ) {
				triples = triples.add(counts[i][k].multiply(bmm3));
			}
			res = res.add(triples.multiply(bten.modPow(bm.subtract(new BigInteger("3")),bmod)));
		}
		res = res.mod(bmod);
		BigInteger bmm4 = bm.subtract(new BigInteger("3"));
		if ( bmm4.compareTo(BigInteger.ZERO) > 0 ) {
			BigInteger qwe = new BigInteger("0");
			for ( Rule e : rules_set ) {
				for ( Rule w : rules_set ) {
					if ( e.n2 != w.n1 ) {					
						qwe = qwe.add(bone);
					}
				}
			}
			res = res.subtract(qwe.multiply(bten.modPow(bm.subtract(new BigInteger("4")),bmod)));
		}
		res = res.mod(bmod);
		System.out.println(res.intValue());
		return res.intValue();
	 }

}
