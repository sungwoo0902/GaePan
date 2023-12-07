package co.kr.gaepan.controller.pet;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.service.pet.PetBoardService;
import com.nimbusds.jose.shaded.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PetUploadController {

    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public Map<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        Map<String, Object> jsonResponse = new HashMap<>();

        String fileRoot = "upload/"; // 저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

        String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
            jsonResponse.put("url", "/upload/" + savedFileName);
            jsonResponse.put("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
            jsonResponse.put("responseCode", "error");
            e.printStackTrace();
        }

        return jsonResponse;
    }

    // 서머노트 이미지 업로드 temp폴더에 저장
    /*@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "upload/";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/upload/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject.toString();
    }*/

}
