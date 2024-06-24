package QLBTC_BVUB;

import QLBTC_BVUB.Entity.DanhGia;
import com.mysql.cj.result.Row;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<DanhGia> danhGias() {
        List<DanhGia> danhGias = new ArrayList<>();
        danhGias.add(new DanhGia());
        return danhGias;
    }
}
