/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * RayTracerBase is an abstract class representing a type of ray scanner
 * 
 * @author Shai&Avishay
 *
 */
public abstract class RayTracerBase {
	/**
	 * The photographed scene
	 */
	protected Scene scene;

	/**
	 * RayTracerBase constructor receiving {@link scene}.
	 * 
	 * @param scene the photographed scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * The function activates the acceleration of performance by building boxes 
	 * and optimizes the improvement by arranging boxes in case it is sent true.
	 * 
	 * @param ToOrder A Boolean variable that decides whether to arrange the boxes
	 * @return The ray tracer
	 */
	public RayTracerBase accelerate(boolean ToOrder) {
		scene.geometries.setToOrder(ToOrder).buildBox();
		return this;
	}

	/**
	 * The function checks what color the ray coming out towards the scene meets
	 * 
	 * @param ray
	 * @return Color The color of the points that the ray meets
	 */
	public abstract Color traceRay(Ray ray);

}
