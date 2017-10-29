package com.nao20010128nao.DroidComplex

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialog
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.EditText

/**
 * Created by lesmi on 17/10/28.
 */
class ComplexCodeInputDialog private constructor(context: Context?) : AppCompatDialog(context) {
    companion object {
        fun startEdit(ctx:Context, text:String,callback:(CharSequence)->Unit)=
            ComplexCodeInputDialog(ctx).startEdit(text, callback)
    }

    private lateinit var toolbar: Toolbar
    private lateinit var code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complex_code_input_dialog)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        toolbar=findViewById(R.id.toolbar)!!
        code=findViewById(R.id.code)!!
    }

    fun startEdit(text:String,callback:(CharSequence)->Unit) {
        show()
        code.text = text.toEditable()
        toolbar.menu.let {
            it.add(0, 0, 0, context.getString(R.string.help)).let {
                it.icon = ContextCompat.getDrawable(context, R.mipmap.help).tint(Color.WHITE)
                it.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            }
            it.add(0, 1, 1, context.getString(R.string.type_complex)).let {
                it.icon = ContextCompat.getDrawable(context, R.mipmap.input).tint(Color.WHITE)
                it.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            }
        }
        toolbar.setOnMenuItemClickListener { item->
            when(item.itemId){
                0->{
                    AlertDialog.Builder(context)
                            .setMessage("TEST")
                            .show()
                    true
                }
                1->{
                    AlertDialog.Builder(context)
                            .setView(R.layout.complex_real_imag)
                            .setPositiveButton(context.getString(R.string.ok)){dialog,_->
                                val content= dialog as AlertDialog
                                val (real,imag) = listOf(R.id.real,R.id.imag).map { content.findViewById<EditText>(it)!!.text }
                                code.replaceOrInsertText("$real+i$imag")
                            }
                            .show()
                    true
                }
                else->false
            }
        }
        toolbar.navigationIcon=ContextCompat.getDrawable(context,R.mipmap.ok).tint(Color.WHITE)
        toolbar.setNavigationOnClickListener{
            callback(code.text)
            dismiss()
        }
    }
}