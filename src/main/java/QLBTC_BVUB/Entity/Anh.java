package QLBTC_BVUB.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "anh")
public class Anh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danhgia_id", referencedColumnName = "id", nullable = false)
    private DanhGia danhGia;

    // Thêm các trường dữ liệu khác của ảnh
    @Column(name = "duong_dan")
    private String duongDan;
}