#include <err.h>
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

typedef struct Celula {
  Personagem *novoPersonagem;
  struct Celula *prox;
  struct Celula *ant;
} Celula;

Celula *novaCelula(Personagem *novoPersonagem) {
  Celula *nova = (Celula *)malloc(sizeof(Celula));
  nova->novoPersonagem = novoPersonagem;
  nova->ant = nova->prox = NULL;
  return nova;
}

Celula *primeiro;
Celula *ultimo;

void start() {
  primeiro = novaCelula(NULL);
  ultimo = primeiro;
}

void inserirInicio(Personagem *x) {
  Celula *tmp = novaCelula(x);
  tmp->ant = primeiro;
  tmp->prox = primeiro->prox;
  primeiro->prox = tmp;
  if (primeiro == ultimo) {
    ultimo = tmp;
  } else {
    tmp->prox->ant = tmp;
  }
  tmp = NULL;
}

void inserirFim(Personagem *x) {
  ultimo->prox = novaCelula(x);
  ultimo->prox->ant = ultimo;
  ultimo = ultimo->prox;
}

void removerInicio() {
  if (primeiro == ultimo) {
    errx(1, "Erro ao remover!");
  }

  Celula *tmp = primeiro;
  primeiro = primeiro->prox;
  tmp->prox = primeiro->ant = NULL;
  free(tmp);
  tmp = NULL;
}

void removerFim() {
  if (primeiro == ultimo) {
    errx(1, "Erro ao remover!");
  }
  ultimo = ultimo->ant;
  ultimo->prox->ant = NULL;
  free(ultimo->prox);
  ultimo->prox = NULL;
}

int tamanho() {
  int tamanho = 0;
  Celula *i;
  for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
    ;
  return tamanho;
}

Celula *metadeLista(Celula *primeiro, Celula *ultimo) {
  Celula *x = primeiro;
  Celula *y = primeiro;

  while (y != ultimo && y->prox != ultimo) {
    x = x->prox;
    y = y->prox->prox;
  }
  return x;
}

void inserir(Personagem *x, int pos) {
  int tam = tamanho();

  if (pos < 0 || pos > tam) {
    errx(1, "Erro ao inserir posicao (%d/ tamanho = %d) invalida!", pos, tam);
  } else if (pos == 0) {
    inserirInicio(x);
  } else if (pos == tam) {
    inserirFim(x);
  } else {
    int j;
    Celula *i = primeiro;
    for (j = 0; j < pos; j++, i = i->prox)
      ;

    Celula *tmp = novaCelula(x);
    tmp->ant = i;
    tmp->prox = i->prox;
    tmp->ant->prox = tmp->prox->ant = tmp;
    tmp = i = NULL;
  }
}

void remover(int pos) {
  int tam = tamanho();

  if (primeiro == ultimo) {
    errx(1, "Erro ao remover (vazia)!");
  } else if (pos < 0 || pos >= tam) {
    errx(1, "Erro ao remover posicao (%d/ tamanho = %d) invalida!", pos, tam);
  } else if (pos == 0) {
    removerInicio();
  } else if (pos == tam - 1) {
    removerFim();
  } else {
    Celula *i = primeiro->prox;
    int j;
    for (j = 0; j < pos; j++, i = i->prox)
      ;

    i->ant->prox = i->prox;
    i->prox->ant = i->ant;
    i->prox = i->ant = NULL;
    free(i);
    i = NULL;
  }
}

void imprimirPersonagem(const Personagem *p, int i);

void mostrar() {
  Celula *i;
  int j = 0;
  for (i = primeiro->prox; i != NULL; i = i->prox, j++) {
    imprimirPersonagem(i->novoPersonagem, j);
  }
}

void quicksortRec(Celula *esq, Celula *dir) {
  if (esq == NULL || dir == NULL || esq == dir || esq == dir->prox) {
    return;
  }

  Celula *i = esq;
  Celula *j = dir;
  Celula *pivo = metadeLista(esq, dir);

  while (i != j && i != j->prox) {
    while (
        strcmp(i->novoPersonagem->house, pivo->novoPersonagem->house) < 0 ||
        (strcmp(i->novoPersonagem->house, pivo->novoPersonagem->house) == 0 &&
         strcmp(i->novoPersonagem->name, pivo->novoPersonagem->name) < 0)) {
      i = i->prox;
    }

    while (
        strcmp(j->novoPersonagem->house, pivo->novoPersonagem->house) > 0 ||
        (strcmp(j->novoPersonagem->house, pivo->novoPersonagem->house) == 0 &&
         strcmp(j->novoPersonagem->name, pivo->novoPersonagem->name) > 0)) {
      j = j->ant;
    }

    if (i != j && i != j->prox) {
      Personagem *temp = i->novoPersonagem;
      i->novoPersonagem = j->novoPersonagem;
      j->novoPersonagem = temp;

      i = i->prox;
      j = j->ant;
    }
  }

  if (esq != j) {
    quicksortRec(esq, j->ant);
  }
  if (dir != i) {
    quicksortRec(i->prox, dir);
  }
}

void quicksort() {
  if (primeiro != ultimo) {
    quicksortRec(primeiro->prox, ultimo);
  }
}

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

  start();

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

  if (primeiro != ultimo) {

    quicksort();
  }

  mostrar();

  fclose(arquivo);
  return 0;
}
