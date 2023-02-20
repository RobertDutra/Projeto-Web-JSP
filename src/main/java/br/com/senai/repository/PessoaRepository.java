package br.com.senai.repository;

import br.com.senai.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    private  static int AUTO_INCREMENT = 0;
    private List<Pessoa> PESSOAS_DB = new ArrayList<>();

    public void create(Pessoa pessoa){
        AUTO_INCREMENT++;
        pessoa.setId(AUTO_INCREMENT);
        PESSOAS_DB.add(pessoa);

    }

    public List<Pessoa> lista(){
        return PESSOAS_DB;
    }
}
