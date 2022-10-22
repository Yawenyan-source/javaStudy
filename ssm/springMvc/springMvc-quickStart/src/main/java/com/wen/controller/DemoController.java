package com.wen.controller;

import com.wen.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Wen
 * @since 2022年10月12日 11:10 PM
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /*    */

    /**
     * 写在此处，只对当前controller生效
     *
     * @param exception
     * @param request
     * @param response
     *//*
    @ExceptionHandler(ArithmeticException.class)
    public void handleException(Exception exception, HttpServletRequest request, HttpServletResponse response){
        //异常处理逻辑
        try {
            response.getWriter().write(exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    @RequestMapping("/handle01")
    public ModelAndView dateHandle01(@ModelAttribute("name") String name) {
        Date date = new Date(); //服务器时间
        //封装了数据和页面信息
        ModelAndView mv = new ModelAndView();
        //addObject 向请求域中放入了属性值 相当于request.setAttribute()
        mv.addObject("date", date);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping(value = "/handle07", method = {RequestMethod.POST})
    @ResponseBody
    public User demoHandle07(@RequestBody User user) {
        user.setName("站三封");
        return user;
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView upload(MultipartFile uploadFile, HttpSession session) throws IOException {
        //获取原始文件名
        String originalFilename = uploadFile.getOriginalFilename();
        //获取拓展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        //拼接新文件名
        String newFileName = UUID.randomUUID() + "." + ext;
        //存储到指定的文件夹 /uploads 考虑文件过多的情况，按照日期，生成一个子文件夹

        //真实磁盘路径
        String realPath = session.getServletContext().getRealPath("/uploads");
        System.out.println(realPath);
        //按日期创建文件夹
        String dataPath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + dataPath);
        //创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //存储文件
        uploadFile.transferTo(new File(folder, newFileName));
        Date date = new Date(); //服务器时间
        //封装了数据和页面信息
        ModelAndView mv = new ModelAndView();
        //addObject 向请求域中放入了属性值 相当于request.setAttribute()
        mv.addObject("date", date);
        mv.setViewName("success");
        return mv;
    }

    /**
     * SpringMVC转发和重定向
     *
     * @return
     */
    @RequestMapping("/handlerRedirect")
    public String handlerRedirect(String name, RedirectAttributes redirectAttributes) {
        //使用flash属性 暂存到session中，跳转到页面后会销毁
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:handle01";
    }
}
