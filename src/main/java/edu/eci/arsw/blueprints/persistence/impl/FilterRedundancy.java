package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.FilterBluePrints;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("FilterRedundancy")

public class FilterRedundancy implements FilterBluePrints {
    @Override
    public Blueprint filter(Blueprint bp) {
        ArrayList<Point> points= new ArrayList<>();
        for (Point i :bp.getPoints()){
            boolean found=false;
            for(Point j : points){
                if(i.equals(j)){
                    found=true;
                    break;
                }
            }
            if(!found)points.add(i);
        }
        return new Blueprint(bp.getAuthor(),bp.getName(),points);
    }
}
