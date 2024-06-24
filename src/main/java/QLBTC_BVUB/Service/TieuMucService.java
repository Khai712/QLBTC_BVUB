package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.DanhGia;
import QLBTC_BVUB.Entity.TCChuong;
import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Entity.TieuMuc;
import QLBTC_BVUB.Repository.IDanhGiaRepository;
import QLBTC_BVUB.Repository.ITieuMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TieuMucService {
    @Autowired
    private ITieuMucRepository itmucRepository;
    private IDanhGiaRepository iDanhGiaRepository;
    public void saveTieuMuc(TieuMuc tieuMuc) {
        itmucRepository.save(tieuMuc);
    }
    public List<TieuMuc> getAllTieuMuc() {
        return itmucRepository.findAll();
    }

    public TieuMuc getTieuMucById(Long id) {
        return itmucRepository.findById(id).orElse(null);
    }

    public void saveDanhGia(DanhGia danhGia) {
            iDanhGiaRepository.save(danhGia);}
}
