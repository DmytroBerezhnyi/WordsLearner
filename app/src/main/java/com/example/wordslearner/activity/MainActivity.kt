package com.example.wordslearner.activity

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wordslearner.R
import com.example.wordslearner.engine.Node
import com.example.wordslearner.engine.Subtitle
import com.example.wordslearner.engine.TextAnalyser
import com.example.wordslearner.engine.comparator.ComparatorByN
import com.example.wordslearner.recycler.WordRecyclerAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFile(Environment.getExternalStorageDirectory().absolutePath + "/Subtitles/testAT1.srt")

    }

    private fun openFile(path: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        analyse(path)
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        Toast.makeText(this@MainActivity, "The program needs the permission for analysing subtitles", Toast.LENGTH_LONG).show()
                    }

                }).check()
        } else {
            analyse(path)
        }
    }

    private fun analyse(path: String) {
        val result = ArrayList<Node>()
        val sentences = Subtitle(path).sentences
        val analyser = TextAnalyser()

        sentences.forEach {
            analyser.analyzeText(it, result)
        }

        Collections.sort(result, ComparatorByN())
        recyclerview.layoutManager = GridLayoutManager(this, 4)
        recyclerview.adapter = WordRecyclerAdapter(this, result)

    }
}
