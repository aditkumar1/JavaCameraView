import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import sun.security.ssl.Debug;

import java.awt.*;

public class DisplayCameraFeed implements Runnable {
    CameraFrame frame;
    public DisplayCameraFeed(CameraFrame frame){
        this.frame=frame;
    }
    public void run() {
        VideoCapture camera = new VideoCapture(0);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH,screenSize.getWidth());
        camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT,screenSize.getHeight());
        if(!camera.isOpened()){
            Debug.println("camera error","Error Opening Camera");
        }
        else {
            Mat iframe = new Mat();
            while(true){
                if (camera.read(iframe)){
                    Debug.println("Frame Obtained","Captured Frame Width " +iframe.width() + " Height " + iframe.height());
                    frame.getCanvas().displayImage(CommonUtils.toBufferedImage(iframe));
                }
            }
        }
        camera.release();
    }
}
