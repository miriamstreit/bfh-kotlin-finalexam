package exam

/** Programming with Kotlin,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage
import tornadofx.*

class MainApp : App(MainView::class) {
  override fun start(stage: Stage) {
    stage.width = 300.0
    stage.height = 300.0
    super.start(stage)
  }
}

class MainView : View("Vocable Trainer") {
  var resultMessage = ""
  var resultSuccess = false

  val vocables : List<Vocable> = mutableListOf(
    Vocable("mögen", "aimer"),
    Vocable("schreiben", "ecrire"),
    Vocable("bauen", "construire")
  )

  val trainer = Trainer(vocables, 0.4)
  val trainerIterator = trainer.iterator()

  var resultLabel : Label by singleAssign()

  // todo the gui needs to be initialized on startup too
  override val root = gridpane {
    row {
        label("Vocable Trainer: Target ${trainer.targetSuccessRate*100}%") {
          textFill = Color.BLUE
          font = Font(16.0)
          style = "-fx-font-weight: bold"
        }
    }
    row {
      val successRate = if (trainer.countTotal > 0) trainer.countSuccess / trainer.countTotal else 0
      label("Round ${trainer.round} - ${successRate*100}%") {
        style = "-fx-font-weight: bold"
      }
    }
    row {
      form {
        fieldset {
          field("${trainerIterator.next().originalVocable} =") {
            textfield {
              // todo do validate
              // onKeyTyped = ...
              // check if key code is enter
              // if yes, do
              // val validationResult = currentVocable.checkTranslation(input)
              // trainer.count(validationResult)
              //      if (validationResult) {
//                        resultMessage = "Success"
//                        resultSuccess = true
            //      }
              //      else {
//              resultMessage = "WRONG. Correct translation is: ${currentVocable.toBeTranslatedVocable}"
//              resultSuccess = false
            //      println("WRONG. Correct translation is: ${currentVocable.toBeTranslatedVocable}")
            }
          }
        }
      }
    }
    row {
      resultLabel = label(resultMessage) {
        textFill = if (resultSuccess) Color.GREEN else Color.RED
      }
    }
    row {
      button("Next") {
        action {
          initializeForNewAttempt()
        }
      }
    }
  }


  private fun initializeForNewAttempt() {
    if (trainerIterator.hasNext()) trainerIterator.next()
    resultMessage = ""
  }
}

fun main(args: Array<String>) {
  launch<MainApp>(args)
}