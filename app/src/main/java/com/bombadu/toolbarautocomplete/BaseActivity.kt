package com.bombadu.toolbarautocomplete

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {

    private var searchIsVisible = false

    fun setupToolbar(list: MutableList<String>) {
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        myToolbar.setTitleTextColor(Color.WHITE)
        myToolbar.inflateMenu(R.menu.main_menu)
        myToolbar.setOnMenuItemClickListener { item ->
            val id = item.itemId
            if (id == R.id.action_search) {
                if (!searchIsVisible) {
                    acTextView.visibility = View.VISIBLE
                    showSoftKeyboard(this)
                    acTextView.isFocusableInTouchMode = true
                    acTextView.requestFocus()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
                    val editText = findViewById<AutoCompleteTextView>(R.id.acTextView)
                    editText.threshold = 1
                    editText.setAdapter(adapter)
                    titleTextView.visibility = View.GONE
                    cancelArrow.visibility = View.VISIBLE
                    searchIsVisible = true

                } else {
                    cleanUpSearch()

                }
            }

            acTextView.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    cleanUpSearch()
                    //true
                }
                false
            }


            false
        }

        cancelArrow.setOnClickListener {
            cancelArrow.visibility = View.GONE
            acTextView.visibility = View.INVISIBLE
            titleTextView.visibility = View.VISIBLE
            hideSoftKeyBoard(this, toolbar)
            searchIsVisible = false
        }
    }

    private fun cleanUpSearch() {
        val text = acTextView.text.toString()
        if (text != "") {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show() //Call ViewModel
            acTextView.text = null
            acTextView.clearFocus()
            titleTextView.visibility = View.VISIBLE
            cancelArrow.visibility = View.GONE

            acTextView.visibility = View.INVISIBLE
            hideSoftKeyBoard(this, toolbar)
            searchIsVisible = false
        }
    }


    private fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {

            e.printStackTrace()
        }

    }

    private fun showSoftKeyboard(context: Context) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}