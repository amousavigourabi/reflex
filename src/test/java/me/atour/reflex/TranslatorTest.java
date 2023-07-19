package me.atour.reflex;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TranslatorTest {

  private static Translator translator;

  @BeforeAll
  static void setUp() {
    translator = new Translator();
  }

  @ParameterizedTest
  @MethodSource("primitivesAndWrappers")
  void wrapPrimitives(Class<?> primitive, Class<?> wrapper) {
    Class<?> actual = translator.toWrapper(primitive);
    assertThat(actual).isEqualTo(wrapper);
  }

  @ParameterizedTest
  @MethodSource("primitivesAndWrappers")
  void unwrapWrappers(Class<?> primitive, Class<?> wrapper) {
    Class<?> actual = translator.toPrimitive(wrapper);
    assertThat(actual).isEqualTo(primitive);
  }

  @NonNull private static Stream<Arguments> primitivesAndWrappers() {
    return Stream.of(
        Arguments.of(int.class, Integer.class),
        Arguments.of(long.class, Long.class),
        Arguments.of(char.class, Character.class),
        Arguments.of(byte.class, Byte.class),
        Arguments.of(short.class, Short.class),
        Arguments.of(double.class, Double.class),
        Arguments.of(float.class, Float.class),
        Arguments.of(boolean.class, Boolean.class));
  }

  @Test
  void wrapRegularClasses() {
    Class<?> actual = translator.toWrapper(Stream.class);
    assertThat(actual).isEqualTo(Stream.class);
  }

  @Test
  void unwrapRegularClasses() {
    Class<?> actual = translator.toPrimitive(Stream.class);
    assertThat(actual).isEqualTo(Stream.class);
  }
}
