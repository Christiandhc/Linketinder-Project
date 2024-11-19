package controller;

import model.Empresa;
import model.Competencia;
import model.dao.EmpresaDAO;
import model.dao.EmpresaDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class EmpresaController extends HttpServlet {

    private EmpresaDAO empresaDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meubanco", "usuario", "senha");
            empresaDAO = new EmpresaDAOImpl(connection);
        } catch (SQLException e) {
            throw new ServletException("Erro na conexão com o banco de dados.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            List<Empresa> empresas = empresaDAO.listarEmpresas();
            String jsonResponse = new Gson().toJson(empresas);
            out.print(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Erro ao buscar empresas.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = getEmpresaFromRequest(request);
            empresaDAO.inserirEmpresa(empresa);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("{\"error\": \"Erro ao inserir empresa.\"}");
        }
    }

    private Empresa getEmpresaFromRequest(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String emailCorporativo = request.getParameter("emailCorporativo");
        String pais = request.getParameter("pais");
        String cep = request.getParameter("cep");
        String descricao = request.getParameter("descricao");
        String[] competenciasArray = request.getParameterValues("competencias");
        List<Competencia> competencias = new ArrayList<>();
        if (competenciasArray != null) {
            for (String competenciaNome : competenciasArray) {
                competencias.add(new Competencia(0, competenciaNome));
            }
        }
        return new Empresa(0, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias);
    }

    @Override
    public void destroy() {
        empresaDAO.closeConnection();
    }
}

