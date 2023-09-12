package com.example.deber03_snapchat.activities

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.deber03_snapchat.fragments.CameraFragment
import com.example.deber03_snapchat.R
import com.example.deber03_snapchat.utils.FirebaseAuthHelper

class CameraActivity : AppCompatActivity(), CameraFragment.OnPhotoCapturedListener {

    private val cameraFragment: CameraFragment = CameraFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        supportActionBar?.hide()
        addCameraFragment()
    }

    private fun addCameraFragment() {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder, cameraFragment)
            commit()
        }
    }

    fun logoutButtonPressed(view: View) {
        if (FirebaseAuthHelper.signOut()) {
            Intent(this, MainActivity::class.java).let {
                it.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(it)
            }
        }
    }

    fun goToRetrievedSnapsButtonPressed(view: View) {
        startActivity(Intent(this, ShowRetrievedSnapsActivity::class.java))
    }

    override fun onPhotoCaptured(fileLocation: String) {
        Intent(this, CameraCapturedActivity::class.java).let {
            it.putExtra(CameraCapturedActivity.EXTRA_FILE_LOCATION_ID, fileLocation)
            startActivity(it)
        }
    }
}