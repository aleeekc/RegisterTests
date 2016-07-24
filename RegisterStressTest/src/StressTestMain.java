public class StressTestMain {

	public static void main(String[] args) {

		int argument = Integer.parseInt(args[0].toString());

		for (int i = 0; i <= argument; i++) {

			StressTest test = new StressTest();
			test.run();
		}
	}

}
