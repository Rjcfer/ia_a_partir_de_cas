package logger;

/**
 * Utility class for colored console printing.
 * Supports ANSI color codes for different text colors.
 * 
 * @author Ricardo Ferreira
 * @version 2.0
 */
public class Print {

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";

	/**
	 * Prints the specified message in green.
	 * 
	 * @param message The message to be printed.
	 * @param <T>     The type of the message.
	 */
	public static <T> void Green(T message) {
		System.out.println(ANSI_GREEN + message + ANSI_RESET);
	}

	/**
	 * Prints the specified error message in red.
	 * Feel free to change this to save errors before logging them.
	 *
	 * @param message The error message to be printed.
	 * @param <T>     The type of the error message.
	 */

	public static <T> void Error(T message) {
		System.out.println(ANSI_RED + message + ANSI_RESET);
	}

	/**
	 * Prints the specified message in yellow.
	 * 
	 * @param message The message to be printed.
	 * @param <T>     The type of the message.
	 */
	public static <T> void Yellow(T message) {
		System.out.println(ANSI_YELLOW + message + ANSI_RESET);
	}

	/**
	 * Prints the specified message in purple.
	 * 
	 * @param message The message to be printed.
	 * @param <T>     The type of the message.
	 */
	public static <T> void Purple(T message) {
		System.out.println(ANSI_PURPLE + message + ANSI_RESET);
	}

	/**
	 * Prints the specified message in orange.
	 * 
	 * @param message The message to be printed.
	 * @param <T>     The type of the message.
	 */
	public static <T> void Orange(T message) {
		System.out.println(ANSI_ORANGE + message + ANSI_RESET);
	}
}
