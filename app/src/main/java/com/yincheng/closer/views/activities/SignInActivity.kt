package com.yincheng.closer.views.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yincheng.closer.R
import com.yincheng.closer.databinding.ActivitySignInBinding
import com.yincheng.closer.links.ActivityJumpObserver
import com.yincheng.closer.provider.ViewModelFactoryProvider
import com.yincheng.closer.viewmodels.LoginViewModel
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SignInActivity : OneOffActivity(), View.OnClickListener {
    private var binding: ActivitySignInBinding? = null

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactoryProvider.provideLoginViewModelFactory(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v) {
                binding?.ivGithub -> loginViewModel.githubBasicLogin(
                    binding?.etUsername?.text.toString(),
                    binding?.etPassword?.text.toString()
                )
                binding?.ivWechat -> loginViewModel.wechatLogin()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding?.apply {
            viewModel = loginViewModel
            viewModel?.isLogin?.observe(
                this@SignInActivity,
                ActivityJumpObserver(
                    this@SignInActivity,
                    Intent(this@SignInActivity, LauncherActivity::class.java)
                )
            )
            clickListener = this@SignInActivity
            lifecycleOwner = this@SignInActivity
        }
        window.setBackgroundDrawable(null)
        binding?.cdvLogin?.setDeadLine(2019, 10, 8, 12, 0, 0)
        val intent = Intent()
        binding?.pv?.pathAnimator
            ?.delay(100)//pathView.getSequentialPathAnimator().
            ?.duration(1000)
            ?.interpolator(AccelerateDecelerateInterpolator())
            ?.listenerEnd {

            }
            ?.start()

    }

    fun basicLogin(userName: String?, password: String?) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) return
        Log.i("basicLogin", "startlogin")
        val baseUrl = "https://api.github.com/authorizations"
        val okHttpClient = OkHttpClient()

        val jsonArray = JSONArray().apply {
            put("user")
            put("repo")
            put("gist")
            put("notifications")
        }
        val basicAuth = JSONObject().apply {
            put("scopes", jsonArray)
            put("note", "com.thirtydegreesray.openhub")
            put("noteUrl", "https://github.com/ThirtyDegreesRay/OpenHub/CallBack")
            put("client_id", "8f7213694e115df205fb")
            put("clientSecret", "82c57672382db5c7b528d79e283c398ad02e3c3f")
        }
        val token = Credentials.basic(userName, password)
        Log.i("basicLogin", "token:" + token)
        Log.i("basicLogin", "basicauth:" + basicAuth.toString())

        val request = Request.Builder()
            .url(baseUrl)
            .addHeader("Authorization", token)
            .post(
                RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    basicAuth.toString()
                )
            )
            .build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("basicLogin", "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
//                startActivity(Intent().setClass(SignInActivity::class.java., LauncherActivity::class.java))
                Log.i("basicLogin", "" + response.isSuccessful)
                Log.i("basicLogin", "" + response.code())
                Log.i("basicLogin", "" + response.toString())
            }
        })
    }

    val token: String by lazy { Credentials.basic("luoyincheng", "LuO1XiO2Ng99") }


}