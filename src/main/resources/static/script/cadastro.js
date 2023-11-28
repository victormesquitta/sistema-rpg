function cadastrar(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get form data
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    // Create an object with the form data
    const formData = {
        nome: nome,
        email: email,
        senha: senha
    };

    // Make a POST request to the backend
    fetch("http://localhost:8080/api/usuarios", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            // Handle success
            console.log("Usuario criado com sucesso.");
        } else {
            // Handle errors
            console.error("Erro ao criar usuário.");
        }
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
    });
}
