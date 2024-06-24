package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.DanhGia;
import QLBTC_BVUB.Entity.NhanVien_DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INhanVien_DanhGiaRepository extends JpaRepository<NhanVien_DanhGia,Long> {
    NhanVien_DanhGia findNhanVienDanhGiaById(Long currentUser);
}
