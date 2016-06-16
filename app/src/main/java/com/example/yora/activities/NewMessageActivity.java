package com.example.yora.activities;


import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.yora.R;
import com.example.yora.views.CameraPreview;

public class NewMessageActivity extends BaseAuthenticatedActivity {
    public static final String EXTRA_CONTACT = "EXTRA_CONTACT";

    private static final String TAG = "NewMessageActivity";
    private static final String STATE_CAMERA_INDEX = "STATE_CAMERA_INDEX";

    private Camera _camera;
    private Camera.CameraInfo _cameraInfo;
    private int _currentCameraIndex;
    private CameraPreview _cameraPreview;

    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_message);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (savedInstanceState != null) {
            _currentCameraIndex = savedInstanceState.getInt(STATE_CAMERA_INDEX);
        } else {
            _currentCameraIndex = 0;
        }

        _cameraPreview = new CameraPreview(this);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_new_message_frame);
        frameLayout.addView(_cameraPreview, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        establishCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (_camera != null) {
            _cameraPreview.setCamera(null, null);
            _camera.release();
            _camera = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CAMERA_INDEX, _currentCameraIndex);
    }

    private void establishCamera() {
        if (_camera != null) {
            _cameraPreview.setCamera(null, null);
            _camera.release();
            _camera = null;
        }

        try {
            _camera = Camera.open(_currentCameraIndex);
        } catch (Exception e) {
            Log.e(TAG, "Could not open camera " + _currentCameraIndex, e);
            Toast.makeText(this, "Error establishing camera!", Toast.LENGTH_LONG).show();
            return;
        }

        _cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(_currentCameraIndex, _cameraInfo);
        _cameraPreview.setCamera(_camera, _cameraInfo);

        if (_cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            Toast.makeText(this, "Using back camera", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Using front camera", Toast.LENGTH_SHORT).show();
        }
    }
}
