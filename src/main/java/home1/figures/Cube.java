package home1.figures;


import java.util.Objects;

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
    public void calculateVolume() {
        setVolume(side * side * side);
    }

    @Override
    public String toString() {
        return String.valueOf("Cube: "  + side + " " +  getVolume());
    }
}
