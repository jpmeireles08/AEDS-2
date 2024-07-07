import java.text.SimpleDateFormat;
import java.util.*;

class Celula {
    public Celula dir;
    public Celula esq;
    public Celula cima;
    public Celula baixo;
    int valor;

    public Celula() {
        this(0);
    }

    public Celula(int valor) {
        this.valor = valor;
        dir = esq = cima = baixo = null;
    }

}

class Matriz {
    private Celula primeiro;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        primeiro = new Celula(0);
        Celula i = primeiro;
        Celula j = primeiro;
        Celula iAnt = primeiro;
        int jcont = 0;

        for (int k = 1; k <= this.linha; k++) {
            for (int l = 1; l < this.coluna; l++, iAnt = i.cima) {
                i.dir = new Celula();
                i.dir.esq = i;
                if (jcont != 0) {
                    i.dir.cima = iAnt.dir;
                    iAnt.dir.baixo = i.dir;
                }
                i = i.dir;
            }
            jcont++;
            if (jcont == this.linha) {
                i = j = iAnt = null;
                break;
            }
            j.baixo = new Celula();
            j.baixo.cima = j;
            j = j.baixo;
            i = j;
            iAnt = i.cima;
        }
    }

    private Celula getNode(int linha, int coluna) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {

            return null;
        }
        Celula node = primeiro;
        for (int i = 0; i < linha; i++) {
            node = node.baixo;
        }
        for (int j = 0; j < coluna; j++) {
            node = node.dir;
        }
        return node;
    }

    public int obterElemento(int linha, int coluna) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {

            return 0;
        }

        Celula node = getNode(linha, coluna);
        return (node != null) ? node.valor : 0;
    }

    public void atualizarElemento(int linha, int coluna, int valor) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {

            return;
        }

        Celula node = getNode(linha, coluna);
        if (node != null) {
            node.valor = valor;
        }
    }

    public void lerMatriz(Scanner sc) {
        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                int valor = sc.nextInt();
                this.atualizarElemento(i, j, valor);
            }
            sc.nextLine();
        }
    }

    public void imprimirMatriz() {

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                System.out.print(this.obterElemento(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Matriz soma(Matriz outraMatriz) {
        if (this.linha != outraMatriz.linha || this.coluna != outraMatriz.coluna) {
            return null;
        }

        Matriz resultado = new Matriz(this.linha, this.coluna);

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                int valorSoma = this.obterElemento(i, j) + outraMatriz.obterElemento(i, j);
                resultado.atualizarElemento(i, j, valorSoma);
            }
        }

        return resultado;
    }

    public Matriz multiplicacao(Matriz outraMatriz) {
        if (this.coluna != outraMatriz.linha) {

            return null;
        }

        Matriz resultado = new Matriz(this.linha, outraMatriz.coluna);

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < outraMatriz.coluna; j++) {
                int valorMultiplicacao = 0;
                for (int k = 0; k < this.coluna; k++) {
                    valorMultiplicacao += this.obterElemento(i, k) * outraMatriz.obterElemento(k, j);
                }
                resultado.atualizarElemento(i, j, valorMultiplicacao);
            }
        }

        return resultado;
    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, i) + " ");
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, this.coluna - 1 - i) + " ");
        }
        System.out.println();
    }

    public void imprimirDiagonais() {

        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, i) + " ");
        }
        System.out.println();

        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, this.coluna - 1 - i) + " ");
        }
        System.out.println();
    }

}

class Personagem {

    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem() {
        id = "";
        name = "";
        alternateNames = new ArrayList<>();
        house = "";
        ancestry = "";
        species = "";
        patronus = "";
        hogwartsStaff = true;
        hogwartsStudent = true;
        actorName = "";
        alive = true;
        dateOfBirth = new Date();
        yearOfBirth = 0;
        eyeColour = "";
        gender = "";
        hairColour = "";
        wizard = true;
    }

    public Personagem(String id, String name, List<String> alternatNames, String house, String ancestry, String species,
            String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
            boolean alive, Date dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
            boolean wizard) {

        this.id = id;
        this.name = name;
        this.alternateNames = alternatNames;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPatronous(String patronus) {
        this.patronus = patronus;
    }

    public void setHogwartsStaff(Boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public void setHogwartsStudent(Boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public void setWizard(Boolean wizard) {
        this.wizard = wizard;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAlternateNames() {
        return alternateNames;
    }

    public String getHouse() {
        return house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public String getPatronus() {
        return patronus;
    }

    public boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public boolean getAlive() {
        return alive;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public boolean getWizard() {
        return wizard;
    }

    public void imprimir() {

        Date dataNascimento = getDateOfBirth();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        String dataNascimentoFormatada = formatoData.format(dataNascimento);

        System.out.println("[" + getId() + " ## " + getName() + " ## " + formatAlternateNames(getAlternateNames())
                + " ## " + getHouse()
                + " ## " + getAncestry() + " ## "
                + getSpecies() + " ## " + getPatronus() + " ## " +
                getHogwartsStaff() + " ## " + getHogwartsStudent() + " ## " + getActorName() + " ## " + getAlive()
                + " ## " + dataNascimentoFormatada + " ## " + getYearOfBirth() + " ## " +
                getEyeColour() + " ## " + getGender() + " ## " + getHairColour() + " ## " + getWizard() + "]");
    }

    public String formatAlternateNames(List<String> alternateNames) {
        String formattedString = String.join(", ", alternateNames);

        return "{" + formattedString + "}";
    }

}

public class MatrizFlex {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        while (n != 0) {
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();
            sc.nextLine();

            Matriz matriz1 = new Matriz(l1, c1);
            matriz1.lerMatriz(sc);

            int l2 = sc.nextInt();
            int c2 = sc.nextInt();
            sc.nextLine();

            Matriz matriz2 = new Matriz(l2, c2);
            matriz2.lerMatriz(sc);

            matriz1.imprimirDiagonais();

            Matriz matrizSoma = matriz1.soma(matriz2);
            if (matrizSoma != null) {
                matrizSoma.imprimirMatriz();
            }

            Matriz matrizMultiplicacao = matriz1.multiplicacao(matriz2);
            if (matrizMultiplicacao != null) {
                matrizMultiplicacao.imprimirMatriz();
            }
            n--;

        }
        sc.close();

    }
}