/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * @author Assist
 *
 */
public class MiniProject {

	private Scene scene = new Scene("Mini Project");

	@Test
	public void threeDModel() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setViewPlaneDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Polygon(new Point3D(-5, 120, -250), new Point3D(-5, 5, -290), new Point3D(-120, 5, -250),
						new Point3D(-120, 120, -210))//
								.setMaterial(new Material().setKS(0.8).setKD(0.2).setnShininess(100).setkR(1.0)), //
				new Plane(new Point3D(85, -85, -140), new Point3D(15, -85, -140), new Point3D(50, -50, -220))//
						.setEmmission(new Color(java.awt.Color.DARK_GRAY))//
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(60)),
				new Polygon(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new Point3D(85, -85, -140),
						new Point3D(85, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Polygon(new Point3D(85, -15, -60), new Point3D(15, -15, -60), new Point3D(15, -15, -140),
						new Point3D(85, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Polygon(new Point3D(15, -15, -60), new Point3D(15, -85, -60), new Point3D(15, -85, -140),
						new Point3D(15, -15, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Polygon(new Point3D(15, -85, -60), new Point3D(85, -85, -60), new Point3D(85, -85, -140),
						new Point3D(15, -85, -140))//
								.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -85, -60), new Point3D(15, -85, -60), new Point3D(50, -50, -80))//
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -15, -60), new Point3D(15, -15, -60), new Point3D(50, -50, -80))//
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -15, -60), new Point3D(85, -85, -60), new Point3D(50, -50, -80))//
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(15, -85, -60), new Point3D(15, -15, -60), new Point3D(50, -50, -80))//
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -85, -140), new Point3D(15, -85, -140), new Point3D(50, -50, -220))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -15, -140), new Point3D(15, -15, -140), new Point3D(50, -50, -220))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(85, -15, -140), new Point3D(85, -85, -140), new Point3D(50, -50, -220))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Triangle(new Point3D(15, -85, -140), new Point3D(15, -15, -140), new Point3D(50, -50, -220))
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkT(0.8)), //
				new Sphere(new Point3D(50, -50, -100), 20) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30).setkR(1)), //
				new Sphere(new Point3D(50, -50, -150), 10) //
						.setEmmission(new Color(java.awt.Color.PINK)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30)), //
				new Sphere(new Point3D(50, -50, -190), 5) //
						.setEmmission(new Color(java.awt.Color.WHITE)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setnShininess(30)) //
		);
		scene.lights.add(new DirectionalLight(new Color(java.awt.Color.YELLOW).reduce(8), new Vector(0, 0, -1)));
		scene.lights.add(
				new SpotLight(new Color(java.awt.Color.WHITE).reduce(2), new Point3D(100, -90, -140), new Vector(-1, 0, 0))
						.setKl(0.00001).setKq(0.0000099).setRadius(20).setBeamsNum(400));
		scene.lights
				.add(new PointLight(new Color(java.awt.Color.YELLOW).reduce(4).reduce(2), new Point3D(-200, -50, -100))
						.setKl(0.0000001).setKq(0.0000000001).setRadius(20).setBeamsNum(400));

		Render render = new Render() //
				.setImageWriter(new ImageWriter(scene.name, 600, 600)) //
				.setCam(camera) //
				.setRayTracer(new RayTracerBasic(scene))//
				.setMultithreading(3) //
				.setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}

}