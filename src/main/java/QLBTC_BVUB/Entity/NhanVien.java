package QLBTC_BVUB.Entity;
import QLBTC_BVUB.Error.ErrorDB;
import QLBTC_BVUB.Validator.Annotation.ValidUsername;
import jakarta.persistence.*;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static QLBTC_BVUB.Error.SetSizeNvarchar.DB_NVARCHARMAX;


@Data
@Entity
@Table(name = "nhanvien")
public class NhanVien {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    @NotBlank(message = ErrorDB.DB_REQUIRED)
    @Size(max = 500,message = ErrorDB.DB_50CHARACTERS)
    @ValidUsername
    private String username;

    @Column(name = "password", length = 500, nullable = false)
    @NotBlank(message = ErrorDB.DB_REQUIRED)
    @Size(max = 500,message = ErrorDB.DB_50CHARACTERS)
    private String password;

    @Column(name = "tennv", length = 200, nullable = false,columnDefinition = DB_NVARCHARMAX)
    @NotBlank(message = ErrorDB.DB_BLANK)
    @Size(max = 200,message = ErrorDB.DB_200CHARACTERS)
    @Getter
    private String tennv;

    @Column(name = "machucvu", length = 200, nullable = false)
    @NotBlank(message = ErrorDB.DB_BLANK)
    @Size(max = 200,message = ErrorDB.DB_200CHARACTERS)
    @Getter
    private String machucvu;

    @Column(name = "tencv", length = 200, nullable = false,columnDefinition = DB_NVARCHARMAX)
    @NotBlank(message = ErrorDB.DB_BLANK)
    @Size(max = 200,message = ErrorDB.DB_200CHARACTERS)
    @Getter
    private String tencv;


    @Column(name = "CCCD", length = 13)
    @NotBlank(message = ErrorDB.DB_BLANK)
    @Size(max = 13,message = ErrorDB.DB_13CHARACTERS)
    private String CCCD;

    @Column(name = "ngaysinh", nullable = false)
    private Date ngaysinh;

    @Column(name = "SDT", length = 13, nullable = false)
    @NotBlank(message = ErrorDB.DB_REQUIRED)
    @Size(max = 13,message = ErrorDB.DB_13CHARACTERS)
    private String SDT;

    @Column(name = "gioitinh")
    @Size(max = 5,min = 1)
    private String gioitinh;

    @Column(name = "quequan")
    private String quequan;

    @Column(name = "Avatar_url",nullable = true)
    private String avatarUserFileName;

    @ManyToOne
    @JoinColumn(name="phongban_id", referencedColumnName = "id",nullable = false)
    private PhongBan phongBan;

    //--------------------------Relationship------------------------

    @OneToMany(mappedBy = "nhanVienid")
    private Set<NhanVien_DanhGia> nhanVien_danhGias;

    @OneToMany(mappedBy = "nhanVien_id")
    private Set<DanhGia> taodanhGias;

}
