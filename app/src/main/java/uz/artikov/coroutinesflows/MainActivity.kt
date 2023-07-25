package uz.artikov.coroutinesflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.artikov.coroutinesflows.databinding.ActivityMainBinding
import uz.artikov.coroutinesflows.vm.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.getUserLiveData().observe(this, Observer {

            when {

                it.isSuccess -> {

                    Log.d(TAG, "onCreate: ${it.getOrNull()}")
                    binding.idText.text = it.toString()

                }

                it.isFailure -> {

                    Log.d(TAG, "onCreate: ${it.exceptionOrNull()}")

                }

            }

        })

    }

}