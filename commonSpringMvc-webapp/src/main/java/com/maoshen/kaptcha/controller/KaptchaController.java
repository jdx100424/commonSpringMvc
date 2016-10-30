//package com.maoshen.kaptcha.controller;
//
//import java.awt.image.BufferedImage;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.google.code.kaptcha.Producer;
//import com.maoshen.kaptcha.constance.KaptchaConstance;
//
//@Controller
//@RequestMapping("/kaptcha")
//public class KaptchaController {
//    private Producer            captchaProducer     = null;
//
//    @Autowired
//    public void setCaptchaProducer(Producer captchaProducer) {
//        this.captchaProducer = captchaProducer;
//    }
//
//    /**
//     * 
//     * @Description: 检测是否登录
//     * @author Daxian.jiang
//     * @Email Daxian.jiang@vipshop.com
//     * @Date 2015年9月24日 上午9:38:15
//     * @param request
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/getKaptcha", method = { RequestMethod.POST, RequestMethod.GET})
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        response.setDateHeader("Expires", 0);
//        // Set standard HTTP/1.1 no-cache headers.  
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        // Set standard HTTP/1.0 no-cache header.  
//        response.setHeader("Pragma", "no-cache");
//        // return a jpeg  
//        response.setContentType("image/jpeg");
//        // create the text for the image  
//        String capText = captchaProducer.createText();
//        // store the text in the session  
//        request.getSession().setAttribute(KaptchaConstance.KAPTCHA_SESSION_KEY, capText);
//        // create the image with the text  
//        BufferedImage bi = captchaProducer.createImage(capText);
//        ServletOutputStream out = response.getOutputStream();
//        // write the data out  
//        ImageIO.write(bi, "jpg", out);
//        try {
//            out.flush();
//        } finally {
//            out.close();
//        }
//        return null;
//    }
//}
