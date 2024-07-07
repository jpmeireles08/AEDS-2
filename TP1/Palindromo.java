import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        while (str.equals("FIM") == false) {
            int cont = 0;
            for (int i = 0; i < str.length() / 2; i++) {
                if (str.charAt(i) == str.charAt(str.length() - i - 1)) {
                    cont++;
                }
            }

            if (str.length() / 2 == cont) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            str = sc.nextLine();
        }

        sc.close();
    }
}