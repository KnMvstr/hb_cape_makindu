package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GameDTO {
    @NotBlank(message = "Specify the name of the game")
    private String name;

    @NotBlank(message = "Describe this new name")
    private String description;

    @NotBlank(message = "Specify the release date")
    private LocalDate releaseDate;

    @NotBlank(message = "Illustrate the game")
    private String image;

    @NotBlank(message = "Embellish our website")
    private String backgroundImage;

    @NotBlank(message = "For promotional purpose give use a trailer link")
    private String trailer;


    private Genre genre;


    private Publisher publisher;


    private BusinessModelDTO businessModel;


    private Moderator moderator;


    private List<Platform> platforms;


    private Classification classification;

}
