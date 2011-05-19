package org.asoem.kdtree

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class KDTreeSpec extends FlatSpec with ShouldMatchers {
	
	val points : List[HyperPoint] = List(
		HyperPoint.at(2,3),
		HyperPoint.at(5,4),
		HyperPoint.at(9,6),
		HyperPoint.at(4,7),
		HyperPoint.at(8,1),
		HyperPoint.at(7,2))
	val tree = new KDTree(points, e => 0)


  "A KDTree" should "contain corect number of keys after construction" in {
      assert(tree.size == points.size,
        tree.size + " != " + points.size)
  }

  it should "handle zero points" in {
      val emptyTree = new KDTree(Nil);
      assert(emptyTree.size == 0, "");
  }

	it should "correctly report it's nodes" in {
		val nodes = tree.toList
		assert(nodes.forall(e => points contains e.point))
  }

  it can "be searched for the nearest neighbours to a given point" in {
     val nodeList1 = tree.findNeighbours(HyperPoint.at(9.1, 6.1), max=1) map (e => e.point)
     val expected1 = List[HyperPoint](points(2))
		 assert( nodeList1.sameElements(expected1),
       "NNSearch reported " + nodeList1 + "; Expected was " + expected1 + " for tree\n" + tree )
  }

  it should "find an existing node by it's key" in {
    val expected = points(0)
    val resultList = tree.findNeighbours(expected, range=0)
    assert(resultList.size == 1 && resultList.head.point == expected,
      "NNSearch reported " + resultList + "; Expected was " + expected + " for tree\n" + tree)
  }

  it should "handle duplicates" in {
    val points_with_dup = HyperPoint.at(7,2) :: points
    val tree_with_dup = new KDTree(points_with_dup, Unit => 0)

    val resultList = tree_with_dup.findNeighbours(HyperPoint.at(7,2), range=1.5)
    val resultListPoints = resultList.map(e => e.point)

    val expected = List[HyperPoint](points_with_dup(0), points_with_dup(6), points_with_dup(5))

		assert( resultListPoints.sameElements(expected),
      "NNSearch reported " + resultListPoints + "; Expected was " + expected + " for tree\n" + tree_with_dup )
  }
}
