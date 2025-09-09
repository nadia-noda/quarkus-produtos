package ada.mod2.service;

import ada.mod2.DTO.ProdutosDTO;
import ada.mod2.DTO.ProdutosMapper;
import ada.mod2.exception.IdNaoEncontrado;
import ada.mod2.exception.NomeDeProdutoJaExiste;
import ada.mod2.model.Produtos;
import ada.mod2.repository.ProdutosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutosService {
    private final ProdutosRepository repository;
    public ProdutosService(ProdutosRepository repository){
        this.repository = repository;
    }

    private Produtos procurarPorId (Long id){
        Produtos produtos = repository.procurarPorId(id);
        if (produtos == null){
            throw new IdNaoEncontrado(id);
        }
        return  produtos;
    }

    private void procurarPorNome (String nome){
        Optional<Produtos> optionalProdutos = repository.procurarPorNome(nome);
        if (optionalProdutos.isPresent()){
            throw new NomeDeProdutoJaExiste(nome);
        }

    }

    public Produtos criar(ProdutosDTO produtosDTO){
        procurarPorNome(produtosDTO.getNome());
        Produtos produtos = ProdutosMapper.toEntity(produtosDTO);
        repository.persist(produtos);
        return produtos;
    }

    public List<ProdutosDTO> consultarTodos(){
        return repository.findAll()
                .list().stream().map(ProdutosMapper::toDTO).toList();
    }

    public ProdutosDTO buscarPorId(Long id) {
        return ProdutosMapper.toDTO(procurarPorId(id));
    }

    public void atualizar(Long id, ProdutosDTO produtosDTO){
        Produtos produtos = procurarPorId(id);
        if (produtosDTO.getNome() == produtos.getNome()){
            throw new NomeDeProdutoJaExiste(produtos.getNome());
        }
        ProdutosMapper.updateProdutos(produtosDTO,produtos);
    }

    public void deletar(Long id){
        Produtos produtos = procurarPorId(id);
        repository.delete(produtos);
    }
}
