package menace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchBox {
    private List<Bead> beads;
    private String state;
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

    public Bead getBead(){
        int pos = new Random().nextInt(beads.size());
        return beads.remove(pos);
    }

    public void reward(){
        addBeads(this.currentBead.getPosition(), Constants.BETA);
    }

    public void punish(){
        for (int i = 0; i < Constants.GAMMA - 1; i++) {
            int index = this.beads.indexOf(currentBead);
            if (index == -1) {
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
}
