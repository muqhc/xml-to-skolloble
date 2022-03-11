import io.github.muqhc.xmltoskolloble.xmlToSkolloble

fun main() {
    val _xml = """
                <style>
                    #texttest {
                        width:600px;
                        height:700px;
                        background:white;
                    }
                    #textlevel {
                        width:600px;
                        height:700px;
                        background:rgb(180,180,180);
                    }
                    .button {
                        width:200px;
                    }
                    #timer {
                        width:200px;  
                    }
                </style>
    """.trimIndent()
    xmlToSkolloble(_xml).let(::println)
}