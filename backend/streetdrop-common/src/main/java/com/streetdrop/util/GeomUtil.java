package com.streetdrop.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeomUtil {
	private static final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

	public static Point createPoint(double lat, double lon) {
		return gf.createPoint(new Coordinate(lat, lon));
	}
}
