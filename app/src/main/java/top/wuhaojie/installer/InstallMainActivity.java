package top.wuhaojie.installer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.materialdemo.R;

import java.io.File;

import top.wuhaojie.installerlibrary.AutoInstaller;

public class InstallMainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String APK_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download" + File.separator + "test.apk";
    public static final String CACHE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download";
    public static final String APK_URL = "http://ftp-apk.pconline.com.cn/c274ba195c0444599da88893f1ff9024/pub/download/201010/pconline1469672427822.apk";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在下载");

        findViewById(R.id.btn_install).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
         /* 方案一: 默认安装器 */
        AutoInstaller installer = AutoInstaller.getDefault(InstallMainActivity.this);
//        installer.install(APK_FILE_PATH);
        installer.installFromUrl(APK_URL);
        installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
            @Override
            public void onStart() {
                mProgressDialog.show();
            }

            @Override
            public void onComplete() {
                mProgressDialog.dismiss();
            }

            @Override
            public void onNeed2OpenService() {
                Toast.makeText(InstallMainActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
            }
        });


//        /* 方案二: 构造器 */
//        AutoInstaller installer = new AutoInstaller.Builder(MainActivity.this)
//                .setMode(AutoInstaller.MODE.AUTO_ONLY)
//                .setCacheDirectory(CACHE_FILE_PATH)
//                .build();
//        installer.install(APK_FILE_PATH);
//        installer.installFromUrl(APK_URL);
//        installer.setOnStateChangedListener(new AutoInstaller.OnStateChangedListener() {
//            @Override
//            public void onStart() {
//                mProgressDialog.show();
//            }
//
//            @Override
//            public void onComplete() {
//                mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onNeed2OpenService() {
//                Toast.makeText(MainActivity.this, "请打开辅助功能服务", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
