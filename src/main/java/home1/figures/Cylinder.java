package home1.figures;

public class Cylinder extends Shape {

    private double radius;
    private double height;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
        calculateVolume();
    }

    @Override
    public void calculateVolume() {
        setVolume(Math.PI * radius * radius * height);
    }

    @Override
    public String toString() {
        return String.valueOf("Cylinder:" + radius + " "  + height +  " " +  getVolume());
    }
}
