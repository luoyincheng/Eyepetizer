package com.yincheng.closer.views.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yincheng.closer.R;
import com.yincheng.closer.viewmodels.LoginViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.yincheng.closer.provider.GlobalConfigKt.GITHUB_BASE_URL;

public class SignInActivityJava extends OneOffActivity {
    LoginViewModel loginViewModel;
    private ViewDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        retrofitLogin("luoyincheng", "LuO1XiO2Ng99");
    }

    public void retrofitLogin(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) return;
        String baseUrl = GITHUB_BASE_URL + "authorizations";

        String token = Credentials.basic(userName, password);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put("user");
        jsonArray.put("repo");
        jsonArray.put("gist");
        jsonArray.put("notifications");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("scopes", jsonArray);
            jsonObject.put("note", "com.thirtydegreesray.openhub");
            jsonObject.put("noteUrl", "https://github.com/ThirtyDegreesRay/OpenHub/CallBack");
            jsonObject.put("client_id", "8f7213694e115df205fb");
            jsonObject.put("client_secret", "82c57672382db5c7b528d79e283c398ad02e3c3f");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .url(baseUrl)
                .addHeader("Authorization", token)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString()))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("fadfafdaf", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("fadfafdaf", "onResponse" + response.toString());
                Log.i("fadfafdaf", "onResponse" + response.body().string());
            }
        });
    }
}
