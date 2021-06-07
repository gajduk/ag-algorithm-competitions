package CodeForces;

import java.util.Scanner;

public class Test {
	public static final long B  = 253;
	public static final long M = 194317911;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.next();
		String s2 = in.next();
		String s3 = in.next();
		int a1 = min(min(s1,s2),s3).length();
		int a2 = min(min(s2,s1),s3).length();
		int a3 = min(min(s1,s3),s2).length();
		int a4 = min(min(s3,s1),s2).length();
		int a5 = min(min(s2,s3),s1).length();
		int a6 = min(min(s3,s2),s1).length();
		/*
		int a7 = min(s3,min(s1,s2)).length();
		int a8 = min(s2,min(s1,s3)).length();
		int a9 = min(s3,min(s2,s1)).length();
		int a10 = min(s1,min(s2,s3)).length();
		int a11 = min(s1,min(s3,s2)).length();
		int a12 = min(s2,min(s3,s1)).length();
		*/
		//System.out.println(min(min(s1,s2),s3));
		
		int min = a1;
		min = Math.min(min,a2);
		min = Math.min(min,a3);
		min = Math.min(min,a4);
		min = Math.min(min,a5);
		min = Math.min(min,a6);
		/*
		min = Math.min(min,a7);
		min = Math.min(min,a8);
		min = Math.min(min,a9);
		min = Math.min(min,a10);
		min = Math.min(min,a11);
		min = Math.min(min,a12);
		*/
		System.out.println(min);
	}
	
	public static String min ( String a , String b ) {
		if ( isSubString(a,b) ) return b;
	    if ( isSubString(b,a) ) return a;
	    int c1 = merge(a,b);
	    int c2 = merge(b,a);
	    if ( c1 > c2 ) {
	    	return a+b.substring(c1);
	    }
	    else {
	    	return b+a.substring(c2);
	    }
	}
	
	private static int merge(String a, String b) {
	    int res = 0;
		int m = a.length(); int n = b.length();
		long ha = 0; long hb = 0;
	    long h = 1;
	    int i;
	    for ( i = 0 ; i < Math.min(m,n) ; ++i ) {
	    	if ( ha == hb ) {
	    		if ( equals(a,b,m-i,0,i) ) res = i;
	    	}
	    	hb = (hb*B)%M+b.charAt(i)-'a'+1; hb %= M;
	    	ha = ha+(h*(a.charAt(m-i-1)-'a'+1))%M; ha %= M;
	    	h *= B;	h %= M;
	    	/*
	    	if ( b.substring(0,i).equals(a.substring(m-i))) {
	    		System.out.println(b.substring(0,i)+" "+a.substring(m-i));
	    		System.out.println(ha+" "+hb);
	    	}
	    	*/
	     	//System.out.println(ha+" "+hb+" "+largest+" "+i+" "+b.substring(0,i)+" "+a.substring(m-i));
		}
	    if ( ha == hb ) {
    		if ( equals(a,b,m-i,0,i)) res = i;
    	}
	    return res;
	}

	private static boolean equals(String a, String b, int i, int j, int n) {/*
		for ( int k = 0 ; k < n ; ++k ) {
			if ( a.charAt(i+k) != b.charAt(j+k) ) return false;
		}*/
		return true;
	}

	public static boolean isSubString( String a , String b ) {
	    int m = a.length();
	    int n = b.length();
	    if ( m > n ) return false;
	    long h = 1;
	    for ( int i=0 ; i<m-1 ; i++ ) h = (h*B) % M;    
	    long p = 0;                          // value of the pattern string         
	    long t = 0;                          // value of the current portion of text
	    for ( int i=0 ; i<m ; i++ ) {    
	      p = (B*p + a.charAt(i)) % M;
	      t = (B*t + b.charAt(i)) % M;
	    }
	    int s;
	    for (s=0; s<=n-m; s++) {
	      if (p == t) {
	    	  if ( equals(a, b, 0, s, m) ) return true;
	      }
	      if (s < n-m) {
	        t = (B*(t - ((b.charAt(s)*h) % M)) + b.charAt(s+m)) % M;       
	        if (t < 0)  t += M; 
	      }
	    }  
	    return false;
	}

}
