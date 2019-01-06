package com.guillemserralorenz.twitchcompanion

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.squareup.picasso.Picasso
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.twitter.sdk.android.core.models.User

import retrofit2.Call

class MainActivity2 : AppCompatActivity() {
    private var twitterLoginButton: TwitterLoginButton? = null

    private var userProfileImageView: ImageView? = null
    private var userDetailsLabel: TextView? = null


    //twitter auth client required for custom login
    private var client: TwitterAuthClient? = null

    /**
     * get authenticates user session
     *
     * @return twitter session
     */
    private//NOTE : if you want to get token and secret too use uncomment the below code
            /*TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        String secret = authToken.secret;*/ val twitterSession: TwitterSession?
        get() = TwitterCore.getInstance().sessionManager.activeSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize twitter auth client
        client = TwitterAuthClient()

        //find the id of views
        twitterLoginButton = findViewById(R.id.default_twitter_login_button)

        userProfileImageView = findViewById(R.id.user_profile_image_view)
        userDetailsLabel = findViewById(R.id.user_details_label)

        //NOTE : calling default twitter login in OnCreate/OnResume to initialize twitter callback
        defaultLoginTwitter()

    }

    /**
     * method to do Default Twitter Login
     */
    fun defaultLoginTwitter() {
        //check if user is already authenticated or not
        if (twitterSession == null) {

            //if user is not authenticated start authenticating
            twitterLoginButton!!.callback = object : Callback<TwitterSession>() {
                override fun success(result: Result<TwitterSession>) {

                    // Do something with result, which provides a TwitterSession for making API calls
                    val twitterSession = result.data

                    //call fetch email only when permission is granted
                    fetchTwitterEmail(twitterSession)

                }

                override fun failure(exception: TwitterException) {
                    // Do something on failure
                    Toast.makeText(this@MainActivity2, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {

            //if user is already authenticated direct call fetch twitter email api
            Toast.makeText(this, "User already authenticated", Toast.LENGTH_SHORT).show()
            fetchTwitterEmail(twitterSession)
        }
    }

    fun customLoginTwitter(view: View) {
        //check if user is already authenticated or not
        if (twitterSession == null) {

            //if user is not authenticated start authenticating
            client!!.authorize(this, object : Callback<TwitterSession>() {
                override fun success(result: Result<TwitterSession>) {

                    // Do something with result, which provides a TwitterSession for making API calls
                    val twitterSession = result.data

                    //call fetch email only when permission is granted
                    fetchTwitterEmail(twitterSession)
                }

                override fun failure(e: TwitterException) {
                    // Do something on failure
                    Toast.makeText(this@MainActivity, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        } else {
            //if user is already authenticated direct call fetch twitter email api
            Toast.makeText(this, "User already authenticated", Toast.LENGTH_SHORT).show()
            fetchTwitterEmail(twitterSession)
        }
    }

    /**
     * Before using this feature, ensure that “Request email addresses from users” is checked for your Twitter app.
     *
     * @param twitterSession user logged in twitter session
     */
    fun fetchTwitterEmail(twitterSession: TwitterSession?) {
        client!!.requestEmail(twitterSession, object : Callback<String>() {
            override fun success(result: Result<String>) {
                //here it will give u only email and rest of other information u can get from TwitterSession
                userDetailsLabel!!.text = "User Id : " + twitterSession!!.userId + "\nScreen Name : " +
                        twitterSession.userName + "\nEmail Id : " + result.data
            }

            override fun failure(exception: TwitterException) {
                Toast.makeText(this@MainActivity, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    /**
     * call Verify Credentials API when Twitter Auth is successful else it will go in exception block
     * this metod will provide you User model which contain all user information
     *
     * @param view calling view
     */
    fun fetchTwitterImage(view: View) {
        //check if user is already authenticated or not
        if (twitterSession != null) {

            //fetch twitter image with other information if user is already authenticated

            //initialize twitter api client
            val twitterApiClient = TwitterCore.getInstance().apiClient

            //Link for Help : https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-verify_credentials

            //pass includeEmail : true if you want to fetch Email as well
            val call = twitterApiClient.accountService.verifyCredentials(true, false, true)
            call.enqueue(object : Callback<User>() {
                override fun success(result: Result<User>) {
                    val user = result.data
                    userDetailsLabel!!.text = "User Id : " + user.id + "\nUser Name : " + user.name + "\nEmail Id : " +
                            user.email + "\nScreen Name : " + user.screenName

                    var imageProfileUrl = user.profileImageUrl
                    Log.e(TAG, "Data : $imageProfileUrl")
                    //NOTE : User profile provided by twitter is very small in size i.e 48*48
                    //Link : https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners
                    //so if you want to get bigger size image then do the following:
                    imageProfileUrl = imageProfileUrl.replace("_normal", "")

                    ///load image using Picasso
                    Picasso.with(this@MainActivity)
                        .load(imageProfileUrl)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(userProfileImageView)
                }

                override fun failure(exception: TwitterException) {
                    Toast.makeText(this@MainActivity, "Failed to authenticate. Please try again.", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        } else {
            //if user is not authenticated first ask user to do authentication
            Toast.makeText(this, "First to Twitter auth to Verify Credentials.", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the twitterAuthClient.
        if (client != null)
            client!!.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        twitterLoginButton!!.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }


}
