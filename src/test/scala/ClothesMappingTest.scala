import org.scalatest.FunSuite
/*
Test class for checking that mapping has not changed
 */
class ClothesMappingTest extends FunSuite {

  var testValidCommandInputListHot: List[Int] = List(1,2,3,4,5,6,7,8)
  val clothesMapping: ClothesMapping = new ClothesMapping(testValidCommandInputListHot)


  //1
  test("testHotMap_sandals") {
    assert(clothesMapping.hotMap(1) == "sandals")
  }

  //2
  test("testHotMap_sunglasses") {
    assert(clothesMapping.hotMap(2) == "sunglasses")

  }

  //3=Fail
  test("testHotMap_socks") {
    assert(clothesMapping.hotMap(3) == "fail")

  }

  //4
  test("testHotMap_shirt") {
    assert(clothesMapping.hotMap(4) == "shirt")

  }

  //5
  test("testHotMap_jacket") {
    assert(clothesMapping.hotMap(5) == "fail")

  }

  //6
  test("testHotMap_shorts") {
    assert(clothesMapping.hotMap(6) == "shorts")

  }

  //7
  test("testHotMap_leavingHouse") {
    assert(clothesMapping.hotMap(7) == "leaving house")

  }

  //8
  test("testHotMap_RemovingPJs") {
    assert(clothesMapping.hotMap(8) == "Removing PJs")

  }

  test("testColdMap_boots") {
    assert(clothesMapping.coldMap(1) == "boots")

  }

  test("testColdMap_hat") {
    assert(clothesMapping.coldMap(2) == "hat")

  }

  test("testColdMap_socks") {
    assert(clothesMapping.coldMap(3) == "socks")

  }

  test("testColdMap_shirt") {
    assert(clothesMapping.coldMap(4) == "shirt")

  }

  test("testColdMap_jacket") {
    assert(clothesMapping.coldMap(5) == "jacket")

  }

  test("testColdMap_pants") {
    assert(clothesMapping.coldMap(6) == "pants")

  }

  //7
  test("testColdMap_leavingHouse") {
    assert(clothesMapping.coldMap(7) == "leaving house")

  }
  //8
  test("testColdMap_Removing PJs") {
    assert(clothesMapping.coldMap(8) == "Removing PJs")

  }

}
