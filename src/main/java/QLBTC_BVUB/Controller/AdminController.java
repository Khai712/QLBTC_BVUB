package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.*;
import QLBTC_BVUB.Model.DanhGiaForm;
import QLBTC_BVUB.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private PhanService phanService;
    @Autowired
    private ChuongService chuongService;
    @Autowired
    private TieuMucService tieuMucService;
    @Autowired
    private DanhGiaService danhGiaService;
    @Autowired
    private UserService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
//<!----------------------phần-----------------!>
    @GetMapping("/them-phan")
    public String ThemPhanForm(Model model) {
        model.addAttribute("phans", new TCPhan());
        return "admin/Them_Phan";
    }

    @PostMapping("/them-phan")
    public String ThemPhanForm(@ModelAttribute ("newPhan") TCPhan phan) {
        phanService.savePhan(phan);
        return "redirect:/them-phan";
    }
    //------------------ Chương ----------------------
    @GetMapping("/them-chuong")
    public String ThemChuongForm(Model model) {
        model.addAttribute("phans", phanService.getAllPhan());
        model.addAttribute("newChuong", new TCChuong());
        return "admin/Them_Chuong";
    }
    @PostMapping("/them-chuong")
    public String ThemChuongForm(@ModelAttribute ("newChuong")  TCChuong chuong) {
        TCPhan phanSeclected = phanService.getPhanById(chuong.getTcPhan().getId());
        chuong.setTcPhan(phanSeclected);
        chuongService.saveChuong(chuong);
        return "redirect:/them-chuong";
    }
    //------------------ Mục ----------------------
   @GetMapping("/them-muc")
    public String themMucForm(Model model) {
       model.addAttribute("phans", phanService.getAllPhan());
       model.addAttribute("chuongs",chuongService.getAllChuong());
       model.addAttribute("NewMuc", new TieuMuc());
        return "admin/Them_Muc";
    }

    @PostMapping("/them-muc")
    public String ThemMucForm(@ModelAttribute("NewMuc") TieuMuc tieuMuc) {
        TCChuong chuongSeclected = chuongService.getChuongById(tieuMuc.getTcChuong().getId());
        tieuMuc.setTcChuong(chuongSeclected);
        tieuMucService.saveTieuMuc(tieuMuc);
        return "redirect:/them-muc";
    }
    //------------------------Bảng đánh giá-----------------
    //Thêm tiểu mục
    @GetMapping("/them-tieu-muc")
    public String themtieuMucForm(Model model) {
        model.addAttribute("chuongs", chuongService.getAllChuong());
        model.addAttribute("Mucs", tieuMucService.getAllTieuMuc());
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        model.addAttribute("danhGias", new ArrayList<DanhGia>());
        return "admin/Them_Tieu_Muc";
    }
    @PostMapping("/them-tieu-muc")
    public String themtieuMucForm(@ModelAttribute DanhGiaForm danhGiaForm,
                                  Authentication authentication) {
        // Lấy danh sách danhGia từ danhGiaForm
        List<DanhGia> danhGias = danhGiaForm.getDanhGias();

        // Xử lý logic để lưu danh sách đánh giá nhận được từ form
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        Long currentUserId = nhanVienService.getUserIdByUsername(currentUsername);
        NhanVien currentUser = nhanVienService.getNhanVienbyId(currentUserId);
        for (DanhGia danhGia : danhGias) {
            danhGia.setNgayDang(LocalDateTime.now());
            danhGia.setNhanVien(currentUser);
            // Lưu từng đối tượng danhGia
            danhGiaService.save(danhGia);
        }

        return "redirect:/them-tieu-muc";
    }


}
