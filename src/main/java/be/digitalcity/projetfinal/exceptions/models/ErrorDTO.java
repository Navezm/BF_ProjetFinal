package be.digitalcity.projetfinal.exceptions.models;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ErrorDTO {

    private String message;
    private Instant timestamp = Instant.now();

    public ErrorDTO(String message){ this.message = message; }

}
