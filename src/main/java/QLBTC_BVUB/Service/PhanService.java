package QLBTC_BVUB.Service;

import QLBTC_BVUB.Entity.TCPhan;
import QLBTC_BVUB.Repository.IPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhanService {
    @Autowired
    private IPhanRepository iPhanRepository;

    public  List<TCPhan> getAllPhan() {
        return iPhanRepository.findAll();
    }
    public void savePhan(TCPhan phan) { iPhanRepository.save(phan);}
    public TCPhan getPhanById(Long id) { return iPhanRepository.findById(id).orElse(null);}
}
