package Chapter1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
  1.6.5 Graphical Editor
PC/UVa IDs: 110105/10267, Popularity: B, Success rate: low Level: 1
Graphical editors such as Photoshop allow us to alter bit-mapped images in the same
way that text editors allow us to modify documents. Images are represented as anM×N
array of pixels, where each pixel has a given color.
Your task is to write a program which simulates a simple interactive graphical editor.

Input
The input consists of a sequence of editor commands, one per line. Each command is
represented by one capital letter placed as the first character of the line. If the command
needs parameters, they will be given on the same line separated by spaces.
Pixel coordinates are represented by two integers, a column number between 1 ...M
and a row number between 1 ...N, where 1 <= M,N <= 250. The origin sits in the
upper-left corner of the table. Colors are specified by capital letters.

The editor accepts the following commands:

I M N Create a new M ×N image with all pixels initially colored
white (O).

C Clear the table by setting all pixels white (O). The size
remains unchanged.

L X Y C Colors the pixel (X,Y ) in color (C).

V X Y1 Y2 C  Draw a vertical segment of color (C) in column X, between
the rows Y 1 and Y 2 inclusive.

H X1 X2 Y C Draw a horizontal segment of color (C) in the row Y ,
between the columns X1 and X2 inclusive.

K X1 Y1 X2 Y2 C Draw a filled rectangle of color C, where (X1,Y 1) is the
upper-left and (X2,Y 2) the lower right corner.

F X Y C Fill the region R with the color C, where R is defined as
follows. Pixel (X,Y ) belongs to R. Any other pixel which
is the same color as pixel (X,Y ) and shares a common side
with any pixel in R also belongs to this region.

S Name Write the file name in MSDOS 8.3 format followed by the
contents of the current image.

X Terminate the session.

Output
On every command S NAME, print the filename NAME and contents of the current
image. Each row is represented by the color contents of each pixel. See the sample
output.
Ignore the entire line of any command defined by a character other than I, C, L,
V, H, K, F, S, or X, and pass on to the next command. In case of other errors, the
program behavior is unpredictable.

I 5 6
L 2 3 A
S one.bmp
G 2 3 J
F 3 3 J
V 2 3 4 W
H 3 4 2 Z
F 2 4 B
S two.bmp
X
 */

public class Problem5 {
	
	
	 public static void main ( String args[] ) {
		 test_file();
		// test_judge();
	 }
	 
	 public static void test_file () {
		 	FileInputStream inputFile = null;
		    try {
		    	inputFile = new FileInputStream("C:/Documents and Settings/Andrej Gajduk/My Documents/Dropbox/Програмирање/Папката програмирање у Ф/UVA judge test/chapter1 problem5.txt");
		    }
	    	catch (FileNotFoundException e) {
		      e.printStackTrace(System.err);
		      return;
		    }
	    	Scanner in = new Scanner(inputFile);
	    	String temp = in.nextLine();
			 while ( ! temp.equals("X") ) {
				 executeCommand(temp);
				 temp = in.nextLine();
			 }
	 }
	 
	 public static void test_judge () {
		 Scanner in = new Scanner(System.in);
		 String temp = in.nextLine();
		 while ( ! temp.equals("X") ) {
			 executeCommand(temp);
			 temp = in.nextLine();
		 }
	 }
	 
	
	 static void test() {
		 int max_x = (int) (Math.random()*100) + 1;
		 int max_y = (int) (Math.random()*100) + 1;
		 executeCommand("I "+max_x+" "+max_y);
		 for ( int i = 0 ; i < 100000 ; ++i ) {
			 int command = (int) (Math.random()*7);
			 //System.out.println("number generated="+command);
			 if ( command == 0 ) {
				 int x = (int)(Math.random()*max_x)+1;
				 int y = (int)(Math.random()*max_y)+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				 executeCommand("L "+x+" "+y+" "+c);
			 }
			 if ( command == 1 ) {
				 executeCommand("S test.bmp");
			 }
			 if ( command == 2 ) {
				 int x = (int)(Math.random()*max_x)+1;
				 int y = (int)(Math.random()*max_y)+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				 executeCommand("L "+x+" "+y+" "+c);
			 }
			 if ( command == 3 ) {
				 int x1 = (int)(Math.random()*(max_x/2))+1;
				 int x2 = (int)(Math.random()*(max_x-x1))+x1+1;
				 int y = (int)(Math.random()*max_y)+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				 executeCommand("H "+x1+" "+x2+" "+y+" "+c);
			 }
			 if ( command == 4 ) {
				 int x = (int)(Math.random()*max_x)+1;
				 int y1 = (int)(Math.random()*max_y/2)+1;
				 int y2 = (int)(Math.random()*(max_y-y1))+y1+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				 executeCommand("V "+x+" "+y1+" "+y2+" "+c);
			 }
			 if ( command == 5 ) {
				 int x1 = (int)(Math.random()*(max_x/2))+1;
				 int x2 = (int)(Math.random()*(max_x-x1))+x1;
				 int y1 = (int)(Math.random()*max_y/2)+1;
				 int y2 = (int)(Math.random()*(max_y-y1))+y1+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				// System.out.println("Attempting to rect fill");
				// System.out.println("X coordinates: "+x1+" "+x2);
				// System.out.println("Y coordinates: "+y1+" "+y2);
				// System.out.println("Size of picture: "+image.length+"X"+image[0].length);
				 executeCommand("K "+x1+" "+y1+" "+x2+" "+y2+" "+c);
			 }
			 if ( command == 6 ) {
				 int x1 = (int)(Math.random()*max_y)+1;
				 int y1 = (int)(Math.random()*max_x)+1;
				 char c = (char) (((int)(Math.random()*26))+'A');
				 executeCommand("F "+x1+" "+y1+" "+c);
				 
			 }
			 //executeCommand("S test.bmp");
		 }
		 executeCommand("X");
	 }
	 
