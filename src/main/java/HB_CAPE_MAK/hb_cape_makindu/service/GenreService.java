package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.GenreDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Genre;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService implements DAOServiceInterface {
    private final GenreRepository genreRepository;


    @Override
    public List findAll() {
        return genreRepository.findAll();
    }
    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre persist(GenreDTO genreDTO, Long id) {
        if (id != null && genreRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Genre", "id", id
            );
        }

        Genre g = new Genre();
        g.setId(id);
        g.setName(genreDTO.getName());


        // Si id = null, le save fera un insert, sinon un update
        return genreRepository.saveAndFlush(g);
    }
}
