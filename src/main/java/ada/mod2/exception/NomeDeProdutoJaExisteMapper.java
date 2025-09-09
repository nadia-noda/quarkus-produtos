package ada.mod2.exception;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NomeDeProdutoJaExisteMapper implements ExceptionMapper<NomeDeProdutoJaExiste> {
    @Override
    public Response toResponse(NomeDeProdutoJaExiste e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
    }
}
