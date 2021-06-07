package August_30_2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class NetworkXMessageRecovery {
	/*
	  int map[][] = new int[200][200];
	  int before_count[] = new int[200];
	  int after_count[] = new int[200];
	  boolean met[] = new boolean[200];
	  boolean used[] = new boolean[200];
	  int total_count = 0;
	// -1 before , + after


	public  String recover( String[] messages ) {
		for ( int i = 0 ; i < messages.length ; ++i ) {
			for ( int k = 0 ; k < messages[i].length() ; ++k ) {
				if ( ! met[messages[i].charAt(k)] ) {
					met[messages[i].charAt(k)] = true;
					++total_count;
				}
				
				for ( int m = k-1 ; m >= 0 ; --m ) {
					if ( map[messages[i].charAt(k)][messages[i].charAt(m)] == 0 ) {
						map[messages[i].charAt(k)][messages[i].charAt(m)] = -1;
						++before_count[messages[i].charAt(k)];
					}
					
				}
				for ( int m = k+1 ; m < messages[i].length()  ; ++m ) {
					if ( map[messages[i].charAt(k)][messages[i].charAt(m)] == 0 ) {
						++after_count[messages[i].charAt(k)];
						map[messages[i].charAt(k)][messages[i].charAt(m)] = 1;
					}
				}
			}
		}
		String result = "";
		for ( int w = 0 ; w < total_count ; ++w ) {
			for ( int i = 0 ; i < 200 ; ++i ) {
				if ( met[i] ) {
					if ( !used[i] &&  before_count[i] == 0 ) {
						result += (char) i;
						System.out.println((char)i);
						used[i] = true;
						for ( int p = 0 ; p < 200 ; ++p ) {
							if ( met[p] ) {
								if ( map[p][i] == -1 ) {
									--before_count[p];
								}
							}
						}
//						break;
					}
					
				}
			}
		}
		return result;
	}
	*/
	
	public static void main ( String args[] ) {
		NetworkXMessageRecovery t = new NetworkXMessageRecovery();
		String messages[] = {"ASDF","asdf" };
		System.out.println(t.recover(messages));
	}
	
	 HashSet<Character> before_counter[] = new HashSet[200]; 
	 int map[][] = new int[200][200];
	 int before_count[] = new int[200];
	 int after_count[] = new int[200];
	 boolean met[] = new boolean[200];
	 boolean used[] = new boolean[200];
	 int total_count = 0;
	

	public  String recover( String[] messages ) {
		for ( int i = 0 ; i < before_counter.length ; ++i ) {
			before_counter[i] = new HashSet<Character>();
		}
		for ( int i = 0 ; i < messages.length ; ++i ) {
			for ( int k = 0 ; k < messages[i].length() ; ++k ) {
				if ( ! met[messages[i].charAt(k)] ) {
					met[messages[i].charAt(k)] = true;
					++total_count;
				}
				for ( int m = k-1 ; m >= 0 ; --m ) {
					before_counter[messages[i].charAt(k)].add(messages[i].charAt(k));
				}
				
			}
		}
		String result = "";
		for ( int h = 0 ; h < total_count ; ++h ) {
			for ( int i = 0 ; i < met.length ; ++i ) {
				if ( met[i] && ! used[i] ) {
					if ( before_counter[i].size() == 0 ) {
						for ( int w = 0 ; w < met.length ; ++w ) {
							if ( met[w] ) {
								before_counter[w].remove((char)i);
							}
						}
						used[i] = true;
						result += (char) i;
						break;
					}
				}
				
			}
		}
		return result;
	}

}
