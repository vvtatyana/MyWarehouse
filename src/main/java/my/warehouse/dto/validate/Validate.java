package my.warehouse.dto.validate;

import my.warehouse.dto.IDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class Validate {
    private final Validator validator;

    @Autowired
    public Validate(Validator validator) {
        this.validator = validator;
    }

    public List<String> validate(IDTO dto){
        List<String> errors = new ArrayList<>();
        Set<ConstraintViolation<IDTO>> cvs = validator.validate(dto);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<IDTO> cv : cvs) {
                StringBuilder err = new StringBuilder();
                err.append(cv.getPropertyPath());
                err.append(" ");
                err.append(cv.getMessage());
                errors.add(err.toString());
            }
        }

        return errors;
    }
}
