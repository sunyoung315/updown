package com.ssafy.updown.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.updown.model.dto.Img;
import com.ssafy.updown.model.dto.User;
import com.ssafy.updown.model.service.UserService;

@RestController
@RequestMapping("/updown")
public class UserFileController {

	// resource 경로를 가져오기 위해 ResourcesLoader를 주입받는다.
	@Autowired
	ResourceLoader resLoader;

	@Autowired
	UserService uService;
	
	// C드라이브에 upload파일 만들어야 코드 아래 메서드 실행됨
	static File dir = new File("C:/upload");

//	 * '/img/regist' 요청이 post 방식으로 들어왔을 때 전달된 User 객체를 regist_result로 연결한다. 
//	 * 요청에 이미지 파일이 포함되어있을 경우, resources/upload 폴더 밑에 파일을 저장하도록 한다.
//	 * 같은 이름의 파일이 여러개 있을 경우를 위해서 업로드한 시간(밀리초단위)를 파일 이름 앞에 추가한다.
//	 * 중복방지 처리가된 파일이름을 img, 원래 파일이름을 orgImg로 한다.
	
	// 이미지 업로드
	@PostMapping(value = "/img/regist", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Img> imgRegist(@RequestPart("file") MultipartFile file) throws IllegalStateException, IOException {

		Img img = new Img();
		// 중복방지를 위해 파일 이름앞에 현재 시간 추가
		img.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
		// User 객체에 원본 파일 이름 저장
		img.setOrgImg(file.getOriginalFilename());
		System.out.println(img);
		// // 파일 저장
		file.transferTo(new File(dir, img.getImg()));
		return new ResponseEntity<Img>(img, HttpStatus.OK);
	}
	
	
	
//	@PostMapping(value = "/img/regist", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<User> imgRegist(@ModelAttribute User user, @RequestPart("file") MultipartFile file,
//			Model model) throws IllegalStateException, IOException {
//
//		// 중복방지를 위해 파일 이름앞에 현재 시간 추가
//		user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
//		// User 객체에 원본 파일 이름 저장
//		user.setOrgImg(file.getOriginalFilename());
//		System.out.println(user);
//		// DB에 저장
//		uService.signUp(user);
//
//		// // 파일 저장
//		file.transferTo(new File(dir, user.getImg()));
//		model.addAttribute("user", user);
//
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}

}
