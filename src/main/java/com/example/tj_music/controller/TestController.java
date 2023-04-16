package com.example.tj_music.controller;


import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static Logger log = Logger.getLogger("TestController.class");

    /**
     * post test api
     * post a string to the server, and return the string
     * @param test_string the string to be returned
     * @return test_string
     */
    @PostMapping("/test_return_string")
    public Result testReturnString(String test_string){
        // test whether the data is null
        if(test_string == null){
            return Result.fail("data is null");
        }
        return Result.success(test_string);
    }

    /**
     * get test api
     * return the helloworld string
     */
    @GetMapping("/get_hello_world")
    public Result getHelloWorld(){
        return Result.success("hello world");
    }

    /**
     * post test api with two strings
     * return the two strings concatenated
     */
    @PostMapping("/get_concat_string")
    public Result getConcatString(String string1, String string2){
        // test whether the data is null
        if(string1 == null || string2 == null){
            return Result.fail("data is null");
        }
        return Result.success(string1 + string2);
    }

}
