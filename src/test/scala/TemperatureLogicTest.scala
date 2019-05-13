import GettingDressed.{commandInputList, temperature}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
class TemperatureLogicTest extends FunSuite {

  test("testBusinessRules_ShirtBeforeHeadWear") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(2,4),"COLD")

    val customException = intercept[CustomException]{
      temperatureLogic.businessRules(temperatureLogic.commandInputList(0))
    }
    assert(customException.getMessage=="Shirt before head wear check")
  }

  test("testBusinessRules_ShirtBeforeJacket") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(5,4),"COLD")

    val customException = intercept[CustomException]{
      temperatureLogic.businessRules(temperatureLogic.commandInputList(0))
    }
    assert(customException.getMessage=="Shirt before jacket check")
  }

  test("testBusinessRules_LeaveHouse") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,4,7,5),"COLD")

    val customException = intercept[CustomException]{
      temperatureLogic.businessRules(temperatureLogic.commandInputList(2))
    }
    assert(customException.getMessage=="Leave House last check")
  }

    test("testStringBuilder_comma") {
      val temperatureLogic: TemperatureLogic  = generateNewList(List(0),"HOT")

      temperatureLogic.stringBuilderCounter = 1;
      val expected = ", testing"
      val actual = temperatureLogic.stringBuilder("testing")
      assert(expected == actual)

  }

  test("testStringBuilder_noComma") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(0),"HOT")

    temperatureLogic.stringBuilderCounter = 0;
    val expected = "testing"
    val actual = temperatureLogic.stringBuilder("testing")
    assert(expected == actual)
  }

  test("testHotBusinessRules_Jacket") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(5),"HOT")


    val customException = intercept[CustomException]{
        temperatureLogic.hotBusinessRules(temperatureLogic.commandInputList(0))
    }
    assert(customException.getMessage=="Its too hot for socks or a jacket")

  }

  test("testHotBusinessRules_Socks") {

    val temperatureLogic: TemperatureLogic  = generateNewList(List(3),"HOT")

    val customException = intercept[CustomException]{
        temperatureLogic.hotBusinessRules(temperatureLogic.commandInputList(0))
    }
    assert(customException.getMessage=="Its too hot for socks or a jacket")

  }

  test("testColdBusinessRules_PantsBeforeFootwear") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(1,6),"COLD")

    val customException = intercept[CustomException]{
      temperatureLogic.coldBusinessRules(temperatureLogic.commandInputList(0))
    }
    assert(customException.getMessage=="Pants before footwear check")
  }

  test("testFindDuplicates_DuplicateFound") {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,8),"HOT")


    val customException = intercept[CustomException]{
      for(a<-temperatureLogic.commandInputList)
        {
          temperatureLogic.findDuplicates(a)
        }
    }
    assert(customException.getMessage=="Duplicate Found")
  }

  test("commonBusinessLogic_InvalidListSizeTooSmall")
  {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,3,4,2),"COLD")

    val customException = intercept[CustomException]{
        temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="Input list is too long or short")
  }

  test("commonBusinessLogic_InvalidListSizeTooLarge")
  {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,3,4,2,5,1,7,9),"HOT")

    val customException = intercept[CustomException]{
      temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="Input list is too long or short")
  }

  test("commonBusinessLogic_PJsRemovedFirst")
  {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(6,8,3,4,2,5,1,7),"HOT")

    val customException = intercept[CustomException]{
      temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="Remove your PJs first")

  }

  test("commonBusinessLogic_InvalidTemp")
  {

    val temperatureLogic: TemperatureLogic = new TemperatureLogic(List(8,6,3,4,2,5,1,7),"WARM")

    val customException = intercept[CustomException]{
      temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="String is neither 'HOT' or 'Cold")

  }

  test("commonBusinessLogic_EmptyTemp")
  {

    val temperatureLogic: TemperatureLogic = new TemperatureLogic(List(8,6,3,4,2,5,1,7),"")

    val customException = intercept[CustomException]{
      temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="String is neither 'HOT' or 'Cold")

  }

  test("commonBusinessLogic_NullTemp")
  {

    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,3,4,2,5,1,7),null)

    val customException = intercept[CustomException]{
      temperatureLogic.commonBusinessLogic()
    }
    assert(customException.getMessage=="String is neither 'HOT' or 'Cold")

  }

  test("hotLogic_ValidInput")
  {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,4,2,1,7),"HOT")
    temperatureLogic.hotLogic()
  }

  test("coldLogic_ValidInput")
  {
    val temperatureLogic: TemperatureLogic  = generateNewList(List(8,6,3,4,2,5,1,7),"COLD")
    temperatureLogic.coldLogic()

  }
private def generateNewList(inputList: List[Int],temp: String): TemperatureLogic ={
  val temperatureLogic: TemperatureLogic = new TemperatureLogic(inputList,temp)
  return temperatureLogic

  }
}
