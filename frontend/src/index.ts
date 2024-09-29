import { inicializarNavegacao } from "./services/Navegacao.js";
document.addEventListener("DOMContentLoaded", () => {
  inicializarNavegacao();
  localStorage.setItem(
    "candidatos",
    JSON.stringify([
      {
        nome: "Harry Potter",
        email: "harry.potter@example.com",
        competencias: "Python, Django",
        descricao: "Desenvolvedor Python e Django",
        cpf: "123.456.789-00",
        idade: "30",
        estado: "Londres",
        cep: "12345-678",
      },
      {
        nome: "Frodo Baggins",
        email: "frodo.baggins@example.com",
        competencias: "Java, Spring",
        descricao: "Especialista em Java e Spring",
        cpf: "222.333.444-55",
        idade: "33",
        estado: "Condado",
        cep: "56789-012",
      },
      {
        nome: "Katniss Everdeen",
        email: "katniss.everdeen@example.com",
        competencias: "Angular, JavaScript",
        descricao: "Desenvolvedora Front-end Angular",
        cpf: "333.444.555-66",
        idade: "27",
        estado: "Panem",
        cep: "67890-123",
      },
            {
        nome: "Daenerys Targaryen",
        email: "daenerys.targaryen@example.com",
        competencias: "SQL, NoSQL",
        descricao: "Analista de Banco de Dados SQL",
        cpf: "444.555.666-77",
        idade: "32",
        estado: "Essos",
        cep: "78901-234",
      },
            {
        nome: "Jon Snow",
        email: "jon.snow@example.com",
        competencias: "Data Science, Machine Learning",
        descricao: "Cientista de Dados",
        cpf: "555.666.777-88",
        idade: "35",
        estado: "Winterfell",
        cep: "89012-345",
      },
    ])
  );
});