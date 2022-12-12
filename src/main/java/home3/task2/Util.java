package home3.task2;

import home3.task2.exceptions.IncorrectTypeOfValue;
import home3.task2.exceptions.IncorrrectPropertyName;
import home3.task2.model.Property;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.stream.Stream;

public class Util {

  public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) {
    try {
      T newInstance = cls.getDeclaredConstructor().newInstance();

      var fields = Stream.of(cls.getDeclaredFields()).toList();

      Properties properties = new Properties();
      properties.load(new FileReader(propertiesPath.toString()));

      fields.forEach(field ->
          setField(cls, newInstance, field, field.getName(), "dd.MM.yyyy HH:mm", properties));

      return newInstance;
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
             InstantiationException | IOException e) {
      throw new RuntimeException(e);
    }

  }

  private static <T> void setField(Class<T> cls, T newInstance, Field field, String nameOfField,
                                   String format, Properties properties) {
    //Check: if annotation exists, change the nameOfField and format how indicated in annotation.
    if (field.isAnnotationPresent(Property.class)) {
      Property annotation = field.getDeclaredAnnotation(Property.class);
      format = annotation.format();
      nameOfField = annotation.name().equals("") ? nameOfField : annotation.name();
    }
    //Check corresponding key in properties. If doesn't exist throw new Exception.
    if (properties.containsKey(nameOfField)) {
      try {
        //Get the setter for this Field.
        String realName = field.getName();
        Method setProperty = cls.getDeclaredMethod(
            "set" + realName.substring(0, 1).toUpperCase() + realName.substring(1),
            field.getType()
        );
        var value = properties.getProperty(nameOfField);
        var type = field.getType().getSimpleName();
        //Check all manner of types like int, Integer, String.
        switch (type) {
          case ("int"), ("Integer") -> {
            try {
              setProperty.invoke(newInstance, Integer.parseInt(value));
            } catch (NumberFormatException e) {
              throw new NumberFormatException(
                  "Incorrect format of number! Can't parse " + value + " to int/Integer");
            }
          }
          case ("Instant") -> {
            try {
              LocalDateTime date = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(format));
              setProperty.invoke(newInstance, date.toInstant(ZoneOffset.UTC));
            } catch (RuntimeException e) {
              throw new IllegalArgumentException(
                  "Incorrect pattern or value of date! Can't parse " + value +
                      " to format like " + format);
            }
          }
          case ("String") -> setProperty.invoke(newInstance, value);
          default -> throw new IncorrectTypeOfValue("Type of " + type + " doesn't supported");
        }
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    } else {
      throw new IncorrrectPropertyName("Not find a property with " + nameOfField + " name");
    }
  }

}
