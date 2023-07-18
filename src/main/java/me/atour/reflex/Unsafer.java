package me.atour.reflex;

import java.lang.reflect.Field;
import lombok.NonNull;

/**
 * Deals with the calls to {@link sun.misc.Unsafe}.
 */
class Unsafer {

  /**
   * The {@link sun.misc.Unsafe} instance we use.
   */
  private static final sun.misc.Unsafe unsafe;

  static {
    try {
      Field unsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
      unsafeField.setAccessible(true);
      unsafe = (sun.misc.Unsafe) unsafeField.get(null);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException();
    }
  }

  /**
   * Instantiates a class without running the constructor.
   *
   * @see sun.misc.Unsafe#allocateInstance(Class)
   */
  public <T> T allocateInstance(Class<T> clazz) throws InstantiationException {
    return (T) unsafe.allocateInstance(clazz);
  }

  /**
   * Gets the memory location of a {@link Field} as its offset.
   *
   * @see sun.misc.Unsafe#objectFieldOffset(Field)
   */
  public long objectFieldOffset(Field field) {
    return unsafe.objectFieldOffset(field);
  }

  /**
   * Sets an instance field to the object reference.
   *
   * @see sun.misc.Unsafe#putObject(Object, long, Object)
   */
  public void putObject(Object instance, long offset, Object value) {
    unsafe.putObject(instance, offset, value);
  }

  /**
   * Sets an instance field to the primitive value.
   *
   * @see sun.misc.Unsafe#putObject(Object, long, Object)
   */
  public void putPrimitive(@NonNull Object instance, long offset, Object value, @NonNull Class<?> primitive) {
    if (primitive.equals(long.class)) {
      unsafe.putLong(instance, offset, (Long) value);
    } else if (primitive.equals(char.class)) {
      unsafe.putChar(instance, offset, (Character) value);
    } else if (primitive.equals(int.class)) {
      unsafe.putInt(instance, offset, (Integer) value);
    } else if (primitive.equals(boolean.class)) {
      unsafe.putBoolean(instance, offset, (Boolean) value);
    } else if (primitive.equals(byte.class)) {
      unsafe.putByte(instance, offset, (Byte) value);
    } else if (primitive.equals(float.class)) {
      unsafe.putFloat(instance, offset, (Float) value);
    } else if (primitive.equals(double.class)) {
      unsafe.putDouble(instance, offset, (Double) value);
    } else if (primitive.equals(short.class)) {
      unsafe.putShort(instance, offset, (Short) value);
    } else {
      throw new RuntimeException();
    }
  }
}
