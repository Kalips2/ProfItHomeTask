package home1.figures;

public abstract class Shape implements Comparable<Shape> {
    private double volume;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public abstract void calculateVolume();

    public int compareTo(Shape shape) {
        return Double.compare(getVolume(), shape.getVolume());
    }

}
