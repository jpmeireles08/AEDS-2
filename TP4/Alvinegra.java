import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

class NoAN {
    public boolean cor;
    public Personagem elemento;
    public NoAN esq, dir;

    public NoAN() {
        this(null);
    }

    public NoAN(Personagem elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(Personagem elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(Personagem elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreAlvinegra {
    private NoAN raiz;

    public ArvoreAlvinegra() {
        raiz = null;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(nome, raiz);
    }

    private boolean pesquisar(String nome, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (nome.compareTo(i.elemento.getName()) == 0) {
            resp = true;
            System.out.println(" SIM ");
        } else if (nome.compareTo(i.elemento.getName()) < 0) {
            System.out.print(" esq ");
            resp = pesquisar(nome, i.esq);
        } else {
            System.out.print(" dir ");
            resp = pesquisar(nome, i.dir);
        }
        return resp;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(NoAN i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(NoAN i) {
        if (i != null) {
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(NoAN i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
        }
    }

    public void inserir(Personagem elemento) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);
            } else {
                raiz.dir = new NoAN(elemento);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);

            } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
                raiz.dir = new NoAN(elemento);

            } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    private void inserir(Personagem elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
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

    public String formatAlternateNames(List<String> alternateNames) {
        String formattedString = String.join(", ", alternateNames);

        return "{" + formattedString + "}";
    }

}

public class Alvinegra {

    static Personagem[] array;
    static protected int comp = 0;
    static protected int mov = 0;

    public static void ler(Personagem[] array) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("/tmp/characters.csv",
                "r");

        String linha;

        arq.readLine();

        int i = 0;
        while ((linha = arq.readLine()) != null) {
            String[] posicao = linha.split(";");
            Personagem temp = new Personagem();
            temp.setId(posicao[0]);
            temp.setName(posicao[1]);
            String nomesAlternativosStr = posicao[2];
            String nomesRenamed = nomesAlternativosStr.replace("[", "").replace("]", "").replace("'", "");
            List<String> nomesAlternativos = Arrays.asList(nomesRenamed.split(", "));
            temp.setAlternateNames(nomesAlternativos);
            temp.setHouse(posicao[3]);
            temp.setAncestry(posicao[4]);
            temp.setSpecies(posicao[5]);
            temp.setPatronous(posicao[6]);
            if (posicao[7].equals("FALSO")) {
                temp.setHogwartsStaff(false);
            } else {
                temp.setHogwartsStaff(true);
            }
            if (posicao[8].equals("FALSO")) {
                temp.setHogwartsStudent(false);
            } else {
                temp.setHogwartsStudent(true);
            }
            temp.setActorName(posicao[9]);
            if (posicao[10].equals("FALSO")) {
                temp.setAlive(false);
            } else {
                temp.setAlive(true);
            }
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            Date data = formato.parse(posicao[12]);
            temp.setDateOfBirth(data);

            temp.setYearOfBirth(Integer.parseInt(posicao[13]));
            temp.setEyeColour(posicao[14]);
            temp.setGender(posicao[15]);
            temp.setHairColour(posicao[16]);
            if (posicao[17].equals("FALSO")) {
                temp.setWizard(false);
            } else {
                temp.setWizard(true);
            }

            array[i] = temp;
            i++;

        }

        arq.close();

    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        ArvoreAlvinegra arvore = new ArvoreAlvinegra();

        array = new Personagem[404];

        ler(array);

        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].getId().compareTo(id) == 0) {
                    arvore.inserir(array[i]);
                    i = array.length;
                }
            }
            id = sc.nextLine();
        }

        String nome = sc.nextLine();
        while (!nome.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                boolean resp;
                if (nome.compareTo(array[i].getName()) == 0) {
                    MyIO.print(nome + " => raiz ");
                    resp = arvore.pesquisar(nome);
                    if (resp == false) {
                        System.out.println(" NAO ");
                    }
                    i = array.length;
                }
            }
            nome = sc.nextLine();
        }
        sc.close();
    }
}