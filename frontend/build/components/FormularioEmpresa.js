function criarCampoEntrada(label, id, type = "text") {
    return `
    <div>
        <label for="${id}">${label}:</label>
        <input type="${type}" id="${id}" name="${id}">
    </div>
  `;
}
export function renderizarFormularioEmpresa(container) {
    if (!container)
        return;
    container.innerHTML = `
        <form id="formularioEmpresa">
            ${criarCampoEntrada("Nome da Empresa", "nome")}
            ${criarCampoEntrada("Email", "email", "email")}
            ${criarCampoEntrada("Estado", "estado")}
            ${criarCampoEntrada("CEP", "cep")}
            ${criarCampoEntrada("Descrição", "descricao")}
            ${criarCampoEntrada("CNPJ", "cnpj")}
            ${criarCampoEntrada("País", "pais")}
            ${criarCampoEntrada("Competências", "competencias")}
            <button type="submit">Cadastrar</button>
        </form>
    `;
    const form = document.getElementById("formularioEmpresa");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(form);
        const empresa = Object.fromEntries(formData.entries());
        const empresas = JSON.parse(localStorage.getItem("empresas") || "[]");
        empresas.push(empresa);
        localStorage.setItem("empresas", JSON.stringify(empresas));
        console.log("Empresa salva com sucesso");
    });
}
