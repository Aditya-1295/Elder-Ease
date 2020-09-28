package com.example.elderease;

import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class magnifier extends AppCompatActivity {
    ImageView imageViewMag;
    Camera camera;
    FrameLayout camera_preview;
    showCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnifier);

        FrameLayout camera_preview = findViewById(R.id.camera_preview);
        Button button_capture = findViewById(R.id.button_capture);
//        TextView txtMag = findViewById((R.id.txtMag));

        //open camera
        camera = Camera.open();
        showCamera = new showCamera(this,camera);
        camera_preview.addView(showCamera);

        
//        button_capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(magnifier.this, "Opening Camera...", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
//                startActivityForResult(intent, 0);
//            }
//        });
        
        
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        ImageView imageViewMag = findViewById(R.id.imageViewMag);
//        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        imageViewMag.setImageBitmap(bitmap);
//    }


//    /** Check if this device has a camera */
//    private boolean checkCameraHardware(Context context) {
//        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
//            // this device has a camera
//            return true;
//        } else {
//            // no camera on this device
//            return false;
//        }
//    }

        
//    /** A basic Camera preview class */
//    public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
//        private SurfaceHolder mHolder;
//        private Camera mCamera;
//
//        public CameraPreview(Context context, Camera camera) {
//            super(context);
//            mCamera = camera;
//
//            // Install a SurfaceHolder.Callback so we get notified when the
//            // underlying surface is created and destroyed.
//            mHolder = getHolder();
//            mHolder.addCallback(this);
//            // deprecated setting, but required on Android versions prior to 3.0
//            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//
//        public void surfaceCreated(SurfaceHolder holder) {
//            // The Surface has been created, now tell the camera where to draw the preview.
//            try {
//                mCamera.setPreviewDisplay(holder);
//                mCamera.startPreview();
//            } catch (IOException e) {
//                Log.d(CAMERA_SERVICE, "Error setting camera preview: " + e.getMessage());
//            }
//        }
//
//        public void surfaceDestroyed(SurfaceHolder holder) {
//            // empty. Take care of releasing the Camera preview in your activity.
//        }
//
//        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
//            // If your preview can change or rotate, take care of those events here.
//            // Make sure to stop the preview before resizing or reformatting it.
//
//            if (mHolder.getSurface() == null){
//                // preview surface does not exist
//                return;
//            }
//
//            // stop preview before making changes
//            try {
//                mCamera.stopPreview();
//            } catch (Exception e){
//                // ignore: tried to stop a non-existent preview
//            }
//
//            try {
//                mCamera.setPreviewDisplay(mHolder);
//                mCamera.startPreview();
//
//            } catch (Exception e){
//                Log.d(CAMERA_SERVICE, "Error starting camera preview: " + e.getMessage());
//            }
//        }
//    }
//
//    public class CameraActivity extends Activity {
//
//        // Create an instance of Camera
//        private Camera mCamera = getCameraInstance();
//        private CameraPreview mPreview;
//        private SurfaceView preview;
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_magnifier);
//
//            // Create our Preview view and set it as the content of our activity.
//            mPreview = new CameraPreview(this, mCamera);
//            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//            preview.addView(mPreview);
//
//            }
//        }
//    public String getFlashMode (){
//        return null;
//    }
//
//    public void setFlashMode (String value){
//
//    }
//
//    public static final String FLASH_MODE_ON(){
//        return null;
//    }
//
//    public static final String FLASH_MODE_OFF(){
//        return null;
//    }
//
//    //Constant emission of light during preview
//    public static final String FLASH_MODE_TORCH(){
//        return null;
//    }

}


