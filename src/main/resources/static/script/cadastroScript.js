const formulario = document.querySelector("form");
const Iemail = document.querySelector("#inputEmail");
const isenha = document.querySelector("#inputPassword");
const isenha2 = document.querySelector("#inputPassword2");

let arrEmails = [];

function cadastrar() {
  preencherArrayComEmailsCadastrados().then(() => {
    console.log("Array de emails: ", arrEmails);
    console.log("Email sendo verificado: ", Iemail.value + " " + typeof Iemail.value);
    console.log("Está no array? ", arrEmails.includes(Iemail.value));

    if (arrEmails.includes(Iemail.value)) {
      alert("Email já cadastrado!");
    } else {
      fetch("http://localhost:8080/api/clients", {
        headers: {
          Accept: "application/json",
          "Content-type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          email: Iemail.value,
          password: isenha.value,
        }),
      })
        .then(() => {
          alert("Cadastro concluído");
          limpar();
        })
        .catch((error) => {
          console.error("Erro ao cadastrar: ", error);
        });
    }
  });
}

function preencherArrayComEmailsCadastrados() {
  return fetch("http://localhost:8080/api/usuarios")
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      arrEmails = post.map((item) => item.email);
    })
    .catch((error) => {
      console.error("Erro ao obter emails cadastrados: ", error);
    });
}

function limpar() {
  Iemail.value = "";
  isenha.value = "";
  isenha2.value = "";
}

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  if (isenha.value === isenha2.value) {
    cadastrar();
  } else {
    alert("As senhas não são idênticas");
  }
});
