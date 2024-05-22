package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.DanhGia;
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
}
