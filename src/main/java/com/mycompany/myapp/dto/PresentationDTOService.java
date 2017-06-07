/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.mycompany.myapp.dto;

import com.mycompany.myapp.domain.Presentation;
import com.mycompany.myapp.repository.PresentationRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple DTO Facility for Presentation.
 */
@Service
public class PresentationDTOService {

    private final PresentationRepository presentationRepository;
    @Inject
    private UserDTOService userService;

    @Inject
    public PresentationDTOService(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    /**
     * Converts the passed presentation to a DTO.
     */
    public PresentationDTO toDTO(Presentation presentation) {
        return toDTO(presentation, 1);
    }

    /**
     * Converts the passed presentation to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param presentation
     * @param depth        the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *                     A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *                     xToOne associations will be serialized, etc.
     */
    public PresentationDTO toDTO(Presentation presentation, int depth) {
        if (presentation == null) {
            return null;
        }

        PresentationDTO dto = new PresentationDTO();

        dto.id = presentation.getId();
        if (depth-- > 0) {
            dto.presenters = userService.toDTOs(presentation.getPresenters(), depth);
        }
        dto.title = presentation.getTitle();

        return dto;
    }

    /**
     * Converts the passed dto to a Presentation.
     * Convenient for query by example.
     */
    public Presentation toEntity(PresentationDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Presentation.
     * Convenient for query by example.
     */
    public Presentation toEntity(PresentationDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Presentation presentation = new Presentation();

        presentation.setId(dto.id);
        if (depth-- > 0 && dto.presenters != null) {
            presentation.setPresenters(userService.toEntities(dto.presenters, depth));
        }

        presentation.setTitle(dto.title);

        return presentation;
    }

    public List<Presentation> toEntities(Set<PresentationDTO> presentations, int depth) {
        return presentations
                .stream()
                .map(presentationDTO -> toEntity(presentationDTO, depth))
                .collect(Collectors.toList());
    }

    private List<PresentationDTO> toDTOs(List<Presentation> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}