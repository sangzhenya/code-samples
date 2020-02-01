package org.xinyue.xsecurity.validator;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xinyue.xsecurity.web.service.IndexService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomizedConstraintValidator implements ConstraintValidator<CustomizedConstraint, Object> {
    private Log log = LogFactory.getLog(CustomizedConstraintValidator.class);

    @Autowired
    private IndexService indexService;

    @Override
    public void initialize(CustomizedConstraint constraintAnnotation) {
        log.info("My validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        indexService.valid("Hello Validator");
        return true;
    }
}
