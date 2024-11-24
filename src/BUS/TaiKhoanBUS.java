package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaiKhoanBUS {
    public static String[] typeSearch = {
            "Tất cả", "Username", "Email",
    };
    public static String[] duplicateMess = {
            "Username đã tồn tại. Vui lòng chọn username khác!"
    };
    private LinkedHashSet<TaiKhoanDTO> setTK;
    private TaiKhoanDAO daoTK;

    public TaiKhoanBUS() {
        setTK = new LinkedHashSet<>();
        daoTK = new TaiKhoanDAO();

        setTK = TaiKhoanBUS.toSet(daoTK.getAllTaiKhoan());
        daoTK.closeDB();
    }

    public static TaiKhoanBUS getInstance() {
        return new TaiKhoanBUS();
    }

    public static LinkedHashSet<TaiKhoanDTO> toSet(ResultSet rs) {
        LinkedHashSet<TaiKhoanDTO> setTK = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                TaiKhoanDTO that = new TaiKhoanDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("id_nhanvien"),
                        rs.getString("id_quyen"),
                        rs.getString("email"),
                        rs.getString("name_quyen"),
                        rs.getBoolean("trangthai"));
                setTK.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTK;
    }

    public TaiKhoanBUS(LinkedHashSet<TaiKhoanDTO> setTK, TaiKhoanDAO daoTK) {
        this.setTK = setTK;
        this.daoTK = daoTK;
    }

    public LinkedHashSet<TaiKhoanDTO> getSetTK() {
        return setTK;
    }

    public void setSetTK(LinkedHashSet<TaiKhoanDTO> setTK) {
        this.setTK = setTK;
    }

    public TaiKhoanDAO getDaoTK() {
        return daoTK;
    }

    public void setDaoTK(TaiKhoanDAO daoTK) {
        this.daoTK = daoTK;
    }

    public int getCountTaiKhoan() {
        ResultSet rs = daoTK.getCountTaiKhoan();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public TaiKhoanDTO getTaiKhoanKByUsername(String username) {
        Optional<TaiKhoanDTO> findTK = setTK.stream().filter(tk -> tk.getUsername().equals(username)).findFirst();
        return findTK.orElse(null);
    }

    public TaiKhoanDTO getTaiKhoanByEmail(String email) {
        Optional<TaiKhoanDTO> findTK = setTK.stream().filter(tk -> tk.getEmail().equals(email)).findFirst();
        return findTK.orElse(null);
    }

    public LinkedHashSet<String> getAllNameQuyen() {
        return setTK.stream()
                .map(TaiKhoanDTO::getNameQuyen)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        boolean updateSuccess = setTK.stream()
                .filter(taiKhoan -> taiKhoan.getUsername().equals(tk.getUsername()))
                .findFirst()
                .map(taiKhoan -> {
                    taiKhoan.setUsername(tk.getUsername());
                    taiKhoan.setPassword(tk.getPassword());
                    taiKhoan.setIdNV(tk.getIdNV());
                    taiKhoan.setIdQuyen(tk.getIdQuyen());
                    taiKhoan.setEmail(tk.getEmail());
                    taiKhoan.setNameQuyen(tk.getNameQuyen());
                    taiKhoan.setDelete(tk.getDelete());
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoTK.updateTaiKhoan(tk);
            daoTK.closeDB();
        }
        return updateSuccess;
    }

    public boolean containsUsername(String username) {
        return setTK.stream()
                .anyMatch(taiKhoanDTO -> taiKhoanDTO.getUsername().equals(username));
    }

    public int addTaiKhoan(TaiKhoanDTO tk) {
        if (containsUsername(tk.getUsername())) {
            return 0;
        }
        setTK.add(tk);
        daoTK.addTKWithData(tk);
        daoTK.closeDB();
        return -1;
    }


    public void removeTaiKhoan(TaiKhoanDTO tk) {
        if (setTK.remove(tk)) {
            daoTK.removeTK(tk.getUsername());
            daoTK.closeDB();
        }
    }

    public LinkedHashSet<TaiKhoanDTO> searchUsername(String content) {
        return setTK.stream()
                .filter(taiKhoanDTO -> taiKhoanDTO.getUsername().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<TaiKhoanDTO> searchEmail(String content) {
        return setTK.stream()
                .filter(taiKhoanDTO -> taiKhoanDTO.getEmail().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<TaiKhoanDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<TaiKhoanDTO> setUserame = searchUsername(searchContent);
            LinkedHashSet<TaiKhoanDTO> setEmail = searchEmail(searchContent);
            LinkedHashSet<TaiKhoanDTO> setAll = Stream.of(setUserame, setEmail)
                    .flatMap(LinkedHashSet::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return setAll;
        }

        if (searchType.equals(typeSearch[1])) {
            return searchUsername(searchContent);
        }
        if (searchType.equals(typeSearch[2])) {
            return searchEmail(searchContent);
        }
        return getSetTK();
    }

    public LinkedHashSet<TaiKhoanDTO> filterTrangThai(String trangThaiString) {
        if (trangThaiString.equals("Đang hoạt động")) {
            return setTK.stream()
                    .filter(taiKhoanDTO -> !taiKhoanDTO.getDelete())
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        if (trangThaiString.equals("Bị khóa")) {
            return setTK.stream()
                    .filter(taiKhoanDTO -> taiKhoanDTO.getDelete())
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return getSetTK();
    }

    public LinkedHashSet<TaiKhoanDTO> filterNameQuyen(String nameQuyen) {
        if (nameQuyen.equals("Tất cả")) {
            return getSetTK();
        }
        return setTK.stream()
                .filter(taiKhoanDTO -> taiKhoanDTO.getNameQuyen().equals(nameQuyen))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<TaiKhoanDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<TaiKhoanDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<TaiKhoanDTO> setTrangThai = filterTrangThai((String) filterContent[0]);
        LinkedHashSet<TaiKhoanDTO> setNameQuyen = filterNameQuyen((String) filterContent[1]);

        LinkedHashSet<TaiKhoanDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setTrangThai);
        result.retainAll(setNameQuyen);

        return result;
    }
}
