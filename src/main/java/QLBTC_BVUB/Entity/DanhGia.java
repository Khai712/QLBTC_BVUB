package QLBTC_BVUB.Entity;

import QLBTC_BVUB.Error.ErrorDB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

import static QLBTC_BVUB.Error.SetSizeNvarchar.DB_NVARCHARMAX;

@Data
@Entity
@Table(name = "danhgia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noidung", columnDefinition = DB_NVARCHARMAX)
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size( min = 1, message =  ErrorDB.DB_500CHARACTERS)
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


    @OneToMany(mappedBy = "danhGiaid")
    private Set<NhanVien_DanhGia> Danhgia_NhanViens;

}
