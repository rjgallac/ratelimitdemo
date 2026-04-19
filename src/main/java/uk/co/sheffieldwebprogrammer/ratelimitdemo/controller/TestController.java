package uk.co.sheffieldwebprogrammer.ratelimitdemo.controller;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }

}
