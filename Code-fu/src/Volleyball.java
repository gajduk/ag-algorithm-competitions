
public class Volleyball {
	
	public static void main(String[] args) {
		Volleyball v = new Volleyball();
		System.out.println(v.getResult("1111111111111112222222222222221111111111111111111"));
	}

	 public String getResult(String games) {
		 int serve = 1;
		 int pointsa = 0;
		 int pointsb = 0;
		 int set = 1;
		 String result = "";
		 for ( int i = 0 ; i < games.length() ; ++i ) {
			 char c = games.charAt(i);
			 if ( c == '1' ) {
				 if ( serve == 1 ) {
					 ++pointsa;
					 if ( pointsa >= 15 && pointsa >= pointsb+2 ) {
						 result+= pointsa+":"+pointsb+"-";
						 ++set;
						 pointsa = 0;
						 pointsb = 0;
						 if ( set % 2 == 1 ) {
							 serve = 1;
						 }
						 else {
							 serve = 2;
						 }
					 }
				 }
				 else {
					 serve = 1;
				 }
			 }
			 else {
				 if ( serve == 2 ) {
					 ++pointsb;
					 if ( pointsb >= 15 && pointsb >= pointsa+2 ) {
						 result+= pointsa+":"+pointsb+"-";
						 pointsa = 0;
						 pointsb = 0;
						 ++set;
						 if ( set % 2 == 1 ) {
							 serve = 1;
						 }
						 else {
							 serve = 2;
						 }
					 }
				 }
				 else {
					 serve = 2;
				 }
			 }
//			 System.out.println(pointsa+":"+pointsb);
		 }
		 return result.substring(0,result.length()-1);
	 }
	
}
