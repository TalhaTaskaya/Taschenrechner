fun isAddition(formel: String): Boolean {
    return "+" in formel
}
fun isSubtraktion(formel: String): Boolean {
    return "-" in formel
}
fun isMultiplikation(formel: String): Boolean {
    return "*" in formel
}
fun isDivision(formel: String): Boolean {
    return "/" in formel
}
fun isKlammern(formel: String): Boolean {
    return ("(" in formel) && (")" in formel)
}
// Prüft welche Operationen getätigt werden
fun prüfeOperationen(formel: String): String {
    var enthalten = ""
    if (isAddition(formel))  enthalten += "Die Formel enthaelt Addtition\n"
    if (isSubtraktion(formel)) enthalten += "Die Formel enthaelt Subtraktion\n"
    if (isMultiplikation(formel))  enthalten += "Die Formel enthaelt Multiplikation\n"
    if (isDivision(formel))  enthalten += "Die Formel enthaelt Division\n"
    if (isKlammern(formel)) enthalten += "Die Formel enthaelt Klammern"
    return enthalten
}
// Prüft ob die Formel mit einer Zahl oder Klammer anfängt und aufhört und mindestens 3 Zeichen
fun isFormelKorrekt(formel: String): Boolean{
    return (formel.first().isDigit() || formel.startsWith("(")) && (formel.last().isDigit() || formel.endsWith(")")) && formel.length >= 3
}
// Prüft wie viele Klammern in der Formel auf gehen
fun anzahlKlammernAuf(formel: String): Int {
    return formel.filter { it == '(' }.count()
}
// Prüft wie viele Klammern in der Formel zu gehen
fun anzahlKlammernZu(formel: String): Int {
    return formel.filter { it == ')' }.count()
}
// Teilt die Formel in die einzelnen Komponenten durch prüfen wo die erste Klammer schließt und kommt dann zurück -> ersetzt das in substring

// TODO
fun teileFormelInKlammern(formel: String) {
    if(formel.contains("(")){
        var temp = formel
        var subAuf = MutableList<Any>(0){}
        var subZU = MutableList<Any>(0){}
        while('(' in temp || ')' in temp){
            subAuf.add(temp.indexOf("("))
            subZU.add(temp.indexOf(")"))
            temp = temp.replace("(","")
            temp = temp.replace(")","")
            println(temp)
            println(subAuf)
            println(subZU)
        }

    }else{

    }
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
        if(isKlammern(formel)){
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
        println("Entweder zu kleiner Formel oder am Anfang/Ende sind falsche Werte")
        starteProgram()
    }
}
fun main() {
    // Start des Programms
    //starteProgram()
    // ((13+3)+3)+4/(4+31)
    // (  x1  + 3) + 4/(4+31)
    //    x2   + 4 / ( 4+31)
    // x2 + 4 / x3
    var a = "((13+3)+3)+4/(4+31)"
    println(a.replace("(", ""))
}




