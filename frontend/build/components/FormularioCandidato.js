export function renderizarFormularioCandidato(container) {
    if (!container)
        return;
    container.innerHTML += `
    <form id="form-candidato">
      <label for="nome">Nome:</label>
      <input type="text" id="nome" name="nome" required />
      
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required />
      
      <label for="cpf">CPF:</label>
      <input type="text" id="cpf" name="cpf" required />
      
      <label for="idade">Idade:</label>
      <input type="number" id="idade" name="idade" required />
      
      <label for="competencias">Competências:</label>
      <input type="text" id="competencias" name="competencias" required />
      
      <label for="estado">Estado:</label>
      <input type="text" id="estado" name="estado" required />
      
      <label for="cep">CEP:</label>
      <input type="text" id="cep" name="cep" required />
      
      <label for="descricao">Descrição:</label>
      <textarea id="descricao" name="descricao" required></textarea>
      
      <button type="submit">Cadastrar</button>
    </form>
    <div id="mensagem-erro" style="color: red;"></div>
  `;
    const form = document.getElementById("form-candidato");
    const mensagemErro = document.getElementById("mensagem-erro");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", (e) => {
        e.preventDefault();
        mensagemErro.innerHTML = "";
        const formData = new FormData(form);
        const nome = formData.get("nome");
        const email = formData.get("email");
        const cpf = formData.get("cpf");
        const idade = formData.get("idade");
        const competencias = formData.get("competencias");
        const estado = formData.get("estado");
        const cep = formData.get("cep");
        const descricao = formData.get("descricao");
        const regexNome = /^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/;
        const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const regexCPF = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
        const regexCEP = /^\d{5}-\d{3}$/;
        let erroEncontrado = false;
        if (!regexNome.test(nome)) {
            mensagemErro.innerHTML += "Nome inválido. Apenas letras e espaços são permitidos.<br>";
            erroEncontrado = true;
        }
        if (!regexEmail.test(email)) {
            mensagemErro.innerHTML += "Email inválido.<br>";
            erroEncontrado = true;
        }
        if (!regexCPF.test(cpf)) {
            mensagemErro.innerHTML += "CMPF inválido. Formato correto: XXX.XXX.XXX-XX.<br>";
            erroEncontrado = true;
        }
        if (!regexCEP.test(cep)) {
            mensagemErro.innerHTML += "CEP inválido. Formato correto: XXXXX-XXX.<br>";
            erroEncontrado = true;
        }
        if (erroEncontrado) {
            console.log("Erros encontrados no formulário");
            mensagemErro.style.color = "red";
            return;
        }
        const candidato = {
            nome,
            email,
            cpf,
            idade,
            competencias,
            estado,
            cep,
            descricao,
        };
        const candidatos = JSON.parse(localStorage.getItem("candidatos") || "[]");
        candidatos.push(candidato);
        localStorage.setItem("candidatos", JSON.stringify(candidatos));
        mensagemErro.innerHTML = "Candidato cadastrado com sucesso!";
        mensagemErro.style.color = "green";
        form.reset();
    });
}
