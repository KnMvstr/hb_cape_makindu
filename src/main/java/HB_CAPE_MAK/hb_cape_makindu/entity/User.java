package HB_CAPE_MAK.hb_cape_makindu.entity;

import HB_CAPE_MAK.hb_cape_makindu.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.UserPrivateView.class)
    protected Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pseudo;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String email;

    @Column(nullable = false)
    @JsonView(JsonViews.UserPublicView.class)
    protected String pwd;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserFullView.class)
    private List<Review> reviews = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

