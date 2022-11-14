package home1.figures;

public abstract class Shape implements Comparable<Shape> {

    public abstract double calculateVolume();

    public int compareTo(Shape shape) {
        return Double.compare(this.calculateVolume(), shape.calculateVolume());
    }

}
