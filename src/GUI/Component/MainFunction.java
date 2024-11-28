package GUI.Component;

import BUS.ChiTietQuyenBUS;
import DTO.ChiTietQuyenDTO;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();
    private final ChiTietQuyenBUS busChiTietQuyen = new ChiTietQuyenBUS();

    public MainFunction(String idQuyen, String idChucNang, String[] listBtn) {
        initData();
        initComponent(idQuyen, idChucNang, listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", "delete"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
        btn.put("import", new ButtonToolBar("NHẬP EXCEL", "import_excel.svg", "create"));
        btn.put("export", new ButtonToolBar("XUẤT EXCEL", "export_excel.svg", "view"));
        btn.put("view", new ButtonToolBar("XEM DS", "phone.svg", "view"));
    }

    private void initComponent(String idQuyen, String idChucNang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String btnItem : listBtn) {
            this.add(btn.get(btnItem));
            if (!busChiTietQuyen.checkPermission(new ChiTietQuyenDTO(idQuyen, idChucNang, btn.get(btnItem).getPermisson()))) {
                btn.get(btnItem).setEnabled(false);
            }
        }
    }
}
