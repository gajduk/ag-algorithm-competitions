
public class FreeArtist {
	
	public static void main(String[] args) {
		FreeArtist f = new FreeArtist();
		
		String drops [] = {"5;5;0xFF0000"};
		System.out.println(f.averageColor(10, 10, drops));
	}

	  int picture[][];
	
	  public String averageColor(int width, int height, String[] drops) {
		  picture = new int[width][height];
		  for ( int i = 0 ; i < width ; ++i ) {
			  for ( int j = 0 ; j < height ; ++j ) {
				  picture[i][j] = 65536*256-1;
			  }
		  }
		  for ( int i = 0 ; i < drops.length ; ++i ) {
			  int i1 = Integer.parseInt(drops[i].substring(0,drops[i].indexOf(";")));
			  drops[i] = drops[i].substring(drops[i].indexOf(";")+1);
			  int j1 = Integer.parseInt(drops[i].substring(0,drops[i].indexOf(";")));
			  drops[i] = drops[i].substring(drops[i].indexOf(";")+1);
			  int colour = Integer.parseInt(drops[i].substring(2), 16);
			  int size = 1;
			  for ( int w = 0 ; w < 3 ; ++w ) {
				  colorSquare(i1-size/2, j1-size/2, size-1, colour);
				  ++size; ++size; 
//				  print(picture);
//				  System.out.println();
			  }
//			  print(picture);
		  }
		  long red = 0;
		  long green = 0;
		  long blue = 0;
		  for ( int i = 0 ; i < width ; ++i ) {
			  for ( int j = 0 ; j < height ; ++j ) {
				 red += picture[i][j]/65536;
				 green += (picture[i][j]%65536)/256;
				 blue += picture[i][j]%256;
			  }
		  }
		  red /= width*height;
		  green /= width*height;
		  blue /= width*height;
		  String res = Integer.toHexString((int)(red*65536+green*256+blue));
		  res = res.toUpperCase();
		  res = "0x"+res;
		  return res;
		  
	  }
	  
	  private void print(int[][] picture2) {
		  for ( int i = 0 ; i < picture2.length ; ++i ) {
			  for ( int j = 0 ; j <  picture2[0].length  ; ++j ) {
				  System.out.print(Integer.toHexString(picture2[i][j])+" ");
			  }
			  System.out.println();
		  }
	}

	public boolean colorSquare( int i1 , int j1 , int size , int colour ) {
		  boolean flag = false;
//		 System.out.println(i1+" "+j1+" "+size);
		  for ( int i = i1 ; i <= i1+size ; ++i ) {
			  if ( check(i,j1,picture) )  {
				  paint(colour,picture,i,j1,size/2);flag = true;
			  }
			  if ( check(i,j1+size,picture) )  {
				  paint(colour,picture,i,j1+size,size/2);flag = true;
			  }
		  }
		  for ( int j = j1+1 ; j < j1+size ; ++j ) {
			  if ( check(i1,j,picture) )  {
				  paint(colour,picture,i1,j,size/2);flag = true;
			  }
			  if ( check(i1+size,j,picture) )  {
				  paint(colour,picture,i1+size,j,size/2);
				  flag = true;
			  }
		  }
		  
		  return flag;
	  }

	private void paint(int colour, int[][] picture2, int i, int j , int mult) {
		picture2[i][j] = addAS(picture2[i][j],colour,mult);		
	}

	private int addAS(int c1, int c2 , int mult ) {
		long r = 1;
		for ( int i = 0 ; i < mult ; ++i )
			 r *= 2;
		--r;
		long red1 = c1/65536;
		long red2 = c2/65536;
		long red = (red1*r+red2)/(r+1L);
		
		long green1 = (c1%65536)/256;
		long green2 = (c2%65536)/256;
		long green = (green1*r+green2)/(r+1L);
		long blue1 = c1%256;
		long blue2 = c2%256;
		long blue = (blue1*r+blue2)/(r+1L);
		return (int)(red*65536L+green*256L+blue);
	}

	private boolean check(int i, int j, int[][] picture2) {
		return i >= 0 && j >=0 && i < picture2.length && j < picture2[0].length;
	}
}
