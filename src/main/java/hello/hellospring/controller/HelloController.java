package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //정적 콘텐츠 -> 정적 리소스파일을 클라이언트로 전송하는 방식
    @GetMapping("hello")
    public String hello(Model model){
    model.addAttribute("data", "Hello!!");
    return "hello";
    }

    //MVC방식,템플린엔진 -> 브라우저에서 요청이오면 controller에서 해당 메서드를 찾는다.
    //브라우저의 요청에 따라 해당 메서드를 실행한다. 아래를 예를 들면 name=spring이라는 요청이오면 아래의 name이 스프링이 되고 모델이 격납
    //그리고 리턴에 있는 템플릿으로 넘어가서 해당 템플릿의 유형에 맞게 데이터가 들어가서 출력
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody는 해당 메서드의 리턴값을 그대로 http상으로 전달하겠다는 것
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //게터세터를 통해서 캡슐화를 유지하는 것
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
