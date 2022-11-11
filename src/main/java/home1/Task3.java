package home1;

import home1.figures.Shape;
import org.w3c.dom.ls.LSOutput;

import java.util.Collections;
import java.util.List;

/**
 * Class with solution for Third Task.
 * */
public class Task3 {

    /**
     *  Sort the List of Shapes by ascending order
     *
     *  @param shapes - List of Shapes
     * */

    public void sortingByVolumeAsc(List<Shape> shapes) {
        Collections.sort(shapes);
    }

    /**
     *  Sort the List of Shapes by descending order
     *
     *  @param shapes - List of Shapes
     * */
    public void sortingByVolumeDes(List<Shape> shapes) {
        Collections.sort(shapes);
        Collections.reverse(shapes);
    }

}

