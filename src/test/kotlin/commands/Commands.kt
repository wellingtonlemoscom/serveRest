package commands

class Commands {

    fun getRandomString(length: Int): String {
        val word = ('a'..'z')
        return (1..length).map { word.random() }.joinToString("")
    }
}