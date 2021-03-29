package filter;

import filter.cyclefilter.ChordCycleFilter;
import filter.graphfilter.*;

public interface FilterVisitor<T> {

    T visit(ChordCycleFilter chordCycleFilter);

    T visit(BridgeGraphFilter bridgeGraphFilter);

    T visit(TwoCutGraphFilter twoCutGraphFilter);

    T visit(ThreeCutGraphFilter threeCutGraphFilter);

    T visit(StrongCDCGraphFilter strongCDCGraphFilter);

    T visit(NegationFilter negationFilter);

    T visit(TrueFilter trueFilter);

    T visit(LessThanGirthFiveGraphFilter lessThanGirthFiveGraphFilter);
}
