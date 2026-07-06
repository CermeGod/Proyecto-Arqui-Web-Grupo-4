package pe.edu.upc.inmovision.securities;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {
        @Bean
        public OpenAPI customOpenAPI() {

                final String securitySchemeName = "bearerAuth";

                return new OpenAPI()
                        .info(new Info()
                                .title("Inmovision")
                                .version("1.0")
                                .description("API REST con JWT-9233"))
                        .addSecurityItem(
                                new SecurityRequirement()
                                        .addList(securitySchemeName))
                        .schemaRequirement(
                                securitySchemeName,
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"));
        }
}