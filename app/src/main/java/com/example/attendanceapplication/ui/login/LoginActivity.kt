package com.example.attendanceapplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.attendanceapplication.DashboardActivity
import com.example.attendanceapplication.R
import com.example.attendanceapplication.databinding.ActivityLoginBinding
import com.example.attendanceapplication.utils.AppUtils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onLogin() {

        if (viewModel.userName.value.isNullOrEmpty()) {
            showToast(this, "User name should not be empty")
            return
        }
        if (viewModel.password.value.isNullOrEmpty()) {
            showToast(this, "Password should not be empty")
            return
        }
        var intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}
