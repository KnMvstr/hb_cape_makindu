package HB_CAPE_MAK.hb_cape_makindu.DTO;

import HB_CAPE_MAK.hb_cape_makindu.entity.Game;
import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherDTO {

    @NotBlank(message = "Specify the publisher")
    private String name;

    @JsonView(JsonViews.PublisherPrivateView.class)
    private List<Game> games;
}