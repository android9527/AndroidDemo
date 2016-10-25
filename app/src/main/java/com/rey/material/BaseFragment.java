package com.rey.material;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;


import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Fragment、
 */
public abstract class BaseFragment extends Fragment implements OnTouchListener {

    protected View rootView;// 缓存Fragment view
    private Unbinder unbinder;

    public BaseFragment() {
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        setRetainInstance(true);// 保存有状态的对象
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.setOnTouchListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    // 将上层的触摸事件拦截
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 此处解决getChildFragmentManager()的No Activity BUG
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void registerFinishLoginReceiver(String action, BroadcastReceiver mBroadcastListener) {
        try {// 广播注册容易崩溃
            IntentFilter mIntentFilter = new IntentFilter();
            mIntentFilter.addAction(action);
            mIntentFilter.setPriority(1000);// 提高广播接受优先级
            getActivity().registerReceiver(mBroadcastListener, mIntentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /********************************
     * 【Fragment Title】
     *******************************************/
    protected boolean hasArgumentsExtra(String pExtraKey) {
        if (getArguments() != null) {
            return getArguments().containsKey(pExtraKey);
        }
        return false;
    }


    /**
     * 替换view 为Fragment
     *
     * @param fragment
     * @param viewId
     */
    protected void replaceFragment(Fragment fragment, int viewId) {
        FragmentTransaction transaction = this.getChildFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.fragments_transition_in, R.anim.fragments_transition_out);
        if (!fragment.isAdded()) {
            transaction.add(viewId, fragment).show(fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    // 起Activity
    public void startActivityForResult(Context mContext, Class<?> mclass, int FLAG_ACTIVITY, Bundle mBundle, int requestCode) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, mclass);
        if (mBundle != null)
            mIntent.putExtras(mBundle);
        if (FLAG_ACTIVITY != -1)
            mIntent.setFlags(FLAG_ACTIVITY);// Intent.FLAG_ACTIVITY_NEW_TASK
        super.startActivityForResult(mIntent, requestCode);
    }

    // 起Activity
    public void startActivity(Context mContext, Class<?> mclass, int FLAG_ACTIVITY, Bundle mBundle) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, mclass);
        if (mBundle != null)
            mIntent.putExtras(mBundle);
        if (FLAG_ACTIVITY != -1)
            mIntent.setFlags(FLAG_ACTIVITY);// Intent.FLAG_ACTIVITY_NEW_TASK
        super.startActivity(mIntent);
    }

    public void startActivity(Context mContext, Class<?> mclass) {
        startActivity(mContext, mclass, -1, null);
    }

    public void finishActivity() {
        getActivity().finish();
    }

}
