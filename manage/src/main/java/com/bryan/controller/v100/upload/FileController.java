package com.bryan.controller.v100.upload;

import com.alibaba.fastjson.JSON;
import com.bryan.common.constant.ApiCodeConstant;
import com.bryan.common.constant.ApiResult;
import com.bryan.common.utils.StringUtil;
import com.bryan.common.utils.upload.UploadUtil;
import com.bryan.controller.BaseController;
import com.bryan.sys.domain.SysUser;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
@RestController
@Scope("prototype")
@RequestMapping("/api/v100/file")
public class FileController extends BaseController {

    /**
     * @Title: imgUpload
     * @Description: 上传图片
     */
    @RequestMapping(value = "/imgUpload")
    public void imgUpload(HttpServletRequest request, HttpServletResponse response,
                          MultipartFile uploadFile) throws Exception {
        ApiResult result = ApiResult.newInstance();
        String type = request.getParameter("type");
        String markTag = request.getParameter("markTag");
        if (uploadFile == null || uploadFile.isEmpty()) {
            result.setCode(ApiCodeConstant.CODE_BIZ_ERROR);
            result.setMsg("请选择文件上传");
        } else {
            String picUrl = UploadUtil.imgUpload(StringUtil.isBlank(type) ? "avatar" : type, uploadFile, markTag);
            result.setResult(picUrl);
        }
        printJson(response, JSON.toJSONString(result));
    }

    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public String dataImagePath(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                String path) throws IOException {
        SysUser user = getSessionUser();
        if (user == null) {
            return null;
        }

        //读取文件相对路径
        boolean isFile = false;
        if (path == "" || path == null) return null;
        File file = new File(path);

        //如果图片不存在 返回Null
        if (!file.exists()) {
            return null;
        }

        String[] names = path.split("/");
        String fileName = names[names.length - 1];

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream output = response.getOutputStream();// 得到输出流
        if (path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".jpeg")
                || path.toLowerCase().endsWith(".jfif")) {
            // 表明生成的响应是图片
            response.setContentType("image/jpeg");
        } else if (path.toLowerCase().endsWith(".bmp")) {//
            response.setContentType("image/bmp");
        } else if (path.toLowerCase().endsWith(".gif")) {
            response.setContentType("image/gif");
        } else if (path.toLowerCase().endsWith(".ico")) {
            response.setContentType("image/x-icon");
        } else if (path.toLowerCase().endsWith(".png")) {
            response.setContentType("image/png");
        } else if (path.toLowerCase().endsWith(".pdf")) {
            isFile = true;
            response.setContentType("application/pdf");
        } else if (path.toLowerCase().endsWith(".xls") || path.toLowerCase().endsWith(".xls")) {
            isFile = true;
            response.setContentType("application/vnd.ms-excel");
        } else if (path.toLowerCase().endsWith(".xlsm")) {
            isFile = true;
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } else if (path.toLowerCase().endsWith(".xlsx")) {
            isFile = true;
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } else if (path.toLowerCase().endsWith(".doc")) {
            isFile = true;
            response.setContentType("application/msword");
        } else if (path.toLowerCase().endsWith(".docm")) {
            isFile = true;
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        } else if (path.toLowerCase().endsWith(".docx")) {
            isFile = true;
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        } else {
            return null;
        }
        if (isFile) {//文件名称
            String agent = request.getHeader("User-Agent").toLowerCase();
            if (agent.indexOf("firefox") > -1) {
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + new String(fileName.getBytes("utf-8"), "ISO8859-1") + "\"");
            } else if (agent.indexOf("MSIE") > -1 || agent.indexOf("edge") > -1 ||
                    (agent.indexOf("trident") > -1 && agent.indexOf("rv") > -1)) {
                //ie浏览器
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
            } else {
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            }
        }
        InputStream imageIn = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
        byte data[] = new byte[4096];// 缓冲字节数
        int size = 0;
        while ((size = bis.read(data)) != -1) {
            bos.write(data, 0, size);
        }
        bis.close();
        bos.flush();// 清空输出缓冲流
        bos.close();
        output.flush();
        output.close();
        return null;
    }

    /**
     * docx等文件上传
     *
     * @param request
     * @param response
     * @param uploadFile
     * @throws Exception
     * @Title: fileUpload
     */
    @RequestMapping(value = "/fileUpload")
    public void fileUpload(HttpServletRequest request, HttpServletResponse response,
                           MultipartFile uploadFile) throws Exception {
        ApiResult result = ApiResult.newInstance();
        if (uploadFile == null || uploadFile.isEmpty()) {
            result.setCode(ApiCodeConstant.CODE_BIZ_ERROR);
            result.setMsg("请选择文件上传");
        } else {

            String filePath = "/data/docx/" + System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
            File destFile = new File(filePath);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            uploadFile.transferTo(destFile);
            result.setResult(filePath);
        }
        printJson(response, JSON.toJSONString(result));
    }

    /**
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     * @Title: loadExcelTemplate
     * @Description: 下载后台导入excel模版
     */
    @RequestMapping(value = "/loadExcelTemplate/{fileName}", method = RequestMethod.GET)
    public String loadExcelTemplate(HttpServletRequest request, HttpServletResponse response
            , @PathVariable("fileName") String fileName) throws IOException {
        fileName = fileName + ".xlsx";
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream output = response.getOutputStream();// 得到输出流
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("firefox") > -1) {
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1") + "\"");
        } else if (agent.indexOf("MSIE") > -1 || agent.indexOf("edge") > -1 ||
                (agent.indexOf("trident") > -1 && agent.indexOf("rv") > -1)) {
            //ie浏览器
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
        } else {
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        }
        InputStream in = this.getClass().getResourceAsStream("/msg/" + fileName);
        BufferedInputStream bis = new BufferedInputStream(in);// 输入缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
        byte data[] = new byte[4096];// 缓冲字节数
        int size = 0;
        while ((size = bis.read(data)) != -1) {
            bos.write(data, 0, size);
        }
        bis.close();
        bos.flush();// 清空输出缓冲流
        bos.close();
        output.flush();
        output.close();
        return null;
    }

    /**
     * 返回数据2
     *
     * @param response
     */
    private void printJson(HttpServletResponse response, String responseObject) {
        // 将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(responseObject);
            out.flush();
        } catch (IOException ignored) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
