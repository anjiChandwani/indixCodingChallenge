package com.indix.tautology.main.types;

public class Variable {
    private Character ch;
    private boolean negated;

    public Character getVariable() {
        return this.ch;
    }

    public void setNegatedVariable(Character ch) {
        this.ch = ch;
        this.negated = true;
    }

    public void setVariable(Character ch) {
        this.ch = ch;
        this.negated = false;
    }

    public boolean isNegated() {
        return this.negated;
    }
}
