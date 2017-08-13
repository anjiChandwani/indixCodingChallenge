package com.indix.tautology.main.sevrices.impl;

import com.indix.tautology.main.sevrices.PropositionEvaluator;
import com.indix.tautology.main.sevrices.TautologyVerifier;
import com.indix.tautology.main.types.Proposition;
import com.indix.tautology.main.types.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Class to verify that a given proposition is a tautology or not
 */
public class TautologyVerifierImpl implements TautologyVerifier {
    private PropositionEvaluator propositionEvaluator;

    public TautologyVerifierImpl(PropositionEvaluator propositionEvaluator) {
        this.propositionEvaluator = propositionEvaluator;
    }

    @Override
    public boolean verify(Proposition p) {

        Stack<Character> stack = new Stack<Character>();

        for (char ch : p.getProposition()) {
            if (ch == ' ') {
                continue;
            }
            if (ch == ')') {
                stack.push(')');
                Proposition subProp = getSubProposition(stack);
                Variable subResult = propositionEvaluator.evaluate(subProp);

                if (subResult == null) {
                    stack.push('0');
                } else {
                    if (subResult.isNegated()) {
                        stack.push('!');
                    }
                    stack.push(subResult.getVariable());
                }
            } else
                stack.push(ch);
        }
        boolean result = false;
        if (stack.pop() == '1' && stack.empty()) {
            result = true;
        }
        return result;
    }

    /**
     * Returns the subProposition within the parenthesis.
     * @param stack
     * @return
     */
    private Proposition getSubProposition(Stack<Character> stack) {
        List<Character> subProp = new ArrayList();
        Character ch = stack.pop();
        while (ch != '(') {
            subProp.add(ch);
            ch = stack.pop();
        }
        subProp.add('(');
        Collections.reverse(subProp);
        return new Proposition(subProp);
    }
}
