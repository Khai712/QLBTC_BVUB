package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IUserRepository extends JpaRepository<NhanVien, Long> {
    @Query("SELECT u FROM NhanVien u WHERE u.username = ?1")
    NhanVien findByUsername(String username);
}
