
import java.net.*;
import java.io.*;

public class Html {
    public static void main(String[] args) throws Exception {
        String nome = MyIO.readLine();
        String parada = "FIM";
        if (nome.equals(parada) == true) {
            return;
        }

        String endereco = MyIO.readLine();
        while (nome.equals(parada) == false) {
            ImprimirDados(nome, html(endereco));
            nome = MyIO.readLine();
            if (nome.equals(parada) == true) {
                return;
            }
            endereco = MyIO.readLine();
        }

    }

    public static String html(String endereco) throws Exception {
        URL link = new URL(endereco);
        URLConnection ponte = link.openConnection();
        BufferedReader leitor = new BufferedReader(new InputStreamReader(ponte.getInputStream()));
        String aux = leitor.readLine();
        String conteudo = "";
        while (aux != null) {
            conteudo += aux;
            aux = leitor.readLine();
        }

        return conteudo;

    }

    public static boolean isConsoante(char caracter) {
        boolean resp = true;

        if (caracter == 97 || caracter == 101 || caracter == 105 || caracter == 111 || caracter == 117) {
            resp = false;
            return resp;
        } else {
            if (caracter > 96 && caracter < 123) {

            } else {
                resp = false;
                return resp;
            }
        }
        return resp;
    }

    public static void ImprimirDados(String nome, String conteudo) {
        int caracteres[] = new int[25];

        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] = 0;
        }

        for (int i = 0; i < conteudo.length(); i++) {
            if (conteudo.charAt(i) == 'a')
                caracteres[0]++;
            else if (conteudo.charAt(i) == 'e')
                caracteres[1]++;
            else if (conteudo.charAt(i) == 'i')
                caracteres[2]++;
            else if (conteudo.charAt(i) == 'o')
                caracteres[3]++;
            else if (conteudo.charAt(i) == 'u')
                caracteres[4]++;
            else if (conteudo.charAt(i) == '\u00E1')
                caracteres[5]++;
            else if (conteudo.charAt(i) == '\u00E9')
                caracteres[6]++;
            else if (conteudo.charAt(i) == '\u00ED')
                caracteres[7]++;
            else if (conteudo.charAt(i) == '\u00F3')
                caracteres[8]++;
            else if (conteudo.charAt(i) == '\u00FA')
                caracteres[9]++;
            else if (conteudo.charAt(i) == '\u00E0')
                caracteres[10]++;
            else if (conteudo.charAt(i) == '\u00E8')
                caracteres[11]++;
            else if (conteudo.charAt(i) == '\u00EC')
                caracteres[12]++;
            else if (conteudo.charAt(i) == '\u00F2')
                caracteres[13]++;
            else if (conteudo.charAt(i) == '\u00F9')
                caracteres[14]++;
            else if (conteudo.charAt(i) == '\u00E3')
                caracteres[15]++;
            else if (conteudo.charAt(i) == '\u00F5')
                caracteres[16]++;
            else if (conteudo.charAt(i) == '\u00E2')
                caracteres[17]++;
            else if (conteudo.charAt(i) == '\u00EA')
                caracteres[18]++;
            else if (conteudo.charAt(i) == '\u00EE')
                caracteres[19]++;
            else if (conteudo.charAt(i) == '\u00F4')
                caracteres[20]++;
            else if (conteudo.charAt(i) == '\u00FB')
                caracteres[21]++;
            else if (isConsoante(conteudo.charAt(i)))
                caracteres[22]++;

            else if (conteudo.charAt(i) == 60) {

                if (conteudo.charAt(i + 1) == 98 && conteudo.charAt(i + 2) == 114 && conteudo.charAt(i + 3) == 62) {
                    caracteres[23]++;
                }

                else

                if (conteudo.charAt(i + 1) == 116 && conteudo.charAt(i + 2) == 97 && conteudo.charAt(i + 3) == 98
                        && conteudo.charAt(i + 4) == 108 && conteudo.charAt(i + 5) == 101
                        && conteudo.charAt(i + 6) == 62) {
                    caracteres[24]++;
                }

            }

        }
        MyIO.println("a(" + caracteres[0] + ") e(" + caracteres[1] + ") i(" + caracteres[2] + ") o(" + caracteres[3]
                + ") u(" + caracteres[4] + ") á(" + caracteres[5] + ") é(" + caracteres[6] + ") í(" + caracteres[7]
                + ") ó(" + caracteres[8] + ") ú(" + caracteres[9] + ") à(" + caracteres[10] + ") è(" + caracteres[11]
                + ") ì(" + caracteres[12] + ") ò(" + caracteres[13] + ") ù(" + caracteres[14] + ") ã(" + caracteres[15]
                + ") õ(" + caracteres[16] + ") â(" + caracteres[17] + ") ê(" + caracteres[18] + ") î(" + caracteres[19]
                + ") ô(" + caracteres[20] + ") û(" + caracteres[21] + ") consoante(" + caracteres[22] + ") <br>("
                + caracteres[23] + ") <table>(" + caracteres[24] + ") " + nome);

    }

}
