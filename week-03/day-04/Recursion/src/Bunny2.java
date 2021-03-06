public class Bunny2 {
  // We have bunnies standing in a line, numbered 1, 2, ... The odd bunnies
  // (1, 3, ..) have the normal 2 ears. The even bunnies (2, 4, ..) we'll say
  // have 3 ears, because they each have a raised foot. Recursively return the
  // number of "ears" in the bunny line 1, 2, ... n (without loops or multiplication).
  public static void main(String[] args) {
    int ears = bunnyEars(17);
    System.out.println(ears);
  }

  private static int bunnyEars(int bunnies) {
    int ears = (bunnies % 2 == 0)? 3 : 2;
    if (bunnies > 1)
    {
      return ears + bunnyEars(bunnies - 1);
    }
    else
    {
      return ears;
    }
  }
}
