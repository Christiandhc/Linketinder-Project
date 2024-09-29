import { renderizarPaginaInicial } from "../PaginaInicial.js";
import { renderizarPerfilCandidato } from "../services/PerfilCandidato.js";
import { renderizarPerfilEmpresa } from "../services/PerfilEmpresa.js";
export function inicializarNavegacao() {
    const appDiv = document.getElementById("app");
    function navegar(rotas) {
        switch (rotas) {
            case "/candidato":
                renderizarPerfilCandidato(appDiv);
                break;
            case "/empresa":
                renderizarPerfilEmpresa(appDiv);
                break;
            default:
                renderizarPaginaInicial(appDiv);
        }
    }
    window.onpopstate = () => navegar(window.location.pathname);
    appDiv === null || appDiv === void 0 ? void 0 : appDiv.addEventListener("click", (evento) => {
        const alvo = evento.target;
        if (alvo.tagName === "A") {
            evento.preventDefault();
            const href = alvo.getAttribute("href");
            if (href) {
                history.pushState(null, "", href);
                navegar(href);
            }
        }
    });
    navegar(window.location.pathname);
}
