import java.util.Scanner;

public class CifraCesarRec {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = MyIO.readLine();

        while (str.equals("FIM") == false) {
            cifra(str, 0);
            MyIO.println("");
            str = MyIO.readLine();
        }

        sc.close();
    }

    static boolean cifra (String str, int i) {
        int letra;
        if (i == str.length()) {
            return true;
        } else {
            letra = (int) str.charAt(i) + 3;
            MyIO.print((char)letra);
            return cifra (str, i + 1);
        }
    }
}
