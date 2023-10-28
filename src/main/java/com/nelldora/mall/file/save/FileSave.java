package com.nelldora.mall.file.save;

import com.nelldora.mall.file.domain.UploadFile;
import com.nelldora.mall.file.vo.TransFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileSave {

    // 파일명 변환 할 때 필요한 순서
    //1. 확장자 분리하기
    //2. 서버 저장용 파일명 부여하기
    //3. 경로에 저장하기
    //
    String fileDir;

    public String getFullPath(String fileName){
        return fileDir+fileName;
    }

    public String extraExt(String originalFileName){
        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos+1);
        return ext;
    }

    public String nameConverter(String originalFIleName){
        String ext = extraExt(originalFIleName);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;
    }

    public TransFile saveFile(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String serverFileName = nameConverter(originalFileName);
        multipartFile.transferTo(new File(getFullPath(serverFileName)));
        return new TransFile(originalFileName, serverFileName);
    }

    public List<TransFile> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<TransFile> transFileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            if (!multipartFile.isEmpty()){
                transFileList.add(saveFile(multipartFile));
            }
        }
        return transFileList;
    }

}
