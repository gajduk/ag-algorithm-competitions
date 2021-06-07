package ACM_2000;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JPopupMenu;

public class B {
	public static final int MAX_WEEKS = 50;
	public static final int MAX_JOBS = 200;
	

	public static void main ( String args[] ) {
		Scanner in = new Scanner(System.in);
		while ( true ) {
			int num_lines = new Integer(in.nextLine());
			String jobs[] = new String[MAX_JOBS+2];
			int num_jobs = 0;
			int week_num = 0;
			BigInteger equation_coeficients[][] = new BigInteger[MAX_WEEKS+1][MAX_JOBS+2];
			for ( int i = 0 ; i < num_lines ; ++i ) {
				boolean new_week = false;
				StringTokenizer tkr = new StringTokenizer(in.nextLine());
				String job = tkr.nextToken();
				BigInteger money = new BigInteger(tkr.nextToken());
				int job_index = -1;
				if ( job.charAt(0) == '.' ) {
					job_index = equation_coeficients[0].length-1;
				}
				else {
					job = job.substring(3);
					job_index =  getIndex(jobs, job,num_jobs);
					if ( job_index == -1 ) {
						job_index = num_jobs;
						jobs[num_jobs++] = job;
						new_week = true;
					}
				}
				equation_coeficients[week_num][job_index] = money;
				if ( new_week ) {
					++week_num;
				}
			}
			for ( int i = 0 ; i < week_num ; ++i ) {
				for ( int k = 0 ; k < num_jobs ; ++k ) {
					if ( equation_coeficients[i][k] == null ) {
						equation_coeficients[i][k] = new BigInteger("0");
					}
				}
			}
			for ( int i = 0 ; i < num_jobs-1 ; ++i ) {
				BigInteger lcm = findLCM(i,equation_coeficients);
				updateValues(i,lcm,equation_coeficients);
			}
			for ( int i = 0 ; i < week_num ; ++i ) {
				System.out.print(equation_coeficients[i][week_num-1]+" ");
				System.out.println(equation_coeficients[i][equation_coeficients[0].length-1]);
			}
			
		}
		
	}


	private static void updateValues(int job_index, BigInteger lcm,	BigInteger[][] equation_coeficients) {
		for ( int k = job_index+1 ; k < equation_coeficients[0].length ; ++k ) {
			equation_coeficients[0][k] = equation_coeficients[0][k].multiply(lcm).negate();
		}
		for ( int i = 1 ; i < equation_coeficients.length ; ++i ) {
			for ( int k = job_index+1 ; k < equation_coeficients[0].length ; ++k ) {
				equation_coeficients[i][k] = equation_coeficients[i][k].multiply(lcm);
			}
		}
		for ( int i = 1 ; i < equation_coeficients.length ; ++i ) {
			for ( int k = job_index+1 ; k < equation_coeficients[0].length ; ++k ) {
				equation_coeficients[i][k] = equation_coeficients[i][k].add(equation_coeficients[0][k]);
			}
		}
			
	}


	private static BigInteger findLCM(int job_index, BigInteger[][] equation_coeficients) {
		BigInteger result = new BigInteger("1");
		if ( equation_coeficients.length > 0 ) {
			result = equation_coeficients[0][job_index].abs();
		}
		for ( int i = 1 ; i < equation_coeficients.length ; ++i ) {
			result = lcm(result,equation_coeficients[i][job_index].abs());
		}
		return result;
		
	}


	private static BigInteger lcm(BigInteger result, BigInteger a) {
		return result.multiply(a).divide(gcd(result,a));
	}


	private static BigInteger gcd(BigInteger a, BigInteger b) {
//		a.
		if ( b == BigInteger.ZERO ) {
			return a;
		}
		if ( a.compareTo(b) < 0 ) {
			return gcd(a,b);
		}
		return gcd(a.mod(b),b);
	}


	private static int getIndex(String[] jobs, String job , int n ) {
		for ( int i = 0 ; i < n ; ++i ) {
			if ( jobs[i].equals(job) ) {
				return i;
			}
		}
		return -1;
	}

}
