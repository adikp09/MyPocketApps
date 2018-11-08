package com.dev.adi.collectapps.traversal

class Node (val name: String) {
    var segments: MutableList<Segment> = mutableListOf()
}

class Segment(val node: Node, val distance: Int) {
    var calculatedDistFromSource: Int = 0
}