package org.example.refact.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//DAO - Data Access Object
public class ClienteDAO {

    private final Database dao;

    public ClienteDAO(Database dao) {
        this.dao = dao;
    }

    public BigDecimal salvar(Input input) {

        String[] columns = {"id","nome", "email", "senha", "ie_situacao"};

        List<Map<String, Object>> values = new ArrayList<>();
        values.add(Map.of("String", input.nome()));
        values.add(Map.of("String", input.email()));
        values.add(Map.of("String", input.senha()));
        values.add(Map.of("String", input.situacao()));

        BigDecimal id = this.dao.salvar("cliente", columns, values);

        return id;
    }

    public Output consultarPorId(BigDecimal id) {

        Map<String, Object> output = this.dao.consultarPorId("cliente", id);

        return new Output(new BigDecimal(output.get("id").toString()),
                (String) output.get("nome"),
                (String) output.get("email"),
                (String) output.get("senha"),
                (String) output.get("ie_situacao"));
    }

    public record Input(String nome, String email, String senha, String situacao) {
    }

    public record Output(BigDecimal id, String nome, String email, String senha, String situacao) {
    }
}
