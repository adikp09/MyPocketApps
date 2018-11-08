package com.dev.adi.collectapps.traversal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dev.adi.collectapps.R

class TraversalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traversal)
        val a = Node("A")
        val b = Node("B")
        val c = Node("C")
        val d = Node("D")
        val e = Node("E")
        val f = Node("F")

        a.connectToNode(b, 15)
        a.connectToNode(d, 25)
        a.connectToNode(c, 10)
        b.connectToNode(f, 5)
        c.connectToNode(f, 10)
        c.connectToNode(e, 10)
        b.connectToNode(d, 15)

        a.findShortestDistanceVerbose(d)
    }
}
