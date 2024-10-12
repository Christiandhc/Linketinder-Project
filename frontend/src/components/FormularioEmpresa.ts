function criarCampoEntrada(label: string, id: string, type: string = "text"): string {
  return `
    <div>
        <label for="${id}">${label}:</label>
        <input type="${type}" id="${id}" name="${id}">
    </div>
  `;
}

function validarNome(nome: string): boolean {
  const regex = /^[A-Za-z\s]+$/;
  return regex.test(nome);
}

function validarEmail(email: string): boolean {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
}

function validarCNPJ(cnpj: string): boolean {
  const regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/;
  return regex.test(cnpj);
}

function validarCEP(cep: string): boolean {
  const regex = /^\d{5}-\d{3}$/;
  return regex.test(cep);
}

function mostrarErro(campo: string, mensagem: string) {
  const input = document.getElementById(campo);
  const erro = document.createElement('div');
  erro.style.color = 'red';
  erro.textContent = mensagem;
  input?.parentElement?.appendChild(erro);
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


    document.querySelectorAll('div[style="color: red"]').forEach(el => el.remove());


    let valido = true;
    
    if (!validarNome(empresa.nome as string)) {
      mostrarErro("nome", "Nome inválido. Use apenas letras.");
      valido = false;
    }

    if (!validarEmail(empresa.email as string)) {
      mostrarErro("email", "Email inválido.");
      valido = false;
    }

    if (!validarCNPJ(empresa.cnpj as string)) {
      mostrarErro("cnpj", "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX.");
      valido = false;
    }

    if (!validarCEP(empresa.cep as string)) {
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
