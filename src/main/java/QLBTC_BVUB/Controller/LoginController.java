package QLBTC_BVUB.Controller;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.context.support.DefaultMessageSourceResolvable;
@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("Nhanvien", new NhanVien());
        return "NhanVien/login";
    }
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new NhanVien());
        return "NhanVien/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") NhanVien user,
                           @NotNull BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "NhanVien/register";
        }
        userService.save(user);
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String login(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "NhanVien/login";
        }
    }

}
