package jezsmith.wgs_gcj_transform

object WgsToGcj {

  def apply(coord: List[Double]): List[Double] = {
    val lat = coord(0)
    val lng = coord(1)

    if (Transform.outOfChina(lat, lng)) {
      return coord
    }
    val delta = Transform.delta(lat, lng)
    List(lat + delta(0), lng + delta(1))
  }
}
