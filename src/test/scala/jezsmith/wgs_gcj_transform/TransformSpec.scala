package jezsmith.wgs_gcj_transform

import org.scalatest._

class TransformSpec extends FlatSpec with Matchers {

  "outOfChina" should "return true if latitude/longitude is in China" in {
    Transform.outOfChina(31.1774276, 121.5272106) should be (false)  // Shanghai
    Transform.outOfChina(39.911954,  116.377817 ) should be (false)  // Beijing
    Transform.outOfChina(22.543847,  113.912316 ) should be (false)  // Shenzhen
  }

  "outOfChina" should "return false if latitude/longitude is outside China" in {
    Transform.outOfChina( 51.5286416,   -0.1015987) should be (true)  // London
    Transform.outOfChina(-32.0387695,  115.3872236) should be (true)  // Perth
    Transform.outOfChina( 37.7576793, -122.5076397) should be (true)  // San Francisco
    Transform.outOfChina( 35.6681625,  139.6007844) should be (true)  // Tokyo
  }

  "delta" should "return list of transformation offsets" in {
    Transform.delta(31.1774276, 121.5272106) should be (List(-0.0021236523128949276, 0.004331332555535167))
    Transform.delta(39.911954,  116.377817 ) should be (List(0.0013914789219837372, 0.006230330051995063))
    Transform.delta(22.543847,  113.912316 ) should be (List(-0.003050919965109875, 0.004860563138060881))
  }
}
