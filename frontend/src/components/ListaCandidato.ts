interface ICandidato {
  nome: string;
  email: string;
  competencias: string;
  descricao: string;
}
export function renderizarListaCandidatos(container: HTMLElement | null) {
  if (!container) return;
  const candidatos: ICandidato[] = JSON.parse(localStorage.getItem("candidatos") || "[]");
  const listaCandidatosDiv = document.createElement("div");
  listaCandidatosDiv.innerHTML = `<h3>Candidatos Anônimos</h3>`;
  const ul = document.createElement("ul");
  candidatos.forEach((candidato) => {
    const li = document.createElement("li");
    li.innerHTML = `
      <strong>Competências:</strong> ${candidato.competencias}<br>
      <strong>Descrição:</strong> ${candidato.descricao}
    `;
    ul.appendChild(li);
  });
  listaCandidatosDiv.appendChild(ul);
  container.appendChild(listaCandidatosDiv);
  const contagemCompetencias: Record<string, number> = {};
  candidatos.forEach((candidato) => {
    const competencias = candidato.competencias.toUpperCase().trim().split(",");
    competencias.forEach((competencia) => {
      competencia = competencia.trim();
      contagemCompetencias[competencia] = (contagemCompetencias[competencia] || 0) + 1;
    });
  });
  const competencias = Object.keys(contagemCompetencias);
  const contagens = Object.values(contagemCompetencias);
  const graficoDiv = document.createElement("div");
  graficoDiv.style.display = "flex";
  graficoDiv.style.alignItems = "flex-end";
  graficoDiv.style.marginTop = "20px";
  competencias.forEach((competencia, index) => {
    const containerBarra = document.createElement("div");
    containerBarra.style.display = "flex";
    containerBarra.style.flexDirection = "column";
    containerBarra.style.alignItems = "center";
    containerBarra.style.marginRight = "10px";
    const barraDiv = document.createElement("div");
    barraDiv.style.width = "50px";
    barraDiv.style.height = `${contagens[index] * 20}px`;
    barraDiv.style.backgroundColor = "rgba(75, 192, 192, 0.7)";
    barraDiv.style.display = "flex";
    barraDiv.style.alignItems = "flex-end";
    barraDiv.style.justifyContent = "center";
    barraDiv.style.position = "relative";
    const etiqueta = document.createElement("span");
    etiqueta.textContent = contagens[index].toString();
    etiqueta.style.position = "absolute";
    etiqueta.style.bottom = "100%";
    etiqueta.style.color = "#000";
    etiqueta.style.fontWeight = "bold";
    const etiquetaCompetencia = document.createElement("span");
    etiquetaCompetencia.textContent = competencia;
    etiquetaCompetencia.style.marginTop = "5px";
    etiquetaCompetencia.style.color = "#000";
    etiquetaCompetencia.style.fontSize = "12px";
    etiquetaCompetencia.style.textAlign = "center";
    barraDiv.appendChild(etiqueta);
    containerBarra.appendChild(barraDiv);
    containerBarra.appendChild(etiquetaCompetencia);
    graficoDiv.appendChild(containerBarra);
  });
  listaCandidatosDiv.appendChild(graficoDiv);
}