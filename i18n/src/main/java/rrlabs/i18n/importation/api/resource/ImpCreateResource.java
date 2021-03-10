package rrlabs.i18n.importation.api.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rrlabs.i18n.importation.domain.Importation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImpCreateResource {
    @NotNull(message = "imp.code.cannot.empty")
    @NotEmpty(message = "imp.code.cannot.empty")
    private String impCode;
    private String product;
    private String description;

    public Importation toDomain() {
        return Importation.builder().description(this.description).impCode(this.impCode).build();
    }
}
