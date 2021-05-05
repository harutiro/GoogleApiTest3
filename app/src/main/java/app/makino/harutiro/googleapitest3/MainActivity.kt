package app.makino.harutiro.googleapitest3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import java.util.*


class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("438184272791-iedg8kk5mcliqovmmfni71i5ir774go1.apps.googleusercontent.com")
                .requestEmail()
                .build()



        val mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        findViewById<Button>(R.id.button2).setOnClickListener{
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, 9001)
            Log.d("debag", "OK")
        }

        findViewById<Button>(R.id.button).setOnClickListener{
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        }

        findViewById<Button>(R.id.button3).setOnClickListener{

        }

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            val account: GoogleSignInAccount = result?.signInAccount as GoogleSignInAccount
            val idToken = account.idToken
            Log.d("debag", idToken.toString())
        }

        Log.d("debag", "OK")
    }
}