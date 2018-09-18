package dpg;

import ws.schild.jave.EncoderProgressListener;
import ws.schild.jave.MultimediaInfo;

public class ConvertProgressListener implements EncoderProgressListener {

    private static final char CR = '\r';

    ConvertProgressListener() {
    }

    public void message(String m) {
        System.out.println(m);
    }

    public void progress(int p) {
        //Find %100 progress
        int progress = p / 10;
        System.out.print(CR);
        System.out.print(progress + "%");
        if (progress == 100) {
            System.out.print(CR);
        }
    }

    public void sourceInfo(MultimediaInfo m) {
        //code
    }
}