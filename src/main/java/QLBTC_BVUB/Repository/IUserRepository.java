package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<NhanVien, Long> {
    @Query("SELECT u FROM NhanVien u WHERE u.username = ?1")
    NhanVien findByUsername(String username);

    @Query(value = "SELECT r.phongban_id FROM NhanVien r WHERE r.id = ?1",nativeQuery = true)
    String[] getRolesOfUser(Long userId);
    List<NhanVien> findByPhongBanIdGreaterThan(Long phongBanId);
}
