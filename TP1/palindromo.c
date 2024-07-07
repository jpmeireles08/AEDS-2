#include <stdio.h>
#include <stdbool.h>
#include <string.h>

// Metodo booleano que retorna true caso a string recebida como parametro seja um palindromo e, caso contrario, retorna false.
bool Ispalindromo(char texto[100])
{
    int sum = 0;
    int cont = 0;

    while (texto[cont] != NULL)
    {
        cont++;
    }
    cont = cont - 1;

    for (int i = 0; i < cont; i++)
    {
        if (texto[i] == texto[cont - i - 1])
        {
            sum++;
        }
        else
        {
        }
    }

    if (cont == sum)
    {
        printf("SIM\n");
        return true;
    }
    else
    {
        printf("NAO\n");
        return false;
    }
}

int main()
{
    char texto[100];

    while (1)
    {
        fgets(texto, 100, stdin);

        if (strcmp(texto, "FIM\n") == 0)
        {
            return 0;
        }
        else
        {
            Ispalindromo(texto);
        }
    }
}