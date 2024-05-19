package QLBTC_BVUB.Entity;

import QLBTC_BVUB.Error.ErrorDB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

import static QLBTC_BVUB.Error.SetSizeNvarchar.DB_NVARCHARMAX;

@Data
@Entity
@Table(name = "tcphan")
public class TCPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noidung",columnDefinition = DB_NVARCHARMAX)
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size( min = 1, message =  ErrorDB.DB_500CHARACTERS)
    private String noidung;
    //--------------------------Relationship------------------------

    @OneToMany(mappedBy = "tcPhan")
    private Set<TCChuong> tcChuongs;
}
