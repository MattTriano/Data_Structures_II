package sandbox;
public class Temp{
	
	
	
	public static String reverseString(String input, String output){
		if (input.length() ==0) return output;
		else {
			output = output + input.substring(input.length()-1, input.length());
			input = input.substring(0,input.length()-1);
			return reverseString(input, output);
		}	
	}
	
     public static void main(String[] args) {
    	 String x = "abcdef";
    	 String o = "";
    	 System.out.println("The reverse of "+x+" is " +reverseString(x,o));
	}
}