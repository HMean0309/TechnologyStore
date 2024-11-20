package GUI.Components;

import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import java.util.LinkedHashSet;
import javax.swing.JComboBox;


public class ShowCBB {

    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    NhanVienBUS nhanVienBUS = new NhanVienBUS(); 
    public ShowCBB() {

    }
    
    public void CBBNhaCungCap(JComboBox<String> comboBox) {
        // Xóa tất cả các mục cũ trong ComboBox
        comboBox.removeAllItems();

        // Thêm mục mặc định
        comboBox.addItem("Chọn nhà cung cấp");

        // Lấy danh sách nhà cung cấp từ NhaCungCapBUS
        LinkedHashSet<NhaCungCapDTO> nhaCungCapSet = nhaCungCapBUS.getAllNhaCungCap();

        // Thêm tên các nhà cung cấp vào ComboBox
        for (NhaCungCapDTO nhaCungCap : nhaCungCapSet) {
            comboBox.addItem(nhaCungCap.getName());
        }
    }
    
    public void CBBNhanVienNhap(JComboBox<String> comboBox){
        
        comboBox.removeAllItems();
        
        comboBox.addItem("Chọn nhân viên nhập");
        
        LinkedHashSet<NhanVienDTO> nhanVienSet = nhanVienBUS.getAllNhanVien();
        
        for (NhanVienDTO nhanVien : nhanVienSet) {
            comboBox.addItem(nhanVien.getName());
        }
    }

    public void CBBKhachHang(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        
        comboBox.addItem("Chọn khách hàng");
        
        LinkedHashSet<KhachHangDTO> khachHangSet = khachHangBUS.getAllKhachHang();
        
        for (KhachHangDTO khachHang : khachHangSet) {
            comboBox.addItem(khachHang.getName());
        }
    }
    
    
}
