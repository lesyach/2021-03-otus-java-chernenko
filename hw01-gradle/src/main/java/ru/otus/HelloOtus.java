package ru.otus;

//https://github.com/google/guava/wiki
import com.google.common.collect.*;

public class HelloOtus {
    public static void main(String... args) {
        ImmutableMap<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        ImmutableMap<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesDiffering());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());
    }
}