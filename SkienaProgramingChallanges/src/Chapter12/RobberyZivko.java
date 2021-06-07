package Chapter12;
import java.util.*;

class Robbery {
	
	 static int w;
	 static int h;
	 static int locked;
	 static int num;
	 static int x1[];
	 static int y1[];
	 static int x2[];
	 static int y2[];
	 static int t[];
	 static int mat[][][];
	 static int di[] = new int[] { -1, 0, 1, 0, 0 };
	 static int dj[] = new int[] { 0, 1, 0, -1, 0 };
	
	 public static void main(String[] args) {
		  Scanner s = new Scanner(System.in);
		  int tc=1;
		  while (true) {
			   w = s.nextInt();
			   h = s.nextInt();
			   locked = s.nextInt();
			   if(w==0&&h==0&&locked==0)break;
			   num = s.nextInt();
			   x1 = new int[num];
			   y1 = new int[num];
			   x2 = new int[num];
			   y2 = new int[num];
			   t = new int[num];
	
			   mat = new int[locked + 2][h][w];
			   sols = new int[locked+2][h][w];
			   for (int i = 0; i < num; i++) {
				    t[i] = s.nextInt();
				    if (t[i] > max)
				     max = t[i];
				     x1[i] = s.nextInt() - 1;
				     y1[i] = s.nextInt() - 1;
				     x2[i] = s.nextInt() - 1;
				     y2[i] = s.nextInt() - 1;
				     for (int j = x1[i]; j <= x2[i]; j++) {
				         for (int j2 = y1[i]; j2 <= y2[i]; j2++) {
				        	 mat[t[i]][j2][j] = 1;
				         }
				    }
			   }
			   found = 0;
	//    print();
			   for (int i = 0; i < h; i++) {
				    for (int j = 0; j < w; j++) {
					     if (mat[locked][i][j] == 0){
					    	 sols[locked][i][j]++;
					    	 dfs(i, j, locked-1, "");
					     }
				    }
			   }
	//   print();
			   System.out.println("Robbery #"+tc+":");
			   tc++;
			   String times[] = rez.split(" ");
			   int t = 1;
			   int deduced=0;
			   if (found!=0)
				    for (int i = 1; i <=locked; i++) {
					     for (int j = 0; j < h; j++) {
						      for (int j2 = 0; j2 < w; j2++) {
							       if(sols[i][j][j2]==found){
							        System.out.println("Time step: "+i+": The robber has been at "+(j2+1)+","+(j+1)+".");
							        deduced++;
						       }
					      }
				     }
			   }
			   else if(found==0)
				   System.out.println("The robber has escaped.");
			   if(found!=0&&deduced==0)
				   System.out.println("Nothing known.");
			   System.out.println();
		  }
	 }
	 
	 static int found;
	 static String rez = "";
	 static int max = 0;
	 static int sols[][][];
	 
	 private static void dfs(int i, int j, int locked2, String rett) {
		  if (locked2 == 0) {
			   rett += j + " " + i;
			   found++;
			   rez = rett;
			   String times[]=rett.split(" ");
			//   System.out.println(rett);
			   int tt=1;
			   for (int c = times.length - 1; c >= 0; c -= 2) {
			    int a = Integer.parseInt(times[c]);
			    int b = Integer.parseInt(times[c - 1]);
			    sols[tt][a][b]++;
			    tt++;
			   }
			   
			   return;
		  }
		
		  for (int k = 0; k < 5; k++) {
			  	try {
				    int nexti = i + di[k];
				    int nextj = j + dj[k];
				    if (mat[locked2][nexti][nextj] == 0) {
				            dfs(nexti, nextj, locked2 - 1, rett + j + " " + i + " ");
				    }
			    }
			  	catch (Exception e) { /*DO NOTHING */ };
		  }
	 }
	
	 static void print() {
		  for (int i = 0; i <= locked; i++) {
			  System.out.println("Time: " + i);
			   for (int j = 0; j < h; j++) {
				    for (int j2 = 0; j2 < w; j2++) {
				    	System.out.print(sols[i][j][j2]);
				    }
				    System.out.println();
			   }
	      }
	 }

}

public class RobberyZivko {

}
