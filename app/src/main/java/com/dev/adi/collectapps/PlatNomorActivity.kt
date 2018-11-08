package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_plat_nomor.*


class PlatNomorActivity : AppCompatActivity() {

//    Kabupaten Bantul (AB - kode belakang *B/*G/*J/*K/*T),
//    Kabupaten Kulon Progo (AB - kode belakang *C/*L/*P/*V),
//    Kabupaten Gunung Kidul (AB - kode belakang *D/*M/*W),
//    Kabupaten Sleman (AB - kode belakang *E/*N/*Q/*U/*Y/*Z)
//    Kota Yogyakarta (AB - kode belakang *A/*F/*H/*I/*S)

    private val regGunungKidul = "[a-zA-Z]{2}.[1-9]\\d{1,3}.[A-Z]{0,}[MDW]"
    private val regMbantul = "[a-zA-Z]{2}.[1-9]\\d{1,3}.[A-Z]{0,}[BGJKT]"
    private val regKulonProgo = "[a-zA-Z]{2}.[1-9]\\d{1,3}.[A-Z]{0,}[CLPV]"
    private val regSleman = "[a-zA-Z]{2}.[1-9]\\d{1,3}.[A-Z]{0,}[ENQUYZ]"
    private val regKota = "[a-zA-Z]{2}.[1-9]\\d{1,3}.[A-Z]{0,}[AFHIS]"
    private val regSultan = "([a-zA-Z]{2}).(\\d{1,})"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plat_nomor)

        et_number.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                when {
                    et_number.text.matches(regGunungKidul.toRegex()) -> tv_result.text = "Nomor Plat GunungKidul"
                    et_number.text.matches(regMbantul.toRegex()) -> tv_result.text = "Nomor Plat mBantul"
                    et_number.text.matches(regKulonProgo.toRegex()) -> tv_result.text = "Nomor Plat Kulonprogo"
                    et_number.text.matches(regSleman.toRegex()) -> tv_result.text = "Nomor Plat Sleman"
                    et_number.text.matches(regKota.toRegex()) -> tv_result.text = "Nomor Plat Kota"
                    et_number.text.matches(regSleman.toRegex()) -> tv_result.text = "Halo Sultan Udin"
                    else -> tv_result.text = "Lokasi Plat tidak ditemukan"
                }
//                var string = et_number.text
//                val p = Pattern.compile(regGunungKidul)
//                val m = p.matcher(string)
//                if (m.find()) {
//                    tv_result.text = "Nomor Plat GunungKidul"
//                } else {
//                    tv_result.text = "Not found"
//                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }


    private fun checkNumber () {

    }
}
