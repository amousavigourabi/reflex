package me.atour.reflex;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreatorTest {

  @Getter
  private static class SetClass {
    private Integer integer = 3;
    private double doubleValue = 0.0;
    private char character = 'z';
  }

  private static Creator creator;

  @BeforeAll
  public static void beforeAll() {
    creator = new Creator();
  }

  @Test
  void instantiateInteger() throws InstantiationException {
    int i = creator.instantiate(Integer.class);
    assertThat(i).isEqualTo(0);
  }

  @Test
  void instantiateDouble() throws InstantiationException {
    double d = creator.instantiate(Double.class);
    assertThat(d).isEqualTo(0.0);
  }

  @Test
  void instantiateCharacter() throws InstantiationException {
    char c = creator.instantiate(Character.class);
    assertThat(c).isEqualTo('\u0000');
  }

  @Test
  void setInteger() throws NoSuchFieldException {
    SetClass dummy = new SetClass();
    creator.setField(dummy, "integer", -1);
    creator.setField(dummy, "integer", 1);
    assertThat(dummy.getInteger()).isEqualTo(1);
    assertThat(dummy.getDoubleValue()).isEqualTo(0.0);
    assertThat(dummy.getCharacter()).isEqualTo('z');
  }

  @Test
  void setDouble() throws NoSuchFieldException {
    SetClass dummy = new SetClass();
    Field field = SetClass.class.getDeclaredField("doubleValue");
    creator.setField(dummy, field, -1.0);
    assertThat(dummy.getInteger()).isEqualTo(3);
    assertThat(dummy.getDoubleValue()).isEqualTo(-1.0);
    assertThat(dummy.getCharacter()).isEqualTo('z');
  }

  @Test
  void setCharacter() throws NoSuchFieldException {
    SetClass dummy = new SetClass();
    creator.setField(dummy, "character", 'b');
    assertThat(dummy.getInteger()).isEqualTo(3);
    assertThat(dummy.getDoubleValue()).isEqualTo(0.0);
    assertThat(dummy.getCharacter()).isEqualTo('b');
  }
}
