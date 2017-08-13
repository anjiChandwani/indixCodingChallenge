package com.indix.tautology.main.sevrices;

import com.indix.tautology.main.types.Proposition;

public interface TautologyVerifier {
    boolean verify(Proposition p);
}
