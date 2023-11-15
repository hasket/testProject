class Pair<F, S> {
    public F first;
    public S second;

    @Override
    public String toString() {
        return "Pair{" + first + " " + second + "}";
    }
}

// Создайте дженерик-класс Triple, наследник класса Pair, содержащий одну переменную обобщенного типа с именем triple.

class Triple<F,S,T> extends Pair<F,S> {
    T triple;

    @Override
    public String toString() {
        return "Triple{" + first + " " + second + " " + triple + "}";
    }
}