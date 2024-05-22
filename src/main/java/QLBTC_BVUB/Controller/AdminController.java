package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Entity.TCChuong;
import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Entity.TieuMuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
//<!----------------------Thêm phần-----------------!>
    @PostMapping("/them-phan")
    public String ThemPhanForm(Model model) {
        model.addAttribute("tcphans", new TCPhan());
        return "Admin/Them_Phan";
    }
    //------------------Thêm Chương ----------------------
    @PostMapping("/them-phan")
    public String ThemChuongForm(Model model) {
        model.addAttribute("tcChuongs", new TCChuong());
        return "Admin/Them_Chuong";
    }
    //------------------Thêm Chương ----------------------
    @PostMapping("/them-phan")
    public String ThemTieuMucForm(Model model) {
        model.addAttribute("tcTieuMucs", new TieuMuc());
        return "Admin/Them_TieuMuc";
    }
}
