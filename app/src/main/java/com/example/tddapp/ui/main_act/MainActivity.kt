package com.example.tddapp.ui.main_act

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tddapp.R
import com.example.tddapp.UserBody
import com.example.tddapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewmodel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            if (MainActValidator.validateEmail(email).not()){
                return@setOnClickListener
            }
            if (MainActValidator.validateName(name).not()){
                return@setOnClickListener
            }
            viewmodel.createUser(UserBody(name,email))
        }

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
            if (MainActValidator.validateBitmap(bitmap).not()){
                return@registerForActivityResult
            }
            binding.ivImg.setImageBitmap(bitmap)
        }

        binding.ivImg.setOnClickListener {
            getContent.launch("image/*")
        }
    }
}