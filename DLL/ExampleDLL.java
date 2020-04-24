import java.util.Scanner;

public class ExampleDLL {
	static {
		System.loadLibrary("ExampleDLL");
	}

	public native int mul(int num1, int num2);

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int num1 = 0, num2 = 0;
		System.out.println("DLL - Multipication of Two numbers");
		System.out.print("Enter the first number: ");
		num1 = scanner.nextInt();
		System.out.print("Enter the second number: ");
		num2 = scanner.nextInt();
		System.out.println("Multiplication Result is: "+new ExampleDLL().mul(num1,num2));
	}
}
