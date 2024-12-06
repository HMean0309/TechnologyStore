package GUI.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductList extends JPanel {
    ButtonGroup btnGroup;
    ArrayList<ProductItem> listItem;
    ArrayList<String> listData;

    public ProductList(ArrayList<String> listData) {
        GridLayout layout = new GridLayout(listData.size(), 1, 0, 10);
        this.listData = listData;
        this.listItem = new ArrayList<>(listData.size());
        btnGroup = new ButtonGroup();

        this.setLayout(layout);
        for (int i = 0; i < listData.size(); i++) {
//            //ProductItem item = new initComponents("oppo_f9.png", this.listData.get(i), "", new String[]{}, new String[]{}, ProductItem.ProductType.CHECKBOX);
//            listItem.add(item);
//            btnGroup.add(item.getRadio());
//            this.add(item);
        }

        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test sp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 600);

        String[] data = new String[]{ "Ip1", "Samsung Galaxy Note 10", "Oppo F9" };
        ArrayList<String> listData = new ArrayList<>(Arrays.asList(data));
        ProductList productList = new ProductList(listData);

        frame.add(productList);
        frame.pack();
        frame.setVisible(true);
    }
}
