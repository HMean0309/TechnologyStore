/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InputDate extends JPanel {

    JLabel lbltitle;
    public JDateChooser date;
    private final SimpleDateFormat dateFormat;

    public InputDate(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        lbltitle = new JLabel(title);
        date = new JDateChooser();
        date.setDateFormatString("dd/MM/yyyy");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.add(lbltitle);
        this.add(date);
    }

    public InputDate(String title, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(w, h));
        lbltitle = new JLabel(title);
        date = new JDateChooser();
        date.setDateFormatString("dd/MM/yyyy");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.add(lbltitle);
        this.add(date);
    }

    public JDateChooser getDateChooser() {
        return this.date;
    }

    public Date getDate() throws ParseException {
        Date dt = date.getDate();
        if (dt != null) {
            String txt_date = dateFormat.format(dt);
            return dateFormat.parse(txt_date);
        } else {
            return null;
        }
    }

    public LocalDate getLocalDate() {
        if (date.getDate() == null) {
            return null;
        }
        LocalDate localDate = date.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public void setDate(Date date) {
        this.date.setDate(date);
    }

    public void setDate(LocalDate localDate) {
        this.date.setDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public void setDisable() {
        date.setEnabled(false);
    }
}
