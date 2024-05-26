package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.TCChuong;
import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Repository.IChuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuongService {
    @Autowired
    private IChuongRepository iChuongRepository;
    public void saveChuong(TCChuong chuong) {
        iChuongRepository.save(chuong);
    }

    public List<TCChuong> getAllChuong() {
        return iChuongRepository.findAll();
    }

    public TCChuong getChuongById(Long id) {
        return iChuongRepository.findById(id).orElse(null);
    }
}
