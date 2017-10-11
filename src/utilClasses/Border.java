package utilClasses;

import java.awt.*;

/**
 * 25.09.2017.
 */
// border for our animal objects
public class Border {
    public int top;
    public int bottom;
    public int left;
    public int right;

    public Border(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    public void draw(int[][] arrayOfImage){
        for (int i = top; i <= bottom; i++) {
            arrayOfImage[left][i] = Color.BLUE.getRGB();
            arrayOfImage[right][i] = Color.BLUE.getRGB();
        }
        for (int j = left; j <= right; j++) {
            arrayOfImage[j][top] = Color.BLUE.getRGB();
            arrayOfImage[j][bottom] = Color.BLUE.getRGB();
        }
        //System.out.println((bottom-top)*(right-left));
    }
}
