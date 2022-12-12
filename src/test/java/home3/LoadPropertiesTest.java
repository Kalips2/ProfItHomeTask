package home3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import home3.model.EmptyTest;
import home3.model.IncorrectTypeTest;
import home3.model.NotEmptyTest;
import home3.task2.Util;
import home3.task2.exceptions.IncorrectTypeOfValue;
import home3.task2.exceptions.IncorrrectPropertyName;
import java.nio.file.Path;
import org.junit.Test;

public class LoadPropertiesTest {

  @Test
  public void correctWithoutAnyValuesInAnnotation() {
    EmptyTest myPropertyTest = new EmptyTest();
    String path_to = "src/test/resources/home3/annotation/empty.properties";
    EmptyTest result = Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to));
    assertEquals(result.getFirstName(), "nikita");
    assertEquals(result.getLastName(), "ivanov");
    assertEquals(result.getBirthDate().toString(), "2004-06-14T10:30:00Z");
    assertEquals(result.getFavoriteDate().toString(), "2020-02-25T14:43:00Z");
    assertEquals(result.getSalary(), Integer.valueOf(1000));
    assertEquals(result.getAge(), 18);
    assertEquals(result.getNumber(), 14);
    assertEquals(result.getChildren(), Integer.valueOf(0));
  }

  @Test
  public void correctWithValuesInAnnotation() {
    NotEmptyTest myPropertyTest = new NotEmptyTest();
    String path_to = "src/test/resources/home3/annotation/NotEmpty.properties";
    NotEmptyTest result = Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to));
    assertEquals(result.getFirstName(), "nikita");
    assertEquals(result.getLastName(), "ivanov");
    assertEquals(result.getBirthDate().toString(), "2004-06-14T10:30:00Z");
    assertEquals(result.getFavoriteDate().toString(), "2020-02-25T14:43:00Z");
    assertEquals(result.getSalary(), Integer.valueOf(1000));
    assertEquals(result.getAge(), 18);
    assertEquals(result.getNumber(), 14);
    assertEquals(result.getChildren(), Integer.valueOf(0));
  }

  @Test
  public void throwExceptionWhenIncorrectIntValue() {
    NotEmptyTest myPropertyTest = new NotEmptyTest();
    String path_to = "src/test/resources/home3/annotation/incorInt.properties";
    assertThrows(NumberFormatException.class,
        () -> Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to)));
  }
  @Test
  public void throwExceptionWhenIncorrectIntegerValue() {
    NotEmptyTest myPropertyTest = new NotEmptyTest();
    String path_to = "src/test/resources/home3/annotation/incorInteger.properties";
    assertThrows(NumberFormatException.class,
        () -> Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to)));
  }
  @Test
  public void throwExceptionWhenIncorrectInstantFormat() {
    NotEmptyTest myPropertyTest = new NotEmptyTest();
    String path_to = "src/test/resources/home3/annotation/incorInstant.properties";
    assertThrows(IllegalArgumentException.class,
        () -> Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to)));
  }
  @Test
  public void throwExceptionWhenIncorrectNameOfField() {
    EmptyTest myPropertyTest = new EmptyTest();
    String path_to = "src/test/resources/home3/annotation/notEmpty.properties";
    assertThrows(IncorrrectPropertyName.class,
        () -> Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to)));
  }
  @Test
  public void throwExceptionWhenIncorrectTypeOfField() {
    IncorrectTypeTest myPropertyTest = new IncorrectTypeTest();
    String path_to = "src/test/resources/home3/annotation/notEmpty.properties";
    assertThrows(IncorrectTypeOfValue.class,
        () -> Util.loadFromProperties(myPropertyTest.getClass(), Path.of(path_to)));
  }
}
