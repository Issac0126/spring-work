package com.spring.myweb.snsboard.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SnsBoardService implements ISnsBoardService {
	@Autowired
	private ISnsBoardMapper mapper;
	
	
	@Override
	public void insert(SnsBoardVO vo, MultipartFile file) {
		
		//날짜별로 폴더를 생성해서 관리할 에정
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String fileLoca = now.format(dtf); 
		
		//기본 경로는 C:/test/upload로 사용!
		String uploadPath = "C:/test/upload/";
		
		//폴더가 없다면 새롭게 생성할 것이다.
		File folder = new File(uploadPath+fileLoca);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		//저장될 파일명은 UUID를 이용한 파일명으로 저장한다.
		//UUID가 제공하는 랜덤 문자열에 -를 제거해서 전부 사용할 예정.
		String fileRealName = file.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", ""); //하이픈 전부 제거
		
		
		//확장자 추출
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
		log.info("저장할 폴더 경로: "+uploadPath+fileLoca);
		log.info("실제 파일명: "+fileRealName);
		log.info("확장자: "+fileExtension);
		log.info("고유 랜덤 문자: "+uuids);
		
		String fileName = uuids+fileExtension;
		log.info("변경해서 저장할 파일명: "+fileName);
		
		
		//업로드한 파일을 지정한 로컬 경로로 전송
		File saveFile = new File(uploadPath+fileLoca+"/"+fileName);
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		vo.setUploadPath(uploadPath);
		vo.setFileLoca(fileLoca);
		vo.setFileName(fileName);
		vo.setFileRealName(fileRealName);
		
		mapper.insert(vo);
		
	}

	@Override
	public List<SnsBoardVO> getList(PageVO paging) {
		return mapper.getList(paging);
	}

	@Override
	public SnsBoardVO getDetail(int bno) {
		return mapper.getDetail(bno);
	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);
	}

}
