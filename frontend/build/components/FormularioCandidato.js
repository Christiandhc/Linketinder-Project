function criarCampoEntrada(label, id, type = "text") {
    return `
    <div>
        <label for="${id}">${label}:</label>
        <input type="${type}" id="${id}" name="${id}">
    </div>
  `;
}
export function renderizarFormularioCandidato(container) {
    if (!container)
        return;
    container.innerHTML = `
        <form id="formularioCandidato">
            ${criarCampoEntrada("Nome", "nome")}
            ${criarCampoEntrada("Email", "email", "email")}
            ${criarCampoEntrada("Competências", "competencias")}
            ${criarCampoEntrada("CPF", "cpf")}
            ${criarCampoEntrada("Idade", "idade", "number")}
            ${criarCampoEntrada("Estado", "estado")}
            ${criarCampoEntrada("CEP", "cep")}
            <div>
                <label for="descricao">Descrição:</label>
                <textarea id="descricao" name="descricao"></textarea>
            </div>
            <button type="submit">Cadastrar</button>
        </form>
    `;
    const form = document.getElementById("formularioCandidato");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(form);
        const candidato = Object.fromEntries(formData.entries());
        const candidatos = JSON.parse(localStorage.getItem("candidatos") || "[]");
        candidatos.push(candidato);
        localStorage.setItem("candidatos", JSON.stringify(candidatos));
        console.log("Candidato salvo com sucesso");
    });
}
