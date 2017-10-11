package compare;

import utilClasses.Pixel;

import java.util.Comparator;

/**
 * 25.09.2017.
 */
public class CompareByX implements Comparator<Pixel>{

    @Override
    public int compare(Pixel o1, Pixel o2) {
        return o1.x-o2.x;
    }
}
