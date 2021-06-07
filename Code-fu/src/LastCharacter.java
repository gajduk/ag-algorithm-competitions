
public class LastCharacter {
	
	public static void main(String[] args) {
		LastCharacter l = new LastCharacter();
		System.out.println(l.findLast("LASTONE", 2));
	}
	
	  public String findLast(String s, int N) {
		  int max = N*s.length();
		  int pos = 1;
		  while ( pos < max ) {
			  pos *= 2;
		  }
		  pos /= 2;
		  --pos;
		  int start = pos%s.length();
		  return s.charAt(start)+"";
	  }

}
