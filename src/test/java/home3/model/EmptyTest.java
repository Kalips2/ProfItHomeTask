package home3.model;

import home3.task2.model.Property;
import java.time.Instant;

public class EmptyTest {
  private String firstName;
  @Property
  private String lastName;
  private int age;
  @Property
  private int number;
  private Integer children;
  @Property
  private Integer salary;
  private Instant birthDate;
  @Property
  private Instant favoriteDate;

  public EmptyTest() {
  }

  @Override
  public String toString() {
    return "Person[" + " " + firstName + ", " + lastName + ", " + age + ", " + number +
        ", " + children + ", " + salary + ", " + birthDate + ", " + favoriteDate + ']';
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Integer getChildren() {
    return children;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  public Instant getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Instant birthDate) {
    this.birthDate = birthDate;
  }

  public Instant getFavoriteDate() {
    return favoriteDate;
  }

  public void setFavoriteDate(Instant favoriteDate) {
    this.favoriteDate = favoriteDate;
  }
}
