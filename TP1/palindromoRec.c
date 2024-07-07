#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// parametrizando a função //
int isPalindromo(char palavra[], int tamanho, int x);

int main () {
    char palavra[100];

    while (true) {
        fgets(palavra, 100, stdin);
        palavra[strcspn(palavra, "\n")] = '\0'; /* Uso essa função para remover o caracter de nova linha e nao dar erro*/

        if (strcmp(palavra, "FIM") == 0) {
            break;
        }

        int tamanho = strlen(palavra);
        int x = 0;
        bool palindromo = isPalindromo(palavra, tamanho, x);

        if (palindromo) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}

// Função para verificar se a string é ou nao um palindrmo de forma recursiva //
int isPalindromo(char palavra[], int tamanho, int x) {
    if (x == tamanho) {
        return true;
    } else {
        if (palavra[x] == palavra[tamanho - x - 1]) {
            return isPalindromo(palavra, tamanho, x + 1);
        } else {
            return false;
        }
    }
}