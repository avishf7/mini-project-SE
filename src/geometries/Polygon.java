package geometries;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point3D> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point3D... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length != 3) {// no need for more tests for a Triangle

			Vector n = plane.getNormal();

			// Subtracting any subsequent points will throw an IllegalArgumentException
			// because of Zero Vector if they are in the same point
			Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
			Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

			// Cross Product of any subsequent edges will throw an IllegalArgumentException
			// because of Zero Vector if they connect three vertices that lay in the same
			// line.
			// Generate the direction of the polygon according to the angle between last and
			// first edge being less than 180 deg. It is hold by the sign of its dot product
			// with
			// the normal. If all the rest consequent edges will generate the same sign -
			// the
			// polygon is convex ("kamur" in Hebrew).
			boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
			for (int i = 1; i < vertices.length; ++i) {
				// Test that the point is in the same plane as calculated originally
				if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
					throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
				// Test the consequent edges have
				edge1 = edge2;
				edge2 = vertices[i].subtract(vertices[i - 1]);
				if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
					throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
			}
		}

	}

	@Override
	public void buildBox() {
		
		// --------Finds the minimum and maximum coordinate values between all the vertices--------
		
		double minX = vertices.get(0).getX(), minY = vertices.get(0).getY(), minZ = vertices.get(0).getZ(), maxX = minX,
				maxY = minY, maxZ = minZ;
		for (Point3D point3d : vertices) {
			double x = point3d.getX();
			double y = point3d.getY();
			double z = point3d.getZ();

			if (minX > x)
				minX = x;
			if (minY > y)
				minY = y;
			if (minZ > z)
				minZ = z;

			if (maxX < x)
				maxX = x;
			if (maxY < y)
				maxY = y;
			if (maxZ < z)
				maxZ = z;

		}
		//----------------------------------------------------------------------------------------------
		
		this.box = new WrapBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public Vector getNormal(Point3D point) {
		return plane.getNormal();
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		//Checks if there is a intersect with the wrapping box 
		//(if there is no box continues to the normal intersect test)
		if (this.box != null && !this.box.isIntersect(ray))
			return null;

		List<GeoPoint> intsPoints = plane.findGeoIntersections(ray, maxDistance);

		if (intsPoints == null)
			return null;

		LinkedList<Vector> perEdges = new LinkedList<Vector>();
		for (Point3D v : vertices) {
			perEdges.add(v.subtract(ray.getP0()));
		}

		double result = alignZero(
				ray.getDir().dotProduct(perEdges.get(perEdges.size() - 1).crossProduct(perEdges.get(0)).normalize()));

		if (result == 0)
			return null;

		boolean isPos = result > 0;
		for (int i = 0; i < perEdges.size() - 1; i++) {
			result = ray.getDir().dotProduct(perEdges.get(i).crossProduct(perEdges.get(i + 1)).normalize());

			if (result == 0)
				return null;

			if ((result > 0) != isPos)
				return null;
		}
		intsPoints.get(0).geometry = this;
		return intsPoints;

	}

}
