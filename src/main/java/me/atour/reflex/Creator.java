package me.atour.reflex;

import java.lang.reflect.Field;
import lombok.NonNull;

/**
 * Creates and modifies class instances.
 */
public class Creator {

  /**
   * Layer over {@link sun.misc.Unsafe}.
   */
  private static final Unsafer unsafe = new Unsafer();

  /**
   * Instantiates a class instance.
   *
   * @param clazz the {@link Class} for which to create the instance
   * @param <T> type parameter for the class type
   * @return the created instance
   * @throws InstantiationException when the class cannot be instantiated
   */
  @NonNull public <T> T instantiate(@NonNull Class<T> clazz) throws InstantiationException {
    return unsafe.allocateInstance(clazz);
  }

  /**
   * Sets a field in the given instance.
   *
   * @param instance the instance to modify
   * @param field the {@link Field} to modify
   * @param value the value to set the field to
   */
  public void setField(@NonNull Object instance, @NonNull Field field, Object value) {
    long offset = unsafe.objectFieldOffset(field);
    if (field.getType().isPrimitive()) {
      unsafe.putPrimitive(instance, offset, value, field.getType());
    } else {
      unsafe.putObject(instance, offset, value);
    }
  }

  /**
   * Sets a field in the given instance.
   *
   * @param instance the instance to modify
   * @param field the field to modify, represented as a {@link String}
   * @param value the value to set the field to
   * @param <T> type parameter for the instance type
   * @throws NoSuchFieldException when the given field representation does not exist
   */
  public <T> void setField(@NonNull T instance, @NonNull String field, Object value) throws NoSuchFieldException {
    setField(instance, instance.getClass().getDeclaredField(field), value);
  }
}
