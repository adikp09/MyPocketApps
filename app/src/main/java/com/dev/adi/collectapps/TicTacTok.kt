package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tic_tac_tok.*
import java.util.*

class TicTacTok : AppCompatActivity() {

    private var Player1=ArrayList<Int>()
    private var Player2 = ArrayList<Int>()
    private var ActivePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_tok)
    }

    fun buClick(view: View){
        val buSelected = view as Button
        var cellID = 0
        when(buSelected.id){
            R.id.bu1->cellID =1
            R.id.bu2->cellID =2
            R.id.bu3->cellID =3
            R.id.bu4->cellID =4
            R.id.bu5->cellID =5
            R.id.bu6->cellID =6
            R.id.bu7->cellID =7
            R.id.bu8->cellID =8
            R.id.bu9->cellID =9
        }
        buSelected.isEnabled = false
        playGame(cellID,buSelected)
    }

    private fun playGame(cellID:Int, buSelected:Button){
        buSelected.isEnabled = false

        if(ActivePlayer==1){
            buSelected.text = "X"
            Player1.add(cellID)
            ActivePlayer = 2
            AutoPlay()
        }
        else{
            buSelected.text = "O"
            Player2.add(cellID)
            ActivePlayer = 1
        }

        checkWinner()
    }

    private fun AutoPlay(){
        var emptyCells=ArrayList<Int>()
        for(cellID in 1..9){
            if(!(Player1.contains(cellID) || Player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        var r = Random()
        val randIndex = r.nextInt(emptyCells.size-1)+0

        val CellID = emptyCells[randIndex]

        var buSelect:Button?
        when(CellID){
            1->buSelect= bu1
            2->buSelect=bu2
            3->buSelect=bu3
            4->buSelect=bu4
            5->buSelect=bu5
            6->buSelect=bu6
            7->buSelect=bu7
            8->buSelect=bu8
            9->buSelect=bu9
            else->{
                buSelect = bu1
            }
        }

        playGame(CellID,buSelect)
    }

    private fun checkWinner(){
        var winner = -1
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3))
            winner = 1
        if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3))
            winner = 2

        if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6))
            winner = 1
        if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6))
            winner = 2

        if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9))
            winner = 1
        if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9))
            winner = 2

        if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7))
            winner = 1
        if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7))
            winner = 2

        if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8))
            winner = 1
        if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8))
            winner = 2

        if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9))
            winner = 1
        if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9))
            winner = 2

        if(Player1.contains(1) && Player1.contains(5) && Player1.contains(9))
            winner = 1
        if(Player2.contains(1) && Player2.contains(5) && Player2.contains(9))
            winner = 2

        if(Player1.contains(3) && Player1.contains(5) && Player1.contains(7))
            winner = 1
        if(Player2.contains(3) && Player2.contains(5) && Player2.contains(7))
            winner = 2


        if(winner!=-1){
            if(winner == 1) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show()
                bu1.isEnabled = false
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false
            }
            else {
                Toast.makeText(this,"Bot Win!",Toast.LENGTH_SHORT).show()
                bu1.isEnabled = false
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false
            }
        }
    }
}
