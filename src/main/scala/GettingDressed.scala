object GettingDressed extends App
{
  val HOT_CONSTANT = "HOT"
  val COLD_CONSTANT = "COLD"
  val temperature = args(0).toString.toUpperCase.trim
  val commandInputList: List[String] = args(1).split(",").toList;
  val temperatureLogic: TemperatureLogic = new TemperatureLogic(commandInputList.map(_.toInt),temperature)

  //Validates input data against some common business rules
  //Checks for valid hot cold string and that PJ's are taken off first
  try
    {
      temperatureLogic.commonBusinessLogic()
    }
  catch
    {
      case e: Exception => e.printStackTrace
        temperatureLogic.stringBuilder("Something Broke in the business logic: " + e)

    }

  if (temperature == HOT_CONSTANT) {
    try
      {
        temperatureLogic.hotLogic()
      }
    catch
      {
        case e: Exception => e.printStackTrace
          temperatureLogic.stringBuilder("Something Broke in the hot logic: " + e)
      }
  }

  else if (temperature == COLD_CONSTANT) {
    try {

      temperatureLogic.coldLogic()
    }
    catch
      {
        case e: Exception => e.printStackTrace
          temperatureLogic.stringBuilder("Something Broke in the cold logic: " + e)

      }
  }
}
