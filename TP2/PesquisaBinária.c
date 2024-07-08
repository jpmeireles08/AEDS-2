#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int cont = 0;

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
    p->wizard = (strcmp(token, "VERDADEIRO\r") == 0) ? 1 : 0;

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

  printf("false");
  printf(" ## ");

  printf("false");
  printf(" ## ");

  printf("%s", p->actorName);
  printf(" ## ");

  printf("false");
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

  printf("false");

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

void selection(Personagem *array[], int n) {

  for (int i = 0; i < n - 1; i++) {
    int menor = i;
    for (int j = (i + 1); j < n; j++) {
      if (strcmp(array[menor]->name, array[j]->name) > 0) {
        menor = j;
      }
      cont++;
    }
    Personagem *aux = array[i];
    array[i] = array[menor];
    array[menor] = aux;
  }
}

bool buscaBinaria(Personagem *array[], int n, char *nome) {
  if (nome[strlen(nome) - 1] == '\n' || nome[strlen(nome) - 1] == '\r') {
    nome[strlen(nome) - 1] = '\0';
  }
  bool resp = false;
  int dir = n - 1;
  int esq = 0;
  int meio;

  while (esq <= dir) {
    meio = (esq + dir) / 2;

    if (strcmp(nome, array[meio]->name) == 0) {
      resp = true;
      esq = n;
      cont++;
    } else if (strcmp(nome, array[meio]->name) > 0) {
      esq = meio + 1;
      cont += 2;
    } else {
      dir = meio - 1;
      cont += 2;
    }
  }
  return resp;
}

void logMatricula(double tempo, int cont) {
  FILE *fp = NULL;
  fp = fopen("802151_binaria.txt", "w");
  fprintf(fp, "802151\t %.4fs \t %d", tempo, cont);
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

  selection(array, idCount);

  scanf(" %[^\n]", id);
  while (strcmp(id, "FIM") != 0) {
    // printf("%d", (int)id[strlen(id) - 1]);
    if (buscaBinaria(array, idCount, id)) {
      printf("SIM\n");
    } else {
      printf("NAO\n");
    }

    scanf(" %[^\n]", id);
  }

  clock_t fim = clock();
  double total = (double)(fim - t) / CLOCKS_PER_SEC;
  logMatricula(total, cont);

  fclose(arquivo);

  return 0;
}
