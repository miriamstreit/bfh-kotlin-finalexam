package exam

class Vocable (var originalVocable : String, var toBeTranslatedVocable : String) : Comparable<Vocable> {
    var numberOfChecksTotal : Int = 0
    var numberOfChecksSuccessful : Int = 0

    fun checkTranslation(translation : String) : Boolean {
        var isSuccess = false;
        if (translation == toBeTranslatedVocable) isSuccess = true
        if (isSuccess) numberOfChecksSuccessful++
        numberOfChecksTotal++
        return isSuccess
    }

    override fun compareTo(other: Vocable): Int {
        return if ((this.numberOfChecksTotal / this.numberOfChecksSuccessful) > (other.numberOfChecksTotal / other.numberOfChecksSuccessful)) 1
        else if ((this.numberOfChecksTotal / this.numberOfChecksSuccessful) == (other.numberOfChecksTotal / other.numberOfChecksSuccessful)) 0
        else -1
    }
}