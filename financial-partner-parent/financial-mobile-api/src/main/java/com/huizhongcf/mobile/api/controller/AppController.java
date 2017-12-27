package com.huizhongcf.mobile.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huizhongcf.mobile.api.framework.BaseController;

@Controller
@RequestMapping("/app")
public class AppController extends BaseController{
	/**
	 * 首页用户数据统计
	 * @return
	 * @author zhoujie
	 * @date 2017年12月14日 下午5:20:26
	 */
	@RequestMapping("/version")
	@ResponseBody
	public Object version(){
			String version = getVersion();
			log.info("version="+version);
			String  updataVersions = "0.0.2";
			String  forceUpdataVersions = "0.0.1";
			String isUpdate ="N";
			String force ="N";
			HttpServletRequest request = getRequest();
			String uri = request.getRequestURI().toString();
			String url = request.getRequestURL().toString().replace(uri, "");
			List<String> desList = new ArrayList<String>();
			if(isIOS()) {
				url="http://www.baidu.com/iso";
				desList.add("1.ios更新");
				desList.add("2.ios更新");
				if(forceUpdataVersions.contains(version)) {
					isUpdate="Y";
					force="Y";
				}else if(updataVersions.contains(version)) {
					isUpdate="Y";
				}
			}else if(isAndroid()) {
				url=url+"/app/download";
				desList.add("1.isAndroid 更新");
				desList.add("2.isAndroid 更新");
				if(forceUpdataVersions.contains(version)) {
					isUpdate="Y";
					force="Y";
				}else if(updataVersions.contains(version)) {
					isUpdate="Y";
				}
			}
		 Map<String,Object> retMap = new HashMap<String, Object>();
		 retMap.put("isUpdate", isUpdate);
		 retMap.put("force", force);
		 retMap.put("url", url);	
		 retMap.put("des", desList.toArray());
		 retMap.put("newVersion", "1.0.0");
		return renderSuccess(retMap);
		 
	}
	@RequestMapping("/download")
	public Object download(HttpServletResponse response){
		HttpServletRequest request = getRequest();
		String userAgent = request.getHeader("user-agent");
		log.info("代理   " + userAgent);
		String userAgentLowerCase = userAgent.toLowerCase();
		//判断pc浏览器
		String uri = request.getRequestURI().toString();
		String url = request.getRequestURL().toString().replace(uri, "");
		String[] pcString=new String[]{ "windows","Ubuntu","mac"};
		if(StringUtils.isBlank(getHeader().get("os"))) {
			for (String string : pcString) {
				if(userAgentLowerCase.contains(string)&& !userAgentLowerCase.contains("mobile")){
					log.info("来源pc");
					return "redirect:"+url;
				}
			}
		}
		boolean isAndroid = userAgentLowerCase.contains("android");
		if (userAgentLowerCase.contains("micromessenger")) {
			// 微信浏览器
			log.info("微信浏览器");
			if (isAndroid) {
				getRequest().setAttribute("isAndroid", true);
			} else {
				getRequest().setAttribute("isAndroid", false);
			}
			return "redirect:http://a.app.qq.com/o/simple.jsp?pkgname=cn.mianbaoyun.agentandroidclient";
		}
		if (isAndroid||isAndroid()) {
			log.info("安卓用户");
			String realPath = request.getSession().getServletContext().getRealPath("/software/");
			File file = new File(realPath);
			if (file.isDirectory()) {
				String[] list = file.list();
				Arrays.sort(list);
				if (list != null && list.length > 0) {
					log.info("获取最新版本");
					String fileName = list[list.length - 1];
					try {
						downloadLocal(response, new File(realPath+"/"+fileName));
						return null;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			return "redirect:"+url;
		} else {
			log.info("ios用户");
			return "redirect:https://itunes.apple.com/us/app/%E9%9D%A2%E5%8C%85%E4%BA%91-%E6%95%B4%E5%90%88%E8%90%A5%E9%94%80%E6%9C%8D%E5%8A%A1%E5%B9%B3%E5%8F%B0/id1220366580?l=zh&ls=1&mt=8";
		}

	}
	public void downloadLocal(HttpServletResponse response,File file) throws FileNotFoundException {
        // 下载本地文件
        String fileName = file.getName(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream(file);// 文件的存放路径
        // 设置输出的格式
//        response.reset();
        response.addHeader("Content-Length", "" + file.length());   
        response.setContentType("application/force-download");
        response.setContentLength((int)file.length());
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
        	ServletOutputStream outputStream = response.getOutputStream();
            while ((len = inStream.read(b)) > 0) {
				outputStream.write(b, 0, len);
			}
            outputStream.flush();
            inStream.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
	 
}
