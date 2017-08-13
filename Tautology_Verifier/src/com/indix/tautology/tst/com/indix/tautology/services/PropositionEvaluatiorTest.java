package com.indix.tautology.tst.com.indix.tautology.services;

import com.indix.tautology.main.sevrices.PropositionEvaluator;
import com.indix.tautology.main.sevrices.impl.PropositionEvaluatorImpl;
import com.indix.tautology.main.types.Proposition;
import com.indix.tautology.main.types.Variable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Testing class for PropositionEvaluation class.
 */
public class PropositionEvaluatiorTest {
    private PropositionEvaluator propositionEvaluator;

    @Before
    public void setUp() throws Exception {
        propositionEvaluator = new PropositionEvaluatorImpl();
    }

    @Test
    public void shouldEvaluateProposition() throws Exception {

        Variable result = new Variable();
        result.setVariable('a');

        Proposition p = new Proposition(Arrays.asList('(', 'a', '&', '1', ')'));
        Variable testVariable = propositionEvaluator.evaluate(p);
        Assert.assertNotNull(testVariable);
        Assert.assertEquals(testVariable.getVariable(), result.getVariable());
        Assert.assertEquals(testVariable.isNegated(), result.isNegated());
    }

    @Test
    public void evaluatePropositionWithNestedParentheses() throws Exception {
        Variable result = new Variable();
        result.setVariable('a');

        Proposition p = new Proposition(Arrays.asList('(', '!', 'a', '|', '(', 'a', '&', 'a', ')'));
        Variable testVariable = propositionEvaluator.evaluate(p);
        Assert.assertNotNull(testVariable);
        Assert.assertEquals(testVariable.getVariable(), result.getVariable());
        Assert.assertEquals(testVariable.isNegated(), result.isNegated());
    }

    @Test
    public void evaluatePropositionWithNoOperator() throws Exception {
        Variable result = new Variable();
        result.setVariable('0');

        Proposition p = new Proposition(Arrays.asList('(', 'a', ')'));
        Variable testVariable = propositionEvaluator.evaluate(p);
        Assert.assertNotNull(testVariable);
        Assert.assertEquals(testVariable.getVariable(), result.getVariable());
        Assert.assertEquals(testVariable.isNegated(), result.isNegated());
    }
}
