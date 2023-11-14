package logger;

public class Print {
	// ANSI color codes
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_YELLOW = "\u001B[33m";

	public static <T> void Green(T message) {
		System.out.println(ANSI_GREEN + message + ANSI_RESET);
	}

	public static <T> void Red(T message) {
		System.out.println(ANSI_RED + message + ANSI_RESET);
	}

	public static <T> void Yellow(T message) {
		System.out.println(ANSI_YELLOW + message + ANSI_RESET);
	}
}
