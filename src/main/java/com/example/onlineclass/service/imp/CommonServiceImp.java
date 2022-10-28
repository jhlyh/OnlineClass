package com.example.onlineclass.service.imp;

import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.props.flagInterface.Props;
import com.example.onlineclass.service.CommonService;
import com.example.onlineclass.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xbib.io.ftp.client.FTP;
import org.xbib.io.ftp.client.FTPClient;
import org.xbib.io.ftp.client.FTPReply;

import java.util.*;

/**
 * @author jhlyh
 */
@Service
public class CommonServiceImp implements CommonService {

    private final CommonProps commonProps;

    public CommonServiceImp(CommonProps commonProps) {
        this.commonProps = commonProps;
    }

    /**
     * 上传文件方法实现
     * 首先连接FTP，
     * 再将要上传的文件二进制话，
     * 再返回上传的地址
     * @param multipartFile
     * @param path
     * @return
     */
    @Override
    public Result<?> uploadFile(MultipartFile multipartFile, String path) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;

        int port = Integer.parseInt(commonProps.getFtpPort());
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            ftpClient.connect(commonProps.getFtpHost(), port);
            ftpClient.login(commonProps.getFtpUserName(), commonProps.getFtpPassword());
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return Result.error(commonProps.getFrontEndError(), String.valueOf(result));
            }
            ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName, multipartFile.getInputStream());
        } catch (Exception e) {
            return Result.success(e.toString());
        }

        return Result.success(commonProps.getNginxPath() + fileName);
    }

    /**
     * 将仅仅是排序分页功能进行内聚，降低重复性代码
     * @param page
     * @param size
     * @param sort
     * @param jpaRepository
     * @param props
     * @return
     */
    @Override
    public Map<String, Object> getAllPage(int page, int size, String[] sort, JpaRepository jpaRepository, Props props) {
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[props.getSortDirectionIndex()]), _sort[props.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[props.getSortDirectionIndex()]), sort[props.getTheSortByIndex()]));
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Object> aPage;
            aPage = jpaRepository.findAll(pageable);
            List<Object> objects = aPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put(props.getReturnDomain(), objects);
            response.put(props.getReturnCurrentPage(), aPage.getNumber());
            response.put(props.getReturnTotalPages(), aPage.getTotalPages());
            response.put(props.getReturnTotalItems(), aPage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}
