package ch2.destructor;

public class Teenager {
    public static void main(String[] args) {
        new Room(99); // 이 객체는 운 좋게 cleaner나 finalizer가 작동하면 수거된다. 보장은 안된다.
        System.out.println("whatever~");
    }
}
