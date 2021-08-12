package com.mybury.bucketlist.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.util.FileUtil;

import java.util.UUID;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

  @Override
  public String upload(MultipartFile file) {
    String extension = FileUtil.getExtension(file.getOriginalFilename());
    String saveFileName = String.format("%s.%s", UUID.randomUUID().toString(), extension);
    String uploadDate = DateUtil.getDateString("yyyyMMdd");

    FileUtil.upload(file, String.format("%s/%s", "/DATA/WEB/mybury/storage", uploadDate), saveFileName);
    return String.format("/%s/%s", uploadDate, saveFileName);
  }
}
