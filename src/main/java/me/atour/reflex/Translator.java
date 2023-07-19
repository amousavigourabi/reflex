package me.atour.reflex;

/**
 * Translates classes.
 */
public class Translator {

  /**
   * Converts a {@link Class} to its corresponding wrapper type.
   * If the given {@link Class} is not a primitive, it is returned.
   *
   * @param primitive the {@link Class} to wrap
   * @return the wrapped {@link Class}
   */
  public Class<?> toWrapper(Class<?> primitive) {
    if (int.class.equals(primitive)) {
      return Integer.class;
    } else if (double.class.equals(primitive)) {
      return Double.class;
    } else if (boolean.class.equals(primitive)) {
      return Boolean.class;
    } else if (char.class.equals(primitive)) {
      return Character.class;
    } else if (long.class.equals(primitive)) {
      return Long.class;
    } else if (float.class.equals(primitive)) {
      return Float.class;
    } else if (short.class.equals(primitive)) {
      return Short.class;
    } else if (byte.class.equals(primitive)) {
      return Byte.class;
    } else {
      return primitive;
    }
  }

  /**
   * Converts a {@link Class} to its corresponding primitive type.
   * If the given {@link Class} is not a wrapper, it is returned.
   *
   * @param wrapper the {@link Class} to unwrap
   * @return the unwrapped {@link Class}
   */
  public Class<?> toPrimitive(Class<?> wrapper) {
    if (Integer.class.equals(wrapper)) {
      return int.class;
    } else if (Double.class.equals(wrapper)) {
      return double.class;
    } else if (Boolean.class.equals(wrapper)) {
      return boolean.class;
    } else if (Character.class.equals(wrapper)) {
      return char.class;
    } else if (Long.class.equals(wrapper)) {
      return long.class;
    } else if (Float.class.equals(wrapper)) {
      return float.class;
    } else if (Short.class.equals(wrapper)) {
      return short.class;
    } else if (Byte.class.equals(wrapper)) {
      return byte.class;
    } else {
      return wrapper;
    }
  }
}
