package com.socialapp.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FFmpegService {
    public File compressMedia(File inputFile, String outputPath) throws IOException, InterruptedException {
        File outputFile = new File(outputPath);
        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-i", inputFile.getAbsolutePath(),
                "-vcodec", "libx264",
                "-crf", "28",
                outputFile.getAbsolutePath()
        );
        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) throw new RuntimeException("FFmpeg compression failed");
        return outputFile;
    }
}

