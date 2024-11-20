package GUI.Components;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import java.util.LinkedHashSet;
import javax.swing.JComboBox;

public class LocTrongPhieuNhap {

    private NhaCungCapBUS nhaCungCapBUS;

    public LocTrongPhieuNhap() {
        nhaCungCapBUS = new NhaCungCapBUS();
    }

    public void populateCBBNhaCungCap(JComboBox<String> comboBox) {
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
}
