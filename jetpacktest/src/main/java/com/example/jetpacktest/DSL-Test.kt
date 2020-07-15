package com.example.jetpacktest

/**
 * FileName: `DSL-Test`
 * Author: Naomi
 * Date: 2020/7/16 1:42
 * Description:

 */
class Td {
    var content = ""

    fun html() = "\n\t\t<td>$content</td>"
}

class Tr {
    private val children = ArrayList<Td>()

    fun td(block: Td.() -> String) {
        val td = Td()
        td.content = td.block()
        children.add(td)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (childTag in children) {
            builder.append(childTag.html())
        }
        builder.append("\n\t</tr>")
        return builder.toString()
    }
}

class Table {
    private val children = ArrayList<Tr>()

    fun tr(block: Tr.() -> Unit) {
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.append("\n<table>")
        for (childTag in children) {
            builder.append(childTag.html())
        }
        builder.append("\n</table>")
        return builder.toString()
    }
}

fun table(block: Table.() -> Unit): String {
    val table = Table()
    table.block()
    return table.html()
}

fun main() {
    val html1 = table {
        tr {
            td { "melon" }
            td { "apple" }
            td { "orange" }
            td { "grape" }
        }
        tr {
            td { "banana" }
            td { "pear" }
            td { "cherry" }
            td { "mango" }
        }
    }

    val html2 = table {
        repeat(2) {
            tr {
                val fruits = listOf("Apple", "Melon", "Orange")
                for (fruit in fruits)
                    td { fruit }
            }
        }
    }

    println(html2)
}