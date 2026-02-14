package com.example.taghoymobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.taghoymobile.api.RetrofitClient
import com.example.taghoymobile.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        val userId = prefs.getString("userId", null)

        // Protect this screen — redirect to login if not authenticated
        if (userId == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Fetch user profile
        binding.tvWelcome.text = "Loading..."

        RetrofitClient.instance.getProfile(userId).enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val username = body?.get("username")?.toString() ?: "N/A"
                    val email = body?.get("email")?.toString() ?: "N/A"
                    val role = body?.get("role")?.toString() ?: "USER"

                    binding.tvWelcome.text = "Welcome, $username!"
                    binding.tvUsername.text = username
                    binding.tvEmail.text = email
                    binding.tvRole.text = role
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed to load profile", Toast.LENGTH_SHORT).show()
                    // Clear invalid session
                    prefs.edit().remove("userId").apply()
                    startActivity(Intent(this@DashboardActivity, LoginActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // Logout button with confirmation dialog
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout") { _, _ ->
                    // Call logout endpoint
                    RetrofitClient.instance.logout().enqueue(object : Callback<Map<String, String>> {
                        override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                            // Clear session regardless of response
                            prefs.edit().remove("userId").apply()
                            Toast.makeText(this@DashboardActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@DashboardActivity, LoginActivity::class.java))
                            finish()
                        }

                        override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                            // Still log out locally even if network fails
                            prefs.edit().remove("userId").apply()
                            startActivity(Intent(this@DashboardActivity, LoginActivity::class.java))
                            finish()
                        }
                    })
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
