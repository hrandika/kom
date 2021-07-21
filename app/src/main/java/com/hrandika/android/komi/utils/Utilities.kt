package com.hrandika.android.komi.utils

import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.READ_PHONE_STATE
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class Utilities {

    companion object {
        val MUST_HAVE_PERMISSIONS = arrayOf(CALL_PHONE, READ_PHONE_STATE)
        private const val PERMISSION_RC = 10

       private fun checkPermissionsGranted(context: Context, permission: String): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                context, permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun checkPermissionsGranted(context: Context, permissions: Array<String>): Boolean {
            for (permission in permissions)
                if (!checkPermissionsGranted(context, permission))
                    return false
            return true
        }

        fun checkPermissionsGranted(grantResults: IntArray): Boolean {
            for (result in grantResults)
                if (result == PackageManager.PERMISSION_DENIED)
                    return false
            return true
        }

        fun askForPermissions(activity: FragmentActivity, permissions: Array<String>) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSION_RC)
        }
    } //companion

}// class