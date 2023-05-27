package com.example.tj_music.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class PythonUtils {
    public Result usePython(String originPath, String singPath) {
        try {
            File workingDirectory = new File("/root/gitcode/music_score");
            ProcessBuilder pb = new ProcessBuilder("/root/miniconda3/envs/music/bin/python3", "main.py", originPath, singPath);
//            File workingDirectory = new File("/root/TJ_music");
//            ProcessBuilder pb = new ProcessBuilder("/opt/homebrew/bin/python3", "test.py", s, "arg2");
            pb.directory(workingDirectory);
            Process process = pb.start();
            InputStream inputStream = process.getInputStream();

            String currentPath = System.getProperty("user.dir");
            System.out.println("Current Path: " + currentPath);

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder preciseScore = new StringBuilder();
            StringBuilder qualityScore = new StringBuilder();
            StringBuilder pitchScore = new StringBuilder();

            while ((line = in.readLine()) != null) {
                switch (line) {
                    case "precise_score":
                        preciseScore.append(in.readLine());
                        break;
                    case "quality_score":
                        qualityScore.append(in.readLine());
                        break;
                    case "pitch_score":
                        pitchScore.append(in.readLine());
                        break;
                }
            }
            in.close();

            // 等待脚本执行完毕并获取退出代码
            int exitCode = process.waitFor();
            String scriptOutput = preciseScore.toString() + " " + qualityScore.toString() + " " + pitchScore.toString();

//            System.out.println("Python script exited with code: " + exitCode);
            System.out.println("Script output: " + scriptOutput);
            Map<String, String> scoreList = new HashMap<>();
            scoreList.put("preciseScore", preciseScore.toString());
            scoreList.put("qualityScore", qualityScore.toString());
            scoreList.put("pitchScore", pitchScore.toString());

            return Result.success(scoreList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Result.fail("fail");
    }
}
