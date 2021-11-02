package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.mappers.PaintingQuotationMapper;
import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.entity.PaintingQuotation;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;
import be.digitalcity.projetfinal.repository.PaintingQuotationRepository;
import be.digitalcity.projetfinal.services.PaintingQuotationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintingQuotationServiceImpl implements PaintingQuotationService {
    private final PaintingQuotationRepository repository;
    private final PaintingQuotationMapper mapper;

    public PaintingQuotationServiceImpl(PaintingQuotationRepository repository, PaintingQuotationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PaintingQuotationDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaintingQuotationDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("The painting quotation doesn't exist"));
    }

    @Override
    public PaintingQuotationDTO delete(Long id) {
        PaintingQuotation toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting quotation doesn't exist"));

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public PaintingQuotationDTO update(Long id, PaintingQuotationForm paintingQuotationForm) {
        PaintingQuotation toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The painting quotation doesn't exist"));

        toUpdate.setColorChoice(paintingQuotationForm.getColorChoice());
        toUpdate.setMessage(paintingQuotationForm.getMessage());
        toUpdate.setFormat(paintingQuotationForm.getFormat());
        toUpdate.setPaintingType(paintingQuotationForm.getPaintingType());
        toUpdate.setUser(paintingQuotationForm.getUser());
        toUpdate.setStatus(paintingQuotationForm.getStatus());

        repository.save(toUpdate);

        return mapper.toDto(toUpdate);
    }

    @Override
    public PaintingQuotationDTO insert(PaintingQuotationForm paintingQuotationForm) {
        PaintingQuotation toInsert = mapper.fromFormToEntity(paintingQuotationForm);

        repository.save(toInsert);

        return mapper.toDto(toInsert);
    }
}
