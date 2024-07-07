import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

class Hash {
    String tabela[];
    int tamTab, m1, m2;
    final String NULO = "NULO";

    public Hash() {
        this(21, 9);
    }

    public Hash(int tamTab, int reserva) {
        this.tamTab = tamTab;
        this.m1 = tamTab;
        this.m2 = reserva;
        this.tabela = new String[tamTab + reserva];
        Arrays.fill(this.tabela, NULO);
    }

    public int h1(String elemento) {
        int soma = 0;
        for (char ch : elemento.toCharArray()) {
            soma += (int) ch;
        }
        return soma % tamTab;
    }

    public int h2(String elemento) {
        int soma = 0;
        for (char ch : elemento.toCharArray()) {
            soma += (int) ch;
        }
        return (soma + 1) % tamTab;
    }

    public boolean inserir(String elemento) {
        boolean resp = false;
        if (!elemento.equals(NULO)) {
            int pos = h1(elemento);
            if (tabela[pos].equals(NULO)) {
                tabela[pos] = elemento;
                resp = true;
            } else {
                pos = h2(elemento);
                if (tabela[pos].equals(NULO)) {
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;
        int pos = h1(elemento);
        if (tabela[pos].equals(elemento)) {
            resp = true;
            System.out.println(elemento + " (pos: " + pos + ") SIM");
        } else {
            pos = h2(elemento);
            if (tabela[pos].equals(elemento)) {
                resp = true;
                System.out.println(elemento + " (pos: " + pos + ") SIM");
            }
        }
        if (!resp) {
            System.out.println(elemento + " NAO");
        }
        return resp;
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

    public void ler(String id) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("/tmp/characters.csv",
                "r");

        String linha;

        arq.readLine();

        while ((linha = arq.readLine()) != null) {
            String[] posicao = linha.split(";");

            if (posicao.length >= 17 && posicao[0].equals(id)) {
                setId(id);
                setName(posicao[1]);
                String nomesAlternativosStr = posicao[2];
                String nomesRenamed = nomesAlternativosStr.replace("[", "").replace("]", "").replace("'", "");
                List<String> nomesAlternativos = Arrays.asList(nomesRenamed.split(", "));
                setAlternateNames(nomesAlternativos);
                setHouse(posicao[3]);
                setAncestry(posicao[4]);
                setSpecies(posicao[5]);
                setPatronous(posicao[6]);
                if (posicao[7].equals("FALSO")) {
                    setHogwartsStaff(false);
                } else {
                    setHogwartsStaff(true);
                }
                if (posicao[8].equals("FALSO")) {
                    setHogwartsStudent(false);
                } else {
                    setHogwartsStudent(true);
                }
                setActorName(posicao[9]);
                if (posicao[10].equals("FALSO")) {
                    setAlive(false);
                } else {
                    setAlive(true);
                }
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                Date data = formato.parse(posicao[12]);
                setDateOfBirth(data);

                setYearOfBirth(Integer.parseInt(posicao[13]));
                setEyeColour(posicao[14]);
                setGender(posicao[15]);
                setHairColour(posicao[16]);
                if (posicao[17].equals("FALSO")) {
                    setWizard(false);
                } else {
                    setWizard(true);
                }

            }
        }

        arq.close();

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

public class Rehash {

    static Personagem[] array;

    public static void ler(Personagem[] array) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("/tmp/characters.csv", "r");

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

        array = new Personagem[404];

        ler(array);

        Hash hash = new Hash();

        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].getId().compareTo(id) == 0) {
                    hash.inserir(array[i].getName());
                    break;
                }
            }
            id = sc.nextLine();
        }

        String nome = sc.nextLine();
        while (!nome.equals("FIM")) {
            hash.pesquisar(nome);
            nome = sc.nextLine();
        }

        sc.close();
    }
}


