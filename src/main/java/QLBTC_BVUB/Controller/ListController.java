package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.DanhGia;
import QLBTC_BVUB.Repository.IDanhGiaRepository;
import QLBTC_BVUB.Service.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ListController {
    @Autowired
    private DanhGiaService danhGiaService;

    @GetMapping("/list")
    public String list( Model model, Authentication authentication ) {
        List<DanhGia> DanhGias = danhGiaService.getAllDanhGia();
        model.addAttribute("DanhGias", DanhGias);
        return "NhanVien/list";
    }
}
