#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  char id[37];
  char name[50];
  char alternate_names[100];
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

void lerPersonagem(Personagem *p, const char *linha) {
  char copiaLinha[512];
  strcpy(copiaLinha, linha);

  char *token = strtok(copiaLinha, ";");

  if (token != NULL)
    strcpy(p->id, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->name, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->alternate_names, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->house, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->ancestry, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->species, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->patronus, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    p->hogwartsStaff = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strtok(NULL, ";");
  if (token != NULL)
    p->hogwartsStudent = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->actorName, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    p->alive = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->alternate_actors, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->dateOfBirth, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    p->yearOfBirth = atoi(token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->eyeColour, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->gender, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    strcpy(p->hairColour, token);
  token = strtok(NULL, ";");
  if (token != NULL)
    p->wizard = (strcmp(token, "VERDADEIRO") == 0) ? 1 : 0;
}

void imprimirPersonagem(const Personagem* p) {
    // Início da impressão
    printf("[");

    // Imprimir ID
    printf("%s", p->id);
    printf(" ## ");

    // Imprimir nome
    printf("%s", p->name);
    printf(" ## ");

  // Imprimir nomes alternativos
  // Inicialize o ponteiro para a string original
  char *start = p->alternate_names;
  char *end = start + strlen(start) - 1;

  // Remover colchetes iniciais e finais
  if (*start == '[') start++;
  if (*end == ']') *end = '\0';

  // Remover aspas simples
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

  // Formatar nomes alternativos sem espaços desnecessários
  // Percorra a string para formatar adequadamente
  char *formatted_alternate_names = start;
  while (*formatted_alternate_names != '\0') {
      // Remova os espaços desnecessários
      if (*formatted_alternate_names == ' ' && *(formatted_alternate_names + 1) == ',') {
          // Mova todos os caracteres para a esquerda
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

    // Imprimir casa
    printf("%s", p->house);
    printf(" ## ");

    // Imprimir ascendência
    printf("%s", p->ancestry);
    printf(" ## ");

    // Imprimir espécie
    printf("%s", p->species);
    printf(" ## ");

    // Imprimir patrono
    printf("%s", p->patronus);
    printf(" ## ");

    // Imprimir funcionário de Hogwarts (booleano)
    printf(p->hogwartsStaff ? "true" : "false");
    printf(" ## ");

    // Imprimir estudante de Hogwarts (booleano)
    printf(p->hogwartsStudent ? "true" : "false");
    printf(" ## ");

    // Imprimir nome do ator
    printf("%s", p->actorName);
    printf(" ## ");

    // Imprimir vivo (booleano)
    printf(p->alive ? "true" : "false");
    printf(" ## ");

    // Imprimir data de nascimento
    printf("%s", p->dateOfBirth);
    printf(" ## ");

    // Imprimir ano de nascimento (inteiro)
    printf("%d", p->yearOfBirth);
    printf(" ## ");

    // Imprimir cor dos olhos
    printf("%s", p->eyeColour);
    printf(" ## ");

    // Imprimir gênero
    printf("%s", p->gender);
    printf(" ## ");

    // Imprimir cor do cabelo
    printf("%s", p->hairColour);
    printf(" ## ");

    // Imprimir mago (booleano)
    printf(p->wizard ? "true" : "false");

    // Encerrar impressão
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
  FILE *arquivo = fopen("characters.csv", "r");
  if (arquivo == NULL) {
    printf("Erro ao abrir o arquivo characters.csv\n");
    return 1;
  }

  char id[37];
  char ids[100][37];
  int idCount = 0;

  // Lendo IDs até "FIM"
  while (scanf("%s", id) == 1 && strcmp(id, "FIM") != 0) {
    if (idCount < 100) {
      strcpy(ids[idCount], id);
      idCount++;
    } else {
      printf("Número máximo de IDs excedido.\n");
      return 1;
    }
  }

  // Imprimir personagens após "FIM"
  for (int i = 0; i < idCount; i++) {
    Personagem p;
    inicializarPersonagem(&p);

    if (encontrarPersonagemPorID(arquivo, ids[i], &p)) {
      imprimirPersonagem(&p);
    } else {
      printf("Personagem com ID %s não encontrado.\n", ids[i]);
    }
  }

  fclose(arquivo);

  return 0;
}
