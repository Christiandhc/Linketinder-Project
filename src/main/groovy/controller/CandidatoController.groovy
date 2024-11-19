package controller;

import model.Candidato;
import model.Competencia;
import model.dao.CandidatoDAO;
import model.dao.CandidatoDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class CandidatoController extends HttpServlet {

    private CandidatoDAO candidatoDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meubanco", "usuario", "senha");
            candidatoDAO = new CandidatoDAOImpl(connection);
        } catch (SQLException e) {
            throw new ServletException("Erro na conexão com o banco de dados.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            List<Candidato> candidatos = candidatoDAO.listarCandidatos();
            String jsonResponse = new Gson().toJson(candidatos);
            out.print(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Erro ao buscar candidatos.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Candidato candidato = getCandidatoFromRequest(request);
            candidatoDAO.inserirCandidato(candidato);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Erro ao inserir candidato.\"}");
        }
    }

    private Candidato getCandidatoFromRequest(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String pais = request.getParameter("pais");
        String cep = request.getParameter("cep");
        String descricao = request.getParameter("descricao");
        String senha = request.getParameter("senha");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
        String[] competenciasArray = request.getParameterValues("competencias");
        List<Competencia> competencias = new ArrayList<>();
        if (competenciasArray != null) {
            for (String competenciaNome : competenciasArray) {
                competencias.add(new Competencia(0, competenciaNome));
            }
        }
        return new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias);
    }

    @Override
    public void destroy() {
        candidatoDAO.closeConnection();
    }
}

