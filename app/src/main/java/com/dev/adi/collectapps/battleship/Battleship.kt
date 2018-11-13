package com.dev.adi.collectapps.battleship

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Log.e
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.dev.adi.collectapps.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_battleship.*

class Battleship : AppCompatActivity() {

    private var arrLineParentP1 = arrayOfNulls<MutableList<LinearLayout>>(10)
    private var arrLineTextShipP1 = arrayOfNulls<MutableList<TextView>>(10)
    private var arrLineParentP2 = arrayOfNulls<MutableList<LinearLayout>>(10)
    private var arrLineTextShipP2 = arrayOfNulls<MutableList<TextView>>(10)
    private var tempStokShip = arrayListOf<ItemShip>()
    private var tempInitShipP1 = arrayListOf<ItemShip>()
    private var tempInitShipP2 = arrayListOf<ItemShip>()
    private var orientation = 0
    private var type = 5
    private val bomP1 = arrayListOf<Coordinate>()
    private val bomP2 = arrayListOf<Coordinate>()
    private var playerTurn = 1
    private var isSalvoMode = false
    private var salvoCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battleship)

        tempStokShip.add(ItemShip(id = 1, stock = 1, quota = 5, name = "Carrier"))
        tempStokShip.add(ItemShip(id = 2, stock = 1, quota = 4, name = "Battleship"))
        tempStokShip.add(ItemShip(id = 3, stock = 1, quota = 3, name = "Destroyer"))
        tempStokShip.add(ItemShip(id = 4, stock = 1, quota = 3, name = "Submarine"))
        tempStokShip.add(ItemShip(id = 5, stock = 1, quota = 2, name = "Patrol Boat"))

        var y = 0
        var x: Int
        val arrHurf = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
        val arrAngka = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        textView14.text = "P1"

        x = 0
        val lineParentInit = LinearLayout(this)
        lineParentInit.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lineParentInit.orientation = LinearLayout.HORIZONTAL
        while (x < arrAngka.size) {

            val lineChild = LinearLayout(this)
            val params = LinearLayout.LayoutParams(80, 80)
            params.setMargins(2, 2, 2, 2)
            lineChild.layoutParams = params
            lineChild.orientation = LinearLayout.VERTICAL
            lineChild.gravity = Gravity.CENTER_VERTICAL
            val border = GradientDrawable()
            border.setColor(-0x1) //white background
            border.setStroke(1, -0x1000000) //black border with full opacity
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                lineChild.setBackgroundDrawable(border)
            } else {
                lineChild.background = border
            }
            lineChild.setPadding(5, 5, 5, 5)

            val text2 = TextView(this)
            text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            text2.textSize = 12F
            text2.text = if (x > 0) arrAngka[x].toString() else ""
            text2.gravity = Gravity.CENTER
            lineChild.addView(text2)

            lineParentInit.addView(lineChild)

            x++
        }
        linearLayout4.addView(lineParentInit)

        while (y < 10) {
            val lineParent = LinearLayout(this)
            lineParent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lineParent.orientation = LinearLayout.HORIZONTAL

            val arrLineParent = arrayListOf<LinearLayout>()
            val arrTextShip = arrayListOf<TextView>()

            val lineInit = LinearLayout(this)
            val params = LinearLayout.LayoutParams(80, 80)
            params.setMargins(2, 2, 2, 2)
            lineInit.layoutParams = params
            lineInit.orientation = LinearLayout.VERTICAL
            lineInit.gravity = Gravity.CENTER_VERTICAL
            val borderInit = GradientDrawable()
            borderInit.setColor(-0x1) //white background
            borderInit.setStroke(1, -0x1000000) //black border with full opacity
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                lineInit.setBackgroundDrawable(borderInit)
            } else {
                lineInit.background = borderInit
            }
            lineInit.setPadding(5, 5, 5, 5)

            val textInit = TextView(this)
            textInit.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            textInit.textSize = 12F
            textInit.text = arrHurf[y]
            textInit.gravity = Gravity.CENTER
            lineInit.addView(textInit)

            lineParent.addView(lineInit)

            x = 0
            while (x < 10) {

                val lineChild = LinearLayout(this)
                val params = LinearLayout.LayoutParams(80, 80)
                params.setMargins(2, 2, 2, 2)
                lineChild.layoutParams = params
                lineChild.orientation = LinearLayout.VERTICAL
                lineChild.gravity = Gravity.CENTER_VERTICAL
                val border = GradientDrawable()
                border.setColor(-0x1) //white background
                border.setStroke(1, -0x1000000) //black border with full opacity
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    lineChild.setBackgroundDrawable(border)
                } else {
                    lineChild.background = border
                }
                lineChild.setPadding(5, 5, 5, 5)

                val text2 = TextView(this)
                text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                text2.textSize = 12F
                text2.gravity = Gravity.CENTER
                lineChild.addView(text2)
                arrTextShip.add(text2)

                arrLineParent.add(lineChild)
                lineParent.addView(lineChild)

                x++
            }

            arrLineParentP1[y] = arrLineParent
            arrLineParentP2[y] = arrLineParent
            arrLineTextShipP1[y] = arrTextShip
            arrLineTextShipP2[y] = arrTextShip

            linearLayout4?.addView(lineParent)
            y++
        }
        button6.setOnClickListener {
            when (playerTurn) {
                1 -> {
                    choosePlayer(tempStokShip, playerTurn)
                }
                2 -> {
                    choosePlayer(tempStokShip, playerTurn)
                }
            }
        }
        button10.setOnClickListener {
//            when (playerTurn) {
//                1 -> {
//                    resetArr()
//                    reset()
//                    playerTurn = 2
//                    orientation = 0
//                    type = 5
//                    textView14.text = "P2"
//                }
//                2 -> {
//                    reset()
//                    resetArr()
//                    addingEventBom()
//                    button6.visibility = View.GONE
//                    button10.visibility = View.GONE
//                    playerTurn = 1
//                    textView13.text = "BATTLE BEGIN"
//                    textView14.text = "Player $playerTurn is playing"
//                }
//            }
            Log.e("mem", Gson().toJson(getColumn()))
        }

        button11.setOnClickListener {
            reset()
            playerTurn = if (playerTurn == 1) {
                Toast.makeText(this, "Turn player $playerTurn shoot", Toast.LENGTH_SHORT).show()
                textView14.text = "Turn player $playerTurn shoot"
                2
            } else {
                Toast.makeText(this, "Turn player $playerTurn shoot", Toast.LENGTH_SHORT).show()
                textView14.text = "Turn player $playerTurn shoot"
                1
            }
            renderBom()
            addingEventBom()
        }

        textView13.setOnClickListener {
            setCheat()
        }
    }

    private fun getColumn () : MutableList<Int> {
        var hint = arrayListOf<Int>()

        var i = 0
        while (i < 10) {
            if (playerTurn == 1) {
                hint.add(getObsByColumn(tempInitShipP2, i))
            } else {
                hint.add(getObsByColumn(tempInitShipP1, i))
            }
            i++
        }
        return hint
    }

    private fun getObsByColumn (listShip : MutableList<ItemShip>, x: Int) : Int {
        var count = 0
        var i: Int
        listShip.forEachIndexed { _, itemShip ->
            if (itemShip.orientation == 1) {
                i = itemShip.x?:0
                while (i < (itemShip.x?:0) + itemShip.quota) {
                    if (i == x) {
                        count ++
                    }
                    i++
                }
            } else {
                i = itemShip.y?:0
                while (i < (itemShip.x?:0) + itemShip.quota) {
                    count ++
                }
                i++
            }
        }
        return count
    }

    private fun getObsByRow () {
        Log.e("memem2", Gson().toJson(tempInitShipP1))
    }

    private fun choosePlayer (stock: ArrayList<ItemShip>, turn : Int) {
        val listItems = arrayOf("Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat")
        val mBuilder = android.support.v7.app.AlertDialog.Builder(this@Battleship)
        mBuilder.setTitle("Choose an ship")
        mBuilder.setItems(listItems
        ) { _, which ->
            when {
                listItems[which] == "Carrier" -> {
                    if (stock[0].stock > 0) {
                        showDialogDirection(activity = this, type = 0, message = "Direction?", player = turn)
                    } else {
                        Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
                    }
                }
                listItems[which] == "Battleship" -> {
                    if (stock[1].stock > 0) {
                        showDialogDirection(activity = this, type = 1, message = "Direction?", player = turn)
                    } else {
                        Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
                    }
                }
                listItems[which] == "Destroyer" -> {
                    if (stock[2].stock > 0) {
                        showDialogDirection(activity = this, type = 2, message = "Direction?", player = turn)
                    } else {
                        Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
                    }
                }
                listItems[which] == "Submarine" -> {
                    if (stock[3].stock > 0) {
                        showDialogDirection(activity = this, type = 3, message = "Direction?", player = turn)
                    } else {
                        Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
                    }
                }
                listItems[which] == "Patrol Boat" -> {
                    if (stock[4].stock > 0) {
                        showDialogDirection(activity = this, type = 4, message = "Direction?", player = turn)
                    } else {
                        Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun showDialogDirection(activity: Activity, title: String? = null, message: CharSequence, type: Int, player: Int) {
        val builder = AlertDialog.Builder(activity)

        if (title != null) builder.setTitle(title)

        builder.setMessage(message)
        builder.setPositiveButton("Horizontal") { dialog, id ->
            createShipBattle()
            this.type = type; this.orientation = 1
            dialog.dismiss()
        }
        builder.setNegativeButton("Vertical") { dialog, id ->
            createShipBattle()
            this.type = type; this.orientation = 2
            dialog.dismiss()
        }
        builder.show()
    }

    private fun initShip(x: Int, y: Int) {
        if (playerTurn == 1) {
            singleInit(tempInitShipP1, tempStokShip, orientation, x, y)
        } else {
            singleInit(tempInitShipP2, tempStokShip, orientation, x, y)
        }
        reRender()
        disableButton()
    }

    private fun singleInit (tempInitShip : ArrayList<ItemShip>, stok : ArrayList<ItemShip>, direction : Int, x: Int, y: Int) {
        if ((x + stok[type].quota - 1 < 10 && direction == 1) || (y + stok[type].quota - 1 < 10 && direction == 2)) {
            if (stok[type].stock > 0) {
                var isValid = true
                tempInitShip.mapIndexed { index, itemShip ->
                    if (itemShip.orientation == 2) {
                        if (y in (itemShip.y ?: 0)..itemShip.quota + (itemShip.y
                                        ?: 0) && x == itemShip.x ?: 0) isValid = false
                    } else if (itemShip.orientation == 1) {
                        if (x in (itemShip.x ?: 0)..itemShip.quota + (itemShip.x
                                        ?: 0) && y == itemShip.y ?: 0) isValid = false
                    }
                }
                if (isValid) {
                    tempInitShip.add(ItemShip(id = type,
                            stock = 0, name = stok[type].name,
                            quota = stok[type].quota, x = x, y = y, orientation = direction))
                    stok[type] = (ItemShip(id = type,
                            stock = 0, name = stok[type].name,
                            quota = stok[type].quota))
                } else {
                    Toast.makeText(this, "Can not crossing", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Already in battlefield", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Out Of BattleField", Toast.LENGTH_SHORT).show()
        }
    }

    private fun disableButton() {
        if (playerTurn == 1) {
            arrLineParentP1.mapIndexed { index, mutableList ->
                mutableList!!.map {
                    it.setOnClickListener { }
                }
            }
        } else {
            arrLineParentP2.mapIndexed { index, mutableList ->
                mutableList!!.map {
                    it.setOnClickListener { }
                }
            }
        }
    }

    private fun createShipBattle() {
        if (playerTurn == 1) {
            arrLineParentP1.mapIndexed { indexY, mutableList ->
                mutableList!!.mapIndexed { indexX, linearLayout ->
                    linearLayout.setOnClickListener {
                        initShip(indexX, indexY)
                    }
                }
            }
        } else {
            arrLineParentP2.mapIndexed { indexY, mutableList ->
                mutableList!!.mapIndexed { indexX, linearLayout ->
                    linearLayout.setOnClickListener {
                        e("im clicked", "hello")
                        initShip(indexX, indexY)
                    }
                }
            }
        }
    }

    private fun reRender() {
        if (playerTurn == 1) {
            tempInitShipP1.mapIndexed { index, itemShip ->
                if (itemShip.orientation == 1) {
                    var i = itemShip.x ?: 0
                    while (i < (itemShip.x ?: 0) + itemShip.quota) {
                        arrLineTextShipP1[itemShip.y ?: 0]!![i].text = itemShip.initial
                        i++
                    }
                } else {
                    var i = itemShip.y ?: 0
                    while (i < (itemShip.y ?: 0) + itemShip.quota) {
                        arrLineTextShipP1[i]!![itemShip.x ?: 0].text = itemShip.initial
                        i++
                    }
                }
            }
        } else {
            tempInitShipP2.mapIndexed { index, itemShip ->
                if (itemShip.orientation == 1) {
                    var i = itemShip.x ?: 0
                    while (i < (itemShip.x ?: 0) + itemShip.quota) {
                        arrLineTextShipP2[itemShip.y ?: 0]!![i].text = itemShip.initial
                        i++
                    }
                } else {
                    var i = itemShip.y ?: 0
                    while (i < (itemShip.y ?: 0) + itemShip.quota) {
                        arrLineTextShipP2[i]!![itemShip.x ?: 0].text = itemShip.initial
                        i++
                    }
                }
            }
        }
    }

    private fun setCheat() {
        reset()
        var i: Int
        if (playerTurn == 2) {
            tempInitShipP1.forEachIndexed { index, itemShip ->
                if (itemShip.orientation == 1) {
                    i = itemShip.x?:0
                    while (i < (itemShip.x?:0)+itemShip.quota) {
                        arrLineTextShipP1[itemShip.y?:0]!![i].text = itemShip.initial
                        i++
                    }
                } else {
                    i = itemShip.y?:0
                    while (i < (itemShip.y?:0)+itemShip.quota) {
                        arrLineTextShipP1[i]!![itemShip.x?:0].text = itemShip.initial
                        i++
                    }
                }
            }
        } else {
            tempInitShipP2.forEachIndexed { index, itemShip ->
                if (itemShip.orientation == 1) {
                    i = itemShip.x?:0
                    while (i < (itemShip.x?:0)+itemShip.quota) {
                        arrLineTextShipP2[itemShip.y?:0]!![i].text = itemShip.initial
                        i++
                    }
                } else {
                    i = itemShip.y?:0
                    while (i < (itemShip.y?:0)+itemShip.quota) {
                        arrLineTextShipP2[i]!![itemShip.x?:0].text = itemShip.initial
                        i++
                    }
                }
            }
        }
    }

    private fun reset() {
        if (playerTurn == 1) {
            arrLineTextShipP1.mapIndexed { index, mutableList ->
                mutableList!!.map {
                    it.text = ""
                }
            }
        } else {
            arrLineTextShipP2.mapIndexed { index, mutableList ->
                mutableList!!.map {
                    it.text = ""
                }
            }
        }
    }

    fun resetArr () {
        tempStokShip.mapIndexed { index, itemShip ->
            e("lala", itemShip.name )
            tempStokShip[index] = ItemShip(itemShip.id, 1, itemShip.name, itemShip.quota)
        }
    }

    private fun addingEventBom() {
        if (playerTurn == 1) {
            arrLineParentP1.mapIndexed { indexY, mutableList ->
                mutableList!!.mapIndexed { indexX, linearLayout ->
                    linearLayout.setOnClickListener {
                        var isValid = true

                        bomP1.forEachIndexed { index, coor ->
                            if (indexX == coor.x && coor.y == indexY) {
                                isValid = false
                            }
                        }

                        if (isValid) {
                            afterAddBomP1(indexX, indexY)
                        } else {
                            Toast.makeText(this, "Places has been used", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            arrLineParentP2.mapIndexed { indexY, mutableList ->
                mutableList!!.mapIndexed { indexX, linearLayout ->
                    linearLayout.setOnClickListener {
                        var isValid = true

                        bomP2.forEachIndexed { index, coor ->
                            if (indexX == coor.x && coor.y == indexY) {
                                isValid = false
                            }
                        }

                        if (isValid) {
                            afterAddBomP2(indexX, indexY)
                        } else {
                            Toast.makeText(this, "Places has been used", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun afterAddBomP1(x: Int, y: Int) {
        bomP1.add(Coordinate(x, y))
        renderBom()
        if (isSalvoMode) {
            if (salvoCount < 4) salvoCount++ else {
                isSalvoMode = false
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                disableButton()
                runGantiPlayer()
            }
        } else {
            disableButton()
            runGantiPlayer()
        }
//        btn_ganti.performClick()
    }

    private fun afterAddBomP2(x: Int, y: Int) {
        bomP2.add(Coordinate(x, y))
        renderBom()
        if (isSalvoMode) {
            if (salvoCount < 4) salvoCount++ else {
                isSalvoMode = false
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                disableButton()
                runGantiPlayer()
            }
        } else {
            disableButton()
            runGantiPlayer()
        }
//        btn_ganti.performClick()
    }

    private fun runGantiPlayer() {
        Toast.makeText(this, "Wait for " + if (playerTurn == 1) 2 else 1, Toast.LENGTH_SHORT).show()
        Handler().postDelayed(
                {
                    runOnUiThread {
                        button11.performClick()
                    }
                },
                2500)
    }

    private fun renderBom() {
        var i: Int
        var isShip = false
        var indexItem: Int = 0

        if (playerTurn == 1) {
            bomP1.map {
                isShip = false
                tempInitShipP2.forEachIndexed { index, itemShip ->
                    if (itemShip.orientation == 1) {
                        i = itemShip.x?:0
                        while (i < (itemShip.x?:0)+itemShip.quota) {
                            if (i == it.x && (itemShip.y?:0) == it.y) {
                                indexItem = index
                                textView15.text = "Player 2 ship ${tempInitShipP2[indexItem].name} burn"
                                isShip = true
                                break
                            }
                            i++
                        }
                    } else {
                        i = itemShip.y?:0
                        while (i < (itemShip.y?:0)+itemShip.quota) {
                            if ((itemShip.x?:0) == it.x && i == it.y) {
                                indexItem = index
                                textView15.text = "Player 2 ship ${tempInitShipP2[indexItem].name} burn"
                                isShip = true
                                break
                            }
                            i++
                        }
                    }
                }
                if (isShip) {
                    checkIsWin()
                    if (tempInitShipP2[indexItem].isDestroy!!) {
                        textView15.text = "Player 2 ship ${tempInitShipP2[indexItem].name} destroy"
                    }
                    arrLineTextShipP1[it.y]!![it.x].text = "X"
                } else {
                    arrLineTextShipP1[it.y]!![it.x].text = "O"
                }
            }
        } else {
            bomP2.map {
                isShip = false
                tempInitShipP1.forEachIndexed { index, itemShip ->
                    if (itemShip.orientation == 1) {
                        i = itemShip.x?:0
                        while (i < (itemShip.x?:0)+itemShip.quota) {
                            if (i == it.x && (itemShip.y?:0) == it.y) {
                                indexItem = index
                                textView15.text = "Player 1 ship ${tempInitShipP1[indexItem].name} burn"
                                isShip = true
                                break
                            }
                            i++
                        }
                    } else {
                        i = itemShip.y ?: 0
                        while (i < (itemShip.y ?: 0) + itemShip.quota) {
                            if ((itemShip.x ?: 0) == it.x && i == it.y) {
                                indexItem = index
                                textView15.text = "Player 1 ship ${tempInitShipP1[indexItem].name} burn"
                                isShip = true
                                break
                            }
                            i++
                        }
                    }
                }
                if (isShip) {
                    checkIsWin()
                    if (tempInitShipP1[indexItem].isDestroy!!) {
                        textView15.text = "Player 1 ship ${tempInitShipP1[indexItem].name} destroy"
                    }
                    arrLineTextShipP2[it.y]!![it.x].text = "X"
                } else {
                    arrLineTextShipP2[it.y]!![it.x].text = "O"
                }
            }
        }
    }

    private fun checkIsWin() {
        var i: Int
        var isWin = true
        tempInitShipP2.forEachIndexed { index, itemShip ->
            isWin = true
            if (itemShip.orientation == 1) {
                i = itemShip.x?:0
                while (i < (itemShip.x?:0)+itemShip.quota) {
                    if (arrLineTextShipP2[itemShip.y?:0]!![i].text != "x") {
                        isWin = false
                        break
                    }
                    i++
                }
            } else {
                i = itemShip.y?:0
                while (i < (itemShip.y?:0)+itemShip.quota) {
                    if (arrLineTextShipP2[i]!![itemShip.x?:0].text != "x") {
                        isWin = false
                        break
                    }
                    i++
                }
            }
            tempInitShipP2[index].isDestroy = isWin
        }
        tempInitShipP1.forEachIndexed { index, itemShip ->
            if (itemShip.orientation == 1) {
                i = itemShip.x?:0
                while (i < (itemShip.x?:0)+itemShip.quota) {
                    if (arrLineTextShipP1[itemShip.y?:0]!![i].text != "x") {
                        isWin = false
                        break
                    }
                    i++
                }
            } else {
                i = itemShip.y ?: 0
                while (i < (itemShip.y ?: 0) + itemShip.quota) {
                    if (arrLineTextShipP1[i]!![itemShip.x?:0].text != "x") {
                        isWin = false
                        break
                    }
                    i++
                }
            }
            tempInitShipP1[index].isDestroy = isWin
        }
    }

    class ItemShip (
            val id:Int,
            val stock: Int,
            val name: String,
            val quota: Int,
            val orientation: Int? = 0,
            val x: Int? = 0,
            val y: Int? = 0,
            var isDestroy : Boolean? = true
    ) {
        val initial = name[0].toString()
    }

    class Coordinate (
            val x: Int = 0,
            val y: Int = 0
    )
}


