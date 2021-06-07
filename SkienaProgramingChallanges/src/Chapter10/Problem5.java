package Chapter10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem5 {
	
	/*
	private static boolean exit;

	public static void main ( String args[] ) {
		//test_populateEdges();
		test_file();
		//test_judge();
	}
	
	public static void test_file () {
		FileInputStream inputFile = null;
	    try {
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem5.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	set_id = new int[new Integer(in.nextLine())];
    	while ( true ) {
    		proccessCommand(in.nextLine());
    		if ( exit )
    			break;
    	}
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
	}
	
	public static int set_id[];
	
	public static void proccessCommand ( String command ) {
		StringTokenizer command_parametars = new StringTokenizer(command);
		executeCommand(new Integer(command_parametars.nextToken()),new Integer(command_parametars.nextToken()),new Integer(command_parametars.nextToken()));
	}

	private static void executeCommand(int command_id , int x , int y ) {
		if ( command_id == 1 ) {
			setFriends(x,y);
		}
		if ( command_id == 2 ) {
			setEnemies(x,y);
		}
		if ( command_id == 3 ) {
			if ( areFriends(x,y) ) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}
		if ( command_id == 4 ) {
			if ( areEnemies(x,y) ) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}
		if ( command_id == 0 && x == 0 && y == 0 ) {
			exit = true;
		}
		
	}

	private static boolean areEnemies(int x, int y) {
		if ( set_id[x] != 0 && set_id[y] != 0 ) {
			return set_id[x] != set_id[y];
		}
		return false;
	}

	private static boolean areFriends(int x, int y) {
		if ( set_id[x] != 0 && set_id[y] != 0 ) {
			return set_id[x] != set_id[y];
		}
		return false;
	}

	private static void setEnemies(int x, int y) {
		if ( areFriends(x, y) ) {
			System.out.println("-1");
			return;
		}
		if ( set_id[x] == 2 ) {
			set_id[y] = 1;
			return;
		}
		if ( set_id[x] == 1 ) {
			set_id[y] = 2;
			return;
		}
		if ( set_id[y] == 2 ) {
			set_id[x] = 1;
			return;
		}
		if ( set_id[y] == 1 ) {
			set_id[x] = 2;
			return;
		}
		set_id[x] = 2;
		set_id[y] = 1;
	}

	private static void setFriends(int x, int y) {
		if ( areEnemies(x, y) ) {
			System.out.println("-1");
			return;
		}
		if ( set_id[x] != 0 ) {
			set_id[y] = set_id[x];
			return;
		}
		if ( set_id[y] != 0 ) {
			set_id[x] = set_id[y];
			return;
		}
		set_id[x] = set_id[y] = 1;
	}
	 */
	
	/*
	public static void main ( String args[] ) {
		test_populateEdges();
		System.out.println("2");
		//test_file();
		//test_judge();
	}
	
	public static final int ENEMY = 2;
	public static final int FRIEND = 1;
	public static final int MAX_PEOPLE = 5001;
	public static int num_people;
	public static int edges[][] = new int[MAX_PEOPLE][MAX_PEOPLE];
	public static ArrayList<Integer> graph[] = new ArrayList[MAX_PEOPLE];
	
	public static int getEdge ( int i , int j ) {
		int row = i / 2;
		int col = j / 2;
		int value = edges[row][col];
		int byte_offset = i % 2 * 2 + j % 2;
		byte_offset *= 2;
		int del = (1<<byte_offset);
		int mod = 4;
		return ( value / del) % (mod );
	}
	
	public static void setEdge ( int i , int j , int value ) {
		if ( value < 0 || value >= 4 ) {
			System.out.println("VALUE TO WRITE IS OUT OF BOUNDS");
			return;
		}
		if ( getEdge(i, j) != 0 ) {
			System.out.println("EDGE IS ALREADY SET TO A VALUE");
			return;
		}
		int row = i / 2;
		int col = j / 2;
		int byte_offset = i % 2 * 2 + j % 2;
		byte_offset *= 2;
		int mult = (1<<byte_offset);
		int current_value = edges[row][col];
		int new_value = current_value+value*mult;
		edges[row][col] = new_value;
	}
	
	private static void test_populateEdges() {
		//edges[0][0] = 153;
		setEdge(12,14,1);
		setEdge(12,15,2);
		setEdge(13,14,1);
		setEdge(13,15,2);
		
		System.out.println(getEdge(12,14));
		System.out.println(getEdge(12,15));
		System.out.println(getEdge(13,15));
		System.out.println(getEdge(13,14));
		System.out.println(edges[6][7]);
	}
	
	public static boolean areFriends ( int x , int y ) {
		return getEdge(x,y) == FRIEND;
	}
	
	public static boolean areEnemies ( int x , int y ) {
		return getEdge(x,y) == ENEMY;
	}
	
	public static void setFriends ( int x , int y ) {
		if ( areEnemies(x, y) ) {
			System.out.println("-1");
			return;
		}
		if ( areFriends(x, y) ) {
			return;
		}
		ArrayList<Integer> set_friends_x = getFriends(x);
		ArrayList<Integer> set_friends_y = getFriends(y);
		ArrayList<Integer> set_enemies_x = getEnemies(x);
		ArrayList<Integer> set_enemies_y = getEnemies(y);
		
		//check for contradictions 
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_friends_y ) {
				if ( areEnemies(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_friends_y ) {
				if ( areFriends(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( areFriends(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		
		
		//make the new connections
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_friends_y ) {
				if ( ! areFriends(k, m) ) {
					setEdge(k, m, FRIEND);
				}
			}
		}
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_friends_y ) {
				if ( ! areEnemies(k, m) ) {
					setEdge(k, m, ENEMY);
				}
			}
		}
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( ! areEnemies(k, m) ) {
					setEdge(k, m, ENEMY);
				}
			}
		}
		
	}

	public static ArrayList<Integer> getFriends( int x ) {
		static_list = new ArrayList<Integer>();
		required_status = FRIEND;
		depthFirstSerch(x);
		return static_list;
	}
	
	public static ArrayList<Integer> getEnemies( int x ) {
		static_list = new ArrayList<Integer>();
		required_status = ENEMY;
		depthFirstSerch(x);
		return static_list;
	}

	public static ArrayList<Integer> static_list;
	public static int required_status;
	
	public static void depthFirstSerch(int x) {
		depthFirstSerchRecursive(x);
	}

	public static void depthFirstSerchRecursive(int x) {
		static_list.add(x);
		for ( int k = 0 ; k < num_people ; ++k ) {
			if ( getEdge(x, k) == required_status && static_list.contains(k) ) {
				depthFirstSerchRecursive(k);
			}
		}
	}
	
	public static void setEnemies ( int x , int y ) {
		if ( areFriends(x, y) ) {
			System.out.println("-1");
			return;
		}
		if ( areEnemies(x, y) ) {
			return;
		}
		ArrayList<Integer> set_friends_x = getFriends(x);
		ArrayList<Integer> set_friends_y = getFriends(y);
		ArrayList<Integer> set_enemies_x = getEnemies(x);
		ArrayList<Integer> set_enemies_y = getEnemies(y);
		
		//check for contradictions 
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( areEnemies(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_friends_y ) {
				if ( areFriends(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( areFriends(k, m) ) {
					System.out.println("-1");
					return;
				}
			}
		}
		
		
		//make the new connections
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( ! areFriends(k, m) ) {
					setEdge(k, m, FRIEND);
				}
			}
		}
		for ( Integer k : set_enemies_x ) {
			for ( Integer m : set_friends_y ) {
				if ( ! areEnemies(k, m) ) {
					setEdge(k, m, ENEMY);
				}
			}
		}
		for ( Integer k : set_friends_x ) {
			for ( Integer m : set_enemies_y ) {
				if ( ! areEnemies(k, m) ) {
					setEdge(k, m, ENEMY);
				}
			}
		}
	}
	
	*/
	
	public static boolean friends[][] = new boolean[10000][10000];
	public static boolean enemies[][] = new boolean[10000][10000];
	public static int num_people;
	
	public static void main ( String args[] ) {
//		long start = System.currentTimeMillis();
//		test_file();
		test_judge();
//		long end = System.currentTimeMillis();
//		System.out.println(end-start+" ms");
	}
	
	public static void test_judge () {
		Scanner in = new Scanner(System.in);
    	num_people = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_people ; ++i ) {
    		friends[i][i] = true;
    	}
    	while ( proccesCommand(in.nextLine()) );
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;    
	    try {   
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter10 problem5.txt");
	    }
    	catch ( Exception e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	num_people = new Integer(in.nextLine());
    	for ( int i = 0 ; i < num_people ; ++i ) {
    		friends[i][i] = true;
    	}
    	while ( proccesCommand(in.nextLine()) );
	}
	
	public static void printStatus () {
		System.out.println("Friends status:");
		for ( int i = 0 ; i < num_people ; ++i ) {
			for ( int m = 0 ; m < num_people ; ++m ) {
				System.out.print(friends[i][m]?"1":"0");
			}
			System.out.println("");
		}
		
		System.out.println("Enemies status:");
		for ( int i = 0 ; i < num_people ; ++i ) {
			for ( int m = 0 ; m < num_people ; ++m ) {
				System.out.print(enemies[i][m]?"1":"0");
			}
			System.out.println("");
		}
	}

	public static boolean proccesCommand ( String command ) {
//		printStatus();
//    	System.out.println("Current command to be executed "+command);
		int parametars[] = getCommandParametars(command);
		if ( parametars[0] == 0 && parametars[1] == 0 && parametars[2] == 0  ) {
			return false;
		}
		if ( parametars[0] == 1 ) {
			executeCommandSetFriends(parametars[1],parametars[2]);
		}
		if ( parametars[0] == 2 ) {
			executeCommandSetEnemies(parametars[1],parametars[2]);
		}
		if ( parametars[0] == 3 ) {
			executeCommandAreFriends(parametars[1],parametars[2]);
		}
		if ( parametars[0] == 4 ) {
			executeCommandAreEnemies(parametars[1],parametars[2]);
		}
		return true;
	}

	public static void executeCommandAreEnemies( int u, int v) {
		System.out.println(enemies[u][v]?"1":"0");
	}

	public static void executeCommandAreFriends( int u, int v ) {
		System.out.println(friends[u][v]?"1":"0");
	}

	public static void executeCommandSetEnemies( int u, int v ) {
		if ( friends[u][v] ) {
			System.out.println("-1");
			return;
		}
		if ( enemies[u][v] ) {
			return;
		}
		enemies[u][v] = enemies[v][u] = true;
		for ( int i = 0 ; i < friends[u].length ; ++i ) {
			friends[u][i] = friends[i][u] = friends[u][i] || enemies[v][i];
			friends[v][i] = friends[i][v] = friends[v][i] || enemies[u][i];
			enemies[u][i] = enemies[i][u] = enemies[u][i] || friends[v][i];
			enemies[v][i] = enemies[i][v] = enemies[v][i] || friends[u][i];
		}
	}

	public static void executeCommandSetFriends( int u, int v ) {
		if ( enemies[u][v] ) {
			System.out.println("-1");
			return;
		}
		if ( friends[u][v] ) {
			return;
		}
		for ( int i = 0 ; i < friends[u].length ; ++i ) {
			friends[u][i] = friends[i][u] = friends[u][i] || friends[v][i];
			friends[v][i] = friends[i][v] = friends[u][i] || friends[v][i];
			enemies[u][i] = enemies[i][u] = enemies[u][i] || enemies[v][i];
			enemies[v][i] = enemies[i][v] = enemies[u][i] || enemies[v][i];
		}
	}

	public static int[] getCommandParametars(String command) {
		int result[] = new int[3];
		StringTokenizer tkr = new StringTokenizer(command);
		for ( int i = 0 ; i < 3 ; ++i ) {
			result[i] = new Integer(tkr.nextToken());
		}
		return result;
	}
	
	

}
