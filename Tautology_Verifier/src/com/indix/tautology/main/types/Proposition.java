package com.indix.tautology.main.types;

import java.util.List;

public class Proposition {
    private List<Character> proposition;

    public Proposition(List<Character> proposition) {
        this.proposition = proposition;
    }

    public List<Character> getProposition() {
        return this.proposition;
    }
}
