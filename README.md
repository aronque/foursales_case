# Case Foursales

### Para rodar o projeto

Antes de rodar docker-compose, é necessário a geração de 2 chaves (privada e pública) com algoritmo **RSA** para utilização do mecanismo de autenticação e autorização. Para gerar ambas, é necessário rodar o seguintes comandos:
## Linux: 
1. primeiro comando a ser executado:
`openssl genrsa > app.key`

2. segundo comando a ser executado:
`openssl rsa -in app.key -pubout -out app.pub`

## Windows:
Utilizar algum bash não nativo do windows (**recomendado WSL ou Git Bash**) e rodar dois os comandos anteriores.

**Após a geração das chaves**: Copiar os arquivos gerados para o diretório [resources](./backend/src/main/resources) ou copiar seu conteúdo e colar em arquivos, sendo **obrigatório** os nomes dos arquivos contendo as chaves: **app.key** (privada) e **app.pub** (pública).

## Inicialização do projeto e informações:
1. Rodar o comando `docker-compose up` no diretório raiz do projeto, onde se encontra este README e o [docker-compose.yaml](./docker-compose.yaml) já deve inicializar o projeto, exportando as portas **3306** do banco de dados e **8080** da aplicação
2. Com a aplicação no ar, é possível acessar a documentação dos endpoints disponível em http://localhost:8080/swagger-ui/index.html#/ , onde é possível testar os endpoints através desta mesma ferramenta, ou se preferido através de um API Client como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/)
3. **TODOS** os endpoints estão configurados para serem acessados somente por usuários autenticados, cada um seguindo as regras de negócio definidas na estrutura do case. Utilizar a autorização através de "Bearer Token". O tempo de sessão por token é de 10 minutos até que expire.
4. É inicializado por padrão um usuário com perfil administrador com username "admin" e senha "123", para fins de teste.
5. Foi gerado um [dump](./db/mysqldump.sql) do banco de dados utilizado para os testes antes do upload do código ao GitHub, porém está definido no properties para o JPA inicializar a estrutura de banco de dados automaticamente em caso de não ter sido criada.
6. Se ao utilizar ao gerar as chaves houver algum problema relacionado às senhas dos usuários criptografadas no BD, entrar em contato para que possa fornecer as chaves sem realizar o upload.