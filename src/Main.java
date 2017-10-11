import algorithms.OutlineFinder;
import compare.CompareByX;
import compare.CompareByY;
import utilClasses.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 25.09.2017.
 */
public class Main {

    public static final String INPUT_IMAGE_URL = "NewPixels\\resources\\input.png";
    public static final String OUTPUT_IMAGE_URL = "NewPixels\\resources\\output.png";
    // main logic is realised in algorithms.OutlineFinder
    public static void main(String[] args) throws IOException {
        UtilImageClass utilImageClass = new UtilImageClass();
        int[][] imageArray = utilImageClass.writePNGIntoArray(INPUT_IMAGE_URL);
        List<Border> borders = new ArrayList<>();
        OutlineFinder finder = new OutlineFinder();
        borders.addAll(finder.findContour(imageArray,borders));
        imageArray = utilImageClass.writePNGIntoArray(INPUT_IMAGE_URL);
        for (int i = 0; i < borders.size(); i++) {
            borders.get(i).draw(imageArray);
        }
        utilImageClass.savePNG(utilImageClass.map(imageArray), OUTPUT_IMAGE_URL);
    }





}
