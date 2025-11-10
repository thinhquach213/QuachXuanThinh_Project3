package K23cnt.qxt.day2.K23cnt.qxt.day2.dependency_injection;
// dependency injection
interface Shape{
    void draw();
}
class CircleShape implements Shape{
    @Override
    public void draw() {
        System.out.println("CircleShape draw");
    }
}
class RectangleShape implements Shape{
    @Override
    public void draw() {
        System.out.println("RectangleShape draw");
    }
}
public class DrawShape {
    private Shape shape;
    public DrawShape(Shape shape) {
        this.shape = shape;
    }
    public void Draw(){
        shape.draw();
    }
    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape(new CircleShape());
        drawShape.Draw();
        drawShape = new DrawShape(new RectangleShape());
        drawShape.Draw();
    }
public class NonIoC {
    public static void main(String[] args) {
        Client client = new Client();
        client.doSomething();
    }}}

