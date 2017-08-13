package com.indix.tautology.main.sevrices.impl;

import com.indix.tautology.main.sevrices.PropositionEvaluator;
import com.indix.tautology.main.types.Proposition;
import com.indix.tautology.main.types.Variable;

import java.util.Iterator;
import java.util.Stack;

/**
 * Class to evaluate a proposition or its part and return the output.
 */
public class PropositionEvaluatorImpl implements PropositionEvaluator {
    private static Stack<Character> stack = new Stack();
    private static Variable result = new Variable();
    private static Iterator iterator;

    @Override
    public Variable evaluate(Proposition prop) {

        iterator = prop.getProposition().iterator();
        while (iterator.hasNext()) {
            Character character = (Character) iterator.next();
            if (character == ')') {
                break;
            } else if (character == '|') {
                evaluateWithOr();
            } else if (character == '&') {
                evaluateWithAnd();
            } else
                stack.push(character);
        }

        if (stack.pop() != '(') {
            result.setVariable('0');
        }
        return result;
    }

    //Instead of adding the truth table, implementation has been done by taking multiple cases and predicting their output based.

    /**
     * Evaluating operands with OR. Here all the possible cases are mentioned.
     */
    private void evaluateWithOr() {
        Character operator1 = stack.pop();
        Character operator2 = (Character) iterator.next();
        if (operator1 == '1') {
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                if (operator2 == '!') {
                    result.setNegatedVariable((Character) iterator.next());
                } else
                    result.setVariable(operator2);
            } else
                result.setVariable('1');
        } else if (operator1 == '0') {
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                result.setVariable('1');
            } else {
                if (operator2 == '!') {
                    result.setNegatedVariable((Character) iterator.next());
                } else
                    result.setVariable(operator2);
            }
        }

        if (operator2 == '!') {
            operator2 = (Character) iterator.next();
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                if (operator1 == operator2) {
                    result.setNegatedVariable(operator2);
                } else if (operator2 == '1') {
                    result.setNegatedVariable(operator1);
                }
            } else if (operator1 == operator2) {
                result.setVariable('1');
            } else if (operator2 == '0') {
                result.setVariable('1');
            } else if (operator2 == '1') {
                result.setVariable(operator1);
            }
        } else if (!stack.empty() && stack.peek() == '!') {
            stack.pop();
            if (operator1 == operator2) {
                result.setVariable('1');
            } else if (operator1 == '0') {
                result.setVariable('1');
            } else if (operator1 == '1') {
                result.setVariable('0');
            } else if (operator2 == '1') {
                result.setVariable('1');
            } else if (operator2 == '0') {
                result.setNegatedVariable(operator1);
            }
        } else if (operator2 == '1' || operator1 == '1') {
            result.setVariable('1');
        } else if (operator2 == '0') {
            result.setVariable(operator1);
        } else if (operator1 == '0') {
            result.setVariable(operator2);
        } else if (operator1 == operator2) {
            result.setVariable(operator2);
        } else {
            result.setVariable('0');
        }
    }

    /**
     * Evaluating operands with AND. Here all the possible cases are mentioned.
     */
    private void evaluateWithAnd() {
        Character operator1 = stack.pop();
        Character operator2 = (Character) iterator.next();

        if (operator1 == '1') {
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                result.setVariable('0');
            } else {
                if (operator2 == '!') {
                    result.setNegatedVariable((Character) iterator.next());
                } else
                    result.setVariable(operator2);
            }
        } else if (operator1 == '0') {
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                if (operator2 == '!') {
                    result.setNegatedVariable((Character) iterator.next());
                } else
                    result.setVariable(operator2);
            } else {
                result.setVariable('0');
            }
        }

        if (operator2 == '!') {
            operator2 = (Character) iterator.next();
            if (!stack.empty() && stack.peek() == '!') {
                stack.pop();
                if (operator1 == operator2) {
                    result.setVariable(operator2);
                } else if (operator2 == '0') {
                    result.setNegatedVariable(operator1);
                }
            } else if (operator1 == operator2) {
                result.setVariable('0');
            } else if (operator2 == '1') {
                result.setVariable('0');
            } else if (operator2 == '0') {
                result.setVariable(operator1);
            } else {
                result.setVariable('0');
            }
        } else if (!stack.empty() && stack.peek() == '!') {
            stack.pop();
            if (operator1 == operator2) {
                result.setVariable('0');
            } else if (operator1 == '0') {
                result.setVariable(operator2);
            } else if (operator1 == '1') {
                result.setVariable('0');
            } else if (operator2 == '0') {
                result.setVariable('0');
            } else if (operator2 == '1') {
                result.setNegatedVariable(operator1);
            }
        } else if (operator2 == '0' || operator1 == '0') {
            result.setVariable('0');
        } else if (operator2 == '1') {
            result.setVariable(operator1);
        } else if (operator1 == '1') {
            result.setVariable(operator2);
        } else if (operator1 == operator2) {
            result.setVariable(operator2);
        } else {
            result.setVariable('0');
        }
    }
}
