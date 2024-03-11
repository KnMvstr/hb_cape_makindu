package HB_CAPE_MAK.hb_cape_makindu.service;


import HB_CAPE_MAK.hb_cape_makindu.entity.Moderator;
import HB_CAPE_MAK.hb_cape_makindu.entity.User;
import HB_CAPE_MAK.hb_cape_makindu.repository.UserRepository;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOFindByEmailInterface;
import HB_CAPE_MAK.hb_cape_makindu.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements DAOFindByIdInterface<User>, DAOFindByEmailInterface, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    // function to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // function that retrieve all users from database
    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    // function that update an existing user in database
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // function that remove a user from database by it's id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getPseudo(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }


    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }

}

