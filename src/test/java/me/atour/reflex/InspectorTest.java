package me.atour.reflex;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InspectorTest {

  private static Inspector inspector;

  @BeforeAll
  static void setUp() {
    inspector = new Inspector();
  }

  @RequiredArgsConstructor
  private static class InspectorPojo {
    private final int integer;
  }

  @Test
  void inspectInteger() throws ReflectiveOperationException {
    InspectorPojo pojo = new InspectorPojo(2);
    Field valueField = InspectorPojo.class.getDeclaredField("integer");
    int actual = inspector.getField(pojo, valueField);
    assertThat(actual).isEqualTo(2);
  }

  private static class StaticInspectorPojo {

    private static int integer;

    public StaticInspectorPojo(int i) {
      integer = i;
    }
  }

  @Test
  void inspectStaticInteger() throws ReflectiveOperationException {
    new StaticInspectorPojo(37);
    Field staticField = StaticInspectorPojo.class.getDeclaredField("integer");
    int actual = inspector.getField(staticField);
    assertThat(actual).isEqualTo(37);
  }

  @Test
  void inspectStaticIntegerFromInstance() throws ReflectiveOperationException {
    StaticInspectorPojo pojo = new StaticInspectorPojo(-2);
    Field staticField = StaticInspectorPojo.class.getDeclaredField("integer");
    int actual = inspector.getField(pojo, staticField);
    assertThat(actual).isEqualTo(-2);
  }
}
