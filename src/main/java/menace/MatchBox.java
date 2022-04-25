package menace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchBox {
    private final List<Bead> beads;
    private final String state;
    private Bead currentBead;

    public MatchBox(String state) {
        this.beads = new ArrayList<>();
        this.state = state;
        fillBox();
    }

    private void fillBox() {
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '-') {
                addBeads(i, Constants.ALPHA);
            }
        }
    }

    public String getState() {
        return this.state;
    }

    public Integer[] getMappingIfEqual(String state) {
        Integer[] mapping = SymmetryUtil.getMappingIfEqual(this.state, state);
        return mapping;
    }

    public Bead getBead(String state, Integer[] mapping, Mode mode){
//        int pos = 0;
//
//        if (mode == Mode.TRAIN) {
//            pos = new Random().nextInt(beads.size());
//        } else {
//            pos = getMaxSize(beads);
//        }

        if (this.beads.size() == 0) {
            this.fillBox();
        }

        int pos = new Random().nextInt(beads.size());

//        if (this.state.charAt(beads.get(pos).getPosition()) != '-') {
//            System.out.println("Bade lode");
//        }
        //System.out.println("Original: " + this.state + " -> pos: " + beads.get(pos).getPosition());
        //System.out.println("Bahar wala: " + state + " -> pos: " + mapping[beads.get(pos).getPosition()]);
        this.currentBead = beads.remove(pos);
        return new Bead(mapping[currentBead.getPosition()]);
//        if (state.charAt(mapping[beads.get(pos).getPosition()]) == '-') {
//
//        }
//        while (true) {
//            //System.out.println("lode: " + state);
//
//        }
//        for (int i = 0; i < beads.size(); i++) {
//            int pos = new Random().nextInt(beads.size());
//            if (state.charAt(mapping[beads.get(pos).getPosition()]) == '-') {
//                return new Bead(mapping[beads.remove(pos).getPosition()]);
//            }
//        }

        //throw new IllegalStateException("Could not find bead");
    }

    private int getMaxSize(List<Bead> beads) {
        int max = Integer.MIN_VALUE;
        for (Bead bead : beads) {
            max = Math.max(max, bead.getPosition());
        }

        return max;
    }

    public void reward(){
        addBeads(this.currentBead.getPosition(), Constants.BETA);
    }

    public void punish(){
        for (int i = 0; i < Constants.GAMMA - 1; i++) {
            int index = this.beads.indexOf(currentBead);
            if (index == -1) {
                //Changed this
//                addBeads(this.currentBead.getPosition(), 1);
                break;
            }

            this.beads.remove(index);
        }
    }

    public void draw(){
        addBeads(this.currentBead.getPosition(), Constants.DELTA);
    }

    private void addBeads(int beadPosition, int count) {
        for (int i = 0; i < count; i++) {
            this.beads.add(new Bead(beadPosition));
        }
    }

    @Override
    public String toString() {
        int[] freq = new int[9];
        for (Bead bead : beads) {
            freq[bead.getPosition()]++;
        }

        String output = state;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > Constants.ALPHA) {
                output += ", Bead: " + i + " freq: " + freq[i];
            }
        }

        return output;
    }
}
