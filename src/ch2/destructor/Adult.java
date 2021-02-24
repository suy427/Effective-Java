package ch2.destructor;


public class Adult {
    public static void main(String[] args) {
        try(Room myRoom = new Room(7)) {
            System.out.println("hello!");
        }
        // cleaner나 finalize를 쓰지말고 try-with-resource 를 쓰라는데
        // 얘는 결국 AutoClosable의 close()를 호출하는거고, 이 메소드는 결국 cleaner나 finalize를
        // 사용하는걸로 보이는데...?
    }

    @Override protected void finalize() {
        // finalize method는 Object 클래스에 있지만 접근제어자가 protected라서 밖에서 안보인다.
        // 하지만 override는 할 수 있다.
    }
}
