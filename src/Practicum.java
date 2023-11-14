import java.util.ArrayList;

class TrashCan<T> {
    ArrayList<T> trash = new ArrayList<>();;
    final int canCapacity = 20;

    public void putTrash(T t) {
        if (canIsFull()) {
            System.out.println("Корзина заполнена!");
        } else {
            trash.add(t);
        }
    }

    public boolean canIsFull() {
        return trash.size() >= canCapacity ;
    }

    public void emptyTrash() {
        trash.clear();
        System.out.println("Мусор уничтожен");
    }
}