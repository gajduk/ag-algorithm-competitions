package Chapter3;

import java.io.FileInputStream;
import java.util.Scanner;

public class Problem6 {

	public static int n;
	public static String fragments[];
	public static String files[] = new String[70000];
	public static int occurances[] = new int[70000];
	public static int num_files = 0;
	
	public static void decodeFile ( String file_fragments[] , int num_file_fragments ) {
		n = num_file_fragments;
		fragments = new String[n];
		for (int i = 0; i < num_file_fragments; i++) {
			fragments[i] = file_fragments[i];
		}
		genereateAllPosibleCombinationsOredringMatters(n,2);
		System.out.println(getSolution());
	}
	 
	public static String getSolution () {
		int max = 0;
		int max_index = -1;
		for ( int i = 0 ; i < num_files ; ++i ) {
			if ( max < occurances[i] ) {
				max = occurances[i];
				max_index = i;
			}
		}
		return files[max_index];
	}
	
	public static String file = "";
	public static final int FILE_COUNT = 150;
	public static int FILE_SIZE = 300;
	
	public static void recoverFile( ) {
	  int votes[][] = new int[FILE_SIZE][2];
	  int lens[] = new int[FILE_COUNT*2];
	  int len = 0;
	  for (int i = 0; i < fragments.length; ++i) {
		    for (int m = 0; m < fragments[i].length() ; ++m) {
			      ++votes[m][fragments[i].charAt(m) - '0'];
			      ++lens[i];
			      ++len;
		    }
	  }
	  len = len * 2 / fragments.length;
	  for (int i = 0; i < fragments.length; ++i) {
		  for (int m = 0; m < fragments[i].length() ; ++m ) {
	    	   ++votes[len-lens[i]+m][fragments[i].charAt(m) - '0'];
		  }
	  }
	  for (int i = 0; i < len; ++i) {
		  if ( votes[i][0] > votes[i][1] ) {
			  file  += '0';
		  }
		  else {
			  file  += '1';
		  }
	  }
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		int test_case_id = 0;
		String file_fragments[] = new String[257];
		int num_file_fragments = 0;
		while ( true ) {
			if (! in.hasNext() ) {
				populateFragments(file_fragments,num_file_fragments);
				recoverFile();
				System.out.println(file);
				//System.out.println();
				cleanAllData();
				break;
			}
			String line = in.nextLine();
			if ( line.equals("") ) {
				//System.out.println(test_case_id);
				if ( test_case_id != 0 ) {
					populateFragments(file_fragments,num_file_fragments);
					recoverFile();
					System.out.println(file);
					if ( test_case_id < num_test_cases )
						System.out.println();
					num_file_fragments = 0;
					cleanAllData();
				}
				if ( test_case_id-1 == num_test_cases ) {
					break;
				}
				test_case_id++;
				
			}
			else {
				file_fragments[num_file_fragments++] = line;
			}
		}
	}
	
	public static void test_file () {
		FileInputStream input = null;
		try {
			input = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter3 problem6.txt");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(input);
		int num_test_cases = new Integer(in.nextLine());
		int test_case_id = 0;
		String file_fragments[] = new String[257];
		int num_file_fragments = 0;
		while ( true ) {
			if (! in.hasNext() ) {
				populateFragments(file_fragments,num_file_fragments);
				recoverFile();
				System.out.println(file);
				//System.out.println();
				cleanAllData();
				break;
			}
			String line = in.nextLine();
			if ( line.equals("") ) {
				//System.out.println(test_case_id);
				if ( test_case_id != 0 ) {
					populateFragments(file_fragments,num_file_fragments);
					recoverFile();
					System.out.println(file);
					if ( test_case_id < num_test_cases )
						System.out.println();
					num_file_fragments = 0;
					cleanAllData();
				}
				if ( test_case_id-1 == num_test_cases ) {
					break;
				}
				test_case_id++;
				
			}
			else {
				file_fragments[num_file_fragments++] = line;
			}
		}
	}
	
	public static void cleanAllData() {
		file = "";
	}
	
	public static void populateFragments(String[] file_fragments, int num_file_fragments) {
		fragments = new String[num_file_fragments];
		for ( int i = 0 ; i < num_file_fragments ; ++i ) {
			fragments[i] = file_fragments[i];
		}
		
	}

	public static void main ( String args[] ) {
		//test_file();
		test_judge();
	}
	
	public static void genereateAllPosibleCombinationsOredringMatters ( int num_total_elements , int num_subset_elements ) {
		n = num_total_elements;
		generateCombinationsOredringMattersRecursive (0,num_subset_elements,"");
	}

	private static void generateCombinationsOredringMattersRecursive(int current_element , int k , String combination) {
		for ( int i = 0 ; i < n ; ++i ) {
			if ( i == current_element-1 ) {
				continue;
			}
			if ( k == 1 ) {
				files[getFileIndex(combination+fragments[i])] = combination+fragments[i];
			}
			else {
				generateCombinationsOredringMattersRecursive(i+1,k-1,combination+fragments[i]);
			}
		}
	}

	private static int getFileIndex(String string) {
		for ( int k = 0 ; k < num_files ; ++k ) {
			if ( files[k].equals(string) ) {
				++occurances[k];
				return k;
			}
		}
		++num_files;
		return num_files-1;
	}
	
}
