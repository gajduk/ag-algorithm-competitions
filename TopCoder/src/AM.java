import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

class Tocka implements Comparable<Tocka> {
	int i;
	int k;
	public Tocka(int i,int k){
		this.i = i;this.k = k;
	}
	
	@Override
	public String toString() {
		return "("+i+","+k+")";
	}

	@Override
	public int compareTo(Tocka o) {
		if ( i-o.i == 0 ) {
			return k-o.k;
		}
		return i-o.i;
	}
}

class Linija implements Comparable<Linija>{
	
	Tocka t1,t2;
	public Linija(Tocka t1 , Tocka t2) {
		this.t1=t1.compareTo(t2)<=0?t1:t2;
		this.t2=t1.compareTo(t2)>0?t1:t2;
	}
	
	/*
	 0 - horizontala
	 1 - vertikala
	 2 - dijagonala
	 */
	public int getType() {
		if ( t1.i == t2.i ) return 0;
		if ( t1.k == t2.k ) return 1;
		return 2;
	}
	
	@Override
	public String toString() {
		return t1+"-"+t2;
	}

	@Override
	public int compareTo(Linija o) {
		if ( t1.compareTo(o.t1) == 0 ) {
			return t2.compareTo(o.t2);
		}
		return t1.compareTo(o.t1);
	}

	public int dolzina() {
		return Math.max(Math.abs(t1.k-t2.k),Math.abs(t1.i-t2.i));
	}

	
}

public class AM {
	
