package com.codigo.aplios.toolbox.core.metrics;

import java.util.Optional;

public class Test {

    public static void main(String[] args) {

        String answer2 = null;
        System.out.println(Test.getParentName());
        System.out.println(Optional.ofNullable(answer2));

        String hh = Test.getParentName()
                .orElse("Brak wartości");
        System.out.println(hh);
    }

    public static Optional<String> getParentName() {

        return Optional.empty();
    }

}
