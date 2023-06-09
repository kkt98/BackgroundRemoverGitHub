package com.example.backgroundremovergithub

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.example.backgroundremovergithub.databinding.ActivityMainBinding
import com.slowmac.autobackgroundremover.BackgroundRemover
import com.slowmac.autobackgroundremover.OnBackgroundChangeListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val imageResult =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { uri ->
                binding.imageView.setImageURI(uri)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        imageResult.launch("image/*")

        binding.delete1.setOnClickListener {
            removeBg()
        }

        binding.button.setOnClickListener {
            binding.parentView.setBackgroundColor(Color.parseColor("#964b00"));
        }

        binding.button2.setOnClickListener {
            binding.parentView.setBackgroundColor(Color.parseColor("#00CC66"))
        }

        binding.button3.setOnClickListener {
            binding.parentView.setBackgroundColor(Color.parseColor("#0080ff"))
        }

    }

    private fun removeBg() { binding.imageView.invalidate()
        BackgroundRemover.bitmapForProcessing(
            binding.imageView.drawable.toBitmap(),
            true,
            object : OnBackgroundChangeListener {
                override fun onSuccess(bitmap: Bitmap) {
                    binding.imageView.setImageBitmap(bitmap)
                }

                override fun onFailed(exception: Exception) {
                    Toast.makeText(this@MainActivity, "Error Occur", Toast.LENGTH_SHORT).show()
                }

            })
    }
}