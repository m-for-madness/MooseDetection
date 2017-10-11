package utilClasses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * 25.09.2017.
 */
//utility class
    // convert image to 2d array
    // save 2d array as picture
    // convert picture into B/W image

public class UtilImageClass {
    public int[][] writePNGIntoArray(String PNGFileName) throws IOException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(PNGFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] array2D = new int[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
        {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
            {
                int color = image.getRGB(xPixel, yPixel);
                if (color== Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = color;// black
                } else {
                    array2D[xPixel][yPixel] = color; // white
                }
            }
        }
        makeWBImage(array2D,PNGFileName);
        return array2D;
    }

    public BufferedImage map( int [][] array ){
        BufferedImage res = new BufferedImage( array.length, array[0].length, BufferedImage.TYPE_INT_RGB );
        for (int x = 0; x < array.length; x++){
            for (int y = 0; y < array[x].length; y++){
                res.setRGB(x, y, array[x][y]);
            }
        }
        return res;
    }
    public void makeWBImage(int[][] array, String INPUT_IMAGE_URL){
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if(array[i][j]!=Color.BLACK.getRGB() && array[i][j]!=Color.WHITE.getRGB())
                    array[i][j] = Color.WHITE.getRGB();

            }
        }
        BufferedImage bi = this.map(array);
        this.savePNG(bi, INPUT_IMAGE_URL);

    }
    public void savePNG( final BufferedImage bi, final String path ){
        try {
            RenderedImage rendImage = bi;
            ImageIO.write(rendImage, "PNG", new File(path));
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
