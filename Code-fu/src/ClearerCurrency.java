
public class ClearerCurrency {
	
	  public String prettyPrint(int value, String currency) {
		  String res = "";
		  String number = Integer.toString(value);
		  while ( number.length() > 3 ) {
			  res = number.substring(number.length()-3)+"'"+res;
			  number = number.substring(0,number.length()-3);
		  }
		  res = number+"'"+res+" "+currency;
		  res =  res.replaceAll("' ", " ");
		  return res;
	  }

}
