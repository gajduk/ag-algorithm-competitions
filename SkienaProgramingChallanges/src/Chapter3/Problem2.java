package Chapter3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Problem2 {
	
	public static char table[][];
	public static int row;
	public static int col;
	
	
	
	public static void main ( String args[] ) {
		test_judges();
		//test_file();
	}
	
	public static void test_judges() {
		Scanner in = new Scanner(System.in);
    	int num_cases = new Integer(in.nextLine());//the number of test cases
    	for ( int i = 0 ; i < num_cases ; ++i ) {
    		in.nextLine();
    		String t = in.nextLine();
    		StringTokenizer tkr = new StringTokenizer(t," ,.");
    		int m = new Integer(tkr.nextToken());//rows of chars
    		int n = new Integer(tkr.nextToken());//cols of chars
    		table = new char[m][n];
    		
    		for ( int k = 0 ; k < m ; ++k ) {
    			String temp = in.nextLine();
    			for ( int l = 0 ; l < n ; ++l ) {
    				table[k][l] = temp.charAt(l);
    			}
    		}
    		//print(table);
    		int k = new Integer(in.nextLine());//how many words need to be found
    		for ( int h = 0 ; h < k ; ++h ) {
    			String temp = in.nextLine();
    			locate(temp);
    			System.out.println((row+1)+" "+(col+1));
    		}
    		if ( i != num_cases-1 ) {
    			
    			System.out.println();
    		}
      	}
	}

	public static void test_file() {
			FileInputStream inputFile = null;
		    try {
		    	inputFile = new FileInputStream("F:/Програмирање/UVA judge test/chapter3 problem2.txt");
		    }
	    	catch (FileNotFoundException e) {
		      e.printStackTrace(System.err);
		      return;
		    }
	    	Scanner in = new Scanner(inputFile);
	    	int num_cases = new Integer(in.nextLine());//the number of test cases
	    	for ( int i = 0 ; i < num_cases ; ++i ) {
	    		in.nextLine();
	    		String t = in.nextLine();
	    		StringTokenizer tkr = new StringTokenizer(t," ,.");
	    		int m = new Integer(tkr.nextToken());//rows of chars
	    		int n = new Integer(tkr.nextToken());//cols of chars
	    		table = new char[m][n];
	    		for ( int k = 0 ; k < m ; ++k ) {
	    			String temp = in.nextLine();
	    			for ( int l = 0 ; l < n ; ++l ) {
	    				table[k][l] = temp.charAt(l);
	    			}
	    		}
	    		//print(table);
	    		int k = new Integer(in.nextLine());//how many words need to be found
	    		for ( int h = 0 ; h < k ; ++h ) {
	    			String temp = in.nextLine();
	    			//System.out.println(temp);
	    			locate(temp);
	    			System.out.println((row+1)+" "+(col+1));
	    			break;
	    		}
	    		if ( i != num_cases-1 ) {
	    			System.out.println();
	    		}
	      	}
	    	
	}
	
	public static void print ( char matrix[][] ) {
		for ( int i = 0 ; i < matrix.length ; ++i ) {
			for ( int j = 0 ; j < matrix[0].length ; ++j ) {
				System.out.print(matrix[i][j]+"");
			}
			System.out.println();
		}
	}
	
	//this func will locate the word in the grid of chars table and will set the 
	//static variable row and col to the appropriate values
	public static void locate ( String word ) {
		int row_left = table.length;
		int col_top = table[0].length;
		//check rows to the right
		//System.out.println("Rows to the right");
		for ( int i = 0 ; i <  table.length ; ++i ) {
			for ( int j = 0 ; j < table[0].length-word.length()+1 ; ++j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i][j+h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
			}
		}
		//check rows to the left
		//System.out.println("Rows to the left");
		for ( int i = 0 ; i <  table.length ; ++i ) {
			for ( int j = table[0].length-1 ; j >= word.length()-1 ; --j ) {
				
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i][j-h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
				
			}
		}
		
		//check cols to the top
		//System.out.println("Cols to the bottom");
		for ( int i = 0 ; i < table.length-word.length()+1 ; ++i ) {
			for ( int j = 0 ; j < table[0].length ; ++j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i+h][j];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
				
			}
		}
		//check cols to the bottom
		//System.out.println("Cols to the top");
		for ( int i = table.length-1 ; i >= word.length()-1 ; --i ) {
			for ( int j = 0 ; j < table[0].length ; ++j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i-h][j];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
				
			}
		}
		
		//check diagonals to the top-right
		//System.out.println("Diagonal to the bottom-right");
		for ( int i = 0 ; i < table.length-word.length()+1 ; ++i ) {
			for ( int j = 0 ; j < table[0].length-word.length()+1 ; ++j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i+h][j+h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
			}
		}
		
		//check diagonals to the bottom-left
		//System.out.println("Diagonals to the top-left");
		for ( int i = table.length-1 ; i >= word.length()-1 ; --i ) {
			for ( int j = table[0].length-1 ; j >= word.length()-1 ; --j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i-h][j-h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
			}
		}
		
		//System.out.println("Diagonal to the top-right");
		for ( int i = 0 ; i < table.length-word.length()+1 ; ++i ) {
			for ( int j = table[0].length-1 ; j >= word.length()-1 ; --j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i+h][j-h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
			}
		}
		
		
		//System.out.println("Diagonal to the bottom-left");
		for ( int i = table.length-1 ; i >= word.length()-1 ; --i ) {
			for ( int j = 0 ; j < table[0].length-word.length()+1 ; ++j ) {
				char result[] = new char[word.length()];
				for ( int h = 0 ; h < word.length() ; ++h ) {
					result[h] = table[i-h][j+h];
				}
				//System.out.println(result);
				if ( new String(result).toUpperCase().equals(word.toUpperCase()) ) {
					if ( row_left == i ) {
						if ( col_top > j ) {
							col_top = j;
							row_left = i;
						}
					}
					else if ( row_left > i ) {
						col_top = j;
						row_left = i;
					}
				}
			}
		}
		
		col = col_top;
		row = row_left;
	}
	
	public static boolean check ( int i , int j ) {
		return i >= 0 && i < table.length && j >= 0 && j < table[0].length;
	}
	
	
}
