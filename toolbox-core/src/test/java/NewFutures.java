import java.time.Clock;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NewFutures {
	public static void main(String[] args) {

		MonthDay md = MonthDay.now();
		YearMonth ym = YearMonth.now();
		Clock utc = Clock.systemUTC();

		Random rnd = new Random();

		for (int idx = 0; idx < 10; idx++) {
			// System.out.println(rnd.nextDouble());
		}

		ThreadLocalRandom th = ThreadLocalRandom.current();
		for (int idx = 0; idx < 10; idx++)
			System.out.println(th.nextDouble());

	}
}
