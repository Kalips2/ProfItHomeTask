package home1.figures;


public class Sphere extends Shape {

    private double radius;

    @Override
    public double calculateVolume() {
        return 4 * Math.PI * radius * radius * radius / 3;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere: " + radius;
    }
}
