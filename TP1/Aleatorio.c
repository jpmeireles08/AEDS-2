#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

// Declarando as funções antes do main
void aleatoria(char palavra[]);
void sorteador(char palavra[], int i);

int main() {
    srand(4); // Gerando os caracteres de forma "aleatória" (Semente inicializada com 4)
    char palavra[100];

    while (true) {
        fgets(palavra, sizeof(palavra), stdin);

        if (strcmp(palavra, "FIM\n") == 0) {
            return 0;
        }

        sorteador(palavra, 0);
    }

    return 0;
}

// Função para trocar o caracter atual pelo caracter sorteado pela função rand
void sorteador(char palavra[], int i) {
    char x = (char)('a' + (rand() % 26));
    char y = (char)('a' + (rand() % 26));
    if (i < strlen(palavra)) {
        if (palavra[i] == x) {
            printf("%c", y);
        }
        else {
            printf("%c", palavra[i]);
        }
        sorteador(palavra, i + 1);
    }
}
