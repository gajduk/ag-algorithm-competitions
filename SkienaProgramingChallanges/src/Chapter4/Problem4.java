package Chapter4;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Problem4 {
	public static final int MAX_MINUTES = 480;
	public static boolean map[] = new boolean[MAX_MINUTES];//true if the minute is scheduled 
	public static ArrayList<Break> my_breaks = new ArrayList<Break>();//list of all possible breaks
	public static int max_duration = -1;//the duration of the longest possible nap
	public static int max_start_time = -1;//the start time of the longest possible nap
	
	public static void main ( String args[] ) {
		//test_populateMap();
	//	test_file();
		test_judges();
	}
	
	public static void test_file() {
		FileInputStream inputFile = null;
	    try {
	     
	    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem4.txt");
	    }
    	catch (FileNotFoundException e) {
	      e.printStackTrace(System.err);
	      return;
	    }
    	Scanner in = new Scanner(inputFile);
    	int day = 1;//will keep track of the test cases ordering
    	while ( true ) {
    		if ( ! in.hasNext() ) 
    			break;
    		int num_appointments = 0;//the number of appointments for the current test case
    		try {
    			num_appointments = new Integer(in.nextLine());
    		}
    		catch ( Exception e ) {
    			break;
    		}
    		int appointments_start_times[] = new int[num_appointments];//when the appointments start in minutes
    		int appointments_end_times[] = new int[num_appointments];//when tjhe appouintment end in minutes 
    		for ( int i = 0 ; i < num_appointments ; ++i ) {
    			String appointment = in.nextLine();
    			StringTokenizer tkr = new StringTokenizer(appointment," ");
    			String start_time = tkr.nextToken();
    			String end_time = tkr.nextToken();
    			StringTokenizer tkr1 = new StringTokenizer(start_time,":");
    			StringTokenizer tkr2 = new StringTokenizer(end_time,":");
    			int start_hour = new Integer(tkr1.nextToken());
    			int start_minute = new Integer(tkr1.nextToken());
    			int end_hour = new Integer(tkr2.nextToken());
    			int end_minute = new Integer(tkr2.nextToken());
    			int start_minutes =  start_minute + (start_hour-10)*60;
    			int end_minutes =  end_minute + (end_hour-10)*60;
    			appointments_start_times[i] = start_minutes;
    			appointments_end_times[i] = end_minutes;
    		}
    		getLongestNap(appointments_start_times,appointments_end_times);
    		String max_start_time_display = ""+(max_start_time/60+10)+":";
    		if ( max_start_time%60  < 10 ) {
    			max_start_time_display += "0";
    		}
    		max_start_time_display += max_start_time%60;
    		String max_duration_time_display = "";
    		if ( max_duration >= 60 ) {
    			max_duration_time_display += max_duration/60+" hours and ";
    		}
    		max_duration_time_display += max_duration%60+" minutes.";
    		System.out.println("Day #"+day+": the longest nap starts at "+max_start_time_display+" and will last for "+max_duration_time_display);
    		++day;
    		resetGlobalData();
    	}
    	
	}
	
	public static void resetGlobalData() {
		my_breaks = new ArrayList<Break>();
		for ( int i = 0 ; i < map.length ; ++i ) {
			map[i] = false;
		}
		max_duration = -1;
		
	}

	public static void test_judges() {
		Scanner in = new Scanner(System.in);
    	int day = 1;//will keep track of the test cases ordering
    	while ( true ) {
    		if ( ! in.hasNext() ) 
    			break;
    		int num_appointments = 0;//the number of appointments for the current test case
    		try {
    			num_appointments = new Integer(in.nextLine());
    		}
    		catch ( Exception e ) {
    			break;
    		}
    		int appointments_start_times[] = new int[num_appointments];//when the appointments start in minutes
    		int appointments_end_times[] = new int[num_appointments];//when tjhe appouintment end in minutes 
    		for ( int i = 0 ; i < num_appointments ; ++i ) {
    			String appointment = in.nextLine();
    			StringTokenizer tkr = new StringTokenizer(appointment," ");
    			String start_time = tkr.nextToken();
    			String end_time = tkr.nextToken();
    			StringTokenizer tkr1 = new StringTokenizer(start_time,":");
    			StringTokenizer tkr2 = new StringTokenizer(end_time,":");
    			int start_hour = new Integer(tkr1.nextToken());
    			int start_minute = new Integer(tkr1.nextToken());
    			int end_hour = new Integer(tkr2.nextToken());
    			int end_minute = new Integer(tkr2.nextToken());
    			int start_minutes =  start_minute + (start_hour-10)*60;
    			int end_minutes =  end_minute + (end_hour-10)*60;
    			appointments_start_times[i] = start_minutes;
    			appointments_end_times[i] = end_minutes;
    		}
    		getLongestNap(appointments_start_times,appointments_end_times);
    		String max_start_time_display = ""+(max_start_time/60+10)+":";
    		if ( max_start_time%60  < 10 ) {
    			max_start_time_display += "0";
    		}
    		max_start_time_display += max_start_time%60;
    		String max_duration_time_display = "";
    		if ( max_duration >= 60 ) {
    			max_duration_time_display += max_duration/60+" hours and ";
    		}
    		max_duration_time_display += max_duration%60+" minutes.";
    		System.out.println("Day #"+day+": the longest nap starts at "+max_start_time_display+" and will last for "+max_duration_time_display);
    		++day;
    		resetGlobalData();
    	}
	}
	
	public static void test_populateMap() {
		int start_times_test[] = {1,5,3};
		int end_times_start[] = {2,10,4};
		
		int debug = 5;
		debug = 3;
		
		while ( ! my_breaks.isEmpty() ) {
			Break t = my_breaks.remove(0);
			if ( t.duration > max_duration ) {
				max_duration = t.duration;
				max_start_time = t.start_time;
			}
		}
		System.out.println(max_duration+"  "+max_start_time);
		debug = 5;
	}
	
	public static void getLongestNap( int start_times[] , int end_times[]) {
		populateMap(start_times,end_times);
		locateBreaks();
		int debug = 5;
		debug = 3;
		while ( ! my_breaks.isEmpty() ) {
			Break t = my_breaks.remove(0);
			if ( t.duration > max_duration ) {
				max_duration = t.duration;
				max_start_time = t.start_time;
			}
		}
	}

	public static void populateMap ( int start_times[] , int end_times[] ) {
		for ( int i = 0 ; i < start_times.length ; ++i ) {
			for ( int k = start_times[i] ; k < end_times[i] ; ++k ) {
				map[k] = true;
			}
		}
	}
	
	public static void locateBreaks () {
		int start_time = 0;
		int end_time = 0;
		while ( end_time < map.length ) {
			while ( end_time < map.length  && map[end_time] ) //until we've reached to the end of the day or until we reached a non-free minute
				++end_time;
			start_time = end_time;
			while ( end_time < map.length  && ! map[end_time] ) //until we've reached to the end of the day or until we reached a non-free minute
				++end_time;
			Break to_add = new Break();
			to_add.duration = end_time-start_time;
			to_add.start_time = start_time;
			to_add.end_time = end_time;
			my_breaks.add(to_add);
			end_time++;
		}
	}
	
}
