package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IDanhGiaRepository extends JpaRepository<DanhGia,Long>{

}
