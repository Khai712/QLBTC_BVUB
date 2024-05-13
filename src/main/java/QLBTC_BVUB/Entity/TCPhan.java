package QLBTC_BVUB.Model;

import QLBTC_BVUB.Error.ErrorDB;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "tcphan")
public class TCPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noidung")
    @NotEmpty(message = ErrorDB.DB_CONTENTEMPTY)
    @Size(max = 500, min = 1, message =  ErrorDB.DB_500CHARACTERS)
    private String noidung;
    //--------------------------Relationship------------------------

    @OneToMany(mappedBy = "tcPhan")
    private Set<TCChuong> tcChuongs;
}
