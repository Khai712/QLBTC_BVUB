package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Repository.IUserRepository;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {
    @Getter
    private final NhanVien nhanVien;
    private final IUserRepository userRepository;

    public CustomUserDetail(NhanVien nhanVien, IUserRepository userRepository){
        this.nhanVien = nhanVien;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(userRepository.getRolesOfUser(nhanVien.getId()))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    public Long getId(){
        return nhanVien.getId();
    }

    @Override
    public String getPassword() {
        return nhanVien.getPassword();
    }

    @Override
    public String getUsername() {
        return nhanVien.getUsername();
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
