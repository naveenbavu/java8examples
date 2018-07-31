package com.java8;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
 
class FizzBuzz {
	
	 public List<String> fizzBuzz(int n) {
	        final IntFunction<String> 
	        func = i -> i % 15 == 0 ? "FizzBuzz" : (i % 3 == 0 ? "Fizz" : (i % 5 == 0 ? "Buzz" : String.valueOf(i)));
	        return IntStream.rangeClosed(1, n).mapToObj(func).collect(Collectors.toList());
	    }
	 
	 
	public static void main(String[] args) {
		PrintWriter writer = new PrintWriter(System.out);
		for (int i = 1; i <= 17; i++) {
			if (i % 5 == 0 && i % 3 == 0)
				writer.println("FizzBuzz");
			else if (i % 3 == 0)
				writer.println("Fizz");
			else if (i % 5 == 0)
				writer.println("Buzz");
			else
				writer.println(i);
 
		}
		writer.flush();
		writer.close();
	}
}