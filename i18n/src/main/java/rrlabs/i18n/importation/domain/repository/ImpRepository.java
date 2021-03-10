package rrlabs.i18n.importation.domain.repository;

import org.springframework.stereotype.Repository;
import rrlabs.i18n.importation.domain.Importation;

import java.util.Objects;
import java.util.Optional;

@Repository
public class ImpRepository {
    public Optional<Importation> find(final Importation filter) {
        if ("1".equals(filter.getImpCode())) {
            return this.mockImpCodeOne(filter);
        }

        if (Objects.nonNull(filter.getDescription())) {
            return this.mockImpByDescriotion(filter);
        }

        return Optional.empty();
    }

    private Optional<Importation> mockImpByDescriotion(final Importation filter) {
        return Optional.of(Importation.builder().impCode("22")
                .description(filter.getDescription())
                .product("product 22").build());
    }

    public void save(final Importation importation) {
        // Empty
    }

    private Optional<Importation> mockImpCodeOne(final Importation filter) {
        return Optional.of(Importation.builder().impCode(filter.getImpCode())
                .description("description")
                .product("product").build());
    }
}
