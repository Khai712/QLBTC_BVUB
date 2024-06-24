    package QLBTC_BVUB.Entity;

    import QLBTC_BVUB.Error.ErrorDB;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.NotNull;
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
        @Column(name = "muc")
        @NotNull(message = ErrorDB.DB_CONTENTEMPTY)
        @Min(value = 1, message = ErrorDB.DB_MIN1)
        private int muc;
        @Column(name = "noidung", columnDefinition = DB_NVARCHARMAX)
        @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
        @Size( min = 1, message =  ErrorDB.DB_500CHARACTERS)
        private String noiDung;

        @Column(name = "ngay_dang")
        private LocalDateTime ngayDang;

        @ManyToOne
        @JoinColumn(name = "tieumuc_id", referencedColumnName = "id", nullable = false)
        private TieuMuc tieuMuc;

        @ManyToOne
        @JoinColumn(name = "phongban_id", referencedColumnName = "id",nullable = false)
        private PhongBan phongBan;

        @ManyToOne
        @JoinColumn(name ="nhanvientao_id",referencedColumnName = "id",nullable = false )
        private NhanVien nhanVien;

        public void setPhongBan(PhongBan phongBan) {
            this.phongBan = phongBan;
        }


        //--------------------------Relationship------------------------


        @OneToMany(mappedBy = "danhGia")
        private Set<NhanVien_DanhGia> Danhgia_NhanViens;

    }
