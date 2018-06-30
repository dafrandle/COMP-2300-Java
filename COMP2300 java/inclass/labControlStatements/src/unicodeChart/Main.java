package unicodeChart;

public class Main {
//Integer.toHexString(rawHexCode);
   public static void main(String[] args) {
      char uni;
      int rawHexCode = 32;
      String hexCode;

      System.out.printf("|");
      for (int z=0;z<5;z++){
         System.out.printf("| symbol -   Hex # |");
      }
      System.out.printf("|\n");

      for(int x=0;x<19;x++) {
         System.out.printf("|");
         for (int y=0;y<5;y++) {
            rawHexCode++;
            hexCode = Integer.toHexString(rawHexCode);
            uni = (char) rawHexCode;
            if (rawHexCode == 127){
               System.out.printf("|  ---   -   00%s  |", hexCode);
            } else {
               System.out.printf("|   %c    -   00%s  |", uni, hexCode);
            }
         }
         System.out.printf("|\n");
      }
   }
}
