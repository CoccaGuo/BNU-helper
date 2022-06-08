package test;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JavaLearn {
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5,6,7).forEach(System.out::println);
        Consumer print = System.out::println;
        Supplier random = Math::random;
        Stream.generate(random).limit(10).forEach(print);
    }
}
