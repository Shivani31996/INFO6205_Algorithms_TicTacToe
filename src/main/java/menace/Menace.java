package menace;

import logger.LogManager;

import java.io.IOException;
import java.util.*;

public class Menace {
    private final List<MatchBox> matchBoxes;
    private final List<MatchBox> currentMatchBoxes;

    public Menace(HashMap<Integer, HashSet<String>> states) {
        this.matchBoxes = new ArrayList<>();
        this.currentMatchBoxes = new ArrayList<>();
        createMatchBoxes(states);
    }

    private void createMatchBoxes(HashMap<Integer, HashSet<String>> states) {
        for (Map.Entry<Integer, HashSet<String>> level: states.entrySet()) {
            for (String state : level.getValue()) {
                this.matchBoxes.add(new MatchBox(state));
            }
        }
    }

    public void reward(){
        for (MatchBox matchBox : currentMatchBoxes) {
            matchBox.reward();
        }
    }

    public void punish(){
        for (MatchBox matchBox : currentMatchBoxes) {
            matchBox.punish();
        }
    }

    public void draw(){
        for (MatchBox matchBox : currentMatchBoxes) {
            matchBox.draw();
        }
    }

    public void finish() {
        this.currentMatchBoxes.clear();
    }

    public Bead getBead(String state, Mode mode ) {
        for (MatchBox matchBox : matchBoxes) {
            Integer[] mapping = matchBox.getMappingIfEqual(state);
            if (mapping != null) {
                this.currentMatchBoxes.add(matchBox);
                Bead bead = matchBox.getBead(state, mapping, mode);
                return bead;
//                for (int i = 0; i < mapping.length; i++) {
//                    if (mapping[i] == bead.getPosition()) {
//                        return new Bead(i);
//                    }
//                }
                //return new Bead(mapping[bead.getPosition()]);
            }
        }

//        System.out.println(state);
//        String[] combinations = SymmetryUtil.getCombinations(state);
//        for (MatchBox matchBox : matchBoxes) {
//            for (String combination : combinations) {
//                Integer[] mapping = matchBox.equals(combination);
//                if (mapping != null) {
//                    this.currentMatchBoxes.add(matchBox);
//                    return matchBox.getBead();
//                }
//            }
//        }

        return null;
    }

    public void printTrained() throws IOException {
        for (MatchBox matchBox: matchBoxes) {
            System.out.println(matchBox);
            LogManager.logResult(matchBox.toString());
        }
    }
}
