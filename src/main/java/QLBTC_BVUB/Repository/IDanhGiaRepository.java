package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.DanhGia;
import QLBTC_BVUB.Entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IDanhGiaRepository extends JpaRepository<DanhGia,Long>{
    List<DanhGia> findDanhGiaByPhongBan(PhongBan phongBan);
}
