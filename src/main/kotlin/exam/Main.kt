package exam

fun main() {
  println("every value is given")
  val vocables : List<Vocable> = mutableListOf(
    Vocable("mögen", "aimer"),
    Vocable("schreiben", "ecrire"),
    Vocable("bauen", "construire")
  )
  val trainer = Trainer(vocables, 0.4)
  while(trainer.hasNextRound()) {
    trainer.nextRound()
    val trainerIterator = trainer.iterator()
    while(trainerIterator.hasNext()) {
      val currentVocable : Vocable = trainerIterator.next()
      println("${currentVocable.originalVocable} = ...")
      val input = readLine()!!
      val validationResult = currentVocable.checkTranslation(input)
      trainer.count(validationResult)
      if (validationResult) println("CORRECT")
      else println("WRONG. Correct translation is: ${currentVocable.toBeTranslatedVocable}")
    }
    println("Round ${trainer.round} ended. achieved success rate: ${trainer.countTotal / trainer.countSuccess}")
  }

}