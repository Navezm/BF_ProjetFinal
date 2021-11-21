package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.form.PaintingForm;
import be.digitalcity.projetfinal.services.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.nio.file.Path;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("painting")
public class PaintingController {
    // Define the location
    public static final String DIRECTORY = "src/main/resources/img/painting";

    private final PaintingService service;

    @Autowired
    public PaintingController(PaintingService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<PaintingDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/isAvailable")
    public ResponseEntity<List<PaintingDTO>> getByAvailibility(){
        return ResponseEntity.ok(service.findByAvailability());
    }

    @PostMapping
    public ResponseEntity<PaintingDTO> insert(@Valid @RequestBody PaintingForm form, @RequestParam("files")MultipartFile multipartFiles) throws IOException {
        String filename = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        copy(multipartFiles.getInputStream(), fileStorage, REPLACE_EXISTING);

//        return ResponseEntity.ok(service.insert(form, filename));
        return ResponseEntity.ok(service.insert(form));
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaintingDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingDTO> update(@PathVariable Long id, @Valid @RequestBody PaintingForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