	 static char[][] image = null;
	 
	 public static void executeCommand ( String command ) {
	        	int counter = 0;
	            if ( command.charAt(0) == 'I' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer m = new Integer(tkr.nextToken());
	                Integer n = new Integer(tkr.nextToken());
	                image = createNewImage(m,n);
	            }
	            if ( command.charAt(0) ==  'C') {
	                clearImage(image);
	            }
	            if ( command.charAt(0) == 'L' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                String t = tkr.nextToken();
	                int colour = t.charAt(0);
	                setPixel(image, x, y, colour);
	            }
	            if ( command.charAt(0) ==  'V' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y1 = new Integer(tkr.nextToken());
	                Integer y2 = new Integer(tkr.nextToken());
	                String t = tkr.nextToken();
	                int colour = t.charAt(0);
	                drawHoryzontalSegment(image, x, y1, y2, colour);
	            }
	            if ( command.charAt(0) ==  'H' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer x1 = new Integer(tkr.nextToken());
	                Integer x2 = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                String t = tkr.nextToken();
	                int colour = t.charAt(0);
	                drawVerticalSegment(image, x1, x2, y, colour);
	            }
	            if ( command.charAt(0) ==  'K' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer x1 = new Integer(tkr.nextToken());
	                Integer y1 = new Integer(tkr.nextToken());
	                Integer x2 = new Integer(tkr.nextToken());
	                Integer y2 = new Integer(tkr.nextToken());
	                String t = tkr.nextToken();
	                int colour = t.charAt(0);
	                drawRectangle(image, x1, x2, y1, y2, colour);
	            }
	            if ( command.charAt(0) ==  'F' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                String t = tkr.nextToken();
	                int colour = t.charAt(0);
	                bucketFill(image, x, y, colour);
	            }
	            if ( command.charAt(0) == 'S' ) {
	                StringTokenizer tkr = new StringTokenizer(command," ");
	                tkr.nextToken();
	                String name = tkr.nextToken();
	                output(name, image);
	            }
	            counter++;
	  }

	    
	

	 /*
	   public static void graphicalEditor ( String[] commands ) {
	        int counter = 0;
	        int[][] image = null;
	        while ( ! commands[counter].equalsIgnoreCase("X") ) {
	            if ( commands[counter].charAt(0) == 'I' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer m = new Integer(tkr.nextToken());
	                Integer n = new Integer(tkr.nextToken());
	                image = createNewImage(m,n);
	            }
	            if ( commands[counter].charAt(0) ==  'C') {
	                clearImage(image);
	            }
	            if ( commands[counter].charAt(0) == 'L' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                Integer colour = new Integer(tkr.nextToken());
	                setPixel(image, x, y, colour);
	            }
	            if ( commands[counter].charAt(0) ==  'V' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y1 = new Integer(tkr.nextToken());
	                Integer y2 = new Integer(tkr.nextToken());
	                Integer colour = new Integer(tkr.nextToken());
	                drawHoryzontalSegment(image, x, y1, y2, colour);
	            }
	            if ( commands[counter].charAt(0) ==  'H' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer x1 = new Integer(tkr.nextToken());
	                Integer x2 = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                Integer colour = new Integer(tkr.nextToken());
	                drawVerticalSegment(image, x1, x2, y, colour);
	            }
	            if ( commands[counter].charAt(0) ==  'K' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer x1 = new Integer(tkr.nextToken());
	                Integer x2 = new Integer(tkr.nextToken());
	                Integer y1 = new Integer(tkr.nextToken());
	                Integer y2 = new Integer(tkr.nextToken());
	                Integer colour = new Integer(tkr.nextToken());
	                drawRectangle(image, x1, x2, y1, y2, colour);
	            }
	            if ( commands[counter].charAt(0) ==  'F' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                Integer x = new Integer(tkr.nextToken());
	                Integer y = new Integer(tkr.nextToken());
	                Integer colour = new Integer(tkr.nextToken());
	                bucketFill(image, x, y, colour);
	            }
	            if ( commands[counter].charAt(0) == 'S' ) {
	                StringTokenizer tkr = new StringTokenizer(commands[counter]," ");
	                tkr.nextToken();
	                String name = tkr.nextToken();
	                output(name, image);
	            }
	            counter++;
	        }

	    }
	    */

