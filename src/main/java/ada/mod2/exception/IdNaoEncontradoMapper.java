package ada.mod2.exception;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IdNaoEncontradoMapper implements ExceptionMapper<IdNaoEncontrado> {
    @Override
    public Response toResponse(IdNaoEncontrado e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}

