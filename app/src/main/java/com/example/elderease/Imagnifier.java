package com.example.elderease;

public interface Imagnifier {

    void setCameraFrontFacing();

    void setCameraBackFacing();

    boolean isCameraFrontFacing();

    boolean isCameraBackFacing();

    void setFrontCameraId(String cameraId);

    void setBackCameraId(String cameraId);

    String getFrontCameraId();

    String getBackCameraId();

    void hideStatusBar();

    void showStatusBar();

    void hideStillshotWidgets();

    void showStillshotWidgets();

    void setTrashIconSize(int width, int height);
}
