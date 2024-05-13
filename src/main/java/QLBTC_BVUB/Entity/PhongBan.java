package QLBTC_BVUB.Model;

import QLBTC_BVUB.Error.ErrorDB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Data
@Entity
@Table(name = "phongban")
public class PhongBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenpb", length = 200, nullable = false)
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size(max = 200,message = ErrorDB.DB_200CHARACTERS)
    @Getter
    private String tenpb;

    @Column(name = "diachipb", length = 200, nullable = false)
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size(max = 200,message = ErrorDB.DB_200CHARACTERS)
    @Getter
    private String diachipb;
    //--------------------------Relationship------------------------

    @OneToMany(mappedBy = "phongBan_id")
    private Set<DanhGia> danhGias;

    @OneToMany(mappedBy = "phongBan")
    private Set<NhanVien> nhanViens;

}
