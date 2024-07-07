import java.io.RandomAccessFile;

public class ArquivoTP {
    public static void main (String[] args) throws Exception {
        double x;
        int n = MyIO.readInt();

        RandomAccessFile arq = new RandomAccessFile("ArqEntrada.txt", "rw"); // Abertura do arquivo //

        // Leitura dos numeros //
        for (int i = 0; i < n; i++) {
            x = MyIO.readDouble();
            arq.writeDouble(x);
        }

        arq.close();

        RandomAccessFile arq1 = new RandomAccessFile("ArqEntrada.txt", "rw"); // Abrindo novamente o arquivo //

        double y;

        // Impressao dos numeros invertidos //
        for (int i = 0; i < n; i++) {
            arq1.seek((n - 1 - i)*8);
            y = arq1.readDouble();
            if (y % 1 != 0) {
                MyIO.println(y);
            } else {
                MyIO.println((int)y);
            }
        }

        arq1.close();   
    }
}
