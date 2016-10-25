package com.rey.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rey.material.utils.AppManager;
import com.rey.material.utils.SystemBarTintManager;

import butterknife.ButterKnife;
import com.rey.material.demo.R;


/**
 * 基类Activity
 *
 * @author chenfeiyue
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected FrameLayout layoutContent;
    protected View rootLayoutView;
    protected FragmentManager supportFragmentManager;

    /**
     * ***************************** 【Activity LifeCycle For Debug】 ******************************************
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the shared Tracker instance.
        rootLayoutView = LayoutInflater.from(this).inflate(R.layout.activity_base_content, null);
        super.setContentView(rootLayoutView);
        layoutContent = (FrameLayout) findViewById(R.id.layout_content);
        AppManager.getAppManager().addActivity(this);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.primary_dark);
        }
        setupContentView();
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public abstract void setupContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(BaseActivity.this);
    }

    /**
     * 切換Fragment
     */
    public void changeFragment(Fragment f) {
        changeFragment(f, R.id.layout_content, true);
    }

    /**
     * 切換Fragment
     */
    public void changeFragment(Fragment f, int resId) {
        changeFragment(f, resId, true);
    }


    /**
     * 初始化Fragment(FragmentActivity中呼叫)
     */
    public void initFragment(Fragment f) {
        changeFragment(f, R.id.layout_content, true);
    }


    private void changeFragment(Fragment f, int resId, boolean init) {
        if (f.getArguments() == null) {
            Bundle bundle = new Bundle();
            f.setArguments(bundle);
        }
        FragmentTransaction ft = getSFragmentManager().beginTransaction();
        ft.replace(resId, f, f.getClass().getName());
        if (!init)
            ft.addToBackStack(null);
        ft.commit();
    }

    public FragmentManager getSFragmentManager() {
        if (supportFragmentManager == null) {
            supportFragmentManager = getSupportFragmentManager();
        }
        return supportFragmentManager;
    }


    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(layoutResID, layoutContent);
        super.setContentView(rootLayoutView);
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 是否含有用户信息
     */
    protected boolean hasLoginForm() {
//        String username = spUtil.getString(ConstValue.Sp.USERNAME, "");
////        password = spUtil.getString(ConstValue.Sp.PASSWORD, "");
////        String cookie = spUtil.getString(ConstValue.Sp.COOKIE, "");
//        UserCache userCache = UserCache.getInstance(this);
//        return userCache.hasLogin();
        //        return (!TextUtils.isEmpty(username)/* && !TextUtils.isEmpty(password)*/ && !TextUtils.isEmpty(cookie));
        return false;
    }

    public Context getContext() {
        return this;
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * ***************************** 【Activity LifeCycle For Debug】 ******************************************
     */

    protected boolean hasExtra(String pExtraKey) {
        if (getIntent() != null) {
            return getIntent().hasExtra(pExtraKey);
        }
        return false;
    }


    /* 显示软键盘 */
    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 显示软键盘
     */
    public void showKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }

    /* 隐藏软键盘 */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void handleOutmemoryError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, "内存空间不足！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /******************************** 【Activity Title】 *******************************************/


    /**
     * 替换view 为Fragment
     *
     * @param fragment
     * @param viewId
     */
    protected void replaceFragment(Fragment fragment, int viewId) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragments_transition_in, R.anim.fragments_transition_out);
        if (!fragment.isAdded()) {
            transaction.add(viewId, fragment).show(fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void onResume() {
        super.onResume();
        Log.i("BaseActivity", "Setting screen name: " + "onResume");
    }
    public void onPause() {
        super.onPause();
    }
}
