package com.jf.textview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jf.styledtextview.StyledTextViewRegistry

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StyledTextViewRegistry.addColor("red", R.color.colorAccent)
        StyledTextViewRegistry.addFont("opensans", R.font.opensans_regular)
        StyledTextViewRegistry.addFont("girassol", R.font.girassol_regular)
        setContentView(R.layout.activity_main)
    }
}
