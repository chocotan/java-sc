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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ConfigFrame extends JFrame {
	private JPanel jpanel1;
	private JPanel jpanel2;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(392, 487);
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
		savePathLabel = new JLabel("地址：");
		savePathField = new JTextField(20);
		browsePathButton = new JButton("浏览");
		jpanel1 = new JPanel();
		jpanel1.setLayout(null);

		jpanel2 = new JPanel();
		jpanel2.setLayout(null);
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
		tab = new JTabbedPane();
		uploadToLabel = new JLabel("上传到");

		startWithSystemCheck = new JCheckBox("是否开机自启动");
		saveAsTitleLabel = new JLabel("<html><strong>截图保存在</strong></html>");

		fileNameFormatLabel = new JLabel("截图文件名格式");

		fileNameFormatExampleLabel = new JLabel("实例:yyyy-MM-dd mm:ss的截图");

		
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
		okButton.setBounds(200, 415, 70, 30);
		cancelButton.setBounds(290, 415, 70, 30);
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

		startWithSystemCheck.setBounds(40, 190, 200, 30);

		saveAsTitleLabel.setBounds(10, 10, 190, 30);

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

	private JCheckBox startWithSystemCheck;

	private JTabbedPane tab;

	private JLabel saveAsTitleLabel;

	private JLabel fileNameFormatLabel;

	private JLabel fileNameFormatExampleLabel;

	private JTextField fileNameFormatField;

	private void addcomponents() {
		setLayout(null);
		jpanel1.setBounds(3, 3, getWidth(), getHeight());
		jpanel2.setBounds(3, 3, getWidth(), getHeight());
		jpanel1.add(savePathLabel);
		jpanel1.add(savePathField);
		jpanel1.add(browsePathButton);

		jpanel2.add(imgurLabel);
		jpanel2.add(imgurAuthLabel);
		jpanel2.add(imgurAuthButton);
		jpanel2.add(imgurRemoveAuthButton);

		jpanel2.add(dropboxLabel);
		jpanel2.add(dropboxAuthLabel);
		jpanel2.add(dropboxAuthButton);
		jpanel2.add(dropboxRemoveAuthButton);

		jpanel2.add(gDriveLabel);
		jpanel2.add(gDriveAuthLabel);
		jpanel2.add(gDriveAuthButton);
		jpanel2.add(gDriveRemoveAuthButton);

		jpanel2.add(imageCloudLabel);
		jpanel2.add(imageCloudAuthLabel);
		jpanel2.add(imageCloudAuthButton);
		jpanel2.add(imageCloudRemoveAuthButton);

		jpanel2.add(uploadChoice);
		jpanel2.add(uploadToLabel);
		jpanel1.add(startWithSystemCheck);
		jpanel1.add(saveAsTitleLabel);

//		jpanel1.add(fileNameFormatLabel);
//
//		jpanel1.add(fileNameFormatExampleLabel);
//
//		jpanel1.add(fileNameFormatField);
		
		add(tab);
		tab.setTabPlacement(JTabbedPane.TOP);
		tab.add("通常设置", jpanel1);
		tab.add("连接网站", jpanel2);
		tab.setBounds(5, 5, 382, 400);

		add(okButton);
		add(cancelButton);

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
				dispose();
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
				dispose();
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
