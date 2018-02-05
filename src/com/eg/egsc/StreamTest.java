package com.eg.egsc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

  private static List<PersonModel> list = null;

  static {
    PersonModel wu = new PersonModel("wu qi", 18, '男');
    PersonModel zhang = new PersonModel("zhang san", 19, '男');
    PersonModel wang = new PersonModel("wang si", 20, '女');
    PersonModel zhao = new PersonModel("zhao wu", 20, '男');
    PersonModel chen = new PersonModel("chen liu", 21, '男');
    list = Arrays.asList(wu, zhang, wang, zhao, chen);
  }

  public static List<PersonModel> getData() {
    return list;
  }

  /**
   * 
   * TODO void
   */
  @Test
  public void test() {
    List<PersonModel> data = StreamTest.getData().stream().filter(person -> '男' == person.getSex())
        .collect(Collectors.toList());
    data.forEach(person -> System.out.println(person));
    List<Boolean> list2 = StreamTest.getData().stream().map(person -> '男' == person.getSex())
        .collect(Collectors.toList());
    List<String> list3 =
        StreamTest.getData().stream().map(PersonModel::getName).collect(Collectors.toList());
    List<Stream<String>> list4 = StreamTest.getData().stream()
        .map(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());
    List<String> list5 = StreamTest.getData().stream()
        .flatMap(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());
    List<String> list6 = StreamTest.getData().stream().map(person -> person.getName().split(" "))
        .flatMap(str -> Arrays.asList(str).stream()).collect(Collectors.toList());
    list2.forEach(action -> System.out.println(action));
    System.out.println("222222222222222222222222222222");
    list3.forEach(action -> System.out.println(action));
    System.out.println("333333333333333333333333333333");
    list4.forEach(action -> {
      List<String> collect = action.collect(Collectors.toList());
      collect.forEach(str -> System.out.println(str));

    });
    System.out.println("4444444444444444444444444444444");
    list5.forEach(action -> System.out.println(action));
    System.out.println("5555555555555555555555555555555");
    list6.forEach(action -> System.out.println(action));
    System.out.println("6666666666666666666666666666666");

    Integer reduce = Stream.of(1, 2, 3, 4).reduce(10, (count, item) -> {
      System.out.println("count:" + count);
      System.out.println("item:" + item);
      return count + item;
    });
    System.out.println(reduce);
    Integer reduce2 = Stream.of(1, 2, 3, 4).reduce(0, (x, y) -> x + y);
    System.out.println(reduce2);
    String[] strs = {"1", "2", "3", "4"};
    String reduce3 = Stream.of(strs).reduce("", (x, y) -> x + ":" + y);
    System.out.println(reduce3);

    Set<String> set =
        StreamTest.getData().stream().map(PersonModel::getName).collect(Collectors.toSet());
    Map<String, Integer> collect = StreamTest.getData().stream()
        .collect(Collectors.toMap(PersonModel::getName, PersonModel::getAge));
    Map<String, String> collect2 =
        StreamTest.getData().stream().collect(Collectors.toMap(Per -> Per.getName(), value -> {
          return value + "1";
        }));
    Set<Entry<String, Integer>> entrySet = collect.entrySet();
    Set<Entry<String, String>> entrySet2 = collect2.entrySet();
    entrySet2.forEach(arg0 -> {
      System.out.println(arg0.getKey() + ":" + arg0.getValue());
    });
    entrySet.forEach(arg0 -> {
      System.out.println(arg0.getKey() + ":" + arg0.getValue());
    });

  }

}


class PersonModel {
  private String name;
  private Integer age;
  private Character sex;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Character getSex() {
    return sex;
  }

  public void setSex(Character sex) {
    this.sex = sex;
  }

  public PersonModel(String name, Integer age, Character sex) {
    super();
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  @Override
  public String toString() {
    return "PersonModel [name=" + name + ", age=" + age + ", sex=" + sex + "]";
  }

}
