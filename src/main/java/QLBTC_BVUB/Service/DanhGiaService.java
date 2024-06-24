package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.DanhGia;
import QLBTC_BVUB.Entity.PhongBan;
import QLBTC_BVUB.Entity.TieuMuc;
import QLBTC_BVUB.Repository.IDanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DanhGiaService {
    @Autowired
    private IDanhGiaRepository danhGiaRepository;

    public List<DanhGia> getAllDanhGia()
    {
        return danhGiaRepository.findAll();
    }

    public DanhGia save(DanhGia danhGia) {
        return danhGiaRepository.save(danhGia);
    }
    public List<DanhGia> getDanhGiaByphongBan_id(PhongBan phongBan) {
        return danhGiaRepository.findDanhGiaByPhongBan(phongBan);
    }

    public List<DanhGia>getDanhGiaByTieuMucId(Long id) {
        return danhGiaRepository.findDanhGiaByTieuMuc_Id(id);
    }

    public List<DanhGia> findAll() {
        return danhGiaRepository.findAll();
    }

    public DanhGia getDanhGiaById(Long danhGiaId) {
        return danhGiaRepository.getById(danhGiaId);
    }
}
