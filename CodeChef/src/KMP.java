import java.util.Arrays;


public class KMP {
	
	public static void main(String[] args) {
		char[] a = new String("participate in parachute").toCharArray(); 
		//char[] b = new String("ABC ABCDAB ABCDABCDABDE").toCharArray();
		//char[] c = new String("ABCDABD").toCharArray();
		char[] b = new String("abacababababaaabaaaabbbaba").toCharArray();
		char[] c = new String("aba").toCharArray();
		System.out.println(count(b, b.length, c,c.length));
		System.out.println(Arrays.toString(c)+"\n"+Arrays.toString(tableGenerator(c, 3)));
	}
	
	public static int[] tableGenerator1 ( char[] word , int n ) {
		int res[] = new int[n];
		int cnd = 0;
		res[0] = -1;
		res[1] = 0;
		for ( int pos = 2 ; pos < n ;  ) {
			if ( word[pos-1] == word[cnd]) {
				++cnd;
				res[pos] = cnd;
				++pos;
			}
			else {
				if ( cnd > 0 ) {
					cnd = res[cnd];
				}
				else {
					res[pos] = 0;
					++pos;
				}
			}
		}
		return res;
	}
	
	public static int[] tableGenerator ( char[] word , int n ) {
		int res[] = new int[n];
		int cnd = 0;
		res[0] = -1;
		for ( int pos = 1 ; pos < n ;  ) {
			if ( word[pos] == word[cnd] ) {
				++cnd;
				res[pos] = cnd;
				++pos;
			}
			else {
				if ( cnd > 0 ) {
					--cnd;
				}
				else {
					cnd = 0;
					res[pos] = cnd;
					++pos;
				}
			}
			
		}
		return res;
	}
	
	public static int count_b ( char[] s , int n_s , char[] w , int n_w ) {
		int t[] = tableGenerator(w, n_w);
		int count = 0;
		int m = 0;
		int i = 0;
		while ( m+i < n_s ) {
			if ( w[i] == s[m + i] ) {
		            if ( i == n_w-1 ) {
		                ++count;
		                m = m+i-t[i];		            	
			            if ( t[i] > -1 ) {
			                i = t[i];
			            }
			            else {
			                i = 0;
			            }
		            }
		            ++i;
		            
			}
		    else {
		            m = m+i-t[i];		            	
		            if ( t[i] > -1 ) {
		                i = t[i];
		            }
		            else {
		                i = 0;
		            }
		    }
		}
		return count;
	}
	
	static int i = 0;
	
	public static boolean iw ( char s_m , char[] w , int n_w  , int t[] ) {
	//	System.out.println(i+" "+s_m);
		if ( w[i] == s_m ) {
            if ( i == n_w-1 ) {
            	if ( t[i] > -1 ) {
                    i = t[i];
            	}
            	else {
            		i = 0;
            	}
	            return true;
            }
            ++i;
            return false;
		}
	    else {
	    	if ( t[i] > -1 ) {
                i = t[i];
                if ( t[i] > -1 ) {
                    i = t[i];
            	}
            	else {
            		i = 0;
            		return false;
            	}
        	}
        	else {
        		i = 0;
        		return false;
        	}
            iw(s_m,w,n_w,t);
            return false;
	    }
	}
	
	public static int count ( char[] s , int n_s , char[] w , int n_w ) {
		int t[] = tableGenerator(w, n_w);
		int count = 0;
		for ( int i = 0 ; i < n_s ; ++i ) {
		//	System.out.println("count "+count);
			count += iw(s[i],w,n_w,t)?1:0;
		}
	//	if ( i == n_w-1 ) ++count;
		return count;
	}
	
	public static int find ( char[] s , int n_s , char[] w , int n_w ) {
		int t[] = tableGenerator(w, n_w);
		int m = 0;
		int i = 0;
		while ( m+i < n_s ) {
			if ( w[i] == s[m + i] ) {
		            if ( i == n_w-1 ) {
		                return m;
		            }
		            ++i;
			}
		    else {
		            m = m+i-t[i];		            	
		            if ( t[i] > -1 ) {
		                i = t[i];
		            }
		            else {
		                i = 0;
		            }
		    }
		}
		return -1;
	}

}
