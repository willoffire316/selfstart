
/*
* we are going to Create a new Console Credit card Checker and
* a by-product it will produce valid credit card as well
* */
object Runner {

  def main(args: Array[String]): Unit = {
    code(args:Array[String])
  }

  private def code(args: Array[String]) = {

      args.headOption
      .map(creditCard)
      .map(println)
      .getOrElse(runDemo)

  }

  private def runDemo() ={
    println("Running Demo")
    val ValidCard:creditCard.Valid = creditCard()

    println(ValidCard)
    println(ValidCard.isValid)
    println(ValidCard.number)

    val InvalidCard:creditCard = creditCard("123457896547")

    println(InvalidCard)
    println(InvalidCard.isValid)
    println(InvalidCard.number)

    val morefakeNUmbers  = Set ( "4539298434797041","4556829101705913","4916819551565183924","5223492482132809","5294254994603626","5518357704193653","345392459171711","341353256179384","374397469634982","6011843042755733","6011127516276289","6011629022492448556","3528749297680794","3531105953466108","3534448325683390963","5530851901217228","5417274665400526","5494747094974373","30271962815669","30299973055785","30073572558261","36424387843600","36295168236879","36001549261747","5038017631131884","6759536526492890","6304374156834367","4844214616968912","4175004260926285","4844382555222052","6372944317361109","6384160633707943","6378883907003685" )
      .map{creditCard}

    val (validcards,invalidcards) = morefakeNUmbers.partition(_.isValid)

    if(invalidcards.nonEmpty){
      println()
      invalidcards.foreach(println)
    }

    println()
    println("You can also pass the credit card number as a command line argument like this : run 4916819551565183924 ")
  }
}


/*
* why we need to extend the object creditcard by a function string=> CreditCard
* */