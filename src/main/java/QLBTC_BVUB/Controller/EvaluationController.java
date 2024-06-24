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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class EvaluationController {
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
    //Hiện danh sách các đánh giá theo khoa.
    @GetMapping("/evaluation")
    public String EvaluationForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        Long currentUserId = nhanVienService.getUserIdByUsername(currentUsername);
        NhanVien currentUser = nhanVienService.getNhanVienbyId(currentUserId);
        PhongBan pb = currentUser.getPhongBan();

        List<DanhGia> danhGias = danhGiaService.getDanhGiaByphongBan_id(pb);
        danhGias = removeDuplicateDanhGias(danhGias);

        model.addAttribute("danhgias", danhGias);
        return "admin/evaluation/index";
    }

    // Method để loại bỏ các tiểu mục trùng nhau từ danh sách danhGias
    private List<DanhGia> removeDuplicateDanhGias(List<DanhGia> danhGias) {
        Set<Long> seenTieuMucIds = new HashSet<>();
        List<DanhGia> uniqueDanhGias = new ArrayList<>();

        for (DanhGia danhGia : danhGias) {
            Long tieuMucId = danhGia.getTieuMuc().getId();
            if (!seenTieuMucIds.contains(tieuMucId)) {
                seenTieuMucIds.add(tieuMucId);
                uniqueDanhGias.add(danhGia);
            }
        }
        return uniqueDanhGias;
    }
//Xử lý tác vụ Đánh giá cá nhân----------------------------------------------
    @PostMapping("/evaluation")
    public String EvaluationForm(Authentication authentication,
            @RequestParam("anhBangChung") MultipartFile file,
                @RequestParam("taskId") String taskId,
                RedirectAttributes redirectAttributes) {
            // Xử lý lưu trữ tệp tin và các thông tin khác nếu cần
            // Ví dụ: lưu file vào hệ thống hoặc cơ sở dữ liệu

            redirectAttributes.addFlashAttribute("message", "Đã tải lên bằng chứng thành công!");
            return "redirect:/evaluation";
        }
}