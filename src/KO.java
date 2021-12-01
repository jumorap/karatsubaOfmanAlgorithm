import java.util.Scanner;

public class KO {
    public static String padLeftAtZeros(String s, int space) {
        // Pads the string s with zeros at the left until it has the length space
        return String.format("%" + space + "s", s).replace(' ', '0');
    }

    public static long multiply(String x, String y) {
        int nx = x.length();
        int ny = y.length();
        int n = nx;
        int offset = 0;
        int floor, ceil, discountN;
        long resultA, resultB, resultC;
        String a, b, c, d;

        // If is a simple multiplication with at least 1 number of length 1, will return the result immediately
        if (nx == 1 || ny == 1) {
            return Long.parseLong(x) * Long.parseLong(y);
        }

        // Is added 0s to the left of the numbers to make them the same length x and y
        if (nx > ny) {
            y = padLeftAtZeros(y, nx);
        } else if (nx < ny) {
            x = padLeftAtZeros(x, ny);
            n = ny;
        }

        // When n is even, n++ to make it odd and offset = 1
        if ((n % 2) != 0) {
            n += 1;
            offset = 1;
        }

        // Get s the floor and ceil of the number of digits of the result
        floor = (int) Math.floor((float) n / 2) - offset;
        ceil = (int) Math.ceil((float) n / 2) - offset;

        // Get the number of digits to be discounted from the limit at index of substrings
        discountN = n % Math.max(nx, ny);

        // Get the substrings of the numbers
        a = x.substring(0, floor);
        b = x.substring(ceil, n - discountN);
        c = y.substring(0, floor);
        d = y.substring(ceil, n - discountN);

        // Multiply the substrings
        resultA = (long) (Math.pow(10, n) * multiply(a, c));
        resultB = (long) Math.pow(10, ((float) n / 2)) * (multiply(a, d) + multiply(b, c));
        resultC = multiply(b, d);

        // Add the results
        return resultA + resultB + resultC;
    }

    public static void main(String[] args) {
        // User input
        System.out.println("Karatsuba-Ofman multiplication.\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        String x = sc.nextLine();
        System.out.print("Enter the second number: ");
        String y = sc.nextLine();
        System.out.println("The result is: " + multiply(x, y));
    }
}
