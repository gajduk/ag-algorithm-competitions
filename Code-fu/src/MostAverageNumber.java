
public class MostAverageNumber {
	
	 public int mostAverage(int[] numbers) {
		    double avg = 0;
		    for (int i = 0; i < numbers.length; i++) {
				avg += numbers[i];
			}
		    avg /= numbers.length;
		    int closest = numbers[0];
		    for (int i = 1 ; i < numbers.length; i++) {
				if ( Math.abs(numbers[i]-avg) < Math.abs(closest-avg) ) {
					closest = numbers[i];
				}
				else {
					if ( Math.abs(numbers[i]-avg) == Math.abs(closest-avg) ) {
						if ( closest > numbers[i] ) closest = numbers[i];
					}
				}
			}
		    return closest;
	 }

}
