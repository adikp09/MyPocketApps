package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_graph.*
import java.util.*

class GraphActivity : AppCompatActivity() {

    private val listNode = arrayListOf<String>()
    private val listRoute = arrayListOf<Route>()
    private val listResult = arrayListOf<MutableList<Route>>()
    var nomina = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven")



    val a = Vector<String>()
    val b = LinkedList<String>()

    var hash_Set: Set<String> = HashSet()
    var tree_Set: Set<String> = TreeSet(hash_Set)
    var tree_Sets: SortedSet<String> = TreeSet(hash_Set)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        form_node.visibility = View.GONE
        line_rute.visibility = View.GONE
        line_find_rute.visibility = View.VISIBLE

        listRoute.add(Route("A", "B", 2))
        listRoute.add(Route("A", "C", 4))
        listRoute.add(Route("A", "D", 1))
        listRoute.add(Route("B", "D", 1))
        listRoute.add(Route("B", "F", 2))
        listRoute.add(Route("C", "F", 1))
        listRoute.add(Route("C", "E", 3))
        listRoute.add(Route("E", "F", 2))

        listRoute.add(Route("B", "A", 2))
        listRoute.add(Route("B", "A", 4))
        listRoute.add(Route("D", "A", 1))
        listRoute.add(Route("D", "B", 1))
        listRoute.add(Route("F", "B", 2))
        listRoute.add(Route("F", "C", 1))
        listRoute.add(Route("E", "C", 3))
        listRoute.add(Route("F", "E", 2))


        bt_save_node.setOnClickListener {
            val node = et_node.text.toString().toInt()
            initNode(node)
            form_node.visibility = View.GONE
            line_rute.visibility = View.VISIBLE
        }

