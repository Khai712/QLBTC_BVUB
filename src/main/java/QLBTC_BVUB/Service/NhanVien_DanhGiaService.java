package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.NhanVien_DanhGia;
import QLBTC_BVUB.Repository.IDanhGiaRepository;
import QLBTC_BVUB.Repository.INhanVien_DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVien_DanhGiaService {
    @Autowired
    private INhanVien_DanhGiaRepository nhanVien_danhGiaRepository;

    public NhanVien_DanhGia save(NhanVien_DanhGia nhanVienDanhGia) {
        return nhanVien_danhGiaRepository.save(nhanVienDanhGia);
    }

    public NhanVien_DanhGia getNhanVienDanhGiaByID(Long currentUser) {
        return nhanVien_danhGiaRepository.findNhanVienDanhGiaById(currentUser);
    }
}
