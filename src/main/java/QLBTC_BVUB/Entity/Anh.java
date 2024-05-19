package QLBTC_BVUB.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "anh")
public class Anh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nhanvien_danhgia_id")
    private NhanVien_DanhGia nhanVienDanhGia;

    @Column(name = "ten_anh")
    private String tenanh;

    @Column(name = "duong_dan")
    private String duongDan;

}