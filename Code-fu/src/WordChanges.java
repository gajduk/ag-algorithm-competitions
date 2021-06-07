
public class WordChanges {
	
	public static void main(String[] args) {
		WordChanges w = new WordChanges();
		System.out.println(w.minimal("hello",
				"yellow"
));
	}
	  
	public int minimal(String a, String b) {
		int d[][] = new int[51][51];
		d[0][0] = 0;
		for ( int i = 1 ; i <= a.length() ; ++i ) {
			d[i][0] = d[i-1][0]+1;
		}
		for ( int i = 1 ; i <= b.length() ; ++i ) {
			d[0][i] = d[0][i-1]+1;
		}
		for ( int i = 1 ; i <= a.length() ; ++i ) {
			for ( int j = 1 ; j <= b.length() ; ++j ) {
				if ( a.charAt(i-1) == b.charAt(j-1) ) {
					d[i][j] = d[i-1][j-1];
				}
				else {
					d[i][j] = d[i-1][j-1]+1;
				}
				d[i][j] = Math.min(d[i][j-1]+1, d[i][j]);
				d[i][j] = Math.min(d[i-1][j]+1, d[i][j]);
			}
		}
		return d[a.length()][b.length()];
	}

}
