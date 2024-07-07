import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Personagem {

    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private String hogwartsStudent;
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
        hogwartsStudent = "";
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
            String patronus, boolean hogwartsStaff, String hogwartsStudent, String actorName,
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

    public void setHogwartsStudent(String hogwartsStudent) {
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

    public String getHogwartsStudent() {
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
                "false" + " ## " + "false" + " ## " + getActorName() + " ## " + "false"
                + " ## " + dataNascimentoFormatada + " ## " + getYearOfBirth() + " ## " +
                getEyeColour() + " ## " + getGender() + " ## " + getHairColour() + " ## " + "false" + "]");
    }

    public String formatAlternateNames(List<String> alternateNames) {
        String formattedString = String.join(", ", alternateNames);

        return "{" + formattedString + "}";
    }

}

public class HeapsortAntigo {

    static Personagem[] array;
    static protected int comp = 0;
    static protected int mov = 0;

    public static void ler(Personagem[] array) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("characters.csv",
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
                temp.setHogwartsStudent("false");
            } else {
                temp.setHogwartsStudent("true");
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

    public static void log(int comp, int mov, double fim) throws Exception {
        RandomAccessFile escrever = new RandomAccessFile("matrícula_insercao.txt", "rw");
        escrever.writeBytes("802151\t" + fim + "\t" + comp + "\t" + mov);
        escrever.close();
    }

    public static void sort(List<Personagem> subarray, int n) {
        Personagem[] tmp = new Personagem[n + 1];

        for (int i = 0; i < n; i++) {
            tmp[i + 1] = new Personagem();
            tmp[i + 1] = subarray.get(i);
        }
        array = tmp;

        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(subarray, tamHeap);
        }

        int tamHeap = n;
        while (tamHeap > 1) {
            Personagem temp = new Personagem();
            temp = subarray.get(1);
            subarray.set(1, subarray.get(tamHeap));
            subarray.set(tamHeap, temp);
            tamHeap--;
            reconstruir(subarray, tamHeap);
        }

        tmp = array;
        array = new Personagem[n];
        for (int i = 0; i < n; i++) {
            subarray.set(i, tmp[i + 1]);
        }
    }

    public static void construir(List<Personagem> subarray, int tamHeap) {
        for (int i = tamHeap; i > 1
                && (subarray.get(i).getHairColour().compareTo(subarray.get((i / 2)).getHairColour()) > 0); i /= 2) {
            Personagem tmp = subarray.get(i);
            subarray.set(i, subarray.get((i / 2)));
            subarray.set((i / 2), tmp);
        }
    }

    public static void reconstruir(List<Personagem> subarray, int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(subarray, i, tamHeap);
            if (subarray.get(i).getHairColour().compareTo(subarray.get(filho).getHairColour()) < 0) {
                Personagem tmp = new Personagem();
                tmp = subarray.set(i, subarray.get(filho));
                subarray.set(filho, tmp);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(List<Personagem> subarray, int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap
                || subarray.get(2 * i).getHairColour().compareTo(subarray.get(2 * i + 1).getHairColour()) > 0) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }

        return filho;
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        array = new Personagem[404];

        List<Personagem> subArray = new ArrayList<>();

        double comeco = System.currentTimeMillis();

        ler(array);

        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].getId().compareTo(id) == 0) {
                    subArray.add(array[i]);
                    i = array.length;
                }
            }
            id = sc.nextLine();
        }

        sort(subArray, subArray.size());

        for (int i = 0; i < subArray.size(); i++) {
            subArray.get(i).imprimir();
        }

        double fim = (System.currentTimeMillis() - comeco) / 1000;
        log(comp, mov, fim);
                        
        sc.close();

    }
}