package com.dev.adi.collectapps.traversal

fun Node.connectToNode(node: Node, weight: Int) {
    // add link for each direction
    var segment = Segment(node, weight)
    addSegment(segment)

    segment = Segment(this, weight)
    node.addSegment(segment)

    println(this.name + " <---> " + node.name + " (" + weight + ")")
}

fun Node.connectDirectlyToNode(node: Node, weight: Int) {
    val segment = Segment(node, weight)
    addSegment(segment)

    println(this.name + " ---> " + node.name + " (" + weight + ")")
}

fun Node.addSegment(segment: Segment) {
    segments.add(segment)
}

fun Node.findShortestDistanceVerbose(destinationNode: Node): Int {
    val distance = findShortestDistance(destinationNode);
    println("Distance " + this.name + " ---> " + destinationNode.name + " = " + distance)
    return distance
}

fun Node.findShortestDistance(destinationNode: Node): Int {
    // keep track of the distance from source to each node, add self
    var distancesToNodes: MutableMap<Node, Int> = mutableMapOf()
    distancesToNodes[this] = 0

    // maintain a list of segments that still should be explored
    var segmentsToExplore: MutableList<Segment> = mutableListOf()
    segmentsToExplore.addAll(this.segments.toMutableList())

    // maintain a list of segments that have already been explored
    var segmentsExplored: MutableList<Segment> = mutableListOf()

    while (segmentsToExplore.size > 0) {
        // find the shortest distance from source node to next node
        var segToTest: Segment = segmentsToExplore.minBy { it.calculatedDistFromSource + it.distance }!!

        // check if the selected segment is the shortest path so far to the node it leads to
        val testNodeDistance = segToTest.calculatedDistFromSource + segToTest.distance
        if (!distancesToNodes.containsKey(segToTest.node) ||
                distancesToNodes[segToTest.node]!! > testNodeDistance) {
            distancesToNodes[segToTest.node] = testNodeDistance

            // if this is our destination, might as well bail
            if (segToTest.node == destinationNode) {
                break
            }
        }

        // add the new node's segments to the needs to be explored list
        var segmentList = segToTest.node.segments.toMutableList()

        // update each segment with the distance from the source node
        segmentList.forEach { it -> it.calculatedDistFromSource = testNodeDistance }
        segmentsToExplore.addAll(segmentList)

        // mark the current segment as explored
        segmentsExplored.add(segToTest)

        // remove all explored segments from segments to explore
        segmentsToExplore.removeAll(segmentsExplored)
    }

    // reset temporary values
    segmentsExplored.forEach {
        it -> it.calculatedDistFromSource = 0
    }

    // we now have the distance to every reachable node from the source
    return if (distancesToNodes.containsKey(destinationNode)) {
        val distance = distancesToNodes[destinationNode]!!
        distance
    } else {
        -1
    }
}