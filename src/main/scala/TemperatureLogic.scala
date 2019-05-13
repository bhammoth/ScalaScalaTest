class TemperatureLogic(val commandInputList: List[Int], val temperature: String) {

  val HOT_CONSTANT = "HOT"
  val COLD_CONSTANT = "COLD"
  val FAIL_CONSTANT = "fail"

  val B = new StringBuilder

  val clothesMapping: ClothesMapping = new ClothesMapping(commandInputList)

  var stringBuilderCounter: Int = 0
  var duplicateList = List[Int]()
  var indexToStartFromDuplicate: Int = 0

  def hotLogic(): Unit = {
    for (a <- commandInputList) {
      //finds duplicate values
      // fails after finding the first second duplicate in the list
      findDuplicates(a)
      businessRules(a)
      hotBusinessRules(a)
      stringBuilder(clothesMapping.hotMap(a))
      if (commandInputList.indexOf(commandInputList.last) == commandInputList.indexOf(a)) {
        println(B)
      }
    }
  }

  def coldLogic(): Unit = {
    for (a <- commandInputList) {
      //finds duplicate values
      // fails after finding the first second duplicate in the list
      findDuplicates(a)
      businessRules(a)
      coldBusinessRules(a)
      stringBuilder(clothesMapping.coldMap(a)).toString
      //if done print
      if (commandInputList.indexOf(commandInputList.last) == commandInputList.indexOf(a)) {
        println(B)
      }
    }
  }

  //Logic relating ot data input
  def commonBusinessLogic(): Unit = {

    if (commandInputList.size > 8 || commandInputList.size < 6) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Input list is too long or short")
    }

    //Checks for valid hot cold string
    if (temperature != HOT_CONSTANT && temperature != COLD_CONSTANT) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("String is neither 'HOT' or 'Cold")
    }

    //need to check that 8 is first number in list
    if (commandInputList(0) != 8) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Remove your PJs first")

    }
  }

  //Logic relating to specific rules
  def businessRules(currentListValue: Int): Unit = {

    //The shirt must be put on before the head wear
    //need to check 4 is before 2
    if (commandInputList.indexOf(4) > commandInputList.indexOf(2)
      && commandInputList.indexOf(2) == commandInputList.indexOf(currentListValue)) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Shirt before head wear check")
    }

    //The shirt must be put on before the jacket
    //need to check 4 is before 5
    if (commandInputList.indexOf(4) > commandInputList.indexOf(5)
      && commandInputList.indexOf(5) == commandInputList.indexOf(currentListValue)) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Shirt before jacket check")
    }

    //You cannot leave the house until all items of clothing are on
    if (commandInputList.last != 7 && currentListValue.equals(7)) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Leave House last check")
    }
  }

  def findDuplicates(currentListValue: Int): Unit = {

    val indexOfLastValue = commandInputList.lastIndexOf(currentListValue)
    val currentIndexListValue = commandInputList.indexOf(currentListValue,indexToStartFromDuplicate)

    if (currentIndexListValue != indexOfLastValue) {
      duplicateList = List(currentListValue)
    }
    else if(duplicateList.contains(currentListValue))
      {
        println(stringBuilder(FAIL_CONSTANT))
        throw new CustomException("Duplicate Found")
      }
    indexToStartFromDuplicate += 1
  }

  def hotBusinessRules(currentListValue: Int): Unit = {
    //You cannot put on socks when it is hot
    //You cannot put on a jacket when it is hot
    if ((currentListValue.equals(3) || currentListValue.equals(5))) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Its too hot for socks or a jacket")
    }
  }

  def coldBusinessRules(currentListValue: Int): Unit = {
    //Pants must be put on before footwear
    //check that 6-pants is before 1-footwear in list for cold
    if (commandInputList.indexOf(6) > commandInputList.indexOf(1)
      && commandInputList.indexOf(1) == commandInputList.indexOf(currentListValue)) {
      println(stringBuilder(FAIL_CONSTANT))
      throw new CustomException("Pants before footwear check")
    }
  }

  def stringBuilder(input: String): String = {
    if (stringBuilderCounter == 0) {
      B.append(input)
    }
    else {
      B.append(", ")
      B.append(input)
    }

    stringBuilderCounter += 1
    return B.toString();
  }
}
