package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(Model model) {
        return "admin/index";
    }
}
