

public class C {
    private double num = 5.0;

    private double num1 = 5.0;

    private double num2 = 5.0;

    private double num3 = 5.0;

    public double cal(int num1, int num2, java.lang.String type, FizzBuzz fizzBuzz) {
        double temp = 0;
        if (type == "sum") {
            for (int i = 0; i <= (num); i++) {
                temp = temp + i;
            }
        } else
            if (type == "average") {
                for (int i = 0; i <= (num); i++) {
                    temp = temp + i;
                }
                temp = temp / ((num) - 1);
            } else {
                java.lang.System.out.println("Please enter the right type(sum or average)");
            }

        num1 = num2;
        return temp;
    }
}

