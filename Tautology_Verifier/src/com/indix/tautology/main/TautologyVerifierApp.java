package com.indix.tautology.main;

import com.indix.tautology.main.sevrices.PropositionEvaluator;
import com.indix.tautology.main.sevrices.TautologyVerifier;
import com.indix.tautology.main.sevrices.impl.PropositionEvaluatorImpl;
import com.indix.tautology.main.sevrices.impl.TautologyVerifierImpl;
import com.indix.tautology.main.types.Proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Class to execute the tautology verifier application.
 */
public class TautologyVerifierApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Taking input from console
        System.out.println("Enter number of propositions");
        int totalPropositions = parseInt(scanner.nextLine());
        System.out.println("Enter propositions:");
        ArrayList<String> propositions = new ArrayList<String>();
        for(int i=0;i<totalPropositions;i++){
            propositions.add(scanner.nextLine());
        }

        // Verify and print result to console
        System.out.println("Output");
        ArrayList<Boolean> results = new ArrayList<Boolean>(totalPropositions);
        int i = 0;
        for (String props : propositions) {

            List<Character> list = new ArrayList<Character>();
            for (char p : props.toCharArray()) {
                list.add(p);
            }

            Proposition p = new Proposition(list);
            PropositionEvaluator propositionEvaluator = new PropositionEvaluatorImpl();
            TautologyVerifier tautologyVerifier = new TautologyVerifierImpl(propositionEvaluator);
            results.add(tautologyVerifier.verify(p));
            System.out.println(results.get(i));
            i++;
        }
    }
}
