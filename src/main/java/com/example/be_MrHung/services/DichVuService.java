package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.DichVu;

import com.example.be_MrHung.repository.DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DichVuService {
    @Autowired
    private DichVuRepository dichVuRepository;

    public ResponseData<DichVu> getAllDichVu(){
        return new ResponseData(HttpStatus.OK, "success", dichVuRepository.findAll());
    }

    public ResponseData<DichVu> createDichvu(DichVu dichVu) {
        try {
            // Validate dữ liệu đầu vào
            if (dichVu.getService_name() == null || dichVu.getService_name().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Tên dịch vụ không được để trống", null);
            }

            // Lưu phim vào database
            DichVu savedDichvu = dichVuRepository.save(dichVu);

            return new ResponseData<>(HttpStatus.CREATED, "Thêm dịch vụ thành công", savedDichvu);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi thêm: " + e.getMessage(), null);
        }
    }
    public ResponseData<DichVu> deleDichvuById(int id) {
        try {
            DichVu dichVuToDelete = dichVuRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy dịch vụ với ID: " + id));
            dichVuRepository.deleteById(id);
            return new ResponseData<>(HttpStatus.CREATED, "Xóa thành công",dichVuToDelete);
        }
        catch (Exception e) {
            // Xử lý lỗi nếu có
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi xóa: " + e.getMessage(), null);
        }
    }

    public ResponseData<DichVu> updateDichvu( int id, DichVu dichVu ) {
        try {
            DichVu dichvuExisting = dichVuRepository.findById(dichVu.getService_id())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy dịch vụ với ID: " + dichVu.getService_id()));

            if (dichVu.getService_id() != id) {
                throw new IllegalArgumentException("ID trong đường dẫn không khớp với ID trong body");
            }

            dichvuExisting.setService_name(dichVu.getService_name());
            dichvuExisting.setService_price(dichVu.getService_price());
            dichvuExisting.setService_description(dichVu.getService_description());

            DichVu updatedDichVu = dichVuRepository.save(dichvuExisting);

            return new ResponseData<>(HttpStatus.CREATED, "Cap nhat thanh cong", updatedDichVu);
        }
        catch (IllegalArgumentException e) {
            return new ResponseData<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi cập nhật: " + e.getMessage(), null);
        }

    }


}
