import kotlin.math.abs
import kotlin.math.min
import kotlin.math.round

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

fun isOperatorIn(formel: String):Boolean{
    return isMultiplikation(formel) || isAddition(formel) ||
            isDivision(formel) || isSubtraktion(formel)
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

// Prüft in der Formel die passenden indices der Klammern.
fun teileFormelInKlammern(formel: String):String {
    var ret = ""
    if(formel.contains("(")) {
        // In diesen Listen werden alle Indices der Klammern gespeichert.
        var subAuf = MutableList(anzahlKlammernAuf(formel)) { 0 }
        var subZU = MutableList(anzahlKlammernZu(formel)) { 0 }
        var sub = MutableList<String>(0) { "" }
        // für die Indizes in den Listen -> um Arrayoutofbound zu vermeiden.
        var pointerA = 0
        var pointerB = 0
        // Prüfung der indices
        for (i in formel.indices) {
            if (formel[i] == '(') {
                subAuf[pointerA] = i
                pointerA++
            }
            if (formel[i] == ')') {
                subZU[pointerB] = i
                pointerB++
            }
        }
        var lauf = 0
        while (lauf < anzahlKlammernAuf(formel)) {
            sub.add(
                formel.substring(
                    subAuf[lauf] + 1,
                    subZU[lauf]
                )
            )
            if ('(' in sub[lauf]) {
                sub[lauf] = formel.substring(
                    subAuf[lauf] + 1,
                    subZU[lauf + 1]
                )
                sub.add(
                    formel.substring(
                        subAuf[lauf + 1] + 1,
                        subZU[lauf]
                    )
                )
                ret += "${sub[lauf]} "
                lauf++
            }
            ret += "${sub[lauf]} "
            lauf++
        }
    }else{
        ret = formel
    }
    println(ret)
    return ret
}


fun berechneKlammern(formel: String) {
    var teil = formel.split(" ")
    var sub = ""
    var sub2 = List(0){""}
    var sub3 = 0
    for (i in teil.indices) {
        if (isKlammern(teil[i])) {
            sub = teil[i+1]
            when{
                isAddition(sub) -> {
                    sub2 = sub.split("+")
                    sub3 = sub2[0].toInt() + sub2[1].toInt()
                }
                isMultiplikation(sub) -> {
                    sub2 = sub.split("*")
                    sub3 = sub2[0].toInt() * sub2[1].toInt()
                }
                isSubtraktion(sub) -> {
                    sub2 = sub.split("-")
                    sub3 = sub2[0].toInt() - sub2[1].toInt()
                }
                isDivision(sub) -> {
                    sub2 = sub.split("/")
                    sub3 = round((sub2[0].toInt() / sub2[1].toInt()).toDouble()).toInt()
                }
            }
            continue
        }
    }
}

fun berechneFormel(formel: String): String {
    var teil = formel

    if(isMultiplikation(formel)){
        teil += teil.split("*").toString()
    }
    return teil
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

                berechneKlammern(teileFormelInKlammern(formel))
            }
            // Bei ungleich -> Neue Formel
            else {
                println("Die Formel hat eine unterschiedliche Anzahl an KLammen, die auf- und zugehen!!")
                println("Rechner neustart!")
                starteProgram()
            }
        }
        else{
            berechneFormel(formel)
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
    //var formel = "((13+3)+3)+4/(4+31)"
    var formel = "3*3+(2-3) 2-3"
    println(when(formel){
        in "*" -> "M"
        in "+" -> "A"
        in "/" -> "D"
        in "-" -> "S"
        else -> {"L"}
    })
}