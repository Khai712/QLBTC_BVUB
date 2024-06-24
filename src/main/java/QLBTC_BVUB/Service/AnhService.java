package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.Anh;
import QLBTC_BVUB.Repository.IAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnhService {
    @Autowired
    IAnhRepository anhRepository;
    public Anh save(Anh anh) {
        return anhRepository.save(anh);
    }
}
