
public class MobileOperators {
	
	public String mostlyUsed(String[] numbers) {
		int oa = 0 ,ob = 0 ,oc = 0;
		for ( String number : numbers ) {
			if ( number.startsWith("071") || number.startsWith("070") || number.startsWith("072") ) {
				++oa;
			}
			if ( number.startsWith("075") || number.startsWith("076") ) {
				++ob;
			}
			if ( number.startsWith("077") || number.startsWith("078") ) {
				++oc;
			}
		}
		
		if ( oa >= ob && oa >= oc )
			return "Operator A";
		if ( ob >= oc ) 
			return "Operator B";
		return "Operator C";
	}

}
