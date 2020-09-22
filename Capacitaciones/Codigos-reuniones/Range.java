public class Range {
  public static double clip(double number, double min, double max){
    if (number < min) {
      return min;
    } else if (number > max) {
      return max;
    } else {
      return number;
    }
  }
}
