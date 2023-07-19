package me.atour.reflex;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import lombok.NonNull;

/**
 * Inspects class instances.
 */
public class Inspector {

  /**
   * Gets a field value.
   *
   * @param instance the instance to get the field value of
   * @param field the field the value of which to read
   * @param <T> type parameter for the field return type
   * @return the field value
   * @throws IllegalAccessException when the field getter cannot be resolved
   */
  public <T> T getField(@NonNull Object instance, @NonNull Field field) throws IllegalAccessException {
    MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(field.getDeclaringClass(), MethodHandles.lookup());
    MethodHandle handle = lookup.unreflectGetter(field);
    try {
      return (T) handle.invoke(instance);
    } catch (Throwable e) {
      throw new ReflectedGetterThrowsException(e);
    }
  }
}
