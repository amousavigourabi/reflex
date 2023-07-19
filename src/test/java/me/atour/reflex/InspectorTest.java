package me.atour.reflex;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InspectorTest {

  private static Inspector inspector;

  @BeforeAll
  static void setUp() {
    inspector = new Inspector();
  }

  @EqualsAndHashCode
  @RequiredArgsConstructor
  private static class InspectorPojo {
    private final int integer;
  }

  @Test
  void inspectInteger() throws Throwable {
    InspectorPojo i = new InspectorPojo(2);
    Field valueField = InspectorPojo.class.getDeclaredField("integer");
    int actual = inspector.getField(i, valueField);
    assertThat(actual).isEqualTo(2);
  }
}
