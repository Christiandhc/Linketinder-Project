import { renderizarFormularioEmpresa } from "../components/FormularioEmpresa.js";
import { renderizarListaCandidatos } from "../components/ListaCandidato.js";
export function renderizarPerfilEmpresa(container: HTMLElement | null) {
  if (!container) return;
  container.innerHTML = `<h2>Empresa</h2>`;
  renderizarFormularioEmpresa(container);
  renderizarListaCandidatos(container);
}