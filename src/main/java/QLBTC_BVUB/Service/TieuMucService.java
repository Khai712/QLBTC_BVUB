package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.TCChuong;
import QLBTC_BVUB.Entity.TieuMuc;
import QLBTC_BVUB.Repository.ITieuMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TieuMucService {
    @Autowired
    private ITieuMucRepository itmucRepository;
    public void saveTieuMuc(TieuMuc tieuMuc) {
        itmucRepository.save(tieuMuc);
    }
}
