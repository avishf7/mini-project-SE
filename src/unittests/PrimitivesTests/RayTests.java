/**
 * 
 */
package unittests.PrimitivesTests;

import static org.junit.Assert.*;


import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable;
import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author User
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		Ray ray = new Ray(new Point3D(0,1,0),new Vector(1,0,0));
		List <Point3D> points = new LinkedList<Point3D>();
		points.add(new Point3D(3,4,0));
		points.add(new Point3D(1,1,0));
		points.add(new Point3D(2,3,0));
		// ============ Equivalence Partitions Tests ==============

		// TC01: The closest point to the head of the ray is not at the edges of the list
		assertEquals("Wrong point", ray.findClosestPoint(points), points.get(1));
		
		// =============== Boundary Values Tests ==================
		
		// TC11: The closest point to the head of the ray is the first point
		points.add(0,points.remove(1));
		assertEquals("Wrong point", ray.findClosestPoint(points), points.get(0));
		
		// TC12: The closest point to the head of the ray is the last point
		points.add(points.remove(0));
		assertEquals("Wrong point", ray.findClosestPoint(points), points.get(2));

		// TC13: empty list
		points.clear();
		assertNull("empty list",ray.findClosestPoint(points));
		// TC14: empty list(2)the list is null
		points=null;
		assertNull("empty list",ray.findClosestPoint(points));
	}

}
