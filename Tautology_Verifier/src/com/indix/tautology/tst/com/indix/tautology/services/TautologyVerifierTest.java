
package com.indix.tautology.tst.com.indix.tautology.services;

import com.indix.tautology.main.sevrices.TautologyVerifier;
import com.indix.tautology.main.sevrices.impl.PropositionEvaluatorImpl;
import com.indix.tautology.main.sevrices.impl.TautologyVerifierImpl;
import com.indix.tautology.main.types.Proposition;
import com.indix.tautology.main.types.Variable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Test cases for Tautology Verifier class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TautologyVerifierTest {
    @Mock
    private static PropositionEvaluatorImpl propositionEvaluator;

    private TautologyVerifier tautologyVerifier;

    @Before
    public void setUp() throws Exception {
        tautologyVerifier = new TautologyVerifierImpl(propositionEvaluator);
    }

    @Test
    public void shouldVerifyIfTautology() throws Exception {

        List<Character> list = Arrays.asList('(', '!', 'a', '|', 'a', ')');
        Proposition p = new Proposition(list);
        Variable var = new Variable();
        var.setVariable('1');

        when(propositionEvaluator.evaluate(any(Proposition.class))).thenReturn(var);
        Assert.assertTrue(tautologyVerifier.verify(p));
    }
}

