package home1;

import home1.figures.Cube;
import home1.figures.Cylinder;
import home1.figures.Shape;
import home1.figures.Sphere;
import home1.Task3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ThirdTask {

    private static final Task3 task3 = new Task3();

    @ParameterizedTest
    @MethodSource("Cases")
    public void checkCorrectSortingByAsc(List<Shape> arguments) {
        List<Shape> result = new ArrayList<>(List.copyOf(arguments));
        Collections.shuffle(result);
        task3.sortingByVolumeAsc(result);
        assertEquals(arguments, result);
    }

    @ParameterizedTest
    @MethodSource("Cases")
    public void checkCorrectSortingByDes(List<Shape> arguments) {
        List<Shape> mutableArguments = new ArrayList<>(List.copyOf(arguments));
        List<Shape> result = new ArrayList<>(List.copyOf(arguments));
        Collections.shuffle(result);
        task3.sortingByVolumeDes(result);
        Collections.reverse(mutableArguments);
        assertEquals(mutableArguments, result);
    }


    public static Stream<Arguments> Cases() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Cube(2),
                                new Sphere(2),
                                new Cube(4),
                                new Cylinder(3, 4),
                                new Cylinder(10, 2.5),
                                new Cube(10),
                                new Sphere(10)
                        )),

                Arguments.of(
                        List.of(
                                new Sphere(3),
                                new Cylinder(4, 4),
                                new Cylinder(12, 2),
                                new Cube(14),
                                new Sphere(20),
                                new Cube(78)
                        )),


                Arguments.of(
                        List.of(
                                new Cube(5),
                                new Cylinder(5, 5),
                                new Sphere(5),
                                new Sphere(10),
                                new Cylinder(100, 100)
                        )),

                Arguments.of(
                        List.of(
                                new Sphere(1),
                                new Cube(3),
                                new Cube(5),
                                new Cylinder(3, 11),
                                new Cylinder(5, 13),
                                new Cube(12),
                                new Sphere(12),
                                new Cylinder(20, 99.5)
                        ))
        );
    }
}
