package com.indix.tautology.main.sevrices;

import com.indix.tautology.main.types.Proposition;
import com.indix.tautology.main.types.Variable;

public interface PropositionEvaluator {
    Variable evaluate(Proposition prop);
}
