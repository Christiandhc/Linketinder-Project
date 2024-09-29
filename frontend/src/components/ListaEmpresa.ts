interface IEmpresa {
  nome: string;
  email: string;
  estado: string;
  cep: string;
  descricao: string;
  cnpj: string;
  pais: string;
  competencias: string;
}

export function renderizarListaEmpresas(container: HTMLElement | null) {
  if (!container) return;
  const empresas: IEmpresa[] = JSON.parse(localStorage.getItem("empresas") || "[]");
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
