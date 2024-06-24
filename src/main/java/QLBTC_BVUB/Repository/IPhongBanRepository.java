package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.PhongBan;
import QLBTC_BVUB.Entity.TCPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhongBanRepository  extends JpaRepository<PhongBan,Long> {
}
