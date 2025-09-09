package ada.mod2.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ProdutosDTO {

    @NotBlank(message = "Informe nome do produto")
    private String nome;
    @NotBlank(message = "Informe decrição do produto")
    private String descricao;
    @DecimalMin(value = "0", message = "O valor não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo duas casas decimais")
    private BigDecimal preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
