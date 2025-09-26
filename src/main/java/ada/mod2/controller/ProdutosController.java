package ada.mod2.controller;
import ada.mod2.DTO.ProdutosDTO;
import ada.mod2.model.Produtos;

import ada.mod2.service.ProdutosService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.keycloak.admin.client.Keycloak;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import java.util.List;


@Tag(name = "Produtos", description = "Gerenciador de Produtos")
@Path("/produtos")
@RequestScoped

public class ProdutosController {

    @Inject
    JsonWebToken jwt;

    @Inject
    Keycloak keycloak;

    //@Inject
    //KeycloakRolesResource keycloakService;

    //@Inject
    //UsuariosKeycloak usuariosKeycloak;

    @Inject
    @Claim(standard = Claims.upn)
    String userId;

    //@Inject
    private final ProdutosService produtosService;
    public ProdutosController(ProdutosService produtosService){
        this.produtosService = produtosService;
    }

    //1. Criar produto
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response criarProdutos(@Valid ProdutosDTO produtosDTO){
        Produtos produtos = produtosService.criar(produtosDTO);
        if (produtos == null){
            return Response.status(Response.Status.BAD_REQUEST).entity(produtos).build();
        }
        System.out.println("Criado por " + userId);
        return Response.status(Response.Status.CREATED).entity(produtos).build();
    }

    //2. Listar produtos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    //@PermitAll
    public Response listarProdutos(){
        List<ProdutosDTO> produtos = produtosService.consultarTodos();
        return  Response
                .status(Response.Status.OK)
                .entity(produtos)
                .build();
    }

    // 3. Buscar por id
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public Response listarPorId(@RestPath Long id){
        return Response
                .status(Response.Status.OK)
                .entity(produtosService.buscarPorId(id))
                .build();
    }

    // 4. Atualizar produtos
    @Path("/{id}")
    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response atualizarProdutos(@RestPath Long id, @Valid ProdutosDTO produtosDTO){
        produtosService.atualizar(id,produtosDTO);
        return Response
                .status(Response.Status.OK)
                .entity(produtosService.buscarPorId(id))
                .build();
    }

    //5. Deletar produtos
    @Path("/{id}")
    @DELETE
    @Transactional
    @RolesAllowed({"admin","user"})
    public Response deletarProdutos(@RestPath Long id){
        produtosService.deletar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }


}

