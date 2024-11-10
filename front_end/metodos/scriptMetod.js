document.getElementById("questionario-form").addEventListener("submit", function (event) {
    event.preventDefault();

    // Aqui estamos criando um único fetch para cada endpoint
    const timeDataInicial = document.getElementById("timeDataInicial").value;
    const integranteDataInicial = document.getElementById("integranteDataInicial").value;
    const timeComumDataInicial = document.getElementById("timeComumDataInicial").value;
    const funcaoDataInicial = document.getElementById("funcaoDataInicial").value;
    const franquiaDataInicial = document.getElementById("franquiaDataInicial").value;
    const contagemFranquiaDataInicial = document.getElementById("contagemFranquiaDataInicial").value;
    const contagemFuncaoDataInicial = document.getElementById("contagemFuncaoDataInicial").value;

    // Envia o fetch para cada endpoint com base na data fornecida
    if (timeDataInicial) {
        fetchData('timeDaData', timeDataInicial, 'respostaTimeDaData');
    }

    if (integranteDataInicial) {
        fetchData('integranteMaisUsado', integranteDataInicial, 'respostaIntegranteMaisUsado');
    }

    if (timeComumDataInicial) {
        fetchData('timeMaisComum', timeComumDataInicial, 'respostaTimeMaisComum');
    }

    if (funcaoDataInicial) {
        fetchData('funcaoMaisComum', funcaoDataInicial, 'respostaFuncaoMaisComum');
    }

    if (franquiaDataInicial) {
        fetchData('franquiaMaisFamosa', franquiaDataInicial, 'respostaFranquiaMaisFamosa');
    }

    if (contagemFranquiaDataInicial) {
        fetchData('contagemPorFranquia', contagemFranquiaDataInicial, 'respostaContagemPorFranquia');
    }

    if (contagemFuncaoDataInicial) {
        fetchData('contagemPorFuncao', contagemFuncaoDataInicial, 'respostaContagemPorFuncao');
    }

    // Função para enviar o fetch
    function fetchData(endpoint, data, idResposta) {
        let url = `http://localhost:8080/cartogames/metodos/${endpoint}?data=${data}`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                document.getElementById(idResposta).innerText = JSON.stringify(data, null, 2);
                document.getElementById(idResposta).style.display = "block";
            })
            .catch(error => {
                document.getElementById(idResposta).innerText = "Erro ao buscar dados.";
                document.getElementById(idResposta).style.display = "block";
            });
    }
});
