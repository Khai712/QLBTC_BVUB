package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.PhongBan;
import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Repository.IPhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    IPhongBanRepository iPhongBanRepository;
    public List<PhongBan> getAllPhongBan() {
        return iPhongBanRepository.findAll();
    }

    public PhongBan findById(Long aLong) {
        return  iPhongBanRepository.findById(aLong).orElse(null);
    }
}
