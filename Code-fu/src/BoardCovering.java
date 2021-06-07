
public class BoardCovering {
	
	
	/*
	public int count ( int N ) {
		int t[] = new int[N+5];
		t[0] = 1;
		t[1] = 1;
		t[2] = 1;
		for ( int i = 3 ; i <= N ; ++i ) {
			t[i] = t[i-1]+t[i-3];
			t[i] %= 1000007;
		}
		return t[N];
	}
	*/
	public int count ( int N ) {
		int a,b,c;
		a=b=c=1;
		for( int i = 3 ; i <= N ; ++i ) {
			int t = a + b;
			a = c;c = b;b = t;
			b %= 1000007;
		}
		return b;
	}
	
}
