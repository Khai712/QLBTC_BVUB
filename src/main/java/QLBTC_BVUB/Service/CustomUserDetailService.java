package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien nhanVien = userRepository.findByUsername(username);
        if(nhanVien == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(nhanVien, userRepository);
    }
}