	    public static char[][] createNewImage ( int m , int n ) {
	    	char[][] image1 = new char[n][m];
	        for ( int i = 0 ; i < n ; i++ ) {
	            for ( int j = 0 ; j < m ; j++ ) {
	                image1[i][j] = 'O';
	            }
	        }
	        return image1;
	    }

	    public static void clearImage ( char[][] image ) {
	         for ( int i = 0 ; i < image.length ; i++ ) {
	            for ( int j = 0 ; j < image[0].length ; j++ ) {
	                image[i][j] = 'O';
	            }
	        }
	    }

	    public static void setPixel ( char[][] image , int x , int y , int colour  ) {
	        image[y-1][x-1] = (char) colour;
	    }

	    public static void drawVerticalSegment ( char[][] image , int x1 , int x2 , int y , int colour ) {
	    	if ( x2 > x1 ) {
	    		for ( int i = x1-1 ; i <= x2-1 ; i++ ) image[y-1][i] = (char) colour;
	    	}
	    	else {
	    		for ( int i = x2-1 ; i <= x1-1 ; i++ ) image[y-1][i] = (char) colour;
	    	}
	    }

	    public static void drawHoryzontalSegment ( char[][] image , int x , int y1 , int y2 , int colour ) {
	    	if ( y2 > y1 ) {
	    		for ( int i = y1-1 ; i <= y2-1 ; i++ ) image[i][x-1] = (char) colour;
	    	}
	    	else {
	    		for ( int i = y2-1 ; i <= y1-1 ; i++ ) image[i][x-1] = (char) colour;
	    	}
	    }

	    public static void drawRectangle ( char[][] image , int x1 , int x2 , int y1 , int y2 , int colour ) {
	        for ( int i = y1-1 ; i <= y2-1 ; i++ ) {
	            for ( int j = x1-1 ; j <= x2-1 ; j++ ) {
	                image[i][j] = (char) colour;
	            }
	        }
	    }
	    
	    public static void bucketFill ( char[][] image , int x , int y , int colour ) {
	        LinkedList<Integer> queuex = new LinkedList<Integer>();
	        LinkedList<Integer> queuey = new LinkedList<Integer>();
	        int replace_colour = image[x-1][y-1];
	        /*
	        if ( ! check(image, x-1, y-1) ) {
	        
	        	System.out.println("Error in parametars");
	        }
	        */
	        int[][] flags = new int[image.length][image[0].length];
	        for ( int i = 0 ; i < image.length ; i++ ) {
	            for ( int j = 0 ; j < image[0].length ; j++ ) {
	                 flags[i][j] = 0;
	            }
	        }
	        int[] dx = { 0 , 0 , -1 , 1 };
	        int[] dy = { -1 , 1 , 0 , 0 };
	        queuex.add(y-1);
	        queuey.add(x-1);
	        while ( ! queuex.isEmpty() ) {
	            int curx = queuex.remove();
	            int cury = queuey.remove();
	            //System.out.println("Checking pixel"+curx+" "+cury);
	           // System.out.println("Current queue size="+queuex.size());
	            for ( int i = 0 ; i < dx.length ; i++ ) {
	                if ( check(image,curx+dx[i],cury+dy[i]) ) {
	                    if ( flags[curx+dx[i]][cury+dy[i]] == 0 && replace_colour == image[curx+dx[i]][cury+dy[i]] ) {
	                    	flags[curx+dx[i]][cury+dy[i]] = 1;
	                        queuex.add(curx+dx[i]);
	                        queuey.add(cury+dy[i]);
	                        
	                    }
	                }
	            }
	            
	            image[curx][cury] = (char) colour;
	        }
	    }

	    public static boolean check ( char[][] image , int x , int y ) {
	        return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
	    }

	    public static void output ( String name , char[][] image ) {
	    	System.out.println(name);
	        String []q = new String[image.length];
	        for ( int i = 0 ; i < image.length ; i++ ) {
	        	System.out.println(image[i]);
	        }
	    }

}
