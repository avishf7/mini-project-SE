/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 *
 * 
 * @author User
 *
 */

public class Geometries implements Intersectable {

	
	List<Intersectable> shapes;
	
	/**
	 * 
	 */
	public Geometries() {
		shapes=new LinkedList<Intersectable>(); 
	}
	public Geometries(Intersectable... geometries) {
	}
	
	public void add(Intersectable... geometries) {
	
	}
	
	
	
	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
