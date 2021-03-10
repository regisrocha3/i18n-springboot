package rrlabs.i18n.importation.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rrlabs.i18n.importation.api.resource.ImpFilterResource;
import rrlabs.i18n.importation.domain.Importation;
import rrlabs.i18n.importation.domain.repository.ImpRepository;

import java.util.Optional;

@Service
public class ImpService {
    @Autowired private ImpRepository impRepository;

    public Optional<Importation> find(ImpFilterResource filter) {
        return this.impRepository.find(Importation.builder()
                .impCode(filter.getImpCode()).description(filter.getDescription()).build());
    }

    public void create(final Importation importation) {
        this.impRepository.save(importation);
    }
}
