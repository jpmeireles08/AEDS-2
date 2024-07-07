#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);
    double x;

    FILE *arq = fopen("ArqEntrada.txt", "w"); // Abertura do arquivo de entrada //

    // Leitura dos números e escrita no arquivo //
    for (int i = 0; i < n; i++) {
        scanf("%lf", &x);
        fprintf(arq, "%.9lf\n", x); 
    }

    fclose(arq);

    FILE *arq1 = fopen("ArqEntrada.txt", "r");

    // Armazenar os números em um array
    double numeros[n];
    int i = 0;
    while (i < n && fscanf(arq1, "%lf", &numeros[i]) == 1) {
        i++;
    }

    fclose(arq1);

    // Impressão dos números invertidos
    for (int j = i - 1; j >= 0; j--) {
        if (numeros[j] == (int)numeros[j]) {
            printf("%.0lf\n", numeros[j]);
        } else {
            printf("%.9g\n", numeros[j]); 
        }
    }

    return 0;
}
