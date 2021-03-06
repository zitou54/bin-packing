package domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.solution.Solution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

@PlanningSolution
public class Packing implements Solution<HardSoftScore> {
    private List<Platform> platformList;
    private List<Part> partList;
    private List<Coordinate> coordinateList;
    private HardSoftScore score;

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    @PlanningEntityCollectionProperty
    public List<Part> getPartList() {
        return partList;
    }

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(platformList);
        facts.addAll(coordinateList);

        return facts;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Packing)) {     //removed id == null
            return false;

        } else {
            Packing other = (Packing) o;
            if(partList.size() != other.partList.size()) {
                return false;
            }
            for(Iterator<Part> it = partList.iterator(), otherIt = other.partList.iterator(); it.hasNext();) {
                Part part = it.next();
                Part otherPart = otherIt.next();
                if(!part.equals(otherPart)) {      //changed from solutionEquals
                    return false;
                }
            }
            return true;
        }
    }
}
