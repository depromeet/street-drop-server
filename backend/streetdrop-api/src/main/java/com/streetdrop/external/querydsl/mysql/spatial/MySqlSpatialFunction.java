package com.streetdrop.external.querydsl.mysql.spatial;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.StringTemplate;
import org.locationtech.jts.geom.Point;

import static com.querydsl.core.types.dsl.Expressions.stringTemplate;

public class MySqlSpatialFunction {
    public static StringTemplate mySqlDistanceSphereFunction(Expression<? extends Point> point1, Point point2) {
        return stringTemplate("ST_Distance_Sphere({0}, {1})", point1, point2);
    }
}
