package JIKE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-5
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class JIKERedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("name");
        System.out.println(param);
        System.out.println(URLDecoder.decode(param,"ISO-8859-1"));
         /*  File file = new File("test.xml");
           System.out.println(file.getAbsolutePath());
           InputStream is = request.getInputStream();
           OutputStream os = new FileOutputStream(file);
           byte[]buff = new byte[1024];
           int len=0;
           while((len=is.read(buff))!=-1){
                os.write(buff, 0,len);
           }
        os.flush();
        is.close();
        os.close();*/

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String start = request.getParameter("start");
        String size= request.getParameter("size");
        URL url = new URL("http://www.jike.com/shipin/mobile/ajax/GetUnqualityFoodList?start="+start+"&size="+size);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream is = urlConnection.getInputStream();
        OutputStream os = response.getOutputStream();
        response.setContentType("application/json");
        byte[] buffer = new byte[1024];
        int len=0;
        while((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        os.flush();
        os.close();
    }
}
