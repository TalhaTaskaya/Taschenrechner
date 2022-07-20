import kotlin.math.abs
import kotlin.math.min

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
// Berechne kleinste Abständen von Listen Werten -- soll nur genutzt werden, wenn listAuf und listZu gleich groß sind.
fun kleinsterAbstandVonIndices(listAuf:MutableList<Int>, listZu:MutableList<Int>): MutableList<MutableList<Int>> {
    // Liste mit den Abständen der AUF Klammern zu den ZU Klammern
    // temp[0] entspricht der ersten AUFGEHENDEN Klammer, die WERTE drinne sind die ABSTÄNDE zu den schließenden Klammern!!
    var temp = MutableList(listAuf.size){MutableList(listAuf.size){0}}
    for (i in listAuf.indices){
        for (j in listZu.indices){
            if(listAuf[i] - listZu[j] < 0)
                temp[i][j] = listAuf[i] - listZu[j]
        }
    }
    return temp
}

// Prüft in der Formel die passenden indices der Klammern.
fun teileFormelInKlammern(formel: String) {
    if(formel.contains("(")){
        var temp = formel
        var map = mutableMapOf<Int, Int>()
        // In diesen Listen werden alle Indices der Klammern gespeichert.
        var subAuf = MutableList(anzahlKlammernAuf(formel)){0}
        var subZU = MutableList(anzahlKlammernZu(formel)){0}
        // für die Indizes in den Listen -> um Arrayoutofbound zu vermeiden.
        var pointerA = 0
        var pointerB = 0
        // Prüfung der indices
        for(i in formel.indices){
            if(formel[i] == '('){
                subAuf[pointerA] = i
                pointerA++
            }
            if(formel[i] == ')'){
                subZU[pointerB] = i
                pointerB++
                if(pointerA > pointerB){

                }
            }
        }
        // PRINTLN ANWEISUNG ÄNDERN ------------------------------------  MAP ALS NEUE IDEE

        // TODO
        println(map)
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
        // Gib Anzahl Klammern aus
        if(isKlammern(formel)){
            // Prüfe ob die Anzahl auf- und zugehender Klammern gleich ist
            if(anzahlKlammernAuf(formel) == anzahlKlammernZu(formel)){
                println("Die Anzahl der Klammern in dieser Formel betraegt ${anzahlKlammernAuf(formel)}")
                // AB HIER WEITER
            }
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
    var formel = "((13+3)+3)+4/(4+31)"
    teileFormelInKlammern(formel)
}