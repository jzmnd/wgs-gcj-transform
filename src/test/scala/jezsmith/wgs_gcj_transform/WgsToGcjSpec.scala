package jezsmith.wgs_gcj_transform

import org.scalatest._

class WgsToGcjSpec extends FlatSpec with Matchers {

  val tol = 2e-7

  "WgsToGcj" should "convert from WGS to GCJ" in {
    val testWgsCoords = List(
      (List(31.1774276, 121.5272106)),
      (List(39.911954, 116.377817)),
      (List(22.543847, 113.912316)),
    )
    val expectedGcjCoords = List(
      (List(31.17530398364597, 121.531541859215)),
      (List(39.91334545536069, 116.38404722455657)),
      (List(22.540796131694766, 113.9171764808363)),
    )
    for (i <- 0 until testWgsCoords.length) {
      WgsToGcj(testWgsCoords(i))(0) should be (expectedGcjCoords(i)(0) +- tol)
      WgsToGcj(testWgsCoords(i))(1) should be (expectedGcjCoords(i)(1) +- tol)
    }
  }
}
