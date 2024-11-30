package io.rapidtogo.rapidtogo.utils.validation.enum_validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

  private Class<? extends Enum<?>> enumClass;
  private boolean notBlank;
  private boolean notNull;

  @Override
  public void initialize(ValidEnum constraintAnnotation) {

    this.enumClass = constraintAnnotation.enumClass();
    this.notBlank = constraintAnnotation.notBlank();
    this.notNull = constraintAnnotation.notNull();
  }

  @Override
  public boolean isValid(String inputEnumValue,
      ConstraintValidatorContext constraintValidatorContext) {

    constraintValidatorContext.disableDefaultConstraintViolation();

    for (Enum<?> enumValue : enumClass.getEnumConstants()) {

      if (enumValue.name().equalsIgnoreCase(inputEnumValue)) {
        return true;
      }
    }

    if ((inputEnumValue == null || inputEnumValue.trim().isEmpty()) && notBlank) {

      constraintValidatorContext.buildConstraintViolationWithTemplate(
              "The field " + enumClass.getSimpleName() + " is required and can not be blank.")
          .addConstraintViolation();

      return false;
    }

    if (inputEnumValue == null && notNull) {

      constraintValidatorContext.buildConstraintViolationWithTemplate(
          "The field " + enumClass.getSimpleName() + " is required.").addConstraintViolation();

      return false;
    }

    constraintValidatorContext.buildConstraintViolationWithTemplate(getConstraintMessage())
        .addConstraintViolation();

    return false;
  }

  private String getConstraintMessage() {

    String message = "Must be any of ";

    for (Enum<?> enumValue : enumClass.getEnumConstants()) {
      message = message.concat(enumValue.name());
      message = message.concat(", ");
    }

    return message.substring(0, message.length() - 2);
  }
}
