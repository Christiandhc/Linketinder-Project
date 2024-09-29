function criarCampoEntrada(label: string, id: string, type: string = "text"): string {
  return `
    <div>
        <label for="${id}">${label}:</label>
        <input type="${type}" id="${id}" name="${id}">
    </div>
  `;
}
export function renderizarFormularioEmpresa(container: HTMLElement | null) {
  if (!container) return;
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
  form?.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(form as HTMLFormElement);
    const empresa = Object.fromEntries(formData.entries());
    const empresas = JSON.parse(localStorage.getItem("empresas") || "[]");
    empresas.push(empresa);
    localStorage.setItem("empresas", JSON.stringify(empresas));
    console.log("Empresa salva com sucesso");
  });
}