package com.dev.adi.collectapps

import java.util.*

internal class Animal {

    private var type: String? = null
    private var age: Int = 0
    private var weight: Int = 0

    fun SetType(_type: String) {
        type = _type
    }

    fun SetAge(_age: Int) {
        age = _age
    }

    fun SetWeight(_weight: Int) {
        weight = _weight
    }

    fun DailyRoutine() {
        // noon
        if (weight < MAX_WEIGHT) {
            weight += INCR_WEIGHT
        } else {
            weight -= DECR_WEIGHT
        }

        if (weight < MIN_WEIGHT) {
            weight = MIN_WEIGHT
        }

        println("Kondisi $type di siang hari --> usia: $age -- berat: $weight")

        //night
        weight--

        if (weight < MIN_WEIGHT) {
            weight = MIN_WEIGHT
        }

        println("Kondisi $type di malam hari --> usia: $age -- berat: $weight")

        age++
    }

    companion object {
        var MAX_WEIGHT = 100
        var MIN_WEIGHT = 10
        var INCR_WEIGHT = 3
        var DECR_WEIGHT = 1
    }
}

internal object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val `in` = Scanner(System.`in`)

        val numberOfAnimal: Int
        val numberOfDay: Int
        var animalAge: Int
        var animalWeight: Int
        var animalType: String
        val animals = ArrayList<Animal>()

        var tmp = `in`.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        numberOfAnimal = Integer.parseInt(tmp[0])
        numberOfDay = Integer.parseInt(tmp[1])

        for (i in 0 until numberOfAnimal) {
            tmp = `in`.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            animalType = tmp[0]
            animalAge = Integer.parseInt(tmp[1])
            animalWeight = Integer.parseInt(tmp[2])

            val tmpAnimal = Animal()
            tmpAnimal.SetType(animalType)
            tmpAnimal.SetAge(animalAge)
            tmpAnimal.SetWeight(animalWeight)

            animals.add(tmpAnimal)
        }

        for (i in 0 until numberOfDay) {
            println("Hari #" + (i + 1))
            for (j in animals.indices) {
                animals[j].DailyRoutine()
            }
        }
    }
}

