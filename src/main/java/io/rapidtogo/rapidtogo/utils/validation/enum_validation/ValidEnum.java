package io.rapidtogo.rapidtogo.utils.validation.enum_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {

  String message() default "Invalid enum value";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * The enum class to validate against.
   */
  Class<? extends Enum<?>> enumClass();

  /**
   * If the field is required and can not be blank.
   */
  boolean notBlank() default true;

  /**
   * If the field is required.
   */
  boolean notNull() default true;
}
