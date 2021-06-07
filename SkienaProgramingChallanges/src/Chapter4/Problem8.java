package Chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import java.util.StringTokenizer;
import java.util.Scanner;

class FootbalTeam implements Comparable {
	
	public int points= 0;
	public int wins = 0;
	public int loses = 0;
	public int ties = 0;
	public int goal_difference = 0; 
	public int goals_scored = 0;
	public int games_played = 0;
	public int goals_received = 0;
	public String name;
	
	public FootbalTeam( String name1 ) {
		name = name1;
	}
	
	@Override
	public int compareTo(Object arg0) {
		FootbalTeam a = (FootbalTeam) arg0;
		if ( a.points != points ) {
			return -(points-a.points);
		}
		if ( a.wins != wins ) {
			return -(wins-a.wins);
		}
		if ( a.goal_difference != goal_difference ) {
			return -(goal_difference-a.goal_difference);
		}
		if ( a.goals_scored != goals_scored ) {
			return -(goals_scored-a.goals_scored);
		}
		if ( a.games_played != games_played ) {
			return -(a.games_played-games_played);
		}
		return -(a.name.toUpperCase().compareTo(name.toUpperCase()));
	}

	public String toString () {
		return name + " " + points + "p, " +   games_played + "g ("+wins+"-"+ties+"-"+loses+"), "+goal_difference+"gd ("+goals_scored+"-"+goals_received+")";
	}
	
}

 class Problem8 {
	public static final int MAX_TEAMS = 32;
	public static FootbalTeam teams_input[] = new FootbalTeam[MAX_TEAMS];
	public static FootbalTeam teams[]; 
	public static int num_teams;
	
	public static FootbalTeam getTeam ( String name ) {
		for ( int i = 0 ; i < teams.length ; ++i ) {
			if ( teams[i].name.equals(name) )
				return teams[i];
		}
		return teams[0];
	}
	
	public static void calculateDifferences () {
		for ( int i = 0 ; i < num_teams ; ++i ) {
			teams[i].goal_difference = teams[i].goals_scored - teams[i].goals_received;
		}
	}
	
	public static void populateArray ( String[] matches ) {
		for ( int i = 0 ; i < matches.length ; ++i ) {
			// team name 1#goals1@goals2#team name 2
			StringTokenizer tkr = new StringTokenizer(matches[i],"@#");
			String team1_name = tkr.nextToken();
			int goals1_scored = new Integer(tkr.nextToken());
			int goals2_scored = new Integer(tkr.nextToken());
			String team2_name = tkr.nextToken();
			FootbalTeam team1 = getTeam(team1_name);
			FootbalTeam team2 = getTeam(team2_name);
			team1.games_played++;
			team2.games_played++;
			team1.goals_scored += goals1_scored;
			team2.goals_scored += goals2_scored;
			team2.goals_received += goals1_scored;
			team1.goals_received += goals2_scored;
			if ( goals1_scored == goals2_scored ) {
				team1.points++;
				team2.points++;
				team1.ties++;
				team2.ties++;
				continue;
			}
			if ( goals1_scored > goals2_scored ) {
				team1.points += 3;
				team1.wins++;
				team2.loses++;
				continue;
			}
			else {
				team2.points += 3;
				team2.wins++;
				team1.loses++;
				continue;
			}
		}
	}
	
	public static void main ( String args[] ) {
		test_judges();
		//test_file();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem8.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	 int num_test_cases = new Integer(in.nextLine());
		    for ( int h = 0 ; h < num_test_cases ; ++h ) {
		    	String tournament_name = in.nextLine();
		    	num_teams = new Integer(in.nextLine());
		    	for ( int i = 0 ; i < num_teams ; ++i ) {
		    		teams_input[i] = new FootbalTeam(in.nextLine());
		    	}
		    	copyTeams();
		    	int num_matches = new Integer(in.nextLine());
		    	String matches[] = new String[num_matches];
		    	for ( int i = 0 ; i < num_matches ; ++i ) {
		    		matches[i] = in.nextLine();
		    	}
		    	populateArray(matches);
		    	calculateDifferences();
		    	Arrays.sort(teams);	    	
		    	if ( h != 0 )
		    		System.out.println();
		    	System.out.println(tournament_name);
		    	for ( int i = 0 ; i < teams.length ; ++i ) {
		    		System.out.println(i+1+") "+teams[i] );   
		       	}
		    	
		    }
	}

	private static void copyTeams() {
		teams = new FootbalTeam[num_teams];
		for ( int k = 0 ;k < num_teams ; ++k ) {
			teams[k] = teams_input[k];
		}
	}

	public static void test_judges () {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter cout = null;
		try {
			cout = new PrintWriter(new OutputStreamWriter(System.out, "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 int num_test_cases = new Integer(in.nextLine());
		    for ( int h = 0 ; h < num_test_cases ; ++h ) {
		    	String tournament_name = in.nextLine();
		    	num_teams = new Integer(in.nextLine());
		    	for ( int i = 0 ; i < num_teams ; ++i ) {
		    		teams_input[i] = new FootbalTeam(in.nextLine());
		    	}
		    	copyTeams();
		    	int num_matches = new Integer(in.nextLine());
		    	String matches[] = new String[num_matches];
		    	for ( int i = 0 ; i < num_matches ; ++i ) {
		    		matches[i] = in.nextLine();
		    	}
		    	populateArray(matches);
		    	calculateDifferences();
		    	Arrays.sort(teams);	    	
		    	if ( h != 0 )
		    		cout.print("\n");
		    	cout.print(tournament_name+"\n");
		    	for ( int i = 0 ; i < teams.length ; ++i ) {
		    		cout.print(i+1+") "+teams[i]+"\n");
		       	}
		    	
		    }
		    cout.flush();
	}
		
	
}
