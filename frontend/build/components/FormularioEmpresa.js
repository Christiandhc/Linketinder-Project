function criarCampoEntrada(label, id, type = "text") {
    return `
    <div>
        <label for="${id}">${label}:</label>
        <input type="${type}" id="${id}" name="${id}">
    </div>
  `;
}
function validarNome(nome) {
    const regex = /^[A-Za-z\s]+$/;
    return regex.test(nome);
}
function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}
function validarCNPJ(cnpj) {
    const regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/;
    return regex.test(cnpj);
}
function validarCEP(cep) {
    const regex = /^\d{5}-\d{3}$/;
    return regex.test(cep);
}
function mostrarErro(campo, mensagem) {
    var _a;
    const input = document.getElementById(campo);
    const erro = document.createElement('div');
    erro.style.color = 'red';
    erro.textContent = mensagem;
    (_a = input === null || input === void 0 ? void 0 : input.parentElement) === null || _a === void 0 ? void 0 : _a.appendChild(erro);
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
        document.querySelectorAll('div[style="color: red"]').forEach(el => el.remove());
        let valido = true;
        if (!validarNome(empresa.nome)) {
            mostrarErro("nome", "Nome inválido. Use apenas letras.");
            valido = false;
        }
        if (!validarEmail(empresa.email)) {
            mostrarErro("email", "Email inválido.");
            valido = false;
        }
        if (!validarCNPJ(empresa.cnpj)) {
            mostrarErro("cnpj", "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX.");
            valido = false;
        }
        if (!validarCEP(empresa.cep)) {
            mostrarErro("cep", "CEP inválido. Use o formato XXXXX-XXX.");
            valido = false;
        }
        if (valido) {
            const empresas = JSON.parse(localStorage.getItem("empresas") || "[]");
            empresas.push(empresa);
            localStorage.setItem("empresas", JSON.stringify(empresas));
            console.log("Empresa salva com sucesso");
        }
    });
}