	static TreeSet<Linija> linii = new TreeSet<Linija>();
	static TreeSet<Tocka> cosinja = new TreeSet<Tocka>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int bitmap[][] = new int[n][m];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < m / 8 ; ++k ) {
				int q = in.nextInt();
				for ( int p = 7 ; p >= 0 ; --p ) {
					bitmap[i][k*8+7-p] = (q&(1<<p))>0?1:0;
				}
			}
		}
		//print(bitmap);
		//boolean visited[][] = new boolean[n][m];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int k = 0 ; k < m ; ++k ) {
				if ( bitmap[i][k] > 0 && isKoshe(i,k,bitmap) ) cosinja.add(new Tocka(i,k));
			}
		}
		for ( Tocka cose : cosinja ) {
			bfs(cose.i, cose.k, bitmap);
		}
		TreeMap<Tocka,LinkedList<Tocka>> liniii = new TreeMap<Tocka,LinkedList<Tocka>>();
		for ( Linija l : linii ) {
			LinkedList<Tocka> v = liniii.get(l.t1);
			if ( v == null ) {
				v = new LinkedList<Tocka>();
				v.add(l.t2);
				liniii.put(l.t1,v);
			}
			else {
				v.add(l.t2);
				liniii.put(l.t1,v);
			}
			v = liniii.get(l.t2);
			if ( v == null ) {
				v = new LinkedList<Tocka>();
				v.add(l.t1);
				liniii.put(l.t2,v);
			}
			else {
				v.add(l.t1);
				liniii.put(l.t2,v);
			}
		}
		TreeMap<String,Integer> res = new TreeMap<String,Integer>();
		res.put("Square", 0);
		res.put("Parallelogram", 0);
		res.put("Rectangle", 0);
		res.put("Triangle ", 0);
		
		TreeSet<Tocka> used_cosinja = new TreeSet<Tocka>();
		for ( Tocka t : cosinja ) {
			if ( ! used_cosinja.contains(t) ) {
				LinkedList<Linija> figura = new LinkedList<Linija>();
				Tocka start = t;
				Tocka prethodna = t;
				while ( true ){
						 used_cosinja.add(prethodna);
						 LinkedList<Tocka> naredni = liniii.get(prethodna);
						 Tocka naredna = null;
						 for ( Tocka tt : naredni ) {
							if ( (figura.size() > 2 && tt.compareTo(start) == 0) || !used_cosinja.contains(tt) ) {
                                 naredna = tt;
					 			 break;
						 	}
						 }
						 figura.add(new Linija(prethodna, naredna));
						 if ( naredna.compareTo(start) == 0 ) {
							 String tip = tip(figura);
							 int k = res.get(tip);
							 ++k;
							 res.put(tip, k);
							 break;
						 }
						 prethodna = naredna;
				}
			}
		}
		String ss = "";
		for ( String s : res.keySet() ) {
			int t = res.get(s);
			while ( t-- > 0 ) ss+=s+", ";
		}
		System.out.println(ss.substring(0,ss.length()-2));
	}	 

	private static String tip(LinkedList<Linija> figura) {
		
		if ( figura.size() == 3 ) return "Triangle";
		int count_d = 0;
		for ( Linija l : figura ) {
			int t = l.getType();
			if ( t == 2 ) ++count_d;
		}
		if ( count_d == 2 ) return "Parallelogram";
		int dolzina = figura.get(0).dolzina();
		boolean flag = true;
		for ( Linija l : figura ) {
			if ( dolzina != l.dolzina() )  {
				flag = false;
			}
		}
		if ( flag ) return "Square";
		return "Rectangle";
	}

	private static boolean isKoshe(int i, int k, int[][] bitmap) {
		int count = 0;
		int di[] = { -1,-1,0,1,1, 1, 0,-1};
		int dk[] = { 0,  1,1,1,0,-1,-1,-1};
		for ( int w = 0 ; w < di.length ; ++w ) {
			int ni = i+di[w];
			int nk = k+dk[w];
			try {
			if ( bitmap[ni][nk] > 0 ) ++count;
			} catch ( Exception e ) {}
		}
		if ( count == 2 ) {
			for ( int w = 0 ; w < 4 ; ++w ) {
				int meta_count = 0;
				try {
				int ni = i+di[w];
				int nk = k+dk[w];
				if ( bitmap[ni][nk] > 0 ) ++meta_count;
				} catch ( Exception e ) {}
				try {
				int ni = i+di[w+4];
				int nk = k+dk[w+4];
				if ( bitmap[ni][nk] > 0 ) ++meta_count;
				} catch ( Exception e ) {}
				if ( meta_count == 2 ) count = 0;
			}
		}
		if ( count == 2 ) return true;
		else return false;
	}

	private static void bfs(int i, int k, int[][] bitmap) {
		int di[] = { -1,-1,0,1,1, 1, 0,-1};
		int dk[] = { 0,  1,1,1,0,-1,-1,-1};
		for ( int w = 0 ; w < di.length ; ++w ) {
			findEnd(di[w],dk[w],i,k,bitmap);
		}
	}

	private static void findEnd(int di, int dk, int i, int k, int[][] bitmap) {
		int lasti = i;
		int lastk = k;
		for ( int ii = i,kk = k ; ii < bitmap.length && ii >= 0 && kk < bitmap[0].length && kk >= 0 ; ii+=di,kk+=dk ) {
			if ( bitmap[ii][kk] == 0 ) {
				if ( (lasti !=i || lastk != k) && cosinja.contains(new Tocka(lasti,lastk)) ) {
					linii.add(new Linija(new Tocka(i,k),new Tocka(lasti,lastk)));
				}
				return;
			}
			lasti = ii;
			lastk = kk;
		}
		if ( (lasti!=i || lastk!= k) && cosinja.contains(new Tocka(lasti,lastk)) ) {
			//linii.add(new Linija(new Tocka(i,k),new Tocka(lasti,lastk)));
		}
	}

	private static void print(boolean[][] visited) {
		System.out.println();
		for ( int i = 0 ; i < visited.length ; ++i ) {
			for ( int k = 0 ; k < visited[0].length ; ++k ) {
				System.out.print(visited[i][k]?"*":".");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void print(int[][] bitmap) {
		System.out.println();
		for ( int i = 0 ; i < bitmap.length ; ++i ) {
			for ( int k = 0 ; k < bitmap[0].length ; ++k ) {
				System.out.print(bitmap[i][k]>0?"*":" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}