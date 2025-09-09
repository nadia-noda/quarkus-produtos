package ada.mod2.exception;

public class NomeDeProdutoJaExiste  extends RuntimeException {
    public NomeDeProdutoJaExiste(String nome){
        super("Produto com nome " + nome + " já existe.");

    }
}
