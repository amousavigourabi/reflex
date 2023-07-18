package me.atour.reflex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CreatorTest {

  @Test
  void instantiateInteger() throws InstantiationException {
    Creator creator = new Creator();
    int i = creator.instantiate(Integer.class);
    assertThat(i).isEqualTo(0);
  }

  @Test
  void instantiateDouble() throws InstantiationException {
    Creator creator = new Creator();
    double i = creator.instantiate(Double.class);
    assertThat(i).isEqualTo(0.0);
  }

  @Test
  void instantiateChar() throws InstantiationException {
    Creator creator = new Creator();
    char i = creator.instantiate(Character.class);
    assertThat(i).isEqualTo('\u0000');
  }
}
