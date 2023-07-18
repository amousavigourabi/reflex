package me.atour.reflex;

import java.lang.reflect.Field;

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
   * Gets the offset of a field with respect to th
   *
   * @see sun.misc.Unsafe#objectFieldOffset(Field)
   */
  public long objectFieldOffset(Field field) {
    return unsafe.objectFieldOffset(field);
  }

  /**
   * Sets an instance field.
   *
   * @see sun.misc.Unsafe#putObject(Object, long, Object)
   */
  public void putObject(Object instance, long offset, Object value) {
    unsafe.putObject(instance, offset, value);
  }
}
