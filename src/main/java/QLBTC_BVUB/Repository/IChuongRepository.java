package QLBTC_BVUB.Repository;

import QLBTC_BVUB.Entity.TCChuong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChuongRepository extends JpaRepository<TCChuong,Long> {

}
