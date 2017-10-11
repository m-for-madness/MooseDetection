package algorithms;

import compare.CompareByX;
import compare.CompareByY;
import utilClasses.Border;
import utilClasses.Direct;
import utilClasses.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * 25.09.2017.
 */
// i realised flood fill algorithm for detection animal
// if we have white color inside contour of the figure - it is animal otherwise bush
//it is realized in recursive/iterative algorithms
//function findContour is using 'bug' find algorithm for searching the contour of the figure

public class OutlineFinder {
    public boolean isAnimal = false;

    public List<Border> findContour(int[][] imageArray, List<Border> borders) {
        List<Pixel> pixels = new ArrayList<>();
        int cX;
        int cY;
        Direct direction;
        for (int j = 0; j < imageArray[0].length; j++) {
            for (int i = 0; i < imageArray.length; i++) {
                if (imageArray[i][j] == Color.BLACK.getRGB()) {
                    pixels.add(new Pixel(i, j));
                    cX = i;
                    cY = j - 1;//turn left, direction north
                    direction = Direct.NORTH;
                    pixels.add(new Pixel(cX, cY));
                    while ((cX != i) || (cY != j)) {
                        switch (direction) {
                            case NORTH: {
                                if (imageArray[cX][cY] == Color.BLACK.getRGB()) {
                                    pixels.add(new Pixel(cX, cY));
                                    direction = Direct.WEST;
                                    cX--;
                                } else {
                                    direction = Direct.EAST;
                                    cX++;
                                }
                            }
                            break;

                            case EAST: {
                                if (imageArray[cX][cY] == Color.BLACK.getRGB()) {
                                    pixels.add(new Pixel(cX, cY));
                                    direction = Direct.NORTH;
                                    cY--;
                                } else {
                                    direction = Direct.SOUTH;
                                    cY++;
                                }
                            }
                            break;


                            case SOUTH: {
                                if (imageArray[cX][cY] == Color.BLACK.getRGB()) {
                                    pixels.add(new Pixel(cX, cY));
                                    direction = Direct.EAST;
                                    cX++;
                                } else {
                                    direction = Direct.WEST;
                                    cX--;
                                }
                            }
                            break;

                            case WEST: {
                                if (imageArray[cX][cY] == Color.BLACK.getRGB()) {
                                    pixels.add(new Pixel(cX, cY));
                                    direction = Direct.SOUTH;
                                    cY++;
                                } else {
                                    direction = Direct.NORTH;
                                    cY--;
                                }
                            }
                        }
                    }
                    for (int k = 0; k < pixels.size(); k++) {
                        imageArray[pixels.get(k).x][pixels.get(k).y] = Color.RED.getRGB();
                    }
                    drawBorder(pixels, imageArray, borders);
                    pixels.clear();
                }
            }

        }
        return borders;
    }

    public void drawBorder(List<Pixel> pixels, int[][] arrayOfImage, List<Border> listOfBorders) {
        int maxX, maxY, minX, minY;
        try {
            //recursiveFloodFill(arrayOfImage,pixels.get(0).x+2, pixels.get(0).y+2,Color.BLACK.getRGB());
            iterativeFloodFill(arrayOfImage, new Pixel(pixels.get(0).x + 2, pixels.get(0).y + 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isAnimal) {
            maxX = Collections.max(pixels, new CompareByX()).x;
            minX = Collections.min(pixels, new CompareByX()).x;
            maxY = Collections.max(pixels, new CompareByY()).y;
            minY = Collections.min(pixels, new CompareByY()).y;
            listOfBorders.add(new Border(minY, maxY, minX, maxX));
            isAnimal = false;
        }
    }
    //
    //it is flood fill algorithm, but it is working only with small pictures
    public void recursiveFloodFill(int[][] arrayOfImage, int startingX, int startingY, int oldColor) throws Exception {
        if (arrayOfImage[startingX][startingY] == oldColor && arrayOfImage[startingX][startingY] != Color.RED.getRGB()) {
            arrayOfImage[startingX][startingY] = Color.GREEN.getRGB();

            recursiveFloodFill(arrayOfImage, startingX + 1, startingY, Color.BLACK.getRGB());
            recursiveFloodFill(arrayOfImage, startingX - 1, startingY, Color.BLACK.getRGB());
            recursiveFloodFill(arrayOfImage, startingX, startingY + 1, Color.BLACK.getRGB());
            recursiveFloodFill(arrayOfImage, startingX, startingY - 1, Color.BLACK.getRGB());


        } else if (arrayOfImage[startingX][startingY] == Color.WHITE.getRGB() && isAnimal == false) {
            isAnimal = true;
            arrayOfImage[startingX][startingY] = Color.GREEN.getRGB();
        } else if (arrayOfImage[startingX][startingY] == Color.WHITE.getRGB() && isAnimal) {
            arrayOfImage[startingX][startingY] = Color.GREEN.getRGB();
        }

    }
    //in iterative flood fill we stop StackOverFlowException

    private void iterativeFloodFill(int[][] arrayOfImage, Pixel pixel) {
        Queue<Pixel> points = new LinkedList<>();
        points.add(pixel);

        while (!points.isEmpty()) {
            Pixel currentPoint = points.remove();
            int x = currentPoint.x;
            int y = currentPoint.y;

            int current = arrayOfImage[x][y];
            if ((current == Color.BLACK.getRGB())) {
                arrayOfImage[x][y] = Color.GREEN.getRGB();
                if (x < arrayOfImage.length - 1)
                    points.add(new Pixel(x + 1, y));
                if (x > 0)
                    points.add(new Pixel(x - 1, y));
                if (y < arrayOfImage[x].length)
                    points.add(new Pixel(x, y + 1));
                if (y > 0)
                    points.add(new Pixel(x, y - 1));
            } else if ((current == Color.WHITE.getRGB()) && isAnimal == false) {
                arrayOfImage[x][y] = Color.GREEN.getRGB();
                if (x < arrayOfImage.length - 1)
                    points.add(new Pixel(x + 1, y));
                if (x > 0)
                    points.add(new Pixel(x - 1, y));
                if (y < arrayOfImage[x].length)
                    points.add(new Pixel(x, y + 1));
                if (y > 0)
                    points.add(new Pixel(x, y - 1));
                isAnimal = true;
            }
        }

    }
}
