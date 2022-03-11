import io.github.muqhc.xmltoskolloble.xmlToSkolloble

fun main() {
    val _xml = Unit.javaClass.getResourceAsStream("/test.xml")!!.readBytes().decodeToString()
    xmlToSkolloble(_xml).let(::println)
}