package rrlabs.i18n.importation.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Importation {
    private String impCode;
    private String product;
    private String description;
}
