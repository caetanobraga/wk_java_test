
# Teste de Conhecimento Técnico
# Programador Java

Projeto como parte de um processo de seleção para programador java.
Backend em java spring boot
FrontEnd em Angular
Banco de dados MySql

## Documentação da API

#### Retorna candidatos por estado

```http
  GET /api/v1/candidates
```

#### Retorna media de imc por faixa etária

```http
  GET /api/v1/media-imc
```

#### Retorna porcentagem de obesos por sexo

```http
  GET /api/v1/obeses-percentage
```

#### Retorna média de idade por típo sanggüíneo

```http
  GET /api/v1/average-bloodtype
```

#### Retorna doadores possíveis por cada típo sanggüíneo

```http
  GET /api/v1/donors-by-bloodtype
```

#### Insere dados

```http
  POST /api/v1/candidates
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `file`      | `Multipart Form` | **Obrigatório**. Arquivo formato json com os dados a serem inseridos |



## Funcionalidades

- Upload de arquivo
- Gravação em Banco
- Geração relatórios


