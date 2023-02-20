package br.com.senai.controller;

import br.com.senai.dao.PessoaDao;
import br.com.senai.model.Pessoa;
import br.com.senai.repository.PessoaRepository;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class Controller extends HttpServlet {

    private PessoaDao dao;

//     private PessoaRepository repository = new PessoaRepository();

//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String nome = request.getParameter("nome");
//        String endereco = request.getParameter("endereco");
//
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome(nome);
//        pessoa.setEndereco(endereco);
//
//        repository.create(pessoa);
//
//        List<Pessoa> pessoas = repository.lista();
//
//        request.setAttribute("pessoas", pessoas);
//        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//        rd.forward(request, response);
//    }

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
        dao = new PessoaDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null){
            action = "read";
        }

        switch (action){
            case "create":
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(request.getParameter("nome"));
                pessoa.setEndereco(request.getParameter("endereco"));
                dao.addUser(pessoa);
                response.sendRedirect("/senai-web/");
                break;

            case "read":
                request.setAttribute("pessoas", dao.getAllUsers());
                RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
                view.forward(request, response);
                break;

            case "update":
                Pessoa pessoaUpdate = new Pessoa();
                pessoaUpdate.setNome(request.getParameter("nome"));
                pessoaUpdate.setEndereco(request.getParameter("endereco"));
                String userid = request.getParameter("id");
                pessoaUpdate.setId(Integer.parseInt(userid));
                dao.updateUser(pessoaUpdate);
                response.sendRedirect("/senai-web?");
                break;

            case "delete":
                int userId = Integer.parseInt(request.getParameter("id"));
                dao.deleteUser(userId);
                response.sendRedirect("/senai-web?");
                break;

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setEndereco(request.getParameter("endereco"));

        String userid = request.getParameter("id");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(pessoa);
        }
        else
        {
            pessoa.setId(Integer.parseInt(userid));
            dao.updateUser(pessoa);
        }
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("pessoas", dao.getAllUsers());
        view.forward(request, response);
    }
}
