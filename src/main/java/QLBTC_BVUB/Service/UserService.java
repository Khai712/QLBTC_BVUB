package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Entity.NhanVien_DanhGia;
import QLBTC_BVUB.Repository.IUserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public void save(@NotNull NhanVien user) {
        userRepository.save(user);
    }

    public Long getUserIdByUsername(String currentUsername) {
        NhanVien user = userRepository.findByUsername(currentUsername);
        return (user != null) ? user.getId() : null;
    }

    public NhanVien getNhanVienbyId(Long currentUserId) {
        return userRepository.findById(currentUserId).orElse(null);
    }
}

