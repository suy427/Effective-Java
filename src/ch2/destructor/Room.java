package ch2.destructor;

import sun.misc.Cleaner;

public class Room implements AutoCloseable {
    // private static final Cleaner cleaner = Cleaner.create();
    // 책에는 위의 방식으로 Cleaner를 생성할 수 있으나 지금은 deprecate 된거같다(없다)

    // static inner 클래스는 외부 클래스의 참조를 갖지 않는다.
    // 바꿔말하면 non-static inner 클래스는 자동으로 외부 클래스의 참조를 가진단다.
    private static class State implements Runnable {
        // 이 객체를 작동하는 thread는 Room을 청소하는 thread가 된다(아래에 그렇게 구현해놨다)
        // 그렇기 때문에 이 객체는 '절대' Room객체를 참조해서는 안된다(GC는 참조가 있는 객체를 수거하지 않는다)
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override public void run() {
            System.out.println("cleaning room");
            numJunkPiles = 0;
        }
    }

    private final State state;
    private final Cleaner cleaner;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleaner = Cleaner.create(this, state);
        // create의 첫번째 파라미터로 들어간 객체를 두번째 파라미터로 들어간 thread가 정리를 하게하는 cleaner객체
        // clean이란것도 결국엔 GC를 이용한 수거인것 같다. 왜냐면 이 객체를 참조하는 객체가 있으면 청소가 안된다고 하니까...
    }

    @Override public void close() {
        cleaner.clean();
    }
}