# Desafio de Desenvolvimento - Élin Duxus

## Como Rodar o Projeto

### 1. Clonando o Projeto

Primeiro, clone o repositório para o seu ambiente local. Abra o terminal e execute o seguinte comando:

```bash
git clone git@github.com:joshuadias06/desafioDuxus-Joshua.git
```

Isso criará uma cópia local do projeto no seu computador.

### 2. Configuração do Backend (Spring Boot)

#### 2.1. Pré-requisitos

- **Java 8** ou superior instalado.
- **Maven** instalado (para compilar e rodar a aplicação).
- **IDE de sua preferência** (IntelliJ IDEA é recomendada, mas pode usar qualquer IDE que suporte Java e Maven).

#### 2.2. Abrindo o Backend na IDE

1. Abra a pasta do projeto clonado na sua IDE.
2. Localize a classe principal do Spring Boot, que geralmente está dentro do pacote `br.com.duxusdesafio` e possui o nome `DuxusdesafioApplication.java`.
3. Execute a classe `DuxusdesafioApplication.java` para iniciar a API.

#### 2.3. Configurações do Banco de Dados H2

A aplicação usa o banco de dados **H2** em memória. Não é necessário configurar nada extra, pois a aplicação já está configurada para rodar o H2 automaticamente. Contudo, caso queira acessar o banco de dados diretamente para consultas, o console do H2 está habilitado.

Para acessar o console do banco de dados, abra seu navegador e vá para a URL:

```
http://localhost:8080/h2-console
```

Na página de login do H2, use as seguintes credenciais:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

Isso permitirá que você interaja com o banco de dados em memória.

#### 2.4. Testando a API

Após rodar a aplicação, os endpoints da API estarão disponíveis no seguinte endereço:

```
http://localhost:8080
```

Os principais endpoints são:

- `GET /timeDaData`: Retorna os integrantes de um time específico para uma data.
- `GET /integranteMaisUsado`: Retorna o integrante mais utilizado nos times dentro de um período.
- `GET /timeMaisComum`: Retorna o time mais comum dentro de um período.
- `GET /funcaoMaisComum`: Retorna a função mais comum nos times.
- `GET /franquiaMaisFamosa`: Retorna a franquia mais popular nos times.
- `GET /contagemPorFranquia`: Retorna a quantidade de franquias dentro de um período.
- `GET /contagemPorFuncao`: Retorna a quantidade de funções dentro de um período.

### 3. Configuração do Frontend

#### 3.1. Pré-requisitos

- **VS Code** ou qualquer editor de texto de sua escolha.
- **Live Server** extension para VS Code.

#### 3.2. Abrindo o Frontend

1. Navegue até a pasta `front-end` no seu projeto clonado.
2. Abra a pasta `front-end` no **VS Code**.
3. Instale a extensão **Live Server** (se ainda não estiver instalada).
4. Clique com o botão direito no arquivo `index.html` e selecione **Open with Live Server**. Isso iniciará o frontend em um servidor local.

O frontend irá consumir automaticamente a API que você iniciou no backend. O sistema exibirá os dados dos times e permitirá que você interaja com os componentes da aplicação.

### 5. Testando a Aplicação

1. **Backend**:
  - Certifique-se de que a API está rodando corretamente no IntelliJ IDEA.
  - Verifique os logs da aplicação para garantir que os endpoints estão funcionando.

2. **Frontend**:
  - Após rodar o frontend com o **Live Server**, abra o navegador e interaja com a interface para cadastrar integrantes, formar times e testar os dados processados pela API.

**Desenvolvido por Joshua Dias**
