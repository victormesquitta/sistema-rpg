window.scroll({
    top: 0, 
    behavior: "smooth"
})

function login() {
    fetch('/login-endpoint', {
        method: 'POST',
        // Adicione cabeçalhos ou corpo da requisição, se necessário
    })
    .then(response => {
        if (response.ok) {
            // Lógica para esconder/mostrar elementos na tela
            document.getElementById('btn-login').style.display = 'none';
            document.getElementById('btn-logout').style.display = 'block';
            document.getElementById('profile').style.display = 'block';
            document.getElementById('container__principal--section').style.display = 'none';
            document.getElementById('profile-section').style.display = 'block';
        } else {
            console.error('Falha no login');
        }
    })
    .catch(error => console.error('Erro na requisição:', error));
}

function logout() {
    fetch('/logout-endpoint', {
        method: 'POST',
        // Adicione cabeçalhos ou corpo da requisição, se necessário
    })
    .then(response => {
        if (response.ok) {
            // Lógica para esconder/mostrar elementos na tela
            document.getElementById('btn-login').style.display = 'block';
            document.getElementById('btn-logout').style.display = 'none';
            document.getElementById('profile').style.display = 'none';
            document.getElementById('container__principal--section').style.display = 'block';
            document.getElementById('profile-section').style.display = 'none';
        } else {
            console.error('Falha no logout');
        }
    })
    .catch(error => console.error('Erro na requisição:', error));
}