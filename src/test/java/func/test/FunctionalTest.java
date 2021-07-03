package func.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalTest {


    // Consumer Test
    @Test
    @Disabled("A Consumer is a functional interface that accepts a single input and returns no output.")
    public void whenNamesPresentConsumeAll() {
        Consumer<String> printConsumer = t -> System.out.println(t);
        Stream<String> cities = Stream.of("Sydney", "Dhaka", "New York", "London");
        cities.forEach(printConsumer);
    }

    @Test
    public void whenNamesPresentUseBothConsumer(){
        List<String> cities = Arrays.asList("Sydney", "Dhaka", "New York", "London");

        Consumer<List<String>> upperCase = list -> {
            for(int i =0; i<list.size(); i++) {
                list.set(i, list.get(i).toUpperCase());
            }
        };

        Consumer<List<String>> print = list -> list.stream().forEach(System.out::println);

        print.andThen(upperCase).andThen(print).accept(cities);
    }

    //Supplier test

    @Test
    public void supplier(){
        Supplier<Double> doubleSupplier1 = () -> Math.random();
        DoubleSupplier doubleSupplier2 = Math::random;

        System.out.println(doubleSupplier1.get());
        System.out.println(doubleSupplier2.getAsDouble());
    }

    @Test
    public void supplierWithOptional(){
        Supplier<Double> doubleSupplier = () -> Math.random();
        Optional<Double> optionalDouble = Optional.empty();
        System.out.println(optionalDouble.orElseGet(doubleSupplier));
    }

    // Predicate
    @Test
    public void testPredicate(){
        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie");
        Predicate<String> nameStartsWithS = str -> str.startsWith("S");
        names.stream().filter(nameStartsWithS).forEach(System.out::println);
    }

    @Test
    public void testPredicateAndComposition(){
        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie");
        Predicate<String> startPredicate = str -> str.startsWith("S");
        Predicate<String> lengthPredicate = str -> str.length() >= 5;
        names.stream().filter(startPredicate.and(lengthPredicate)).forEach(System.out::println);
    }

    //Function
    @Test
    public void testFunctions() {
        List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
        Function<String, Integer> lenFunc = str -> str.length();
        names.stream().map(lenFunc).collect(Collectors.toList());
    }
}
