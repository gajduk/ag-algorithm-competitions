 import java.util.Scanner;


public class AI {
	
	public static void drawShape( int shape , char c , String []screen , int size ) {
		if ( shape == 10 ) { 
			// +
			String s = "";
			for ( int k = 0 ; k < size ; ++k )
				s += " ";
			screen[0] += s;
			screen[size-1] += s;
			for ( int i = 1 ; i < size-1 ; ++i ) {
				if ( i == size/2 ) {
					s = "";
					for ( int k = 0 ; k < size ; ++k )
						s += "+";
					screen[i] += s;
				}
				else {
					s = "";
					for ( int k = 0 ; k < size ; ++k ) {
						if ( k == size/2 ) s+= "+";
						else s += " ";
					}
					screen[i] += s;
				}
			}
		}
		if ( shape == 11 ) { 
			for ( int i = 1 ; i < size-1 ; ++i ) {
				if ( i == size/2 ) {
					String s = "";
					for ( int k = 0 ; k < size ; ++k )
						s += "*";
					screen[i] += s;
				}
				else {
					String s = "";
					for ( int k = 0 ; k < size ; ++k )
						s += " ";
					screen[i] += s;
				}
			}
		}
		if ( shape == 12 ) { 
			// -
						String s = "";
						for ( int k = 0 ; k < size ; ++k )
							s += " ";
						screen[0] += s;
						screen[size-1] += s;
						for ( int i = 1 ; i < size-1 ; ++i ) {
							s = " ";
							for ( int k = 1 ; k < size-1 ; ++k )
								s += "*";
							s+=" ";
							screen[i] += s;
						}
			
		}
		if ( shape == 13 ) { 
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i+k == size-1 )
						m[i][k] = '/';
					else
						m[i][k] = ' ';
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 14 ) { 
			// %
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i+k == size-1 )
						m[i][k] = '%';
					else
						m[i][k] = ' ';
				}
			}
			for ( int i = 0 ; i < size/4 ; ++i ) {
				for ( int k = 0 ; k < size/4 ; ++k ) {
					m[i][k] = '%';
					m[size-i-1][size-k-1] = '%';
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 0 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == 0 || i == size-1 || k == 0 || k == size-1 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 1 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || k == size/2 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size/2 ; ++i ) {
				m[i][size/2-i] = c; 
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 2 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || i == 0 || i == size/2 || (k==0&&i>=size/2)||(k==size-1&&i<size/2) ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 3 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || i == 0 || i == size/2 || k == size-1 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 4 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size/2 || k == size-1 || (k==0&&i<size/2) ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 5 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || i == 0 || i == size/2 || (k==0&&i<=size/2)||(k==size-1&&i>size/2) ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 6 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || i == 0 || i == size/2 || k==0||(k==size-1&&i>size/2) ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 7 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == 0 || i+k==size-1 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 8 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == 0 || i == size-1 || k == 0 || k == size-1 || i == size/2 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == 9 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size-1 || i == 0 || i == size/2 || k==size-1||(k==0&&i<size/2) ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
		if ( shape == -1 ) {
			char m[][] = new char[size][size];
			for ( int i = 0 ; i < size ; ++i ) {
				for ( int k = 0 ; k < size ; ++k ) {
					if ( i == size/2 ) {
						m[i][k] = c;
					}
					else {
						m[i][k] = ' ';
					}
				}
			}
			for ( int i = 0 ; i < size ; ++i ) {
				screen[i] += new String(m[i]);
			}
		}
	}
	
	public static void addGap(int gap , String []screen ) {
		String s = "";
		for ( int k = 0 ; k < gap ; ++k ) s += " ";
		for ( int i = 0 ; i < screen.length ; ++i ) 
			screen[i] += s;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		char op = in.next().charAt(0);
		char s = in.next().charAt(0);
		int size = in.nextInt();
		int gap = in.nextInt();
		int res = 0;
		if ( op == '+' ) res = a+b; 
		if ( op == '-' ) res = a-b; 
		if ( op == '*' ) res = a*b; 
		if ( op == '/' ) res = a/b; 
		if ( op == '%' ) res = a%b; 
		int num_cols = size+gap;
		num_cols += (size+gap)*Math.max(Integer.toString(a).length(),Math.max(Integer.toString(b).length(),Integer.toString(res).length()))-gap;
		
		String s_blank = "";
		String s_lines = "";
		for ( int i = 0 ; i < num_cols ; ++i ) {
			s_blank += " ";
			s_lines += "-";
		}
		
		
		//fisrt number
		
		String screen[] = new String[size];
		for ( int i = 0 ; i < size ; ++i ) {
			screen[i] = "";
		}
		
		int blank = num_cols-((size+gap)*Integer.toString(a).length()-gap);
		addGap(blank, screen);
		drawNumber(a,screen,size,s,gap);
		for ( int i = 0 ; i < size ; ++i ) {
			System.out.println(screen[i]);
		}
		
		System.out.println(s_blank);
		
		
		//operator && second number
		
		screen = new String[size];
		for ( int i = 0 ; i < size ; ++i ) {
			screen[i] = "";
		}
		drawShape(getShape(op), s, screen, size);
		blank = num_cols-size-((size+gap)*Integer.toString(b).length()-gap);
		
		addGap(blank, screen);
		drawNumber(b,screen,size,s,gap);
		for ( int i = 0 ; i < size ; ++i ) {
			System.out.println(screen[i]);
		}
		
		
		System.out.println(s_blank);
		System.out.println(s_lines);
		System.out.println(s_blank);
		
		//result
		screen = new String[size];
		for ( int i = 0 ; i < size ; ++i ) {
			screen[i] = "";
		}
		blank = num_cols-((size+gap)*(Integer.toString(res).length())-gap);
		
		addGap(blank, screen);
		drawNumber(res,screen,size,s,gap);
		for ( int i = 0 ; i < size ; ++i ) {
			System.out.println(screen[i]);
		}
		
		
	}

	private static int getShape(char op) {
		if ( op == '+' ) return 10;
		if ( op == '-' ) return 11;
		if ( op == '*' ) return 12;
		if ( op == '/' ) return 13;
		if ( op == '%' ) return 14;
		System.out.println("QWEUIGYDW");
		return -124;
	}

	private static void drawNumber(int a, String[] screen, int size, char s,int gap) {
		String ss = Integer.toString(a);
		for ( int i = 0 ; i < ss.length() ; ++i ) {
			if ( i > 0 )
				addGap(gap, screen);
			if ( ss.charAt(i) == '-' ) {
				drawShape(-1, s, screen, size);
			}
			else {
				drawShape(ss.charAt(i)-'0', s, screen, size);
			}
		}
	}

	private static void test() {
		int size = 9;
		String screen[] = new String[size];
		for ( int i = 0 ; i < size ; ++i ) {
			screen[i] = "";
		}
		drawShape(-1, '#', screen, size);
		for ( int i = 0 ; i < size ; ++i ) {
			System.out.println(screen[i]);
		}
	}
	

}
