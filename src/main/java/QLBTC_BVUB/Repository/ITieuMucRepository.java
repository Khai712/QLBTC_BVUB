package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.TieuMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITieuMucRepository extends JpaRepository<TieuMuc,Long>
{

}
