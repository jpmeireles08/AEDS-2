#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

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
    if (token[0] != '\0')
      strcpy(p->patronus, token);
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

void imprimirPersonagem(const Personagem *p, int i) {
  char codigo[] = "7614cf6e-689e-47ac-a976-b1e9997637e9";
  printf("[");
  printf("%d", i);
  printf(" ## ");

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

Personagem *array[500];
int n = 0;

void start() { n = 0; }

void inserirInicio(Personagem *x) {
  int i;

  if (n >= 500) {
    printf("Erro ao inserir!\n");
    exit(1);
  }

  for (i = n; i > 0; i--) {
    array[i] = array[i - 1];
  }

  array[0] = x;
  n++;
}

void inserirFim(Personagem *x) {
  if (n >= 500) {
    printf("Erro ao inserir!\n");
    exit(1);
  }

  array[n] = x;
  n++;
}

void inserir(Personagem *x, int pos) {
  int i;

  if (n >= 500 || pos < 0 || pos > n) {
    printf("Erro ao inserir!\n");
    exit(1);
  }

  for (i = n; i > pos; i--) {
    array[i] = array[i - 1];
  }

  array[pos] = x;
  n++;
}

void removerInicio() {
  int i;
  Personagem *resp;

  if (n == 0) {
    printf("Erro ao remover!\n");
    exit(1);
  }

  resp = array[0];
  printf("(R) %s\n", resp->name);
  n--;

  for (i = 0; i < n; i++) {
    array[i] = array[i + 1];
  }
}

Personagem removerFim() {
  if (n == 0) {
    printf("Erro ao remover!\n");
    exit(1);
  }
  printf("(R) %s\n", array[n - 1]->name);
  return *array[--n];
}

void remover(int pos) {
  int i;
  Personagem *resp;

  if (n == 0 || pos < 0 || pos >= n) {
    printf("Erro ao remover!\n");
    exit(1);
  }

  resp = array[pos];
  printf("(R) %s\n", resp->name);
  n--;

  for (i = pos; i < n; i++) {
    array[i] = array[i + 1];
  }
}

void mostrar() {
  int i;

  for (i = 0; i < n; i++) {
    imprimirPersonagem(array[i], i);
  }
}

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

int main() {
  FILE *arquivo = fopen("/tmp/characters.csv", "r");
  if (arquivo == NULL) {
    return 1;
  }

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
      inserirFim(p);
    } else {
      free(p);
    }
  }

  int numModificacoes;
  scanf("%d", &numModificacoes);

  for (int i = 0; i < numModificacoes; i++) {
    char comando[3];
    scanf("%s", comando);

    if (strcmp(comando, "II") == 0) {
      char id[37];
      scanf("%s", id);
      Personagem *p = (Personagem *)malloc(sizeof(Personagem));
      inicializarPersonagem(p);
      if (encontrarPersonagemPorID(arquivo, id, p)) {
        inserirInicio(p);
      } else {
        free(p);
      }
    } else if (strcmp(comando, "IF") == 0) {
      char id[37];
      scanf("%s", id);
      Personagem *p = (Personagem *)malloc(sizeof(Personagem));
      inicializarPersonagem(p);
      if (encontrarPersonagemPorID(arquivo, id, p)) {
        inserirFim(p);
      } else {
        free(p);
      }
    } else if (strcmp(comando, "RI") == 0) {
      removerInicio();
    } else if (strcmp(comando, "RF") == 0) {
      removerFim();
    } else if (strcmp(comando, "R*") == 0) {
      int pos;
      scanf("%d", &pos);
      remover(pos);
    } else if (strcmp(comando, "I*") == 0) {
      int pos;
      char id[37];
      scanf("%d %s", &pos, id);
      Personagem *p = (Personagem *)malloc(sizeof(Personagem));
      inicializarPersonagem(p);
      if (encontrarPersonagemPorID(arquivo, id, p)) {
        inserir(p, pos);
      } else {
        free(p);
      }
    }
  }

  mostrar();

  fclose(arquivo);
  return 0;
}
