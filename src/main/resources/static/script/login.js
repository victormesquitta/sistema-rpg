var btnSignin = document.querySelector("#signin");
var btnSignup = document.querySelector("#signup");

var body = document.querySelector("body");


btnSignin.addEventListener("click", function () {
   body.className = "sign-in-js"; 
});

btnSignup.addEventListener("click", function () {
    body.className = "sign-up-js";
})



// const formulario = document.querySelector(".form");
// const Iemail = document.querySelector("#inputEmail");
// const isenha = document.querySelector("#inputPassword");

// function logar(){
// }

// formulario.addEventListener("submit", function (event) {
//   event.preventDefault();
//   logar();
// });