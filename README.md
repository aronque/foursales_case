# Case Foursales

## Inicialização do projeto e informações:
1. Rodar o comando `docker-compose up` no diretório raiz do projeto, onde se encontra este README e o [docker-compose.yaml](./docker-compose.yaml) já deve inicializar o projeto, exportando as portas **3306** do banco de dados e **8080** da aplicação


2. Com a aplicação no ar, é possível acessar a documentação dos endpoints disponível em http://localhost:8080/swagger-ui/index.html#/ , onde é possível testar os endpoints através desta mesma ferramenta, ou se preferido através de um API Client como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/)
   1. Para utilizar o Swagger para as requisições, quando não utilizado o usuário "admin", criar um usuário através do endpoint de usuários e realizar o login. Com o login realizado, copiar o token gerado sem as aspas e inserí-lo na aba "Authorize" no canto superior direito da página, assim automatizando o processo de autenticação e autorização.
   2. Para requisições com objetos aninhados (por exemplo os produtos que compõem um pedido) a unica propriedade a ser informada obrigatoriamente é o ID. Exemplo de um JSON válido para criação de um pedido abaixo:

```
{
    "items": [
        {
        "product": {
                "id": "19c46941-fa16-4113-92a2-8599ba7e407e"
            },
            "qty": 4
        },
        {
        "product": {
                "id": "db267c71-11e6-42d9-a098-e9a2fd9fee77"
            },
            "qty": 1
        }
    ]
}
```


3. **TODOS** os endpoints estão configurados para serem acessados somente por usuários autenticados, cada um seguindo as regras de negócio definidas na estrutura do case. Utilizar a autorização através de "Bearer Token". O tempo de sessão por token é de 10 minutos até que expire.


4. É inicializado por padrão um usuário com perfil administrador com username "admin" e senha "123", para fins de teste.


5. Foi gerado um [dump](./db/mysqldump.sql) do banco de dados utilizado para os testes antes do upload do código ao GitHub, porém está definido no properties para o JPA inicializar a estrutura de banco de dados automaticamente em caso de não ter sido criada.