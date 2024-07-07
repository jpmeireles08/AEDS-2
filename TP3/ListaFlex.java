import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

class Celula {
    public Personagem novoPersonagem;
    public Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Personagem personagem) {
        this.novoPersonagem = personagem;
        this.prox = null;
    }

}

class Lista {
    private static Celula primeiro;
    private static Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(Personagem x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Personagem x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        System.out.println("(R) " + primeiro.novoPersonagem.getName());
        tmp.prox = null;
        tmp = null;
    }

    public void removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        System.out.println("(R) " + ultimo.novoPersonagem.getName());
        ultimo = i;
        i = ultimo.prox = null;

    }

    public void inserir(Personagem x, int pos) throws Exception {

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {

            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public void remover(int pos) throws Exception {
        int tamanho = tamanho();
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / tamanho = " + tamanho + " invalida!");
        } else if (pos == 0) {
            removerInicio();
        } else if (pos == tamanho - 1) {
            removerFim();
        } else {

            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = i.prox;

            System.out.println("(R) " + tmp.novoPersonagem.getName());
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

    }

    public static void mostrar() {

        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            Date dataNascimento = i.novoPersonagem.getDateOfBirth();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFormatada = formatoData.format(dataNascimento);

            MyIO.println(
                    "[" + j + " ## " + i.novoPersonagem.getId() + " ## " + i.novoPersonagem.getName() + " ## "
                            + formatAlternateNames(i.novoPersonagem.getAlternateNames())
                            + " ## " + i.novoPersonagem.getHouse()
                            + " ## " + i.novoPersonagem.getAncestry() + " ## "
                            + i.novoPersonagem.getSpecies() + " ## " + i.novoPersonagem.getPatronus() + " ## " +
                            i.novoPersonagem.getHogwartsStaff() + " ## " + i.novoPersonagem.getHogwartsStudent() + " ## "
                            + i.novoPersonagem.getActorName() + " ## " + i.novoPersonagem.getAlive()
                            + " ## " + dataNascimentoFormatada + " ## " + i.novoPersonagem.getYearOfBirth() + " ## " +
                            i.novoPersonagem.getEyeColour() + " ## " + i.novoPersonagem.getGender() + " ## "
                            + i.novoPersonagem.getHairColour() + " ## " + i.novoPersonagem.getWizard() + "]");
        }

    }

    public static int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

    public static String formatAlternateNames(List<String> alternateNames) {
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

public class ListaFlex {

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

        array = new Personagem[404];


        ler(array);

        Lista lista = new Lista();

        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].getId().compareTo(id) == 0) {
                    lista.inserirFim(array[i]);
                    i = array.length;
                }
            }
            id = sc.nextLine();
        }

        int modificacoes = sc.nextInt();
        sc.nextLine();

        String entrada;

        for (int i = 0; i < modificacoes; i++) {
            entrada = sc.nextLine();
            String[] split = entrada.split(" ");

            if (split[0].equals("II")) {
                for (int j = 0; j < array.length; j++) {
                    if (split[1].equals(array[j].getId())) {
                        lista.inserirInicio(array[j]);
                        j = array.length;
                    }
                }
            } else if (split[0].equals("IF")) {
                for (int j = 0; j < array.length; j++) {
                    if (split[1].equals(array[j].getId())) {
                        lista.inserirFim(array[j]);
                        j = array.length;
                    }
                }
            } else if (split[0].equals("I*")) {
                for (int j = 0; j < array.length; j++) {
                    if (split[2].equals(array[j].getId())) {
                        lista.inserir(array[j], Integer.parseInt(split[1]));
                        j = array.length;
                    }
                }
            } else if (split[0].equals("RI")) {
                lista.removerInicio();
            } else if (split[0].equals("RF")) {
                lista.removerFim();
            } else if (split[0].equals("R*")) {
                lista.remover(Integer.parseInt(split[1]));
            }
        }

        Lista.mostrar();

        
        sc.close();

    }
}