package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.Anh;
import QLBTC_BVUB.Entity.NhanVien_DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnhRepository extends JpaRepository<Anh,Long> {
}
