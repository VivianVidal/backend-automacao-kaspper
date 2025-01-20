// Função para verificar se todos os campos da seção estão preenchidos
function verificarCamposPreenchidos(secaoId) {
    const campos = document.querySelectorAll(`#${secaoId} input[required], #${secaoId} textarea[required]`);
    for (let campo of campos) {
        if (!campo.value.trim()) {
            return false;
        }
    }
    return true;
}

// Função para transitar entre as seções
function transitarSecao(atual, proxima) {
    // Ocultar a seção atual com animação suave
    document.getElementById(atual).style.transition = "opacity 0.5s ease";
    document.getElementById(atual).style.opacity = "0"; // Fade out

    setTimeout(function () {
        document.getElementById(atual).style.display = "none"; // Esconde a seção atual
        // Mostrar a próxima seção com animação suave
        document.getElementById(proxima).style.display = "block";
        document.getElementById(proxima).style.transition = "opacity 0.5s ease";
        document.getElementById(proxima).style.opacity = "1"; // Fade in
    }, 500); // Espera 500ms para transitar
}

// Evento para o botão "Continuar" (aplicado a todas as seções)
document.getElementById("continuarBtn").addEventListener("click", function () {
    const secao1Preenchida = verificarCamposPreenchidos("secao1");
    const secao2Preenchida = verificarCamposPreenchidos("secao2");
    const secao3Preenchida = verificarCamposPreenchidos("secao3");
    const secao4Preenchida = verificarCamposPreenchidos("secao4");
    // const secao5Preenchida = verificarCamposPreenchidos("secao5");

    if (secao1Preenchida) {
        // Verificar qual seção está pronta para avançar
        transitarSecao("secao1", "secao2");
        if (secao2Preenchida) {
            transitarSecao("secao2", "secao3");
        } else if (secao3Preenchida) {
            transitarSecao("secao3", "secao4");
        }
    } else {
        alert("Por favor, preencha todos os campos obrigatórios da primeira seção.");
    }
});
