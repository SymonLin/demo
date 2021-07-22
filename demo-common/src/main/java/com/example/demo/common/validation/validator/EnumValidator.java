package com.example.demo.common.validation.validator;


import com.example.demo.common.annotation.EnumValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author linjian
 * @date 2021/3/18
 */
public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

    private Class<? extends Enum<?>> enumClass;

    private String enumMethodName;

    @Override
    public void initialize(EnumValid enumValid) {
        enumMethodName = enumValid.enumMethodName();
        enumClass = enumValid.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return Boolean.FALSE;
        }
        if (enumClass == null || enumMethodName == null) {
            return Boolean.FALSE;
        }
        Class<?> valueClass = value.getClass();
        try {
            Method method = enumClass.getMethod(enumMethodName, valueClass);
            if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                throw new RuntimeException(String.format("%s method return is not boolean type in the %s class",
                        enumMethodName, enumClass));
            }
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new RuntimeException(String.format("%s method is not static method in the %s class",
                        enumMethodName, enumClass));
            }
            Boolean result = (Boolean) method.invoke(null, value);
            return result != null && result;
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(String.format("This %s(%s) method does not exist in the %s",
                    enumMethodName, valueClass, enumClass), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
