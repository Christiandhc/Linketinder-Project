import { renderizarPaginaInicial } from "../PaginaInicial.js";
import { renderizarPerfilCandidato } from "../services/PerfilCandidato.js";
import { renderizarPerfilEmpresa } from "../services/PerfilEmpresa.js";


export function renderizarConteudo(pagina: string, container: HTMLElement | null) {
  if (!container) return;
    
  container.innerHTML = "";
  switch (pagina) {
    case "Página Inicial":
      renderizarPaginaInicial(container);
      break;
    case "candidato":
      renderizarPerfilCandidato(container);
      break;
    case "empresa":
      renderizarPerfilEmpresa(container);
      break;
    default:
      container.innerHTML = "<h2>Página não encontrada</h2>";
      break;
  }
}