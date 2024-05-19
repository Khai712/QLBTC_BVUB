package QLBTC_BVUB.Validator;

import QLBTC_BVUB.Entity.NhanVien;
import QLBTC_BVUB.Validator.Annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, NhanVien> {
    @Override
    public boolean isValid(NhanVien nhanVien, ConstraintValidatorContext context){
        if(nhanVien == null)
            return true;
        return nhanVien.getId() != null;
    }
}

