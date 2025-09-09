package ada.mod2.repository;

import ada.mod2.DTO.ProdutosMapper;
import ada.mod2.model.Produtos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutosRepository implements PanacheRepository <Produtos>{

    public Optional<Produtos> procurarPorNome (String nome){
        return find("nome",nome).firstResultOptional();
    }

    public Produtos procurarPorId(Long id){
        return  find("id", id).firstResult();
    }

}
