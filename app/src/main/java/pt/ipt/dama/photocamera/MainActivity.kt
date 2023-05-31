package pt.ipt.dama.photocamera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var  button:Button
    lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById(R.id.button)
        imageView=findViewById(R.id.imageView)

        button.setOnClickListener{
            takephoto()
        }


    }

    /**
     * function to open the camera and take a photo
     */
    private fun takephoto() {
        val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // start the camera action
        resultLaucher.launch(cameraIntent)
    }

    var resultLaucher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode== Activity.RESULT_OK){
            val data:Intent?=result.data
            imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)
        }
    }
}