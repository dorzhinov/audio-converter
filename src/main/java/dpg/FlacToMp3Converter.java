package dpg;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.util.Arrays;

import static java.lang.System.out;

public class FlacToMp3Converter {

    private static final String FLAC_EXT = ".flac";
    private static final String MP3_EXT = ".mp3";
    private static final String INPUT = "Input:  ";
    private static final String OUTPUT = "Output: ";

    private static int count = 0;

    public static void main(String[] args) {
        String path;
        if (args.length > 0) {
            path = args[0];
        } else {
            path = System.getProperty("user.dir");
        }

        processFile(new File(path));

        out.println("Number of converted files: " + count);
    }

    private static void processFile(File file) {
        if (file.isFile() && file.getName().toLowerCase().endsWith(FLAC_EXT)) {
            convert(file);
        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                Arrays.stream(children).forEach(FlacToMp3Converter::processFile);
            }
        }
    }

    private static void convert(File source) {
        out.println(INPUT + source);

        String absolutePath = source.getAbsolutePath();
        String mp3absolutePath = absolutePath.substring(0, absolutePath.length() - FLAC_EXT.length())
                + MP3_EXT;
        File target = new File(mp3absolutePath);
        out.println(OUTPUT + target);

        convert(source, target);
    }

    private static void convert(File source, File target) {
        ConvertProgressListener listener = new ConvertProgressListener();

        try {
            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(320000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs, listener);

            count++;
        } catch (Exception ex) {
            // skip failed files
            out.println("Error while converting file " + source);
            ex.printStackTrace(out);
        }
    }
}
