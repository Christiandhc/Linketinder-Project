export function renderizarListaEmpresas(container) {
    if (!container)
        return;
    const empresas = JSON.parse(localStorage.getItem("empresas") || "[]");
    const listaEmpresasDiv = document.createElement("div");
    listaEmpresasDiv.innerHTML = `<h3>Empresas Anônimas</h3>`;
    const ul = document.createElement("ul");
    empresas.forEach((empresa) => {
        const li = document.createElement("li");
        li.innerHTML = `
      <strong>Competências:</strong> ${empresa.competencias}<br>
      <strong>Descrição:</strong> ${empresa.descricao} (${empresa.estado})
    `;
        ul.appendChild(li);
    });
    listaEmpresasDiv.appendChild(ul);
    container.appendChild(listaEmpresasDiv);
}
