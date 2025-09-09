package ada.mod2.exception;

public class IdNaoEncontrado extends RuntimeException {
  public IdNaoEncontrado(Long id) {
    super("Produto com id " + id + " não encontrado.");
  }


}
