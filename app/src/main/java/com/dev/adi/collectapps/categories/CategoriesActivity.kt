package com.dev.adi.collectapps.categories

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.LinearLayout
import android.widget.TextView
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {

    private val categoriesParent: ArrayList<CategoriesModel> = arrayListOf()
    internal lateinit var parentAdapter: CategoriesParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        parentAdapter = CategoriesParentAdapter(categoriesParent as MutableList<CategoriesModel>, this)
        rv_cat_parent.layoutManager = LinearLayoutManager(this)
        rv_cat_parent.itemAnimator = DefaultItemAnimator()
        rv_cat_parent.adapter = parentAdapter

        val postServices = DataRepository.create()
        postServices.getCategory().enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(call: Call<CategoriesResponse>?, response: Response<CategoriesResponse>?) {
                if (response?.isSuccessful!!) {
                    val data = response.body()
                    categoriesParent.addAll(data?.categories!!)
                    parentAdapter.notifyDataSetChanged()

                    response?.let {
                        it.body()?.let {
                            createList(it.categories, 0)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>?, t: Throwable?) {
                if (t != null) {
                    Log.e("onFailure", "${t.message}")
                }
            }
        }
        )
    }

    private fun createList(listCategories: List<CategoriesModel>, level: Int) {
        listCategories.forEach {
            val valueInPixels = resources.getDimension(R.dimen.margin_left).toInt()
            renderPage(it, (level * valueInPixels) + valueInPixels/6, if (level == 0) valueInPixels/2 else 2)

            it.children?.let {
                item ->
                if (item.isNotEmpty())
                    createList(it.children, level+1)
            }
        }
    }

    private fun renderPage(categorie: CategoriesModel, marginLeft: Int, marginTop: Int) {
        val lineChild = TextView(ContextThemeWrapper(this, R.style.categoriesText))
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(marginLeft,marginTop,2,2)
        lineChild.layoutParams = params
        lineChild.text = categorie.name
        line_parent.addView(lineChild)
    }
}
