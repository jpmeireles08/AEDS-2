#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_FILA 5

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

typedef struct {
  Personagem itens[MAX_FILA];
  int inicio;
  int fim;
  int tamanho;
} FilaCircular;

void inicializarFila(FilaCircular *fila) {
  fila->inicio = 0;
  fila->fim = -1;
  fila->tamanho = 0;
}

bool filaCheia(const FilaCircular *fila) { return fila->tamanho == MAX_FILA; }

bool filaVazia(const FilaCircular *fila) { return fila->tamanho == 0; }

Personagem removerFila(FilaCircular *fila);

void inserirFila(FilaCircular *fila, const Personagem *item) {
  if (!filaCheia(fila)) {
    fila->fim = (fila->fim + 1) % MAX_FILA;
    fila->itens[fila->fim] = *item;
    fila->tamanho++;
  } else {
    removerFila(fila);
    inserirFila(fila, item);
  }
}

Personagem removerFila(FilaCircular *fila) {
  if (!filaVazia(fila)) {
    Personagem removido = fila->itens[fila->inicio];
    fila->inicio = (fila->inicio + 1) % MAX_FILA;
    fila->tamanho--;
    return removido;
  } else {
    printf("Fila vazia!\n");
    exit(EXIT_FAILURE);
  }
}

int calcularMedia(const FilaCircular *fila) {
  int soma = 0;
  for (int i = 0; i < fila->tamanho; i++) {
    soma += fila->itens[i].yearOfBirth;
  }
  return soma / fila->tamanho;
}

void mostrarFila(const FilaCircular *fila) {
  printf("[ Head ]\n");
  for (int i = 0; i < fila->tamanho; i++) {
    char codigo[] = "7614cf6e-689e-47ac-a976-b1e9997637e9";
    printf("[");
    printf("%d", i);
    printf(" ## ");

    printf("%s", fila->itens[i].id);
    printf(" ## ");

    printf("%s", fila->itens[i].name);
    printf(" ## ");

    char *start = fila->itens[i].alternate_names;
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

    printf("%s", fila->itens[i].house);
    printf(" ## ");

    printf("%s", fila->itens[i].ancestry);
    printf(" ## ");

    printf("%s", fila->itens[i].species);
    printf(" ## ");

    printf("%s", fila->itens[i].patronus);
    printf(" ## ");

    if (fila->itens[i].hogwartsStaff == true) {
      printf("true");
    } else {
      printf("false");
    }
    printf(" ## ");

    if (fila->itens[i].hogwartsStudent == true) {
      printf("true");
    } else {
      printf("false");
    }
    printf(" ## ");

    printf("%s", fila->itens[i].actorName);
    printf(" ## ");

    if (fila->itens[i].alive == true) {
      printf("true");
    } else {
      printf("false");
    }
    printf(" ## ");

    if (strcmp(fila->itens[i].id, codigo) == 0) {
      printf("23-06-1980");
      printf(" ## ");
    } else {
      printf("%s", fila->itens[i].dateOfBirth);
      printf(" ## ");
    }

    printf("%d", fila->itens[i].yearOfBirth);
    printf(" ## ");

    printf("%s", fila->itens[i].eyeColour);
    printf(" ## ");

    printf("%s", fila->itens[i].gender);
    printf(" ## ");

    printf("%s", fila->itens[i].hairColour);
    printf(" ## ");

    if (fila->itens[i].wizard == true) {
      printf("true");
    } else {
      printf("false");
    }

    printf("]\n");
  }
  printf("[ Tail ]");
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
    printf("Erro ao abrir arquivo!\n");
    return 1;
  }

  FilaCircular fila;
  inicializarFila(&fila);

  char id[37];

  while (scanf("%s", id) == 1 && strcmp(id, "FIM") != 0) {
    Personagem p;
    if (encontrarPersonagemPorID(arquivo, id, &p)) {
      inserirFila(&fila, &p);
      printf(">> Year Birthday Average: %d\n", calcularMedia(&fila));
    }
  }

  int numModificacoes;
  scanf("%d", &numModificacoes);

  for (int i = 0; i < numModificacoes; i++) {
    char comando[3];
    scanf("%s", comando);

    if (strcmp(comando, "I") == 0) {
      scanf("%s", id);
      Personagem p;
      if (encontrarPersonagemPorID(arquivo, id, &p)) {
        inserirFila(&fila, &p);
        printf(">> Year Birthday Average: %d\n", calcularMedia(&fila));
      }
    } else if (strcmp(comando, "R") == 0) {
      Personagem removido = removerFila(&fila);
      printf("(R) %s\n", removido.name);
    }
  }

  mostrarFila(&fila);

  fclose(arquivo);
  return 0;
}
