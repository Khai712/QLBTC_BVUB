package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.*;
import QLBTC_BVUB.Model.DanhGiaForm;
import QLBTC_BVUB.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

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
    private AnhService anhService;
    @Autowired
    private NhanVien_DanhGiaService nhanVienDanhGiaService;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value("${custom.file.path}")
    private String customFilePath;

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

    //Hiện bảng lưu đánh giá-------------------------------------------
    @GetMapping("/evaluation/getDanhGiaByTieuMucId")
    public String getDanhGiaByTieuMucId(@RequestParam Long id, Model model) {
        List<DanhGia> danhGia = danhGiaService.getDanhGiaByTieuMucId(id);
        model.addAttribute("danhGias", danhGia);
        return "admin/fragments/modalContent";
    }

    //Lưu đánh giá------------------------------------------------
@PostMapping("/evaluation/uploadEvidence")
    public String uploadEvidence(@RequestParam Map<String, String> allParams,
                                 @RequestParam("anhBangChung_input") MultipartFile[] files,
                                 Authentication authentication) throws IOException {
        List<Long> danhGiasIds = new ArrayList<>();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        Long currentUserId = nhanVienService.getUserIdByUsername(currentUsername);
        NhanVien currentUser = nhanVienService.getNhanVienbyId(currentUserId);

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("task_")) {
                Long danhGiaId = Long.parseLong(entry.getValue());
                danhGiasIds.add(danhGiaId);
                NhanVien_DanhGia nv_dg = new NhanVien_DanhGia();
                DanhGia danhGia = danhGiaService.getDanhGiaById(danhGiaId);
                nv_dg.setDanhGia(danhGia);
                nv_dg.setNgaydanhgia(LocalDateTime.now());
                nv_dg.setNhanVien(currentUser);

                nhanVienDanhGiaService.save(nv_dg);

                if (files != null) {
                    for (MultipartFile file : files) {
                        if (!file.isEmpty()) {
                            // Lưu file vào hệ thống và lưu thông tin vào cơ sở dữ liệu
                            String fileName = saveImageToDatabase(file, nv_dg.getDanhGia().getId().toString(), nv_dg.getDanhGia().getTieuMuc().getMatieumuc());
                            Anh anh = new Anh();
                            anh.setNhanVienDanhGia(nv_dg);
                            anh.setDuongDan(fileName);
                            anhService.save(anh);

                        }
                    }
                }
            }
        }
        return "redirect:/evaluation";
    }

    private String sanitizeFileName(String fileName) {
        String fileNameWithoutAccent = convertToAscii(fileName);
        return fileNameWithoutAccent.replaceAll("\\s", "_");
    }

    private String convertToAscii(String input) {
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    private String saveImageToDatabase(MultipartFile image, String madanhgia, String mamuc) throws IOException {

        String fileName = image.getOriginalFilename();
        String sanitizedFileName = sanitizeFileName(fileName);
        Path directoryPath = Paths.get(customFilePath, sanitizeFileName(mamuc), sanitizeFileName(madanhgia));
        Path filePath = directoryPath.resolve(sanitizedFileName);

        try {
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            if (!Files.isWritable(directoryPath)) {
                throw new IOException("Directory is not writable: " + directoryPath);
            }

            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
            throw new IOException("Failed to save image: " + sanitizedFileName, e);
        }

        return sanitizeFileName(mamuc) + "/" + sanitizeFileName(madanhgia) + "/" + sanitizedFileName;
    }

}
