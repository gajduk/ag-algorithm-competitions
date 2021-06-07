
public class SmallestPair {
	public static int smallest(int[] numbers) {
        int smallest = 0;
        for ( int i = 0 ; i < numbers.length-1 ; i++ ) {
            if ( numbers[i]+numbers[i+1] < smallest ) smallest = numbers[i]+numbers[i+1];
        }
        return smallest;
    }
}
