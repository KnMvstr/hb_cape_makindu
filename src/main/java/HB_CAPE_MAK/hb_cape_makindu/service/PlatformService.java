package HB_CAPE_MAK.hb_cape_makindu.service;

import HB_CAPE_MAK.hb_cape_makindu.DTO.PlatformDTO;
import HB_CAPE_MAK.hb_cape_makindu.entity.Platform;
import HB_CAPE_MAK.hb_cape_makindu.exception.NotFoundInstantFakingException;
import HB_CAPE_MAK.hb_cape_makindu.repository.PlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlatformService implements DAOServiceInterface{

    private final PlatformRepository platformRepository;

    @Override
    public List findAll() {
        return platformRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return platformRepository.findById(id);
    }

    public Platform persist(PlatformDTO platformDTO, Long id) {
        if (id != null && platformRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Platform", "id", id
            );
        }

        Platform p = new Platform();
        p.setId(id);
        p.setName(platformDTO.getName());


        // Si id = null, le save fera un insert, sinon un update
        return platformRepository.saveAndFlush(p);
    }
}
