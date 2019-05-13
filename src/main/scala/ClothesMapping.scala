class ClothesMapping(val inputList: List[Int]) {

  val hotResponse = Map(1->"sandals",
    2->"sunglasses",
    3->"fail",
    4->"shirt",
    5->"fail",
    6->"shorts",
    7->"leaving house",
    8->"Removing PJs");

  val coldResponse = Map(1->"boots",
    2->"hat",
    3->"socks",
    4->"shirt",
    5->"jacket",
    6->"pants",
    7->"leaving house",
    8->"Removing PJs");

  //function for mapping of hot responses
  def hotMap(x:Int) : String = {
    val hotText = hotResponse(x)
    return hotText
  }

  //function for mapping of cold responses
  def coldMap(x:Int) : String = {
    val coldText = coldResponse(x)
    return coldText
  }

}
