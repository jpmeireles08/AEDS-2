import java.util.Scanner;

public class CifraCesar {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = MyIO.readLine();

        while (str.equals("FIM") == false) {
            codificar(str);

            str = MyIO.readLine();
        }

        sc.close();
    }

    public static void codificar (String str) {
        int letra;
        for (int i = 0; i < str.length(); i++) {
            letra = (int)str.charAt(i) + 3;
            MyIO.print((char)letra);
        }
        MyIO.println("");
    }
}
