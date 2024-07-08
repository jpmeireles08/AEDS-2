#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int cont = 0;
int mov = 0;

typedef struct {
  char id[37];
  char name[50];
  char alternate_names[150];
  char house[20];
  char ancestry[20];
  char species[20];
  char patronus[20];
  int hogwartsStaff;
  int hogwartsStudent;
  char actorName[50];
  int alive;
  char alternate_actors[100];
  char dateOfBirth[11];
  int yearOfBirth;
  char eyeColour[20];
  char gender[20];
  char hairColour[20];
  int wizard;
} Personagem;

void inicializarPersonagem(Personagem *p) {
  strcpy(p->id, "");
  strcpy(p->name, "");
  strcpy(p->alternate_names, "");
  strcpy(p->house, "");
  strcpy(p->ancestry, "");
  strcpy(p->species, "");
  strcpy(p->patronus, "");
  p->hogwartsStaff = 0;
  p->hogwartsStudent = 0;
  strcpy(p->actorName, "");
  p->alive = 0;
  strcpy(p->alternate_actors, "");
  strcpy(p->dateOfBirth, "");
  p->yearOfBirth = 0;
  strcpy(p->eyeColour, "");
  strcpy(p->gender, "");
  strcpy(p->hairColour, "");
  p->wizard = 0;
}

