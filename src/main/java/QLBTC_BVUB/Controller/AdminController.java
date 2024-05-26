package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Entity.TCChuong;
import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Entity.TieuMuc;
import QLBTC_BVUB.Service.ChuongService;
import QLBTC_BVUB.Service.PhanService;
import QLBTC_BVUB.Service.TieuMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private PhanService phanService;
    @Autowired
    private ChuongService chuongService;
    @Autowired
    private TieuMucService tieuMucService;
//<!----------------------Thêm phần-----------------!>
    @GetMapping("/them-phan")
    public String ThemPhanForm(Model model) {
        model.addAttribute("phans", new TCPhan());
        return "Admin/Them_Phan";
    }

    @PostMapping("/them-phan")
    public String ThemPhanForm(@ModelAttribute ("newPhan") TCPhan phan) {
        phanService.savePhan(phan);
        return "redirect:/them-phan";
    }
    //------------------Thêm Chương ----------------------
    @GetMapping("/them-chuong")
    public String ThemChuongForm(Model model) {
        model.addAttribute("phans", phanService.getAllPhan());
        model.addAttribute("newChuong", new TCChuong());
        return "Admin/Them_Chuong";
    }
    @PostMapping("/them-chuong")
    public String ThemChuongForm(@ModelAttribute ("newChuong")  TCChuong chuong) {
        TCPhan phanSeclected = phanService.getPhanById(chuong.getTcPhan().getId());
        chuong.setTcPhan(phanSeclected);
        chuongService.saveChuong(chuong);
        return "redirect:/them-chuong";
    }
    //------------------Thêm Chương ----------------------
   @GetMapping("/them-tieu-muc")
    public String themTieuMucForm(Model model) {
       model.addAttribute("phans", phanService.getAllPhan());
       model.addAttribute("chuongs",chuongService.getAllChuong());
       model.addAttribute("NewtieuMuc", new TieuMuc());
        return "Admin/Them_TieuMuc";
    }
    @PostMapping("/them-tieu-muc")
    public String ThemTieuMucForm(@ModelAttribute("NewtieuMuc") TieuMuc tieuMuc) {
        TCChuong chuongSeclected = chuongService.getChuongById(tieuMuc.getTcChuong().getId());
        tieuMuc.setTcChuong(chuongSeclected);
        tieuMucService.saveTieuMuc(tieuMuc);
        return "redirect:/them-tieu-muc";
    }
}
