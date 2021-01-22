package controlador;


import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;


@WebServlet(urlPatterns = "/graphql", loadOnStartup =1)
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	public GraphQLEndpoint() {
        super(buildSchema());
	}

    private static GraphQLSchema buildSchema() {
        return new GraphQLSchemaGenerator()
                .withOperationsFromSingletons(
                       // new TareaServiceImpl()
                ).generate();
    }
}