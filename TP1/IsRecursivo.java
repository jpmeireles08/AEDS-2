public class IsRecursivo {
    public static void main(String[] args) {

        String str = MyIO.readLine();

        while (!str.equals("FIM")) {
            if (isVogalRec(str, 0)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isConsoanteRec(str, 0)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isInteiroRec(str, 0)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isRealRec(str, 0, 0)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO");
            }
            MyIO.println("");

            str = MyIO.readLine();

        }
    }

    static boolean isVogal(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
                    || str.charAt(i) == 'u') {
                cont++;
            }
        }

        if (cont == str.length()) {
            return true;
        } else {
            return false;
        }

    }

    static boolean isVogalRec(String str, int i) {

        if (i == str.length()) {
            return true;
        } else if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
                || str.charAt(i) == 'u') {
            return isVogalRec(str, i + 1);
        } else {
            return false;
        }

    }

    static boolean isConsoante(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' && str.charAt(i) != 'a' && str.charAt(i) != 'e'
                    && str.charAt(i) != 'i' && str.charAt(i) != 'o' && str.charAt(i) != 'u') {
                cont++;
            }
        }

        if (cont == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isConsoanteRec(String str, int i) {
        if (i == str.length()) {
            return true;
        } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' && str.charAt(i) != 'a' && str.charAt(i) != 'e'
                && str.charAt(i) != 'i' && str.charAt(i) != 'o' && str.charAt(i) != 'u') {
            return isConsoanteRec(str, i + 1);
        } else {
            return false;
        }

    }

    static boolean isInteiro(String str) {
        int cont = 0;
        int aux = 0;
        for (int i = 0; i < str.length(); i++) {
            char caractere = str.charAt(i);
            if (caractere >= '0' && caractere <= '9' && caractere != ',' && caractere != '.') {
                cont++;
            }
        }

        if (cont == str.length() && aux < 2) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isInteiroRec(String str, int i) {
        if (i == str.length()) {
            return true;
        } else {
            char caractere = str.charAt(i);

            if (caractere >= '0' && caractere <= '9' && caractere != ',' && caractere != '.') {
                return isInteiroRec(str, i + 1);
            } else {
                return false;
            }
        }
    }

    static boolean isReal(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char caractere = str.charAt(i);
            if (caractere >= (char) 48 && caractere <= (char) 57) {
                cont++;
            }
        }

        for (int j = 0; j < str.length(); j++) {
            char caractere = str.charAt(j);
            if (caractere == (char) 44 || caractere == (char) 46) {
                cont++;
                j = str.length();
            }
        }

        if (cont == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isRealRec(String str, int i, int cont) {
        if (i == str.length()) {
            return true;
        } else {
            char caractere = str.charAt(i);
            if (caractere >= (char) 48 && caractere <= (char) 57) {
                return isRealRec(str, i + 1, cont);
            } else if ((caractere == (char) 44 || caractere == (char) 46) && cont < 1) {
                return isRealRec(str, i + 1, cont + 1);
            } else {
                return false;
            }
        }
    }
}
