public class Is {
    public static void main(String[] args) {

        String str = MyIO.readLine();


        while (!str.equals("FIM")) {
            if (isVogal(str)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isConsoante(str)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isInteiro(str)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (isReal(str)) {
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
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                cont++;
            }
        }

        if (cont == str.length()) {
            return true;
        } else {
            return false;
        }

    }

    static boolean isConsoante(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' && str.charAt(i) != 'a' && str.charAt(i) != 'e' && str.charAt(i) != 'i' && str.charAt(i) != 'o' && str.charAt(i) != 'u') {
                cont++;
            }
        }

        if (cont == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isInteiro (String str) {
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

    static boolean isReal (String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char caractere = str.charAt(i);
            if (caractere >= (char)48 && caractere <= (char)57) {
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
}