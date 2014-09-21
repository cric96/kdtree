package org.asoem.kdtree

trait HyperPoint extends HyperObject {

  def distance(that: HyperPoint): Double

  def edit(dim: Int, value: Double): HyperPoint

  def apply(idx: Int): Double

  def boundingBox = HyperRect(this, this)

  def coordinates: List[Double]

  override def toString: String = {
    "[" + coordinates.mkString(" ") + "]"
  }

  def /(divisor: Double): HyperPoint

  def -(c: Double): HyperPoint

  def +(c: Double): HyperPoint

  def -(that: HyperPoint): HyperPoint

  def +(that: HyperPoint): HyperPoint
}

object HyperPoint {
  def zero(dim: Int): HyperPoint = fill(dim, () => 0)

  def min(dim: Int): HyperPoint = fill(dim, () => Double.MinValue)

  def max(dim: Int): HyperPoint = fill(dim, () => Double.MaxValue)

  def fill[A](dim: Int, f: () => Double): HyperPoint = dim match {
    case 2 => HyperPoint(f(), f())
    case _ => HyperPoint(List.fill(dim)(f()))
  }

  def apply(c1: Double, c2: Double): HyperPoint = {
    new HyperPoint2(c1, c2)
  }

  def apply(coords: Array[Double]): HyperPoint = {
    apply(coords.toTraversable)
  }

  def apply(coords: Double*): HyperPoint = coords.length match {
    case 2 => new HyperPoint2(coords(0), coords(1))
    case _ => new HyperPointN(coords)
  }

  def apply(coords: Traversable[Double]): HyperPoint = apply(coords.toArray: _*)
}