import java.util.Scanner;

public class PalindromoRec {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        while (str.equals("FIM") == false) {

            if (isPalindromo(str, 0)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            str = sc.nextLine();
        }

        sc.close();
    }

    public static boolean isPalindromo(String str, int i) {
        if (i == str.length() / 2) {
            return true;
        } else if (str.charAt(i) == str.charAt(str.length() - i - 1)) {
            return isPalindromo(str, i + 1);
        } else {
            return false;
        }

    }
}
