package jezsmith.wgs_gcj_transform

import scala.math._

object Transform {

  val earthEccentricity: Double = 0.00669342162296594323
  val earthRadius: Double = 6378137.0  // meters

  def outOfChina(lat: Double, lng: Double): Boolean = {
    lng < 72.004 || lng > 137.8347 || lat < 0.8293 || lat > 55.8271
  }

  private def transformLat(x: Double, y: Double): Double = {
    val xPi = x * Pi
    val yPi = y * Pi

    var lat = 20.0 * sin(6.0 * xPi) + 20.0 * sin(2.0 * xPi)
    lat += 20.0 * sin(yPi) + 40.0 * sin(yPi / 3.0)
    lat += 160.0 * sin(yPi / 12.0) + 320.0 * sin(yPi / 30.0)
    lat *= (2.0 / 3.0)
    lat - 100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * sqrt(abs(x))
  }

  private def transformLng(x: Double, y: Double): Double = {
    val xPi = x * Pi

    var lng = 20.0 * sin(6.0 * xPi) + 20.0 * sin(2.0 * xPi)
    lng += 20.0 * sin(xPi) + 40.0 * sin(xPi / 3.0)
    lng += 150.0 * sin(xPi / 12.0) + 300.0 * sin(xPi / 30.0)
    lng *= (2.0 / 3.0)
    lng + 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * sqrt(abs(x))
  }

  def delta(lat: Double, lng: Double): List[Double] = {
    var dLat = transformLat(lng - 105.0, lat - 35.0)
    var dLng = transformLng(lng - 105.0, lat - 35.0)

    val radLat = lat / 180.0 * Pi
    val sinRadLat = sin(radLat)
    val magic = 1 - earthEccentricity * sinRadLat * sinRadLat
    val sqrtMagic = sqrt(magic)

    dLat = (dLat * 180.0) / (earthRadius * (1 - earthEccentricity) / (magic * sqrtMagic) * Pi)
    dLng = (dLng * 180.0) / (earthRadius / sqrtMagic * cos(radLat) * Pi)

    List(dLat, dLng)
  }
}
