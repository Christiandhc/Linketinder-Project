export function renderizarPaginaInicial(container) {
    if (!container)
        return;
    const title = document.createElement("h1");
    title.textContent = "Linketinder";
    const paragraph = document.createElement("p");
    paragraph.textContent = "Escolha uma opção para prosseguir:";
    const list = document.createElement("ul");
    const candidatoItem = document.createElement("li");
    const candidatoLink = document.createElement("a");
    candidatoLink.href = "/candidato";
    candidatoLink.textContent = "Candidato";
    candidatoItem.appendChild(candidatoLink);
    const empresaItem = document.createElement("li");
    const empresaLink = document.createElement("a");
    empresaLink.href = "/empresa";
    empresaLink.textContent = "Empresa";
    empresaItem.appendChild(empresaLink);
    list.appendChild(candidatoItem);
    list.appendChild(empresaItem);
    container.appendChild(title);
    container.appendChild(paragraph);
    container.appendChild(list);
}
