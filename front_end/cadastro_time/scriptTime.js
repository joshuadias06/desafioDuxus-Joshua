document.addEventListener("DOMContentLoaded", () => {
    const membersListContainer = document.querySelector(".members-list");
    const teamForm = document.getElementById("teamForm");

    function loadMembers() {
        if (!membersListContainer) {
            console.error("Elemento '.members-list' não encontrado.");
            return;
        }

        membersListContainer.innerHTML = "";

        fetch("http://localhost:8080/cartogames/integrantes/listar")
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro na resposta da rede: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                if (Array.isArray(data)) {
                    data.forEach(member => {
                        const memberItem = document.createElement("div");
                        memberItem.classList.add("member-item");

                        memberItem.innerHTML = `
                            <label>
                                <input type="checkbox" value="${member.nome}" class="member-checkbox"> 
                                ${member.nome}
                            </label>
                        `;
                        membersListContainer.appendChild(memberItem);
                    });
                } else {
                    console.error("Dados recebidos não são uma lista.");
                }
            })
            .catch(error => console.error("Erro ao carregar integrantes:", error));
    }

    loadMembers();

    teamForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const teamDateElement = document.getElementById("teamDate");

        if (!teamDateElement) {
            console.error("Elemento 'teamDate' não encontrado.");
            return;
        }

        const teamDate = teamDateElement.value;

        if (!teamDate) {
            alert("Por favor, insira a data do time.");
            return;
        }

        const selectedMembers = Array.from(
            document.querySelectorAll(".member-checkbox:checked")
        ).map(checkbox => checkbox.value); 

        if (selectedMembers.length === 0) {
            alert("Selecione pelo menos um integrante para formar o time.");
            return;
        }

        fetch("http://localhost:8080/cartogames/times/cadastro", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                data: teamDate,
                integrantes: selectedMembers, 
            }),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao cadastrar time: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            alert("Time cadastrado com sucesso!");
            teamForm.reset();
        })
        .catch(error => console.error("Erro ao cadastrar time:", error));
    });
});
