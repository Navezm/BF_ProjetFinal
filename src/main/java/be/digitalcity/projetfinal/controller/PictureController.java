package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.form.PictureForm;
import be.digitalcity.projetfinal.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping(path = "picture")
public class PictureController {
    // Define the location for the files
    public static final String DIRECTORY = "src/main/resources/img/picture";

    private final PictureService service;

    @Autowired
    public PictureController(PictureService service) {
        this.service = service;
    }

    @GetMapping({""})
    public ResponseEntity<List<PictureDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

//    @GetMapping("/type/{id}")
//    public ResponseEntity<List<PictureDTO>> getByType(@PathVariable Long id){
//        return ResponseEntity.ok(service.findByType(id));
//    }

    @GetMapping("/isAvailable")
    public ResponseEntity<List<PictureDTO>> getByAvailibility(){
        return ResponseEntity.ok(service.findByAvailability());
    }

    @PostMapping
    public ResponseEntity<PictureDTO> insert(@Valid @RequestBody PictureForm form){
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
    public ResponseEntity<PictureDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureDTO> update(@PathVariable Long id, @Valid @RequestBody PictureForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
