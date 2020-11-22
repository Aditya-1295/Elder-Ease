package com.example.elderease;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.snackbar.Snackbar;


public class magnifier extends AppCompatActivity implements Imagnifier{

    private static final String TAG = "magnifier";
    private static final int REQUEST_CODE = 1234;
    public static String CAMERA_POSITION_FRONT;
    public static String CAMERA_POSITION_BACK;
    public static String MAX_ASPECT_RATIO;

    private boolean mPermissions;
    public String mCameraOrientation = "none"; // Front-facing or back-facing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnifier);
        init();
    }

    private void startCamera2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.camera_container, Camera2Fragment.newInstance(), getString(R.string.fragment_camera2));
        transaction.commit();
    }

    private void init(){
        if(mPermissions){
            if(checkCameraHardware(this)){

                // Open the Camera
                startCamera2();
            }
            else{
                showSnackBar("You need a camera to use this application", Snackbar.LENGTH_INDEFINITE);
            }
        }
        else{
            verifyPermissions();
        }
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        // this device has a camera
        // no camera on this device
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public void verifyPermissions(){
        Log.d(TAG, "verifyPermissions: asking user for permissions.");
        String[] permissions = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0] ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1] ) == PackageManager.PERMISSION_GRANTED) {
            mPermissions = true;
            init();
        } else {
            ActivityCompat.requestPermissions(
                    magnifier.this,
                    permissions,
                    REQUEST_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){
            if(mPermissions){
                init();
            }
            else{
                verifyPermissions();
            }
        }
    }


    private void showSnackBar(final String text, final int length) {
        View view = this.findViewById(android.R.id.content).getRootView();
        Snackbar.make(view, text, length).show();
    }

    @Override
    public void setCameraFrontFacing() {
        Log.d(TAG, "setCameraFrontFacing: setting camera to front facing.");
        mCameraOrientation = CAMERA_POSITION_FRONT;
    }

    @Override
    public void setCameraBackFacing() {
        Log.d(TAG, "setCameraBackFacing: setting camera to back facing.");
        mCameraOrientation = CAMERA_POSITION_BACK;
    }

    @Override
    public void setFrontCameraId(String cameraId){
        CAMERA_POSITION_FRONT = cameraId;
    }


    @Override
    public void setBackCameraId(String cameraId){
        CAMERA_POSITION_BACK = cameraId;
    }

    @Override
    public boolean isCameraFrontFacing() {
        return mCameraOrientation.equals(CAMERA_POSITION_FRONT);
    }

    @Override
    public boolean isCameraBackFacing() {
        return mCameraOrientation.equals(CAMERA_POSITION_BACK);
    }

    @Override
    public String getBackCameraId(){
        return CAMERA_POSITION_BACK;
    }

    @Override
    public String getFrontCameraId(){
        return CAMERA_POSITION_FRONT;
    }

    @Override
    public void hideStatusBar() {

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void showStatusBar() {

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void hideStillshotWidgets() {
        Camera2Fragment camera2Fragment = (Camera2Fragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_camera2));
        if (camera2Fragment != null) {
            if(camera2Fragment.isVisible()){
                camera2Fragment.drawingStarted();
            }
        }
    }

    @Override
    public void showStillshotWidgets() {
        Camera2Fragment camera2Fragment = (Camera2Fragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_camera2));
        if (camera2Fragment != null) {
            if(camera2Fragment.isVisible()){
                camera2Fragment.drawingStopped();
            }
        }
    }

    @Override
    public void setTrashIconSize(int width, int height){
        Camera2Fragment camera2Fragment = (Camera2Fragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_camera2));
        if (camera2Fragment != null) {
            if(camera2Fragment.isVisible()){
                camera2Fragment.setTrashIconSize(width, height);
            }
        }
    }

}


//import android.hardware.Camera;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.File;
//import java.io.FileOutputStream;
//    FrameLayout camera_preview;
//import java.io.IOException;
//
//public class magnifier extends AppCompatActivity {
//    ImageView imageViewMag;
//    Camera camera;
//    showCamera showCamera;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_magnifier);
//
//        FrameLayout camera_preview = findViewById(R.id.camera_preview);
//        Button button_capture = findViewById(R.id.button_capture);
////        TextView txtMag = findViewById((R.id.txtMag));
//
//        //open camera
//        camera = Camera.open();
//        showCamera = new showCamera(this, camera);
//        camera_preview.addView(showCamera);

//    }
//    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//            File picture_file = getOutputMediaFile();
//
//            if(picture_file == null){
//                return;
//            }
//            else
//            {
//                try {
//                    FileOutputStream fos = new FileOutputStream(picture_file);
//                    fos.write(data);
//                    fos.close();
//
//                    camera.startPreview();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//
//    private File getOutputMediaFile()
//    {
//        String state = Environment.getExternalStorageState();
//        if(!state.equals(Environment.MEDIA_MOUNTED)){
//            return null;
//        }
//        else
//        {
//            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");
//
//            if(folder_gui.exists())
//            {
//                folder_gui.mkdirs();
//            }
//
//            File outputFile = new File(folder_gui, "temp.jpg");
//            return outputFile;
//        }
//    }
//    public void captureImage(View v){
//        if(camera != null){
//            camera.takePicture(null, null, mPictureCallback);
//        }
//    }
//}
//
