package filter;

import filter.cyclefilter.ChordCycleFilter;
import filter.graphfilter.*;

public class OutputFilterVisitor implements FilterVisitor<String> {
    @Override
    public String visit(ChordCycleFilter chordCycleFilter) {
        return "CYCLE WITH CHORD";
    }

    @Override
    public String visit(BridgeGraphFilter bridgeGraphFilter) {
        return "BRIDGE";
    }

    @Override
    public String visit(TwoCutGraphFilter twoCutGraphFilter) {
        return "TWO-CUT";
    }

    @Override
    public String visit(ThreeCutGraphFilter threeCutGraphFilter) {
        return "THREE-CUT";
    }

    @Override
    public String visit(StrongCDCGraphFilter strongCDCGraphFilter) {
        return "STRONG CDC";
    }

    @Override
    public String visit(NegationFilter negationFilter) {
        return "NO " + negationFilter.getNegatedGraphFilter().accept(this);
    }

    @Override
    public String visit(TrueFilter trueFilter) {
        throw new IllegalArgumentException("YOU CAN'T USE TRUE FILTER WITH OUTPUT VISITOR");
    }

    @Override
    public String visit(LessThanGirthFiveGraphFilter lessThanGirthFiveGraphFilter) {
        return "GIRTH LESS THAN FIVE";
    }

}
