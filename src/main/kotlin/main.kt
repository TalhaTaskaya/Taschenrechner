
fun addition(formel: String): Boolean {
    return "+" in formel
}
fun subtraktion(formel: String): Boolean {
    return "-" in formel
}
fun multiplikation(formel: String): Boolean {
    return "*" in formel
}
fun division(formel: String): Boolean {
    return "/" in formel
}
fun klammern(formel: String): Boolean {
    return ("(" in formel) && (")" in formel)
}

// Prüft welche Operationen getätigt werden
fun prüfeOperationen(formel: String): String {
    var enthalten = ""
    if (addition(formel))  enthalten += "Die Formel enthaelt Addtition\n"
    if (subtraktion(formel)) enthalten += "Die Formel enthaelt Subtraktion\n"
    if (multiplikation(formel))  enthalten += "Die Formel enthaelt Multiplikation\n"
    if (division(formel))  enthalten += "Die Formel enthaelt Division\n"
    if (klammern(formel)) enthalten += "Die Formel enthaelt Klammern"
    return enthalten
}


// Prüft ob die Formel mit einer Zahl oder Klammer anfängt und aufhört
fun isFormelKorrekt(formel: String): Boolean{
    return formel.first().isDigit() || formel.startsWith("(") && formel.last().isDigit() || formel.endsWith(")")
}

// Prüft wie viele Klammern in der Formel auf gehen
fun anzahlKlammernAuf(formel: String): Int {
    return formel.filter { it == '(' }.count()
}
// Prüft wie viele Klammern in der Formel zu gehen
fun anzahlKlammernZu(formel: String): Int {
    return formel.filter { it == ')' }.count()
}

// Funktion in der alle Komponenten zusammengefügt werden
fun starteProgram() {
    println("Bitte gib hier deine Formel an!")

    // Einlesen der Formel
    val formel = readln()

    // Wenn die Formel eine FORMEL ist weiter machen! Formel fängt mit Zahl oder Klammer an und auf
    if(isFormelKorrekt(formel)){
        // Der Rechner ist im Stande, Punkt-vor-Strich und Klammern zu erkennen. Darüber hinaus kann er die vier Grundoperationen: Multiplikation, Division, Addition, Subtraktion
        println(prüfeOperationen(formel))
        // Gib Anzahl Kammern aus
        if(klammern(formel)){
            // Prüfe ob die Anzahl auf- und zugehender Klammern gleich ist
            if(anzahlKlammernAuf(formel) == anzahlKlammernZu(formel)) println("Die Anzahl der Klammern in dieser Formel betraegt ${anzahlKlammernAuf(formel)}")
            // Bei ungleich -> Neue Formel
            else {
                println("Die Formel hat eine unterschiedliche Anzahl an KLammen, die auf- und zugehen!!")
                println("Rechner neustart!")
                starteProgram()
            }
        }
    }
    else{
        println("Deine Formel faengt nicht mit einer Zahl oder Klammer an unf hoert nicht mit einer Zahl oder Klammer auf!")
        starteProgram()
    }
}

fun main() {
    // Start des Programms
    //starteProgram()
    println("fuer ): ${isFormelKorrekt(")")}")
    println("fuer (: ${isFormelKorrekt("(")}")
    println("fuer 3): ${isFormelKorrekt("3)")}")
    println("fuer )3: ${isFormelKorrekt(")3")}")
    println("fuer (3): ${isFormelKorrekt("(3)")}")

}

//TODO auf regex gruppen prüfen(was ist regex?)



