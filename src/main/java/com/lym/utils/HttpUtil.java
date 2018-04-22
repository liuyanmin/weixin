package com.lym.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class HttpUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 发送https请求
     * @param requestUrl 请求路径
     * @param requestMethod 请求类型，例如: GET POST等
     * @param outputStr 请求数据
     * @return 返回字符串
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr){
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            LOGGER.error("链接超时: " + ce.getMessage());
            ce.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("https请求异常: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送https请求
     * @param requestUrl 请求路径
     * @param requestMethod 请求类型，例如: GET POST等
     * @param outputStr 请求数据
     * @return 返回字节数组
     */
    public static byte[] httpsRequestResByte(String requestUrl, String requestMethod, String outputStr){
        byte[] buffer = new byte[1024];
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = conn.getInputStream();
            int len = -1;
            while((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.close();
            inputStream.close();
            conn.disconnect();
            return outputStream.toByteArray();
        } catch (ConnectException ce) {
            LOGGER.error("链接超时: " + ce.getMessage());
            ce.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("https请求异常: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
