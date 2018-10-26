import org.scalatest._

class creditCardTest  extends FunSuite with Matchers{

    test("Creating a Card without ay number should generate a valid credit card ") {
      creditCard().isValid shouldBe true
      creditCard() shouldBe 'valid
    }

  test("Creating a card without number should generate a valid card type"){
    creditCard().isInstanceOf[creditCard.Valid] shouldBe true
  }

  test("Creating a card Manually , by passing a Valid Number"){
    val validNumber = creditCard().number
    creditCard(validNumber).isValid shouldBe true
    noException should be thrownBy creditCard(validNumber).asInstanceOf[creditCard.Valid]
  }
}
