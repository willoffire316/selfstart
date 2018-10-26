import creditCard.Valid

sealed trait creditCard {
  def number:String
  final def isValid:Boolean ={
     isInstanceOf[Valid]
  }

  final def isNotValid:Boolean = {
    !isValid
  }

  final override def toString: String = {
    if(isNotValid){
      val invalid = Console.RED + "invalid" + Console.RESET
      s"""$invalid creditcard  number "$number" """
    }else{
      val valid = Console.GREEN + "Valid" + Console.RESET
      val (payload,checkDigit) = creditCard.split(number)
      s"""$valid creditcard  number "$number" with payload "$payload" and check Digit "$checkDigit" """
    }
  }

}

object creditCard extends (String => creditCard ){

  private val MinimumLength = 13
  private val MaximumLength = 19

  object Invalid {
    private[creditCard] def apply(number:String):Invalid = {
      new Invalid(number)
    }
  }

  object Valid {
    private[creditCard] def apply(number:String):Valid = {
      new Valid(number)
    }
  }

  final case class Invalid private (number:String) extends creditCard
  final case class   Valid private (number:String) extends creditCard

  def apply(number:String) : creditCard = {
    if(isValid(number))
      Valid(number)
    else
      Invalid(number)
  }

  def apply():Valid = {
    Valid(generatedNumber)
  }

  private def generatedNumber= {

    val payload = {
      import scala.util.Random
      val min:Int = MinimumLength - 1
      val max:Int = MaximumLength - 1
      val length = min + Random.nextInt((max - min) + 1)

      def randomDigit:Int = Random.nextInt(10)

      (1 to length).map{ _ => randomDigit }.mkString
    }
    val checkDigit = (10 - ( luhn(payload) % 10 ) ) % 10

    val number  =payload + checkDigit
    if (isValid(number))
      number
    else
    //$COVERAGE-OFF$
      sys.error("generated Invalid Number")
    //$COVERAGE-ON$
  }

  private def isValid(number: String):Boolean = {
    number != null &&
    number.nonEmpty &&
    number.forall(Character.isDigit) &&
      (MinimumLength to MaximumLength ).contains(number.length) &&
    doesMathCheckout(number)
  }

  private def doesMathCheckout(number: String):Boolean = {
    val(payload,checkDigit) = split(number)
    val sum = luhn(payload) + checkDigit

    sum % 10 == 0
  }

  private def luhn(payload:String):Int ={
    payload.reverse.map(_.toString.toInt)
      .zipWithIndex // IndexedSeq[(Int=digit,Int=Index)]
      .map{
      case(digit,index) => if(digit % 2 == 0) digit * 2 else digit
    }.map{
      number => if(number > 9 ) number -9 else number
    }.sum
  }

  private def split(number:String) :(String,Int)= {
    val payload = number.dropRight(1)
    val checkDigit = number.takeRight(1).toInt

    //(payload,checkDigit)
    payload -> checkDigit
  }
}
