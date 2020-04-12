package cn.walking.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        // 1 创建一对象 ，在内存中图片（验证码图片对象）
        //
        //
        // 三个参数：宽、高、颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        // 2 美化图片：获取画图对象、填充背景色
        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.gray);
        //填充图片
        g.fillRect(0,0,width,height);

        //产生4个随机验证码，****
        String checkCode = getCheckCode();
        //将验证码放入HttpSession
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的大小
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片格式，如 PNG JPG等等
        //参数三：
        ImageIO.write(image,"jpg",response.getOutputStream()); //将图片输出到页面展示  没有图片时可以用 ImageIO 对象来暂代
    }
    /**
     *产生 4位随机字符串
     */
    private String getCheckCode(){
        String  base = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz0123456789";
        int size = base.length();
        //生成随机角标
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1;i <= 4;i++){
            //产生 0 到 size-1 的随机值
            int index =  ran.nextInt(size);
            //在base 字符串中获取下标为 index的字符
            char c = base.charAt(index);
            //将 c 放入到 StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
