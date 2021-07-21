package com.hrandika.android.komi

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hrandika.android.komi.utils.Utilities
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_keypad, R.id.navigation_sim, R.id.navigation_rule
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        checkPermissions(null);
    }

    private fun checkPermissions(@Nullable grantResults: IntArray?) {
        if (grantResults != null && Utilities.checkPermissionsGranted(grantResults) || Utilities.checkPermissionsGranted(
                this,
                Utilities.MUST_HAVE_PERMISSIONS
            )
        ) { //If granted

        } else {
            Utilities.askForPermissions(this, Utilities.MUST_HAVE_PERMISSIONS)
        }
    }

}
