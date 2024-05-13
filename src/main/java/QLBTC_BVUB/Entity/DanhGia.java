package QLBTC_BVUB.Model;

import QLBTC_BVUB.Error.ErrorDB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "danhgia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noidung")
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size(max = 500, min = 1, message =  ErrorDB.DB_500CHARACTERS)
    private String noiDung;

    @Column(name = "ngay_dang")
    private LocalDateTime ngayDang;

    @ManyToOne
    @JoinColumn(name = "tieumuc_id", referencedColumnName = "id", nullable = false)
    private TieuMuc tieuMuc_id;

    @ManyToOne
    @JoinColumn(name = "phongban_id", referencedColumnName = "id",nullable = false)
    private PhongBan phongBan_id;

    @ManyToOne
    @JoinColumn(name ="nhanvientao_id",referencedColumnName = "id",nullable = false )
    private NhanVien nhanVien_id;


    //--------------------------Relationship------------------------

    @OneToMany(mappedBy = "danhGia")
    private Set<Anh> anhs;
}
