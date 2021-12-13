package HW5;

public class Main {
    public static void main(String[] args) {
        System.out.println(power(3.35, -5));
    }

    private static double power(double num, int pow) {
        if (Math.abs(pow) == 1) {
            return num;
        }

        double result = num * power(num, Math.abs(pow) - 1);
        return pow < 0 ? 1 / result : result;
    }


}
