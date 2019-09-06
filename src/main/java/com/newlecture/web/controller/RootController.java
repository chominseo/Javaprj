package com.newlecture.web.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.newlecture.web.dao.FileDao;

@Controller
@RequestMapping("/")
public class RootController {
	
	@Autowired
	private FileDao fileDao;
	
	@GetMapping("file-list")	
	@ResponseBody
	public List<com.newlecture.web.entity.File> fileList(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ServletContext application = request.getServletContext();
		String urlPath = "/upload";
		String realPath = application.getRealPath(urlPath);
		
		//��� 1 : �츮�� �������� JSON ���ڿ�
//		String jsonList = fileDao.getJSONList(realPath);
//		return jsonList;
		
		//��� 2 : Gson�� �̿��� JSON ���ڿ�
		List<com.newlecture.web.entity.File> list = fileDao.getList(realPath);
	      
	    //Gson gson = new Gson();
	      
	    //return gson.toJson(list);
		
		//��� 3 : �׳� ��ü�� ��ȯ�غ���
		
		return list;
	}
	
//	@Autowired
//	private NoticeDao noticeDao;
	
	/*
	@RequestMapping("index")
	//@ResponseBody
	public String index(Model model) {
		
		model.addAttribute("x","hello,hello");
		
		return "index";
	}
	
	@RequestMapping("index")
	public void index(HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("Welcome Home!!!!");
		
	}
	 */
	
	
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) throws IOException{
		
		// 1. ���ε� ��θ� ���
				String urlPath = "/upload";
				String path = request.getServletContext().getRealPath(urlPath);

				System.out.println(path);

				// 2. ���ε�� ���ϸ� ���
				String fileName = file.getOriginalFilename();

				// 3. ��� ������ �ֱ�
				String filePath = path + File.separator + fileName; // d:\aa + "bb.jpg" -> d:\aabb.jpg

				System.out.println(filePath);

				// 4. ��ΰ� ���ٴ� ���� ����
				File pathFile = new File(path);
				if (!pathFile.exists()) // �������� ������
					pathFile.mkdirs();// �������ּ���.

				// 5. ������ ���ϸ��� ��ο� �̹� �����ϴ� ���� : �̸� ��å
				// aa.jpg -> aa.jpg1 ==> aa1.jpg
				// aa1.jpg -> aa(1).jpg
				/*
				 * File ? = new File(?);
				 * 
				 * if(? �����Ѵٸ�) { ����(Ȯ����)�� �߶� �̸��� ��� �� �������� �Ұ�ȣ()�� �ִ��� Ȯ���ϰ� ������ ��ȣ�� �˾Ƴ��� 1������ ����
				 * ��.. fileName = ?; }
				 */

				File sameFile = new File(filePath);
				System.out.println(sameFile);
				if (sameFile.exists()) {

					int n = fileName.lastIndexOf("."); // fileName=hello.jpg n =?, name= , suffix,
					String name = fileName.substring(0, n); //
					String suffix = fileName.substring(n);

					// name.endsWith(")");
					// int parenS = name.lastIndexOf("(");
					// int parenE = name.lastIndexOf(")");

					// String indexC = name.substring(parenS+1, parenE);

					// int indexN = Integer.parseInt(indexC);

					// if (parenS == -1)
					fileName = name + "(" + 1 + ")" + suffix;
					// else {
					// indexN++;
					// fileName = fileName.substring(0, parenS+1)+ indexN +")"+ suffix;
					// }
				}

				InputStream fis = file.getInputStream();
				OutputStream fos = new FileOutputStream(filePath);

				byte[] buf = new byte[1024];
				int size = 0;
				while ((size = fis.read(buf)) != -1)
					fos.write(buf, 0, size);

				fis.close();
				fos.close();
		
		
		//return "Welcome Home!!!!";
		return "ok";
	
	}
	
	@RequestMapping("index")
	//@ResponseBody
	public String index(){
		
		//return "Welcome Home!!!!";
		return "root.index";
	
	}
	@RequestMapping("hello")
	public void aaa() {
		
		System.out.println("hello ��û�� �־����ϴ�.");
		
	}


}