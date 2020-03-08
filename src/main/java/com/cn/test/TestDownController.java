package com.cn.test;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2020/3/8.
 */
@RestController
public class TestDownController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean download(@RequestParam(value = "id") String id, HttpServletResponse response) {
        String path = "1".equals(id) ? "d://test.docx" : "";
        if (StringUtils.isEmpty(path)) {
            return ResultBean.error("没有此文件");
        }
        File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        try {
            // 以流的形式下载文件。
            InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream outstream = response.getOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                outstream.write(b, 0, n);
            }
            outstream.flush();
            outstream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultBean.success("文件下载成功");
    }
}
