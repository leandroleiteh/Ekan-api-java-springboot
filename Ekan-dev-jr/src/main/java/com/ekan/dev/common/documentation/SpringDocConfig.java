package com.ekan.dev.common.documentation;

import com.ekan.dev.api.exceptionhandler.Problem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {

    private static final String badRequestResponse = "BadRequestResponse";
    private static final String notFoundResponse = "NotFoundResponse";
    private static final String internalServerErrorResponse = "InternalServerErrorResponse";

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ekan - Teste desenvolvedor Java jr")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de Beneficiários e Documentos")
                        .contact(new Contact()
                                .name("Leandro Leite")
                                .email("leandroluz201616@gmail.com")
                        )
                )
                .tags(List.of(
                                new Tag().name("Benefiary").description("API de Beneficiários")
                        )
                )
                .components(
                        new Components()
                                .responses(gerarResponses())
                );
    }


    private Map<String, ApiResponse> gerarResponses() {

        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("Problem")));

        apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Bad Request")
                .content(content));

        apiResponseMap.put(notFoundResponse, new ApiResponse()
                .description("Not Found")
                .content(content));


        apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Internal Server Error Response")
                .content(content));

        return apiResponseMap;
    }
}