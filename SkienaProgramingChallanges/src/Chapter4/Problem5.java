package Chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Job implements Comparable {
	public int time;
	public int fine;
	public int index;
	
	public Job( int t , int f , int i ) {
		 time = t;
		 fine = f;
		 index = i;
	}

	@Override
	public int compareTo(Object o) {
		Job t = (Job) o;
		if ( time/(float)fine - t.time/(float)t.fine > 0 ) {
			return 1;
		}
		else {
			if ( time/(float)fine - t.time/(float)t.fine < 0 ) {
				return -1;
			}
			return index - t.index;
		}
	}
	
	
	
}

public class Problem5 {

	public static int time[];//the time requested to make the shoe 'i'
	public static int fine[];//the fine paid for every delay on 'i'
	public static Job jobs[];
	
	/*
	//will calculate how much many we need to pay if we choose to make the 'shoe_index' shoe now
	public static int calculatePenalty ( int shoe_index ) {
		if ( done[shoe_index] ) 
				return Integer.MAX_VALUE;
		int penalty = 0;
		for ( int i = 0 ; i < fine.length ; ++i ) {
			if ( !done[i] && i != shoe_index ) {
				penalty += time[shoe_index]*fine[i]; 
			}
		}
		return penalty;
	}
	
	
	public static int getNextBestIndex ( ) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for ( int i = 0 ; i < fine.length ; ++i ) {
			int penalty = calculatePenalty(i);
			if ( min > penalty ) {
				min = penalty;
				min_index = i;
			}
		}
		return min_index;
	}
	
	public static String getBestSequence ( int  times[] , int fines[] ) {
		done = new boolean[times.length];
		time = new int[times.length];
		fine = new int[fines.length];
		for ( int i = 0 ; i < times.length ; ++i ) {
			time[i] = times[i];
			fine[i] = fines[i];
			done[i] = false;
		}
		String result = "";
		boolean space = false;
		while ( true ) {
			if ( space ) {
				result += " ";
			}
			else
				space = true;	
			int next_index = getNextBestIndex();
			if ( next_index == - 1 ) return result;
			result += next_index+1;
			done[next_index] = true;
		}
	}
	public static void main ( String args[] ) {
	
		test_getBestSequence();
		
	}


	public static void test_getBestSequence() {
		int t[] = {	3 , 1, 2 , 5 };
		int k[] = { 4 , 1000 , 2 ,5 };
		System.out.println(getBestSequence(t,k));
	}
	*/
	public static void main ( String args[] ) {
		test_judge();
		//test_file();
	}
	
	public static void test_judge() {
		Scanner in = new Scanner(System.in);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			in.nextLine();
			int num_jobs = new Integer(in.nextLine());
			fine = new int[num_jobs];
			time = new int[num_jobs];
			for ( int m = 0 ; m < num_jobs ; ++m ) {
				String s_job_parametars = in.nextLine();
				StringTokenizer job_parametars = new StringTokenizer(s_job_parametars);
				time[m] = new Integer(job_parametars.nextToken());
				fine[m] = new Integer(job_parametars.nextToken());
			}
			sortJobs();
			if ( k < num_test_cases-1 ) {
				System.out.println();
			}
			System.out.println();
			
		}
	}

	public static void test_file() {
		FileInputStream inputFile = null;
		try	 {
     		inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter4 problem5.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
			return;
		}
		Scanner in = new Scanner(inputFile);
		int num_test_cases = new Integer(in.nextLine());
		for ( int k = 0 ; k < num_test_cases ; ++k ) {
			in.nextLine();
			int num_jobs = new Integer(in.nextLine());
			fine = new int[num_jobs];
			time = new int[num_jobs];
			for ( int m = 0 ; m < num_jobs ; ++m ) {
				String s_job_parametars = in.nextLine();
				StringTokenizer job_parametars = new StringTokenizer(s_job_parametars);
				time[m] = new Integer(job_parametars.nextToken());
				fine[m] = new Integer(job_parametars.nextToken());
			}
			sortJobs();
			if ( k < num_test_cases-1 ) {
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public static void print ( int array[] ) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
	}

	public static void sortJobs() {
		jobs = new Job[time.length];
		for ( int k = 0 ; k < time.length ; ++k ) {
			jobs[k] = new Job(time[k],fine[k],k);
		}
		Arrays.sort(jobs);
		for ( int k = 0 ; k < time.length ; ++k ) {
			System.out.print(jobs[k].index+1);
			if ( k < time.length-1 ) {
				System.out.print(" ");
			}
		}
		
	}
	
	
	
		
}
