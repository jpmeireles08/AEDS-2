import java.util.Random;

public class AlteracaoAleatoria {
    public static void main(String[] args) {
        String str = MyIO.readLine();
        while (str.equals("FIM") == false) {
            gerar(str);
            MyIO.println(gerar(str));

            str = MyIO.readLine();
        }
    }

    static String gerar(String str) {
        Random gerador = new Random();
        gerador.setSeed(4);
        int letra1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
        int letra2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
        char caracteres[] = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == (char) letra1) {
                caracteres[i] = (char) letra2;
            } else {
                caracteres[i] = str.charAt(i);
            }
        }
        return new String(caracteres);
    }
}