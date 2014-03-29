package io.loli.sc.ui.swing;

import io.loli.sc.api.DropboxAPI;
import io.loli.sc.api.GDriveAPI;
import io.loli.sc.api.ImageCloudAPI;
import io.loli.sc.api.ImgurAPI;
import io.loli.sc.config.Config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ConfigFrame extends JFrame {
    private JFrame jframe;
    private JPanel jpanel;
    private static final long serialVersionUID = 1L;
    private Config config;

    private void useSystemUI() {
        try {
            if (System.getProperty("os.name").toLowerCase().indexOf("linux") == -1)
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            else {
                try {
                    UIManager
                            .setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                } catch (ClassNotFoundException e) {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                }
            }
            // System.setProperty("awt.useSystemAAFontSettings", "on");
            // System.setProperty("swing.aatext", "true");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        // 确保内部类可以调用到此jframe
        this.jframe = this;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(390, 310);
        setVisible(true);
        this.setResizable(false);
    }

    private void addChoice(JComboBox<String> choice) {
        if (config.getImgurConfig().getAccessToken() != null)
            choice.addItem("imgur");
        if (config.getDropboxConfig().getAccessToken() != null)
            choice.addItem("dropbox");
        if (config.getGdriveConfig().getAccessToken() != null)
            choice.addItem("gdrive");
        if (config.getImageCloudConfig().getToken() != null)
            choice.addItem("imgCloud");
    }

    private void initComponents() {
        savePathLabel = new JLabel("保存在");
        savePathField = new JTextField(20);
        browsePathButton = new JButton("浏览");
        jpanel = new JPanel();
        jpanel.setLayout(null);

        uploadChoice = new JComboBox<String>();
        addChoice(uploadChoice);
        uploadChoice.setSelectedItem(config.getDefaultUpload());

        okButton = new JButton("确定");
        cancelButton = new JButton("取消");
        chooser = new JFileChooser();
        savePathField.setText(config.getSavePath());

        imgurLabel = new JLabel("imgur");
        imgurAuthLabel = new JLabel();
        imgurAuthButton = new JButton("连接");
        imgurRemoveAuthButton = new JButton("移除");

        dropboxLabel = new JLabel("dropbox");
        dropboxAuthLabel = new JLabel();
        dropboxAuthButton = new JButton("连接");
        dropboxRemoveAuthButton = new JButton("移除");

        gDriveLabel = new JLabel("gdrive");
        gDriveAuthLabel = new JLabel();
        gDriveAuthButton = new JButton("连接");
        gDriveRemoveAuthButton = new JButton("移除");

        imageCloudLabel = new JLabel("imgCloud");
        imageCloudAuthLabel = new JLabel();
        imageCloudAuthButton = new JButton("连接");
        imageCloudRemoveAuthButton = new JButton("移除");

        uploadToLabel = new JLabel("上传到");
    }

    private void initButton() {
        if (config.getImgurConfig().getAccessToken() == null) {
            imgurAuthLabel.setText("未连接");
            imgurRemoveAuthButton.setEnabled(false);
        } else {
            imgurAuthLabel.setText("已连接");
            imgurAuthButton.setEnabled(false);
        }
        if (config.getDropboxConfig().getAccessToken() == null) {
            dropboxAuthLabel.setText("未连接");
            dropboxRemoveAuthButton.setEnabled(false);
        } else {
            dropboxAuthLabel.setText("已连接");
            dropboxAuthButton.setEnabled(false);
        }
        if (config.getGdriveConfig().getAccessToken() == null) {
            gDriveAuthLabel.setText("未连接");
            gDriveRemoveAuthButton.setEnabled(false);
        } else {
            gDriveAuthLabel.setText("已连接");
            gDriveAuthButton.setEnabled(false);
        }

        if (config.getImageCloudConfig().getToken() == null) {
            imageCloudAuthLabel.setText("未连接");
            imageCloudRemoveAuthButton.setEnabled(false);
        } else {
            imageCloudAuthLabel.setText("已连接");
            imageCloudAuthButton.setEnabled(false);
        }

    }

    private void initComposition() {
        savePathLabel.setBounds(30, 40, 70, 30);
        savePathField.setBounds(90, 40, 180, 30);
        browsePathButton.setBounds(280, 40, 60, 30);
        okButton.setBounds(100, 205, 60, 30);
        cancelButton.setBounds(200, 205, 60, 30);
        uploadChoice.setBounds(90, 5, 140, 30);
        uploadToLabel.setBounds(30, 5, 60, 30);

        imgurLabel.setBounds(40, 75, 60, 30);
        imgurAuthLabel.setBounds(85, 75, 60, 30);
        imgurAuthButton.setBounds(185, 75, 60, 30);
        imgurRemoveAuthButton.setBounds(250, 75, 60, 30);

        dropboxLabel.setBounds(40, 110, 60, 30);
        dropboxAuthLabel.setBounds(110, 110, 60, 30);
        dropboxAuthButton.setBounds(185, 110, 60, 30);
        dropboxRemoveAuthButton.setBounds(250, 110, 60, 30);

        gDriveLabel.setBounds(40, 145, 60, 30);
        gDriveAuthLabel.setBounds(110, 145, 60, 30);
        gDriveAuthButton.setBounds(185, 145, 60, 30);
        gDriveRemoveAuthButton.setBounds(250, 145, 60, 30);

        imageCloudLabel.setBounds(40, 175, 60, 30);
        imageCloudAuthLabel.setBounds(110, 175, 60, 30);
        imageCloudAuthButton.setBounds(185, 175, 60, 30);
        imageCloudRemoveAuthButton.setBounds(250, 175, 60, 30);

    }

    private JLabel savePathLabel;
    private JTextField savePathField;
    private JButton browsePathButton;

    private JButton okButton;
    private JButton cancelButton;

    private JFileChooser chooser;
    private JLabel imgurLabel;
    private JLabel imgurAuthLabel;
    private JButton imgurAuthButton;
    private JButton imgurRemoveAuthButton;

    private JLabel dropboxLabel;
    private JLabel dropboxAuthLabel;
    private JButton dropboxAuthButton;
    private JButton dropboxRemoveAuthButton;

    private JLabel gDriveLabel;
    private JLabel gDriveAuthLabel;
    private JButton gDriveAuthButton;
    private JButton gDriveRemoveAuthButton;

    private JLabel imageCloudLabel;
    private JLabel imageCloudAuthLabel;
    private JButton imageCloudAuthButton;
    private JButton imageCloudRemoveAuthButton;

    private JComboBox<String> uploadChoice;

    private JLabel uploadToLabel;

    private void addcomponents() {
        jframe.add(jpanel);
        jframe.setLayout(null);
        jpanel.setBounds(3, 3, jframe.getWidth(), jframe.getHeight());
        jpanel.add(savePathLabel);
        jpanel.add(savePathField);
        jpanel.add(browsePathButton);
        jpanel.add(okButton);
        jpanel.add(cancelButton);

        jpanel.add(imgurLabel);
        jpanel.add(imgurAuthLabel);
        jpanel.add(imgurAuthButton);
        jpanel.add(imgurRemoveAuthButton);

        jpanel.add(dropboxLabel);
        jpanel.add(dropboxAuthLabel);
        jpanel.add(dropboxAuthButton);
        jpanel.add(dropboxRemoveAuthButton);

        jpanel.add(gDriveLabel);
        jpanel.add(gDriveAuthLabel);
        jpanel.add(gDriveAuthButton);
        jpanel.add(gDriveRemoveAuthButton);

        jpanel.add(imageCloudLabel);
        jpanel.add(imageCloudAuthLabel);
        jpanel.add(imageCloudAuthButton);
        jpanel.add(imageCloudRemoveAuthButton);

        jpanel.add(uploadChoice);
        jpanel.add(uploadToLabel);
        // add(chooser);
    }

    public void addListeners() {
        browsePathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result;
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file;
                result = chooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    if (file.exists()) {
                        savePathField.setText(file.getAbsolutePath());
                    } else {
                        JOptionPane.showMessageDialog(null, "文件不存在");
                    }
                } else if (result == JFileChooser.CANCEL_OPTION) {
                } else if (result == JFileChooser.ERROR_OPTION) {
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.out.println("Unable to set native look and feel: " + e);
        }
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                config.setSavePath(savePathField.getText());
                Object obj = uploadChoice.getSelectedItem();
                if (obj != null)
                    config.setDefaultUpload((String) obj);
                config.save();
                jframe.dispose();
            }

        });

        imgurAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ImgurAPI api = new ImgurAPI();
                api.auth();
                String pin = JOptionPane.showInputDialog("请输入认证后的PIN码");
                ImgurAPI.AccessToken token = api.pinToToken(pin);
                config.getImgurConfig().setAccessToken(token.getAccess_token());
                config.getImgurConfig().setRefreshToken(
                        token.getRefresh_token());
                config.getImgurConfig().setDate(new Date());
                config.getImgurConfig()
                        .updateProperties(config.getProperties());
                config.save();
                imgurAuthButton.setEnabled(false);
                imgurRemoveAuthButton.setEnabled(true);
                imgurAuthLabel.setText("已连接");
                uploadChoice.addItem("imgur");
            }

        });

        imgurRemoveAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                config.getImgurConfig().removeFromProperties(
                        config.getProperties());
                config.save();
                imgurAuthButton.setEnabled(true);
                imgurRemoveAuthButton.setEnabled(false);
                imgurAuthLabel.setText("未连接");
                uploadChoice.removeItem("imgur");
            }

        });

        dropboxAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DropboxAPI api = new DropboxAPI();
                api.auth();
                String pin = JOptionPane.showInputDialog("请输入认证码");
                DropboxAPI.AccessToken token = api.pinToToken(pin);
                config.getDropboxConfig().setAccessToken(
                        token.getAccess_token());

                config.getDropboxConfig().setUid(token.getUid());
                config.getDropboxConfig().updateProperties(
                        config.getProperties());
                config.save();
                dropboxAuthButton.setEnabled(false);
                dropboxRemoveAuthButton.setEnabled(true);
                dropboxAuthLabel.setText("已连接");
                uploadChoice.addItem("dropbox");
            }

        });

        dropboxRemoveAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                config.getDropboxConfig().removeFromProperties(
                        config.getProperties());
                config.save();
                dropboxAuthButton.setEnabled(true);
                dropboxRemoveAuthButton.setEnabled(false);
                dropboxAuthLabel.setText("未连接");
                uploadChoice.removeItem("dropbox");
            }

        });
        gDriveAuthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GDriveAPI api = new GDriveAPI();
                api.auth();
                String pin = JOptionPane.showInputDialog("请输入code");
                GDriveAPI.AccessToken token = api.pinToToken(pin);
                config.getGdriveConfig()
                        .setAccessToken(token.getAccess_token());
                config.getGdriveConfig().setRefreshToken(
                        token.getRefresh_token());
                config.getGdriveConfig().updateProperties(
                        config.getProperties());
                config.save();
                gDriveAuthLabel.setText("已连接");
                uploadChoice.addItem("gdrive");
                gDriveAuthButton.setEnabled(false);
                gDriveRemoveAuthButton.setEnabled(true);
            }
        });
        gDriveRemoveAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                config.getGdriveConfig().removeFromProperties(
                        config.getProperties());
                config.save();
                gDriveAuthButton.setEnabled(true);
                gDriveRemoveAuthButton.setEnabled(false);
                gDriveAuthLabel.setText("未连接");
                uploadChoice.removeItem("gdrive");
            }
        });

        imageCloudAuthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageCloudAPI api = new ImageCloudAPI();
                api.auth();
                ImageCloudAPI.ClientToken token = api.getToken();
                if (token.getId() != 0) {
                    config.getImageCloudConfig().setToken(token.getToken());
                    config.getImageCloudConfig().setEmail(
                            token.getUser().getEmail());
                    config.getImageCloudConfig().updateProperties(
                            config.getProperties());
                    config.save();
                    imageCloudAuthLabel.setText("已连接");
                    uploadChoice.addItem("imgCloud");
                    imageCloudAuthButton.setEnabled(false);
                    imageCloudRemoveAuthButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "您输入的邮箱或密码错误");
                }
            }
        });
        imageCloudRemoveAuthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                config.getImageCloudConfig().removeFromProperties(
                        config.getProperties());
                config.save();
                imageCloudAuthButton.setEnabled(true);
                imageCloudRemoveAuthButton.setEnabled(false);
                imageCloudAuthLabel.setText("未连接");
                uploadChoice.removeItem("imgCloud");
            }
        });
    }

    public ConfigFrame(Config config) {
        super("设置");
        setConfig(config);
        this.useSystemUI();
        this.init();
        this.initComponents();
        this.initComposition();
        this.initButton();
        this.addcomponents();
        this.addListeners();
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConfigFrame(new Config());
            }
        });
    }
}