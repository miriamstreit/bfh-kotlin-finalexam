package exam

open class Trainer(val vocables : List<Vocable>, val targetSuccessRate : Double) : Iterable<Vocable> {
    var countTotal = 0
    var countSuccess = 0
    var round = 0

    open fun mixUpOrder() : List<Vocable> {
        return vocables
    }

    fun count(isSuccess : Boolean) {
        countTotal++
        if (isSuccess) countSuccess++
    }

    fun hasNextRound(): Boolean {
        return round <= 5
    }

    fun nextRound() {
        if (hasNextRound()) {
            round++
            countTotal = 0
            countSuccess = 0
        }
    }

    override fun iterator(): Iterator<Vocable> {
        return mixUpOrder().iterator()
    }

}

class WorstFirstTrainer(vocables : List<Vocable>, targetSuccessRate : Double) : Trainer(vocables, targetSuccessRate) {
    override fun mixUpOrder() : List<Vocable> {
        return vocables.sortedBy { (it.numberOfChecksTotal / it.numberOfChecksSuccessful) }
    }
}

class RandomTrainer(vocables : List<Vocable>, targetSuccessRate : Double) : Trainer(vocables, targetSuccessRate) {
    override fun mixUpOrder() : List<Vocable> {
        return vocables.shuffled()
    }
}