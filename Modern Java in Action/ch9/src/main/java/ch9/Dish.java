package ch9;

class Dish {
  private String name;
  private int calories;

  public enum CaloricLevel {DIET, NORMAL, FAT;}

  private Dish(String name, int calories) {
    this.name = name;
    this.calories = calories;
  }

  public static Dish make(String name, int calories) {
    return new Dish(name, calories);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }
}
