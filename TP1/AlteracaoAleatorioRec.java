import java.util.Random;

public class AlteracaoAleatorioRec {
    public static void main (String[] args) {
        String str = MyIO.readLine();
        char caracteres[] = str.toCharArray();
        while (str.equals("FIM") == false) {
            alterarAleatorio(str, caracteres, 0);
            for (int i = 0; i < caracteres.length; i++) {
                if (alterarAleatorio(str, caracteres, i)) {
                    MyIO.print(caracteres[i]);
                }
            }
            MyIO.println("");
            str = MyIO.readLine();
        }
    }

    
    static boolean alterarAleatorio (String str, char caracteres[], int i) {
        Random gerador = new Random();
        gerador.setSeed(4);
        int letra1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
        int letra2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));

        
        if (i == str.length()) {
            return true;
        } else if (str.charAt(i) == (char) letra1) {
            caracteres[i] = (char) letra2;
            return alterarAleatorio(str, caracteres, i + 1);
        } else {
            caracteres[i] = str.charAt(i);
            return alterarAleatorio(str, caracteres, i + 1);
        }
    }
}
