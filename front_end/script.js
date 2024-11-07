const formulario = document.querySelector("form");
const Ifranquia = document.querySelector(".franquia");
const Inome = document.querySelector(".nome");
const Ifuncao = document.querySelector(".funcao");

function cadastrar() {
    fetch('http://localhost:8080/cartogames/integrantes/cadastro', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            franquia: Ifranquia.value,
            nome: Inome.value,
            funcao: Ifuncao.value
        })
    })
    .then(res => console.log(res))
    .catch(error => console.log(error));
}

function limpar(){
    Ifranquia.value = "";
    Inome.value = "";
    Ifuncao.value = "";
}

formulario.addEventListener("submit", function(event) {
    event.preventDefault();
    cadastrar();
    limpar();
    setTimeout(() => {
        alert("Integrante cadastrado com sucesso!");
    }, 2000);
});
