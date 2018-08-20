package com.mycompany.faceapiserver.download;

import com.mycompany.faceapiserver.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class PhotoDownloadController {

    @RequestMapping(name="/download", params={"userID"}, method=RequestMethod.GET)
    public Response downloadPhoto(String userID, HttpServletResponse out) {
        Response response = new Response();

        String fileName = userID + ".jpg";
        String fileFullPath = "d:\\baidu\\images\\" + fileName;
        File file = new File(fileFullPath);
        if (!file.exists()) {
            response.setRetCode(0);
            response.setRetMsg("file isn't exist");
            return response;
        }

        out.reset();
        out.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        try {
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = out.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, len);
            }
            outputStream.flush();
            outputStream.close();

            inputStream.close();

            response.setRetCode(1);
            response.setRetMsg("success");
        } catch (Exception e) {
            e.printStackTrace();

        }


        return response;
    }
}
