package lecture1;
import java.util.Scanner;

public class NumberAdder {
	public static void main(String[] args) 	{	
		int sum=0;
		Scanner sc = new Scanner(System.in);

		while(sc.hasNextInt()) {
			sum += sc.nextInt();
			System.out.println(sum);
		}
	}
}