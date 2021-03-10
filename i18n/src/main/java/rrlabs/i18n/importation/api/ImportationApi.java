package rrlabs.i18n.importation.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rrlabs.i18n.importation.api.resource.ImpCreateResource;
import rrlabs.i18n.importation.api.resource.ImpFilterResource;
import rrlabs.i18n.importation.api.resource.ImpResource;

import javax.validation.Valid;

@RequestMapping("/imp")
@OpenAPIDefinition
public interface ImportationApi {

    @Operation(summary = "Create Importation API")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the importation",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ImpResource.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content) })
    @PostMapping
    void create(@RequestBody @Valid ImpCreateResource resource);

    @Operation(summary = "Find Importation API")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the importation",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ImpResource.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameter supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "importation not found", content = @Content) })
    @GetMapping
    ImpResource find(ImpFilterResource filter);

}
