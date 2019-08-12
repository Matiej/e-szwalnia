package com.eszwalnia;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        Set<Long> longSet = new HashSet<>();
        longSet.add(1l);
        longSet.add(33l);
        longSet.add(2l);
        longSet.add(4l);

        Optional<Long> first = longSet.stream().max(Comparator.comparing(Long::longValue));
        System.out.println(first);
    }
}
