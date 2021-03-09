package primitives;

import java.util.Objects;

public class Point3D {

	public static final Point3D ZERO = new Point3D(0, 0, 0);

	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = new Coordinate(x.coord);
		this.y =new Coordinate (y.coord);
		this.z =new Coordinate( z.coord);
	}

	Coordinate x;
	Coordinate y;
	Coordinate z;

	public Vector subtract(Point3D point) {
		return new Vector(x.coord - point.x.coord,y.coord - point.y.coord,z.coord - point.z.coord);
	}
	
	public Point3D add(Vector vec) {
		return new Point3D(x.coord + vec.getHead().x.coord,y.coord + vec.getHead().y.coord,z.coord + vec.getHead().z.coord);
	}
	
	public double distanceSquared(Point3D point) {

		return (x.coord - point.x.coord) * (x.coord - point.x.coord)
				+ (y.coord - point.y.coord) * (y.coord - point.y.coord)
				+ (z.coord - point.z.coord) * (z.coord - point.z.coord);
	}

	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}

	@Override
	public String toString() {
		return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	
}
