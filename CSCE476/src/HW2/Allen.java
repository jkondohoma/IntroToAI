package HW2;

/**
 * 
 * @author jaellekondohoma
 *
 */

public class Allen {

	public static void main(String[] args) {
		
	
		int dif = 2;
		
		System.out.println(
				"===================================================================================================");
		System.out.printf("Allen’s Time Relations (%d time point difference between intervals)\n",dif+1);
		System.out.println(
				"===================================================================================================");
		System.out.printf("%-7s | %-8s | %-5s | %-5s | %s | %-5s | %-5s | %-5s | %-5s | %-5s\n", "Interval 1",
				"Interval 2", "Meet", "Before", "After", "During", "Overlap", "Equals", "Finishes", "Contains");
		System.out.println(
				"===================================================================================================");

		
		for (int i = 5; i <= 10; i++) {
			for (int j = 7; j <= 13; j++) {

				int k = j + 1;

				if (k+dif < 14) {

					TimePoint a = new TimePoint(i);
					TimePoint b = new TimePoint(j);
					TimePoint c = new TimePoint(k+dif);

					Interval INT1 = new Interval(a, b);
					Interval INT2 = new Interval(a, c);

					String interval1 = "(" + (i) + "," + (j) + ")";
					String interval2 = "(" + (i) + "," + (k+dif) + ")";

					System.out.printf("%9s  | %8s   | %5s | %5s  | %5s | %5s  | %5s   | %5s | %5s     | %5s \n", interval1, interval2,
							Meet(INT1, INT2), Before(INT1, INT2), After(INT1, INT2), During(INT1, INT2),
							Overlap(INT1, INT2), Equals(INT1, INT2), Finishes(INT1, INT2),Contains(INT1, INT2));
					System.out.println(
							"___________________________________________________________________________________________________");

				}
			}
		}

	}

	public static int Start(Interval start) {

		return start.getBeginTime().getSec();

	}

	public static int End(Interval end) {
		return end.getEndTime().getSec();

	}

	/**
	 * End(i) = Start(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Meet(Interval i, Interval j) {
		return End(i) == Start(j);

	}

	/**
	 * End(i) < Start(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Before(Interval i, Interval j) {

		return End(i) < Start(j);

	}

	/**
	 * Before(j,i)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean After(Interval i, Interval j) {
		return Before(i, j);

	}

	/**
	 * Start(j) LEQ Start(i) AND End(i) LEQ End(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean During(Interval i, Interval j) {
		return ((Start(j) <= Start(i)) && (End(i) <= End(j)));

	}

	/**
	 * there exists k where During(k,i) AND During(k, j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Overlap(Interval i, Interval j) {
		int k = 1;
		boolean overlap = false;
		int start = Start(i);
		int end = End(j);

		while ((k <= start) && (k <= end)) {

			for (int x = 1; x <= start; x++) {
				for (int y = 1; y <= end; y++) {

					TimePoint a = new TimePoint(x);
					TimePoint b = new TimePoint(y);
					TimePoint c = new TimePoint(k);

					Interval INT1 = new Interval(a, c);
					Interval INT2 = new Interval(b, c);

					if ((During(INT1, INT2) == true) && (During(INT1, INT2) == true)) {
						overlap = false;
						//System.out.printf("%d %d\n",x,y);
						//break;

					}
					k++;
				}
				
			}
			
		}

		return overlap;

	}

	/**
	 * Start(i) = Start(j) AND End(i) = End(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Equals(Interval i, Interval j) {
		return ((Start(i) == Start(j))) && (End(i) == End(j));

	}

	/**
	 * End(i) = End(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Finishes(Interval i, Interval j) {
		return End(i) == End(j);

	}

	/**
	 * Start(i) < Start(j) AND End(i) > End(j)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean Contains(Interval i, Interval j) {
		return ((Start(i) < Start(j)) && (End(i) < End(j)));

	}

}
