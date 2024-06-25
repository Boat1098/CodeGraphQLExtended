

public class FizzBuzz {
    public void printFizzBuzz(int k) {
        if ((k % 15) == 0)
            java.lang.System.out.println("FizzBuzz");
        else
            if ((k % 5) == 0)
                java.lang.System.out.println("Buzz");
            else
                if ((k % 3) == 0)
                    java.lang.System.out.println("Fizz");
                else
                    java.lang.System.out.println(k);



    }

    public void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++)
            printFizzBuzz(i);

    }

    public void Test() {
        for (int i = 1; i <= 100; i++)
            printFizzBuzz(i);

    }
}

