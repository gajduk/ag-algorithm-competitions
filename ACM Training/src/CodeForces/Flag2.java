package CodeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Flag2 {
	
	public static final int NCOLORS = 26;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner in = new Scanner(System.in);
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int n = 0;int m = 0;
		line = bi.readLine();
		String nums [] = line.split("\\s");
		n = Integer.parseInt(nums[0]);
		m = Integer.parseInt(nums[1]);
		char flag[][] = new char[n][m];
		for ( int i = 0 ; i < n ; ++i ) {
			line = bi.readLine();
			for ( int k = 0 ; k < m ; ++k ) {
				flag[i][k] = line.charAt(k);
			}
		}
		char colors[] = new char[NCOLORS];
		for ( int i = 0 ; i < NCOLORS ; ++i ) colors[i] = (char) ('a'+i);
		int cost[][][] = new int[n][NCOLORS][NCOLORS];
		for ( int i = 0 ; i < n ; ++i ) {
			for ( int color1 = 0 ; color1 < NCOLORS ; ++color1 ) {
				for ( int color2 = 0 ; color2 < NCOLORS ; ++color2 ) {
					if ( color1 != color2 ) {
						int cost_of_this_row_only = calculateCost(colors,color1,color2,flag,i);
						int min_cost_of_previous_rows = Integer.MAX_VALUE;
						if ( i == 0 ) {
							min_cost_of_previous_rows = 0;
						}
						else {
							for ( int prev_color1 = 0 ; prev_color1 < NCOLORS ; ++prev_color1 ) {
									for ( int prev_color2 = 0 ; prev_color2 < NCOLORS ; ++prev_color2 ) {
										if ( prev_color1 != prev_color2 && prev_color2 != color2  && prev_color1 != color1 ) {
												min_cost_of_previous_rows = 
														min_cost_of_previous_rows>cost[i-1][prev_color1][prev_color2]?cost[i-1][prev_color1][prev_color2]:min_cost_of_previous_rows;
										
										}
									}
								
							}
						}
						cost[i][color1][color2] = cost_of_this_row_only+min_cost_of_previous_rows;
					}
//					System.out.print(cost[i][color1][color2]);
				}
			}
//			System.out.println();
		}
		int min_cost_of_all_rows = Integer.MAX_VALUE;
		for ( int color1 = 0 ; color1 < NCOLORS ; ++color1 ) {
			for ( int color2 = 0 ; color2 < NCOLORS ; ++color2 ) {
				if ( color1 != color2 ) {
					min_cost_of_all_rows = 
							min_cost_of_all_rows>cost[n-1][color1][color2]?cost[n-1][color1][color2]:min_cost_of_all_rows;
				}
			}
		}
		System.out.println(min_cost_of_all_rows);
		int cost_of_up_to_i_road = min_cost_of_all_rows;
		char new_flag[][] = new char[n][m];
		int next_color1 = NCOLORS; int next_color2= NCOLORS;
		for ( int i = n-1 ; i > 0 ; --i ) {
			boolean break_flag = false;
			for ( int color1 = 0 ; color1 < NCOLORS ; ++color1 ) {
				for ( int color2 = 0 ; color2 < NCOLORS ; ++color2 ) {
					if ( color1 != color2 && next_color1 != color1 && next_color2 != color2 ) {
						 if ( cost_of_up_to_i_road == cost[i][color1][color2] ) {
							 for ( int k = 0 ; k < m ; ++k ) {
								 if ( (k&1) == 0 ) new_flag[i][k] = colors[color1];
								 else new_flag[i][k] = colors[color2];
							 }
							 break_flag = true;
							 next_color1 = color1; next_color2 = color2;
							 cost_of_up_to_i_road -= calculateCost(colors, color1, color2, flag, i);
							 break;
						 }
					}
				}
				if ( break_flag ) break;
			}
		}
		boolean break_flag = false;
		for ( int prev_color1 = 0 ; prev_color1 < NCOLORS ; ++prev_color1 ) {
			for ( int prev_color2 = 0 ; prev_color2 < NCOLORS ; ++prev_color2 ) {
				int cost_of_this_row_only = calculateCost(colors,prev_color1,prev_color2,flag,0);
				if ( prev_color1 != prev_color2 && next_color1 != prev_color1 && next_color2 != prev_color2 ) {
					 if ( cost_of_up_to_i_road==cost_of_this_row_only ) {
						 for ( int k = 0 ; k < m ; ++k ) {
							 if ( (k&1) == 0 ) new_flag[0][k] = colors[prev_color1];
							 else new_flag[0][k] = colors[prev_color2];
						 }
						 break_flag = true;
						 next_color1 = prev_color1; next_color2 = prev_color2;
						 break;
					 }
				}
			}
			if ( break_flag ) break;
		}
		for ( int i = n-1 ; i >= 0 ; --i ) {
			System.out.println(new_flag[n-i-1]);
		}
	}

	private static int calculateCost(char[] colors, int color1, int color2,
			char[][] flag , int row) {
		int cost = 0;
		for ( int k = 0 ; k < flag[0].length ; ++k ) cost += flag[row][k]==((k&1)==1?colors[color2]:colors[color1])?0:1;
		return cost;
	}

}
