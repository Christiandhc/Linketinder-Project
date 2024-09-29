import { renderizarFormularioCandidato } from "../components/FormularioCandidato.js";
import { renderizarListaEmpresas } from "../components/ListaEmpresa.js";
export function renderizarPerfilCandidato(container: HTMLElement | null) {
  if (!container) return;
  container.innerHTML = `<h2>Candidato</h2>`;
  renderizarFormularioCandidato(container);
  renderizarListaEmpresas(container);
}