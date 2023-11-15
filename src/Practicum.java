/* Задача
Время применить полученные знания к Pair!
 Ваши задачи:
- Сделать переменные first и second приватными.
- Создать конструктор класса Pair, присваивающий значения переменным класса.
- Реализовать статический дженерик-метод of(), принимающий в качестве аргумента
  два обобщённых параметра и возвращающий экземпляр Pair с этими параметрами.
 */

class Pair<F, S> {
    private F first;
    private S second;

    Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" + first + " " + second + "}";
    }

    public static <T, E> Pair of(T type, E element){
        // Pair<T,E> pairs = new Pair<>(type, element); // <- сделал экземпляр класса
        // return pairs;
        // Второй способ решения задачи
        return new Pair(type, element);
    }
}