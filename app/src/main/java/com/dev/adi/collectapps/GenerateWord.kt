package com.dev.adi.collectapps

import java.util.*

object GenerateWord{
fun generate():String{
        val words=arrayOf("ayam","kambing","saran","pengaturan","upaya","Agustus","MusimGugur","perbatasan","angin","bata","tenang","kanal","Casey","pemeran","memilih","cakar","pelatih","terus-menerus","kontras","cookie","beacukai","kerusakan","Danny","dalam","mendalam","diskusi","boneka","keledai","Mesir","Ellen","penting","pertukaran","ada","penjelasan","menghadap","film","terbaik","perapian","mengambang","orang-orang","benteng","garasi","meraih","nenek","kebiasaan","bahagia","Harry","menuju","pemburu","Illinois","gambar","mandiri","instan","Januari","anak-anak","label","Lee","paru-paru","manufaktur","Martin","matematika","meleleh","memori","penggilingan","misi","monyet","Gunung","misterius","lingkungan","Norwegia","gila","kadang-kadang","resmi","dirikitasendiri","istana","Pennsylvania","Philadelphia","piring","puisi","polisi","positif","mungkin","praktis","kebanggaan","dijanjikan","ingat","hubungan","luarbiasa","membutuhkan","sajak","berbatu","digosok","terburu-buru","dijual","satellites","puas","takut","seleksi","goyang","gemetar","dangkal","teriakan","konyol","palingsederhana","sedikit","tergelincir","kemiringan","sabun","surya","spesies","berputar","kaku","diayunkan","dongeng","jempol","tembakau","mainan","perangkap","diperlakukan","selaras","Universitas","uap","kapal","kekayaan","serigala","kebunbinatang")
        val randomGenerator= Random()
        randomGenerator.nextInt(words.size)
        return words[randomGenerator.nextInt(words.size)]
    }
}
