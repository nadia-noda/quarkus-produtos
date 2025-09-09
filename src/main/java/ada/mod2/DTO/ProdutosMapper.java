package ada.mod2.DTO;

import ada.mod2.model.Produtos;

public class ProdutosMapper {

    //dados de dto para produtos
    public static Produtos toEntity(ProdutosDTO produtosDTO) {
        if (produtosDTO == null) {
            return null;
        }

        Produtos produtos = new Produtos();
        produtos.setNome(produtosDTO.getNome());
        produtos.setDescricao(produtosDTO.getDescricao());
        produtos.setPreco(produtosDTO.getPreco());
        return produtos;
    }

    //atualizar
    public static Produtos updateProdutos(ProdutosDTO produtosDTO, Produtos produtos) {
        if (produtosDTO == null) {
            return null;
        }

        produtos.setNome(produtosDTO.getNome());
        produtos.setDescricao(produtosDTO.getDescricao());
        produtos.setPreco(produtosDTO.getPreco());
        return produtos;
    }

    //consulta
    public static ProdutosDTO toDTO(Produtos produtos) {
        if (produtos == null) {
            return null;
        }
        ProdutosDTO produtosDTO = new ProdutosDTO();
        produtosDTO.setNome(produtos.getNome());
        produtosDTO.setDescricao(produtos.getDescricao());
        produtosDTO.setPreco(produtos.getPreco());

        return produtosDTO;
    }

}
