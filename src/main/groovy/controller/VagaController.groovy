package controller;

import model.Vaga;
import model.dao.VagaDAO;
import model.dao.VagaDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

public class VagaController extends HttpServlet {

    private VagaDAO vagaDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meubanco", "usuario", "senha");
            vagaDAO = new VagaDAOImpl(connection);
        } catch (SQLException e) {
            throw new ServletException("Erro na conexão com o banco de dados.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            List<Vaga> vagas = vagaDAO.listarVagas();
            String jsonResponse = new Gson().toJson(vagas);
            out.print(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Erro ao buscar vagas.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Vaga vaga = getVagaFromRequest(request);
            vagaDAO.inserirVaga(vaga);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Erro ao inserir vaga.\"}");
        }
    }

    private Vaga getVagaFromRequest(HttpServletRequest request) {
        String nomeVaga = request.getParameter("nomeVaga");
        String descricaoVaga = request.getParameter("descricaoVaga");
        String localVaga = request.getParameter("localVaga");
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        return new Vaga(0, nomeVaga, descricaoVaga, localVaga, idEmpresa);
    }

    @Override
    public void destroy() {
        vagaDAO.closeConnection();
    }
}
