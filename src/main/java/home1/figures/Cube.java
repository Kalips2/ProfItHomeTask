package home1.figures;

public class Cube extends Shape {

    double side;

    public Cube(double side) {
        this.side = side;
        calculateVolume();
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculateVolume() {
        return side * side * side;
    }

    @Override
    public String toString() {
        return "Cube: " + side;
    }
}