        et_start.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) et_end.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        et_end.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) et_weight.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        et_weight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    val from = et_start.text.toString().toUpperCase()
                    val to = et_end.text.toString().toUpperCase()
                    val weight = et_weight.text.toString().toInt()
                    val totalRoute = et_edge.text.toString().toInt()
                    if (from != to) {
                        initRoute(from, to, totalRoute, weight)
                    } else {
                        Toast.makeText(baseContext, "node tidak boleh sama", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        et_find_start.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) et_find_end.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        et_find_end.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    listResult.clear()
                    val from = et_find_start.text.toString().toUpperCase()
                    val to = et_find_end.text.toString().toUpperCase()
                    listResult.clear()
                    if (from != to) {
                        findRoute(from, to)
                    } else {
                        Toast.makeText(baseContext, "node tidak boleh sama", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun initNode(amount: Int) {
        for (i in 0 until amount) {
            val str = namingNode(i % 26, (i / 26)+1)
            listNode.add(str)
        }
        printNode()
    }

    private fun namingNode(index: Int, length: Int): String {
        var name = ""
        for (i in 0 until length) {
            name += Character.toString((index+65).toChar())
        }
        return name
    }

    private fun initRoute(from: String, to: String, totalRoute: Int, weight : Int) {
        if (listNode.contains(from) && listNode.contains(to)){
            if (listRoute.size < totalRoute*2) {
                if (!listRoute.contains(Route(from, to, weight))) {
                    listRoute.add(Route(from, to, weight))
                    listRoute.add(Route(to, from, weight))
                    printRoute()
                    et_start.setText("")
                    et_end.setText("")
                    et_weight.setText("")
                    et_start.requestFocus()
                } else {
                    Toast.makeText(baseContext, "lane has been used", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(baseContext, "the number of routes is full", Toast.LENGTH_SHORT).show()
            }
            if (listRoute.size == totalRoute*2) {
                et_start.visibility = View.GONE
                et_end.visibility = View.GONE
                et_weight.visibility = View.GONE
                textView32.visibility = View.GONE
                textView35.visibility = View.GONE
                line_find_rute.visibility = View.VISIBLE
            }
        } else {
            Toast.makeText(baseContext, "invalid node", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findRoute(from: String, to: String){
        findAvailableRoute(arrayListOf(), from, to)
        printResult()
        et_find_start.setText("")
        et_find_end.setText("")
        et_find_start.requestFocus()
    }

    private fun findAvailableRoute (history : MutableList<Route>, from: String, to: String) {
        val listAvailable = listRoute.filter { it.from == from }
        listAvailable.forEachIndexed { _, route ->
            val historyCopy = arrayListOf<Route>()
            historyCopy.addAll(history)

            val checkHistory = history.filter { ((route.to == it.to && route.from == it.from) || (route.to == it.from)) }
            if (!checkHistory.isNotEmpty()) {
                historyCopy.add(Route(route.from, route.to, route.weight))
                if (to == route.to) {
                    listResult.add(historyCopy)
                }
                else {
                    findAvailableRoute(historyCopy, route.to, to)
                }
            }
        }
    }

    private fun findMaxRoute (listRoute: MutableList<Route>): Int {
        var maxWight = 0
        var result = 0
        listRoute.forEachIndexed { _, route ->
            if (maxWight > route.weight) {
                maxWight = route.weight
                result = maxWight
            }
        }
        return result
    }

    private fun printNode() {
        tv_node.text = ""
        tv_node.append("Node: ")
        listNode.forEachIndexed { index, node ->
            tv_node.append(if (index == 0) node else " - $node")
        }
    }

    private fun printRoute() {
        tv_result.text = ""
        tv_result.append("Route: \n")
        listRoute.forEachIndexed { index, route ->
            tv_result.append("${index+1}. ${route.from} - ${route.to} weight = ${route.weight}\n")
        }
    }

    private fun printResult () {
        var weightSum = 0
        var tempMax = Integer.MIN_VALUE
        var tempMin = Integer.MAX_VALUE
        var indexRouteMax = 0
        var indexRouteMin = 0
        tv_route_result.append("\nAvailable Route from ${et_find_start.text} to ${et_find_end.text}: \n")
        listResult.forEachIndexed { index, data ->
            tv_route_result.append("${index+1}. ")
            weightSum = 0
            data.mapIndexed { _, route ->
                weightSum += route.weight
                tv_route_result.append(if (index == 0) "${route.from} - ${route.to}, " else "${route.from} - ${route.to}")
            }
            tv_route_result.append(" = $weightSum")
            tv_route_result.append("\n")
            if (tempMax < weightSum) {
                tempMax = weightSum
                indexRouteMax = index
            }

            if (tempMin > weightSum) {
                tempMin = weightSum
                indexRouteMin = index
            }
        }
        tv_route_result.append("\n-> Min weight on route ${bilangx((indexRouteMin+1).toDouble())}")
        tv_route_result.append("\n-> Max weight on route ${bilangx((indexRouteMax+1).toDouble())}")
    }

    fun bilangx(angka: Double): String {
        if (angka < 12) {
            return nomina[angka.toInt()]
        }

        if (angka in 12.0..19.0) {
            return nomina[angka.toInt() % 10] + " belas "
        }

        if (angka in 20.0..99.0) {
            return nomina[angka.toInt() / 10] + " puluh " + nomina[angka.toInt() % 10]
        }

        if (angka in 100.0..199.0) {
            return "seratus " + bilangx(angka % 100)
        }

        if (angka in 200.0..999.0) {
            return nomina[angka.toInt() / 100] + " ratus " + bilangx(angka % 100)
        }

        if (angka in 1000.0..1999.0) {
            return "seribu " + bilangx(angka % 1000)
        }

        if (angka in 2000.0..999999.0) {
            return bilangx((angka.toInt() / 1000).toDouble()) + " ribu " + bilangx(angka % 1000)
        }

        return if (angka in 1000000.0..9.99999999E8) {
            bilangx((angka.toInt() / 1000000).toDouble()) + " juta " + bilangx(angka % 1000000)
        } else ""

    }
}