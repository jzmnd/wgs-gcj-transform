package jezsmith.wgs_gcj_transform

import org.scalatest._

class GcjToWgsSpec extends FlatSpec with Matchers {

  val tol = 2e-5

  "GcjToWgs" should "convert from GCJ to WGS" in {
    val testGcjCoords = List(
      (List(31.17530398364597, 121.531541859215)),
      (List(39.91334545536069, 116.38404722455657)),
      (List(22.540796131694766, 113.9171764808363)),
    )
    val expectedWgsCoords = List(
      (List(31.1774276, 121.5272106)),
      (List(39.911954, 116.377817)),
      (List(22.543847, 113.912316)),
    )
    for (i <- 0 until testGcjCoords.length) {
      GcjToWgs(testGcjCoords(i))(0) should be (expectedWgsCoords(i)(0) +- tol)
      GcjToWgs(testGcjCoords(i))(1) should be (expectedWgsCoords(i)(1) +- tol)
    }
  }
}
