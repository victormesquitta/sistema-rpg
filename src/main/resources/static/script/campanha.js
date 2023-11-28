document.addEventListener("DOMContentLoaded", function () {
    const adicionarCardButton = document.getElementById("adicionarCard");
    const container = document.querySelector(".container");

    adicionarCardButton.addEventListener("click", function () {
        // Código para adicionar um novo card

        const novoCard = criarNovoCard();
        container.appendChild(novoCard);

        adicionarEventoDeletar(novoCard);
    });

    function criarNovoCard() {
        const novoCard = document.createElement("div");
        novoCard.className = "card";

        const imagem = document.createElement("img");
        imagem.src = "imagem3.jpg"; // Substitua pelo caminho da sua imagem
        imagem.alt = "Nova Imagem";

        const cardContent = document.createElement("div");
        cardContent.className = "card-content";

        const titulo = document.createElement("h3");
        titulo.textContent = "Título do Novo Card";

        const descricao = document.createElement("p");
        descricao.textContent = "Descrição do Novo Card.";

        const botaoDeletar = document.createElement("button");
        botaoDeletar.className = "deletar-card";
        botaoDeletar.textContent = "Deletar";

        cardContent.appendChild(titulo);
        cardContent.appendChild(descricao);
        cardContent.appendChild(botaoDeletar);

        novoCard.appendChild(imagem);
        novoCard.appendChild(cardContent);

        return novoCard;
    }

    function adicionarEventoDeletar(card) {
        const botaoDeletar = card.querySelector(".deletar-card");

        botaoDeletar.addEventListener("click", function () {
            container.removeChild(card);
        });
    }
});
