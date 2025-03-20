package com.keepcoding.dbandroidavanzado.presentation.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.keepcoding.dbandroidavanzado.databinding.ActivityLoginBinding
import com.keepcoding.dbandroidavanzado.presentation.MainActivity
import com.keepcoding.dbandroidavanzado.presentation.ui.login.model.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setObeservers()
        supportActionBar?.hide() // Oculta la barra de acción

        binding.loginButton.setOnClickListener {
           val user = binding.EditTextUser.text.toString()
            val password = binding.EditTextPassword.text.toString()

            viewModel.login(user,password)

        }
    }

    private fun setObeservers() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is LoginState.Idle -> {
                    }

                    is LoginState.Loading -> {
                        loadingSettingsView()
                    }

                    is LoginState.Success -> {
                        successSettingsView()
                        startActivity()

                    }

                    is LoginState.Error -> {
                        errorSettingsView()
                        Toast.makeText(
                            this@LoginActivity,
                            "Error. \"Incorrect username or password.\"",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }

        }
    }

    private fun startActivity() {
        MainActivity.startActivity(this)
        finish()
    }
    // Funciones para cambiar la vista según el estado.

    private fun loadingSettingsView() {
        binding.spinningLoading.visibility = View.VISIBLE
        binding.loginButton.visibility = View.GONE
    }


    private fun successSettingsView() {
        binding.spinningLoading.visibility = View.GONE
        binding.loginButton.visibility = View.GONE

    }

    private fun errorSettingsView() {
        binding.spinningLoading.visibility = View.GONE
        binding.loginButton.visibility = View.VISIBLE

    }
}