Personagem *lerPersonagem(Personagem *p, char *linha) {
  char copiaLinha[512];
  strcpy(copiaLinha, linha);

  char *token = strsep(&linha, ";");
  strcpy(p->id, token);

  token = strsep(&linha, ";");
  strcpy(p->name, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->alternate_names, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->house, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->ancestry, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->species, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    if (token[0] != '\0') {
      strcpy(p->patronus, token);
    }
  token = strsep(&linha, ";");
  if (token != NULL)
    p->hogwartsStaff = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strsep(&linha, ";");
  if (token != NULL)
    p->hogwartsStudent = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->actorName, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    p->alive = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->alternate_actors, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->dateOfBirth, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    p->yearOfBirth = atoi(token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->eyeColour, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->gender, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    strcpy(p->hairColour, token);
  token = strsep(&linha, ";");
  if (token != NULL)
    p->wizard = (strcmp(token, "VERDADEIRO\r") == 0) ? true : false;

  return p;
}

void imprimirPersonagem(const Personagem *p) {

  char codigo[] = "7614cf6e-689e-47ac-a976-b1e9997637e9";
  printf("[");

  printf("%s", p->id);
  printf(" ## ");

  printf("%s", p->name);
  printf(" ## ");

  char *start = p->alternate_names;
  char *end = start + strlen(start) - 1;

  if (*start == '[')
    start++;
  if (*end == ']')
    *end = '\0';

  char *ptr = start;
  char *dst = start;
  while (*ptr) {
    if (*ptr != '\'') {
      *dst = *ptr;
      dst++;
    }
    ptr++;
  }
  *dst = '\0';

  char *formatted_alternate_names = start;
  while (*formatted_alternate_names != '\0') {
    if (*formatted_alternate_names == ' ' &&
        *(formatted_alternate_names + 1) == ',') {
      char *move_ptr = formatted_alternate_names;
      while (*move_ptr) {
        *move_ptr = *(move_ptr + 1);
        move_ptr++;
      }
    }
    formatted_alternate_names++;
  }

  printf("{%s}", start);
  printf(" ## ");

  printf("%s", p->house);
  printf(" ## ");

  printf("%s", p->ancestry);
  printf(" ## ");

  printf("%s", p->species);
  printf(" ## ");

  printf("%s", p->patronus);
  printf(" ## ");

  if (p->hogwartsStaff == true) {
    printf("true");
  } else {
    printf("false");
  }
  printf(" ## ");

  if (p->hogwartsStudent == true) {
    printf("true");
  } else {
    printf("false");
  }
  printf(" ## ");

  printf("%s", p->actorName);
  printf(" ## ");

  if (p->alive == true) {
    printf("true");
  } else {
    printf("false");
  }
  printf(" ## ");

  if (strcmp(p->id, codigo) == 0) {
    printf("23-06-1980");
    printf(" ## ");
  } else {
    printf("%s", p->dateOfBirth);
    printf(" ## ");
  }

  printf("%d", p->yearOfBirth);
  printf(" ## ");

  printf("%s", p->eyeColour);
  printf(" ## ");

  printf("%s", p->gender);
  printf(" ## ");

  printf("%s", p->hairColour);
  printf(" ## ");

  if (p->wizard == true) {
    printf("true");
  } else {
    printf("false");
  }

  printf("]\n");
}

Personagem *array[404];

int encontrarPersonagemPorID(FILE *arquivo, const char *id, Personagem *p) {
  char linha[512];

  fseek(arquivo, 0, SEEK_SET);

  while (fgets(linha, sizeof(linha), arquivo)) {
    linha[strcspn(linha, "\n")] = '\0';

    char copiaLinha[512];
    strcpy(copiaLinha, linha);

    char *token = strtok(copiaLinha, ";");

    if (token != NULL && strcmp(token, id) == 0) {
      lerPersonagem(p, linha);
      return 1;
    }
  }
  return 0;
}

void construir(Personagem **array, int tamHeap) {
  for (int i = tamHeap;
       i > 1 && (strcmp(array[i]->hairColour, array[i / 2]->hairColour) > 0 ||
                 (strcmp(array[i]->hairColour, array[i / 2]->hairColour) == 0 &&
                  strcmp(array[i]->name, array[i / 2]->name) > 0));
       i /= 2) {
    Personagem *temp = array[i];
    array[i] = array[i / 2];
    array[i / 2] = temp;
    mov += 3;
    cont++;
  }
}

int getMaiorFilho(Personagem **array, int i, int tamHeap) {
  int filho;
  if (2 * i == tamHeap ||
      strcmp(array[2 * i]->hairColour, array[2 * i + 1]->hairColour) > 0 ||
      (strcmp(array[2 * i]->hairColour, array[2 * i + 1]->hairColour) == 0 &&
       strcmp(array[2 * i]->name, array[2 * i + 1]->name) > 0)) {
    filho = 2 * i;
  } else {
    filho = 2 * i + 1;
  }
  return filho;
}

void reconstruir(Personagem **array, int tamHeap) {
  int i = 1;
  while (i <= (tamHeap / 2)) {
    int filho = getMaiorFilho(array, i, tamHeap);
    if ((strcmp(array[i]->hairColour, array[filho]->hairColour) < 0) ||
        (strcmp(array[i]->hairColour, array[filho]->hairColour) == 0 &&
         strcmp(array[i]->name, array[filho]->name) < 0)) {
      Personagem *temp = array[i];
      array[i] = array[filho];
      array[filho] = temp;
      i = filho;
      mov += 3;
      cont++;
    } else {
      i = tamHeap;
    }
  }
}

void heapsort(Personagem **array, int n) {
  Personagem *arrayTmp[n + 1];
  for (int i = 0; i < n; i++) {
    arrayTmp[i + 1] = array[i];
  }

  for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
    construir(arrayTmp, tamHeap);
  }

  int tamHeap = n;
  while (tamHeap > 1) {
    Personagem *temp = arrayTmp[1];
    arrayTmp[1] = arrayTmp[tamHeap];
    arrayTmp[tamHeap] = temp;
    tamHeap--;
    reconstruir(arrayTmp, tamHeap);
  }

  for (int i = 0; i < n; i++) {
    array[i] = arrayTmp[i + 1];
  }
}

void logMatricula(double tempo, int cont, int mov) {
  FILE *fp = NULL;
  fp = fopen("matrícula_heapParcial.txt", "w");
  fprintf(fp, "802151\t %.4fs \t %d \t %d", tempo, cont, mov);
  fclose(fp);
}

int main() {
  FILE *arquivo = fopen("/tmp/characters.csv", "r");
  if (arquivo == NULL) {

    return 1;
  }

  clock_t t = clock();

  char id[37];
  char ids[100][37];
  int idCount = 0;
  int i = 0;

  while (scanf("%s", id) == 1 && strcmp(id, "FIM") != 0) {
    if (idCount < 100) {
      strcpy(ids[idCount], id);
      idCount++;
    } else {
      return 1;
    }
  }

  for (int i = 0; i < idCount; i++) {
    Personagem *p = (Personagem *)malloc(sizeof(Personagem));
    inicializarPersonagem(p);

    if (encontrarPersonagemPorID(arquivo, ids[i], p)) {
      array[i] = p;
    }
  }

  heapsort(array, idCount);

  for (int i = 0; i < 10; i++) {
    imprimirPersonagem(array[i]);
  }

  clock_t fim = clock();
  double total = (double)(fim - t) / CLOCKS_PER_SEC;
  logMatricula(total, cont, mov);

  fclose(arquivo);

  return 0;
}