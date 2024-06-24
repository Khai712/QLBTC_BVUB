package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Entity.PhongBan;
import QLBTC_BVUB.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class UserManagerController {

    @Autowired
    private UserService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("/userprofile")
    public String UsermanagerForm(Model model) {
        List<NhanVien> userList = nhanVienService.getUsersByPhongBan(2L);
        // Gửi danh sách user và số trang tới view
        model.addAttribute("users", userList);
        return "admin/userprofile/index";
    }

    @GetMapping("/userprofile/viewStaffDetails/{id}")
    public String viewStaffDetails(@PathVariable("id") Long userid, Model model) {
        NhanVien thongTinUsers = nhanVienService.getNhanVienbyId(userid);

        model.addAttribute("thongTinUsers", thongTinUsers);
        return "admin/fragments/modalUserprofiles";
    }

    @GetMapping("/userprofile/editStaffDetails/{id}")
    public String getEditStaffDetailsForm(@PathVariable("id") Long userId, Model model) {
        Optional<NhanVien> nhanVien = Optional.ofNullable(nhanVienService.getNhanVienbyId(userId));
       if(nhanVien.isPresent()){
          NhanVien nv =  nhanVien.get();
        List<PhongBan> phongBanList = phongBanService.getAllPhongBan();
        model.addAttribute("nhanvien", nv);
        model.addAttribute("phongban", phongBanList);
        return "admin/fragments/modalEditUserprofiles";
       }
        return "redirect:/userprofile?error";
    }


    @PostMapping("/userprofile/editStaffDetails")
    public String processEditStaffDetailsForm(@ModelAttribute("nhanvien") NhanVien nhanVien,
                                              @RequestParam("confirmPassword") String confirmPassword) {
        NhanVien existingUser = nhanVienService.getNhanVienbyId(nhanVien.getId());
        if (existingUser != null) {
            if (nhanVien.getGioitinh() != null) {
                nhanVien.setGioitinh(nhanVien.getGioitinh());
            }
            if (nhanVien.getQuequan() != null) {
                nhanVien.setQuequan(nhanVien.getQuequan());
            }
            if (nhanVien.getPhongBan() != null) {
                nhanVien.setPhongBan(nhanVien.getPhongBan());
            }
            if (nhanVien.getMachucvu() != null) {
                nhanVien.setMachucvu(nhanVien.getMachucvu());
            }
            if (nhanVien.getTencv() != null) {
                nhanVien.setTencv(nhanVien.getTencv());
            }
            if (nhanVien.getCCCD() != null) {
                existingUser.setCCCD(nhanVien.getCCCD());
            }
            if (nhanVien.getSDT() != null) {
                existingUser.setSDT(nhanVien.getSDT());
            }
            if (nhanVien.getNgaysinh() != null) {
                existingUser.setNgaysinh(nhanVien.getNgaysinh());
            }
            if (nhanVien.getUsername() != null) {
                existingUser.setUsername(nhanVien.getUsername());
            }
            if (nhanVien.getPassword() != null || nhanVien.getPassword() != confirmPassword) {
                existingUser.setPassword(nhanVien.getPassword());
            }
            nhanVienService.save(nhanVien);
            return "redirect:/userprofile";
        }
        return "redirect:/userprofile?error";
    }

    @GetMapping("/userprofile/addStaffDetails")
    public String getAddStaffDetailsForm(Model model) {
        NhanVien nhanVien = new NhanVien();
        model.addAttribute("nhanvien", nhanVien);
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
            return "admin/fragments/modalAddUser";
    }

    @PostMapping("/userprofile/addStaffDetails")
    public String register(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult bindingResult, Model model) {
        nhanVienService.save(nhanvien);
        return "redirect:/userprofile";
    }
    @PostMapping("/userprofile/deleteStaffDetails/{id}")
    public  String deletedStaffDetailsForm(Model model, @PathVariable("id") Long userId )
    {
        NhanVien existingUser = nhanVienService.getNhanVienbyId(userId);
        if (existingUser != null) {
            nhanVienService.deleteNhanVien(userId);
            return "redirect:/userprofile";
        }
        else
        return "redirect:/userprofile?error";
    }
}

