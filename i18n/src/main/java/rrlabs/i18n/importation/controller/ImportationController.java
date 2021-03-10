package rrlabs.i18n.importation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rrlabs.i18n.config.exception.ResourceNotFoundException;
import rrlabs.i18n.importation.api.ImportationApi;
import rrlabs.i18n.importation.api.resource.ImpCreateResource;
import rrlabs.i18n.importation.api.resource.ImpFilterResource;
import rrlabs.i18n.importation.api.resource.ImpResource;
import rrlabs.i18n.importation.domain.Importation;
import rrlabs.i18n.importation.domain.services.ImpService;

import java.util.Optional;

@RestController
public class ImportationController implements ImportationApi {

    @Autowired private ImpService impService;

    @Override
    public void create(final ImpCreateResource resource) {
        this.impService.create(resource.toDomain());
    }

    @Override
    public ImpResource find(final ImpFilterResource filter) {
        final Optional<Importation> importation = this.impService.find(filter);
        return this.toResource(importation);
    }

    private ImpResource toResource(final Optional<Importation> importation) {
        final Importation imp = importation.orElseThrow(ResourceNotFoundException::new);
        return ImpResource.builder().impCode(imp.getImpCode())
                .description(imp.getDescription()).build();
    }
}
