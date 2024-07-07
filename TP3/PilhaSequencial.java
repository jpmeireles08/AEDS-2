import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Pilha {

    private Personagem[] arrayPilha;
    private int n;

    public Pilha() {
        arrayPilha = new Personagem[500];
        n = 0;
    }

    public Pilha(int tamanho) {
        arrayPilha = new Personagem[tamanho];
        n = 0;
    }

    public void inserirTopo(Personagem newPersonagem) throws Exception {

        // validar insercao
        if (n >= arrayPilha.length) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > 0; i--) {
            arrayPilha[i] = arrayPilha[i - 1];
        }

        arrayPilha[0] = newPersonagem;
        n++;
    }


    public void remover() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Personagem resp = arrayPilha[0];
        System.out.println("(R) " + resp.getName());
        n--;

        for (int i = 0; i < n; i++) {
            arrayPilha[i] = arrayPilha[i + 1];
        }

    }

    public void mostrar() {
        System.out.println("[ Top ]");
        for (int i = 0; i < n; i++) {

            Date dataNascimento = arrayPilha[i].getDateOfBirth();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFormatada = formatoData.format(dataNascimento);
            MyIO.println("[" + i + " ## " + arrayPilha[i].getId() + " ## " + arrayPilha[i].getName() + " ## "
                    + formatAlternateNames(arrayPilha[i].getAlternateNames())
                    + " ## " + arrayPilha[i].getHouse()
                    + " ## " + arrayPilha[i].getAncestry() + " ## "
                    + arrayPilha[i].getSpecies() + " ## " + arrayPilha[i].getPatronus() + " ## " +
                    arrayPilha[i].getHogwartsStaff() + " ## " + arrayPilha[i].getHogwartsStudent() + " ## "
                    + arrayPilha[i].getActorName() + " ## " + arrayPilha[i].getAlive()
                    + " ## " + dataNascimentoFormatada + " ## " + arrayPilha[i].getYearOfBirth() + " ## " +
                    arrayPilha[i].getEyeColour() + " ## " + arrayPilha[i].getGender() + " ## "
                    + arrayPilha[i].getHairColour() + " ## " + arrayPilha[i].getWizard() + "]");
        }
        System.out.println("[ Bottom ]");
    }

    public String formatAlternateNames(List<String> alternateNames) {
        String formattedString = String.join(", ", alternateNames);

        return "{" + formattedString + "}";
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

public class PilhaSequencial {

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

        Pilha pilha = new Pilha();

        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].getId().compareTo(id) == 0) {
                    pilha.inserirTopo(array[i]);
                    i = array.length;
                }
            }
            id = sc.nextLine();
        }

        int modificacoes = sc.nextInt();
        sc.nextLine(); // Consumir a nova linha após o número de modificações

        String entrada;

        for (int i = 0; i < modificacoes; i++) {
            entrada = sc.nextLine();
            String[] split = entrada.split(" ");

            if (split[0].equals("I")) {
                for (int j = 0; j < array.length; j++) {
                    if (split[1].equals(array[j].getId())) {
                        pilha.inserirTopo(array[j]);
                        j = array.length;
                    }
                }
            } else if (split[0].equals("R")) {
                pilha.remover();
            } 
        }

        pilha.mostrar();

        sc.close();

    }
}