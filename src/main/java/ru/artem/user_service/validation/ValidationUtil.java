package ru.artem.user_service.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import ru.artem.user_service.exception.ValidationException;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> void validate(T object) {

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {

            String message = violations.stream()
                    .map(v -> v.getPropertyPath() + " : " + v.getMessage())
                    .collect(Collectors.joining(", "));

            throw new ValidationException(message);
        }
    }
}
