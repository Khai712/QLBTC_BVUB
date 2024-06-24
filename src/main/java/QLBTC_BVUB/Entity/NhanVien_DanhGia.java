package QLBTC_BVUB.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "nhanvien_danhgia")
public class NhanVien_DanhGia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "danhgiaId")
    private DanhGia danhGia;

    @ManyToOne
    @JoinColumn(name = "nhanvienId")
    private NhanVien nhanVien;

    @Column(name = "ngaydanhgia")
    private LocalDateTime ngaydanhgia;

    @OneToMany(mappedBy = "nhanVienDanhGia")
    private List<Anh> anhs_danhgia;

}

