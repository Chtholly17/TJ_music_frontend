package com.example.tj_music.controller;

import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tj_music.utils.PythonUtils;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class scoringController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private PythonUtils pythonUtils;

    @PostMapping("/usePython")
    public Result usePython(@RequestParam("originPath") String originPath, @RequestParam("singPath") String singPath) {
        return pythonUtils.usePython(originPath, singPath);
    }

}